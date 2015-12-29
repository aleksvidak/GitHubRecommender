package fon.tags.github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class ProjectRecommender {
		
	public static void main(String[] args) throws IOException {
		
		Matrix matrix = new Matrix();
		List<String> dictionary = new ArrayList<String>();
		
		//create connection to mongodb
		MongoDb mdb = new MongoDb();
		mdb.add();
		//fetch projects from db
		System.out.println("Fetching documents...");
		List<Project> projects = mdb.getDocuments();
		// create dictionary of unique words in all projects
		dictionary = matrix.createDictionary(projects);
		System.out.println("Dictionary created. Size: "+dictionary.size()+".");
		RealMatrix rm;
		//tfidf
		System.out.println("Calculating tf-idf...");
		System.out.println("DONE!");
		rm = Matrix.calculateTfIdf(matrix.createVectorModel(projects, dictionary));
		// SVD
		System.out.println("Calculating svd...");
		SingularValueDecomposition svd = new SingularValueDecomposition(rm);
        RealMatrix V = svd.getV();
        System.out.println("DONE!");
        // scale down the given V matrix
        System.out.println("Scaling down matrix V to 100");
        RealMatrix Vk = V.getSubMatrix(0, V.getRowDimension() - 1, 0, 99); 
        System.out.println("DONE!");
        // delete existing projectSimilarity collection
    	MongoCollection<Document> dbcoll = mdb.db.getCollection("projectSimilarity");
    	dbcoll.drop();
    	mdb.db.createCollection("projectSimilarity");

    	// calculate similarity among projects
    	System.out.println("Calculating cosine similarity...");
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            List<Project> projectListTemp = new ArrayList<>();
            projectListTemp.add(projects.get(i));
            double sim = 0.0;
            for (int j = 0; j < projects.size(); j++) {                
                sim = Matrix.cosinSim(i, j, Vk.transpose());
                projects.get(j).setSimilarity(sim);
                projectListTemp.add(projects.get(j));
            }

            Collections.sort(projectListTemp);

            for (int k = 2; k < 12; k++) {
            	mdb.db.getCollection("projectSimilarity").insertOne(new Document("projectName", project.getProjectName())
            	.append("simProjectName", projectListTemp.get(k).getProjectName())
            	.append("similarity", projectListTemp.get(k).getSimilarity()));
            }            
        }
        
        System.out.println("END");
	}
	
	

	
	
	
}

