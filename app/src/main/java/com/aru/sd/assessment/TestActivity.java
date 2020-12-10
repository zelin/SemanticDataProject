package com.aru.sd.assessment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class TestActivity extends BaseActivity
{
    public static final String Movie_NS = "http://www.movies.com/ontology#";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /*

        String prefix = "prefix mov: <" + Movie_NS + ">\n"+
                "prefix rdfs: <" + RDFS.getURI() + ">\n" ;

        String genre = "Action";
        String query_text=  prefix +
                "SELECT ?name ?thumb \r\n" +
                "WHERE { "
                + "      ?x a mov:Movie. \r\n"
                + "      ?x mov:title ?name. \r\n"
                + "      ?x mov:thumbnail ?thumb. \r\n"
                + "      ?x mov:has_genre ?genre. \r\n"
                + "      ?genre mov:name '"+ genre +"' \r\n" +
                "  } \r\n"
                + " ORDER BY ASC(?name)" ;

        System.out.println(query_text);
    */

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    /*
                    app.mainModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM ); // defined inJena
                    FileManager.get().readModel(app.mainModel, "https://www.dropbox.com/s/qjx7cl114vtix17/assessmentontology.owl?dl=0");

                    Query query = QueryFactory.create( query_text );
                    //QueryExecution qexec = QueryExecutionFactory.sparqlService("http://localhost:3030", query);
                    QueryExecution qexec = QueryExecutionFactory.create(query, app.mainModel);

                    List<String[]> genres = new ArrayList<>();

                    ResultSet results = qexec.execSelect();
                    while ( results.hasNext() ) {
                        QuerySolution qs = results.next();
                        genres.add(new String[] {qs.get("name").toString(), qs.get("thumb").toString()});
                        System.out.println(qs.get("thumb").toString());
                    }
                    qexec.close();*/
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}