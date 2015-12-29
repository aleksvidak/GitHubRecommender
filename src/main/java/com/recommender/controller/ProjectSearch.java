package com.recommender.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fon.tags.github.MongoDb;
import fon.tags.github.Project;
import fon.tags.github.ProjectSimilarity;

@Controller
public class ProjectSearch {

	@RequestMapping("/GetProjects")
	public @ResponseBody
	List<Project> getProjects(@RequestParam("projectName") String projectName) throws IOException {

		return simulateSearchResult(projectName);

	}

	private List<Project> simulateSearchResult(String projectName) throws IOException {

		List<Project> result = new ArrayList<Project>();

		MongoDb mdb = new MongoDb();
		List<Project> projects = mdb.getDocumentsFromDb();
		// iterate a list and filter by projectName
		for (Project project : projects) {
			if (project.getProjectName().contains(projectName)) {
				result.add(project);
			}
		}

		return result;
	}
	
	@RequestMapping("/ProjectDetails")
	public @ResponseBody
	ModelAndView getProjectDetails(@RequestParam("id") String id) throws IOException {
		MongoDb mdb = new MongoDb();
		Map<String, Object> map = new HashMap<>();
		Project pro = mdb.getDocumentFromDb(Integer.parseInt(id));
		List<ProjectSimilarity> simProjects = mdb.getSimilarity(pro);
		
		map.put("projectName", pro.getProjectName());
		map.put("projectReadme", pro.getProjectReadme());
		map.put("simProjects", simProjects);
		return new ModelAndView("projectDetails", map);

	}
}
