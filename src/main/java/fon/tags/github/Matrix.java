package fon.tags.github;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.json.JSONObject;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Matrix {
	
	
	public List<String> createDictionary(List<Project> projectList) {
		List<String> dictionary = new ArrayList<String>();

		for (Project project : projectList) {
			Iterator<?> keys = project.keywords.keys();
			
			while(keys.hasNext()) {
			    String key = (String)keys.next();
			    if (!dictionary.contains(key)) {
			    	dictionary.add(key);
			    }

			}
		}
		
		return dictionary;
	
	}
	
	public RealMatrix createVectorModel(List<Project> projectList, List<String> dictionary) {
		RealMatrix matrix;
		matrix = MatrixUtils.createRealMatrix(dictionary.size(), projectList.size());
		
		//String[] dic = null;
		String[] dic = dictionary.toArray(new String[dictionary.size()]);
		//json.getInt(key)
		for (int i = 0; i<dic.length; i++) {
			for (Project project : projectList) {
				JSONObject json = project.getKeywords();
				Iterator<?> keys = json.keys();
				while(keys.hasNext()) {
				    String key = (String)keys.next();
				    if (dic[i].equalsIgnoreCase(key)) {
				    	matrix.setEntry(i, project.getProjectId(), json.getInt(key));
				    }
				}
			}
		}
		System.out.println("Vector model created.");
		return matrix;
	}
	
	// calculate term frequency in all documents
	public static int calculateTermFrequency(RealMatrix matrix, int row) {
		int tf = 0;
        for (int i = 0; i < matrix.getColumnDimension(); i++) {
            if (matrix.getEntry(row, i) != 0) {
                tf++;
            }
        }
        return tf;
	}
	
	//calculate tf-idf
	public static RealMatrix calculateTfIdf(RealMatrix matrix) {
        double tf;
        double idf;
        int termfrq;
        double ndf;
        for (int i = 0; i < matrix.getRowDimension(); i++) {
        	
            termfrq = calculateTermFrequency(matrix, i);


            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                tf = Math.sqrt(matrix.getEntry(i, j));
                ndf = matrix.getColumnDimension()/termfrq+1;
                idf = Math.log(ndf+1);
                matrix.setEntry(i, j, idf * tf);
            }
        }

        matrix = normalizeMatrix(matrix);
        System.out.println("DONE!");
        return matrix;
    }
	
	// normalization of the matrix
	private static RealMatrix normalizeMatrix(RealMatrix matrix) {
        double sumColumn = 0;

        RealMatrix row = MatrixUtils.createRealMatrix(1, matrix.getColumnDimension());
        for (int i = 0; i < matrix.getColumnDimension(); i++) {
            sumColumn = 0;
            for (int j = 0; j < matrix.getRowDimension(); j++) {
                sumColumn += Math.pow(matrix.getEntry(j, i), 2);
            }
            sumColumn = Math.sqrt(sumColumn);
            row.setEntry(0, i, sumColumn);
        }
        for (int i = 0; i < matrix.getColumnDimension(); i++) {
            for (int j = 0; j < matrix.getRowDimension(); j++) {
            	matrix.setEntry(j, i, matrix.getEntry(j, i) / row.getEntry(0, i));
            }
        }
        return matrix;
    }
	
	public static double cosinSim(int v1, int v2, RealMatrix Vt) {
        double sim = 0.0;
        double sumNum = 0.0;
        double fdenom = 0.0;
        double sdenom = 0.0;

        for (int j = 0; j < Vt.getRowDimension(); j++) {
            sumNum += Vt.getEntry(j, v1) * Vt.getEntry(j, v2);
            fdenom += Math.pow(Vt.getEntry(j, v1), 2);
            sdenom += Math.pow(Vt.getEntry(j, v2), 2);
        }
        sim = sumNum / (Math.sqrt(fdenom) * Math.sqrt(sdenom));
        return sim;
    }
	
	public boolean collectionExists(final String collectionName, MongoDatabase db) {
	    MongoIterable<String> collectionNames = db.listCollectionNames();
	    for (final String name : collectionNames) {
	        if (name.equalsIgnoreCase(collectionName)) {
	            return true;
	        }
	    }
	    return false;
	}
}
