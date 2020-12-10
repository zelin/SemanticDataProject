package com.aru.sd.assessment;

import android.content.Intent;
import android.os.Bundle;

import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class SplashActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    app.mainModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM ); // defined inJena
                    FileManager.get().readModel(app.mainModel, "https://www.dropbox.com/s/qjx7cl114vtix17/assessmentontology.owl?dl=1");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }
                    });
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}