/*********** Get all Customers ***********/

PREFIX  rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX  owl:  <http://www.w3.org/2002/07/owl#>
PREFIX  xsd:  <http://www.w3.org/2001/XMLSchema#>
PREFIX  rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX  asm:  <http://www.semanticweb.org/assessment#>
PREFIX  foaf: <http://xmlns.com/foaf/0.1/>

SELECT  ?a ?firstName ?lastName ?email ?phone ?location ?order
WHERE
  { ?a  rdf:type         asm:Customer ;
        foaf:firstName   ?firstName ;
        foaf:lastName    ?lastName ;
        asm:email        ?email ;
        asm:phone        ?phone ;
        asm:hasLocation  ?location .
    OPTIONAL
      { ?a  asm:hasOrder  ?order . }
  }
ORDER BY ASC(?a)

/*********** Get all Customers with their orders count ***********/

PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX asm: <http://www.semanticweb.org/assessment#>
PREFIX foaf:  <http://xmlns.com/foaf/0.1/>
 
SELECT (count(distinct ?ordersList) as ?ordersCount) ?a ?firstName ?lastName ?email ?phone ?location
WHERE {
    # Gett
    ?a a asm:Customer ; 
	foaf:firstName ?firstName ;
	foaf:lastName ?lastName;
	asm:email ?email;
	asm:phone ?phone;
	asm:hasLocation ?location .
	OPTIONAL { ?a asm:hasOrder ?ordersList }
}
GROUP BY ?a ?firstName ?lastName ?email ?phone ?location
ORDER BY DESC(?ordersCount)

/*********** Get all Customers who bought products with price more than 20 and less than 30 ***********/

PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl:  <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>
PREFIX asm:  <http://www.semanticweb.org/assessment#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?c ?firstName ?lastName ?email ?phone ?location ?order ?px ?price
WHERE {
    # Getting order bought by customer
    ?c a asm:Customer . 
  	?c foaf:firstName   ?firstName .
    ?c foaf:lastName    ?lastName .
    ?c asm:email        ?email .
    ?c asm:phone        ?phone .
    ?c asm:hasLocation  ?location .
  	?c asm:hasOrder ?order .
    ?order asm:hasProduct ?p .
    ?p asm:hasPrice ?px .
    ?px asm:price ?price .   
  		
	FILTER (?price > 20)
    FILTER (?price < 30 )
}
ORDER BY ASC(?a)

/*********** Get all Organizations ***********/

PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl:  <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>
PREFIX asm:  <http://www.semanticweb.org/assessment#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

SELECT ?stores ?name
WHERE {
  ?subclass rdfs:subClassOf foaf:Organization .
  ?stores rdf:type ?subclass .
  ?stores foaf:name ?name. 
}

/*********** Get all Organizations with some details ***********/

PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl:  <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>
PREFIX asm:  <http://www.semanticweb.org/assessment#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX as: <https://www.w3.org/ns/activitystreams#>

SELECT ?entity ?name ?type ?location ?city ?country
WHERE {
  { ?entity a foaf:Organization }
  UNION { 
    ?entity a asm:Store .  	
    ?entity foaf:name ?name .
    ?entity asm:hasLocation ?location .
    ?location asm:city ?city . 
    ?location asm:country ?country

    BIND ('Store' AS ?type)
 }
  UNION { 
    ?entity a asm:Shop .
    ?entity foaf:name ?name .
    ?entity asm:hasLocation ?location .
    ?location asm:city ?city . 
    ?location asm:country ?country
    BIND ("Shop" AS ?type)
  }
}


/*********** Get all Products with prices in a specific city ***********/

PREFIX rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl:  <http://www.w3.org/2002/07/owl#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#>
PREFIX asm:  <http://www.semanticweb.org/assessment#>
PREFIX foaf: <http://xmlns.com/foaf/0.1/>

# Most bought items from dubai stores

SELECT ?productName ?price ?product
WHERE {
  ?order a asm:Order .
  ?order asm:hasProduct ?product .
  ?product foaf:name ?productName .
  ?product asm:hasPrice ?p .
  ?p asm:price ?price .
  ?order asm:hasOrganization ?store . 
  ?store asm:hasLocation ?location .
  ?location asm:city ?city.
  FILTER (?city = "Dubai")
}

GROUP BY ?productName ?price ?product