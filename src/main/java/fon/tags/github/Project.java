package fon.tags.github;

import org.json.JSONObject;

public class Project implements Comparable<Project> {
	int projectId;
	String projectName;
	String projectDescription;
	String projectReadme;
	JSONObject keywords;
	double similarity;
	
	public JSONObject getKeywords() {
		return keywords;
	}

	public void setKeywords(JSONObject keywords) {
		this.keywords = keywords;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public Project(int projectId, String projectName, String projectDescription, String projectReadme, JSONObject keywords) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectReadme = projectReadme;
        this.keywords = keywords;
    }



	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectReadme() {
		return projectReadme;
	}

	public void setProjectReadme(String projectReadme) {
		this.projectReadme = projectReadme;
	}

	public int compareTo(Project p) {
		if (this.similarity > p.similarity) {
            return -1;
        } else if (this.similarity == p.similarity) {
            return 0;
        } else {
            return 1;
        }
	}
	
}
