package global

import java.lang.StringBuilder

class QueryManager {

    companion object {

        // a is sort keyword for rdf:type


        const val BASE_PREFIX =
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX foaf:  <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX asm: <http://www.semanticweb.org/assessment#>\n"

        fun getAllCustomers(): String
        {
            val builder = StringBuilder(BASE_PREFIX)
            builder.append("SELECT ?a ?firstName ?lastName ?email ?phone ?location ?order ")
            builder.append("WHERE { ")
            builder.append("?a a asm:Customer ; ")
            builder.append("foaf:firstName ?firstName ; ")
            builder.append("foaf:lastName ?lastName ; ")
            builder.append("asm:email ?email ; ")
            builder.append("asm:phone ?phone ; ")
            builder.append("asm:hasLocation ?location ; ")
            builder.append("OPTIONAL { ?a asm:hasOrder ?order } ")
            builder.append("} ")
            builder.append("ORDER BY ASC(?a)")

            return builder.toString()
        }

        fun getCustomersWithPriceRange(): String
        {
            val builder = StringBuilder(BASE_PREFIX)
            builder.append("SELECT ?c ?firstName ?lastName ?email ?phone ?location ?order ?px ?price ")
            builder.append("WHERE { ")
            builder.append("?c a asm:Customer . ")
            builder.append("?c foaf:firstName   ?firstName . ")
            builder.append("?c foaf:lastName    ?lastName . ")
            builder.append("?c asm:email        ?email . ")
            builder.append("?c asm:phone        ?phone . ")
            builder.append("?c asm:hasLocation  ?location . ")
            builder.append("?c asm:hasOrder ?order . ")
            builder.append("?order asm:hasProduct ?p . ")
            builder.append("?order asm:hasProduct ?p . ")
            builder.append("?c asm:hasOrder ?order . ")
            builder.append("?p asm:hasPrice ?px . ")
            builder.append("?px asm:price ?price . ")
            builder.append("?px asm:price ?price . ")
            builder.append("FILTER (?price > 20) ")
            builder.append("FILTER (?price < 30) ")
            builder.append("} ")
            builder.append("ORDER BY ASC(?a)")

            return builder.toString()
        }

        fun getAllStores() : String
        {
            val builder = StringBuilder(BASE_PREFIX)
            builder.append("SELECT ?entity ?name ?type ?location ?city ?country ")
            builder.append("WHERE { ")
            builder.append("{ ?entity a foaf:Organization } ")
            builder.append("UNION { ")
            builder.append("?entity a asm:Store . ")
            builder.append("?entity foaf:name ?name . ")
            builder.append("?entity asm:hasLocation ?location . ")
            builder.append("?location asm:city ?city . ")
            builder.append("?location asm:country ?country ")
            //builder.append("BIND ('Store' AS ?type) ")
            builder.append("} ")
            builder.append("UNION { ")
            builder.append("?entity a asm:Shop . ")
            builder.append("?entity foaf:name ?name . ")
            builder.append("?entity asm:hasLocation ?location . ")
            builder.append("?location asm:city ?city . ")
            builder.append("?location asm:country ?country ")
            //builder.append("BIND ('Shop' AS ?type) ")
            builder.append("} ")
            builder.append("} ")
            return builder.toString()
        }

        fun productsSoldForCity(): String
        {
            val builder = StringBuilder(BASE_PREFIX)
            builder.append("SELECT ?productName ?price ?product ?currency ")
            builder.append("WHERE { ")
            builder.append("?order a asm:Order . ")
            builder.append("?order asm:hasProduct ?product . ")
            builder.append("?product asm:hasPrice ?p . ")
            builder.append("?product foaf:name ?productName . ")
            builder.append("?p asm:price ?price . ")
            builder.append("?order asm:hasOrganization ?store . ")
            builder.append("?store asm:hasLocation ?location . ")
            builder.append("?store asm:currency ?currency . ")
            builder.append("?location asm:city ?city. ")
            builder.append("FILTER (?city = \"Dubai\")")
            builder.append("} ")
            //builder.append("GROUP BY ?productName ?price ?product ")
            return builder.toString()
        }
    }
}