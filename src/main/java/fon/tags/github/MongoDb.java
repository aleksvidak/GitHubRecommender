package fon.tags.github;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

public class MongoDb {
	
    MongoClient mongoClient;    
    MongoDatabase db; 
    
    public MongoDb() {
    	mongoClient = new MongoClient();
    	db = mongoClient.getDatabase("gitdb");
    }
	
	public List<Project> getDocuments() throws IOException {
		FindIterable<Document> cursor = null;
		cursor = db.getCollection("projects").find();
		String charset = "UTF-8";
		int number = 10;
		String method = "keywords";
		String filter = "stopwords";
		String url = "http://localhost:8080/TaggingProject/api/v1/tag";
		int id = 0;
		List<Project> projectList = new ArrayList<>();
		
		for (Document document : cursor) {						
			
				String query = String.format("text=%s&method=%s&filter=%s&number="+number+"", 
					     URLEncoder.encode(document.getString("projectReadme"), charset), 
					     URLEncoder.encode(method, charset),
					     URLEncoder.encode(filter, charset));
				
	/*			URLConnection connection = new URL(url + "?" + query).openConnection();
				connection.setRequestProperty("Accept-Charset", charset);
				connection.setRequestProperty("maxHttpHeaderSize", "65536");*/
	
				String pageContent = "";
				pageContent = UrlReader.readUrl(url + "?" + query);
				JSONObject jsonPage = new JSONObject(pageContent);

				//System.out.println(document.getString("projectName"));
				//System.out.println(jsonPage);				
				if (jsonPage.toString().equals("{}"))
					db.getCollection("projects").deleteOne(new Document("projectName", document.getString("projectName")));
				else {
					if (jsonPage.length()>=number) {
					db.getCollection("projects").updateOne(new Document("projectName", document.getString("projectName")),new Document("$set", new Document("keywords", jsonPage.toString())));
					Project p = null;
					p = new Project(id, document.getString("projectName"), document.getString("projectDescription"), document.getString("projectReadme"), jsonPage);
					projectList.add(p);
					id++;
					}
					else {
						// do nothing
					}
				}
							
		}
		System.out.println("Documents fetched. Number of documents: "+projectList.size()+".");
		return projectList;
	}
	
	public List<Project> getDocumentsFromDb() throws IOException {
		FindIterable<Document> cursor = null;
		cursor = db.getCollection("projects").find();
		int id = 0;
		List<Project> projectList = new ArrayList<>();
		
		for (Document document : cursor) {						
					Project p = null;
					p = new Project(id, document.getString("projectName"), document.getString("projectDescription"), document.getString("projectReadme"), new JSONObject(document.getString("keywords")));
					projectList.add(p);
					id++;
				}
							
		
		return projectList;
	}
	
	public Project getDocumentFromDb(int id) throws IOException {
		FindIterable<Document> cursor = null;
		cursor = db.getCollection("projects").find();
		int idd = 0;
		Project result = null;
		for (Document document : cursor) {						
					Project p = null;
					p = new Project(idd, document.getString("projectName"), document.getString("projectDescription"), document.getString("projectReadme"), new JSONObject(document.getString("keywords")));

					if (idd==id) {
						result=p;
						break;
					}	
					else {
						idd++;
					}
				}
		return result;
	}
	public List<ProjectSimilarity> getSimilarity(Project p) throws IOException {
		FindIterable<Document> cursor = null;
		cursor = db.getCollection("projectSimilarity").find(new Document("projectName", p.getProjectName()));
		int id = 0;
		List<ProjectSimilarity> result = new ArrayList<>();
				
		for (Document document : cursor) {						
				ProjectSimilarity pros = null;
				pros = new ProjectSimilarity(id, document.getString("projectName"), document.getString("simProjectName"), document.getDouble("similarity"), document.getString("simProjectDescription"));
				result.add(pros);
				id++;		
		}
		return result;
	}
	
	public void add() {
		
		FindIterable<Document> cursor = db.getCollection("projects").find();
		for (Document document : cursor) {						
		System.out.println(document.getString("projectName"));
			db.getCollection("projectSimilarity").updateMany(new Document("simProjectName", document.getString("projectName")),new Document("$set", new Document("simProjectDescription", document.getString("projectDescription"))));
			

		}

}
		
		
	
	
}
