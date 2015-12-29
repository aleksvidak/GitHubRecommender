package fon.tags.github;


public class ProjectSimilarity {
	int projectId;
	String projectName;
	String simProjectName;
	String simProjectDescription;
	
	public String getSimProjectDescription() {
		return simProjectDescription;
	}

	public void setSimProjectDescription(String simProjectDescription) {
		this.simProjectDescription = simProjectDescription;
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

	public String getSimProjectName() {
		return simProjectName;
	}

	public void setSimProjectName(String simProjectName) {
		this.simProjectName = simProjectName;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	double similarity;
	
	public ProjectSimilarity(int projectId, String projectName, String simProjectName, double similarity, String simProjectDescription) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.simProjectName = simProjectName;
        this.similarity = similarity;
        this.simProjectDescription = simProjectDescription;
    }
}
