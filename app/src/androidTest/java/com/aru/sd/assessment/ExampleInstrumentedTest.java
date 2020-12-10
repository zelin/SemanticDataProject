packagecom.aru.sd.assessment;

importandroid.content.Context;

importandroidx.test.platform.app.InstrumentationRegistry;
importandroidx.test.ext.junit.runners.AndroidJUnit4;

importorg.apache.jena.ontology.OntModel;
importorg.apache.jena.ontology.OntModelSpec;
importorg.apache.jena.query.Query;
importorg.apache.jena.query.QueryExecution;
importorg.apache.jena.query.QueryExecutionFactory;
importorg.apache.jena.query.QueryFactory;
importorg.apache.jena.query.QuerySolution;
importorg.apache.jena.query.ResultSet;
importorg.apache.jena.rdf.model.ModelFactory;
importorg.apache.jena.vocabulary.RDFS;
importorg.junit.Test;
importorg.junit.runner.RunWith;

importjava.util.ArrayList;
importjava.util.List;

importstaticorg.junit.Assert.*;

/**
*Instrumentedtest,whichwillexecuteonanAndroiddevice.
*
*@see<ahref="http://d.android.com/tools/testing">Testingdocumentation</a>
*/
@RunWith(AndroidJUnit4.class)
publicclassExampleInstrumentedTest{

publicstaticfinalStringMovie_NS="http://www.movies.com/ontology#";

@Test
publicvoiduseAppContext(){
//Contextoftheappundertest.
ContextappContext=InstrumentationRegistry.getInstrumentation().getTargetContext();
assertEquals("com.aru.sd.assessment",appContext.getPackageName());
}
}