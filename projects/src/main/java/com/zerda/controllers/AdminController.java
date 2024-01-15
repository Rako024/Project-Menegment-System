package com.zerda.controllers;

import com.zerda.entities.Project;
import com.zerda.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/createProject")
    public String showCreateProjectForm(Model model) {
        return "createProject";
    }

    @PostMapping("/saveProject")
    public String saveProject(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam int price,
                              @RequestParam String projectLink) {
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setPrice(price);
        project.setProjectLink(projectLink);
        projectService.saveProject(project);
        return "redirect:/admin/";
    }
    @GetMapping("/")
    public String showAdminMain(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "admin-main";
    }

    @GetMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/admin/";
    }

    @GetMapping("/editProject/{id}")
    public String showEditProjectForm(@PathVariable Long id,Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "editProject";
    }

    @PostMapping("/updateProject")
    public String updateProject(Project project) {
        projectService.saveProject(project);
        return "redirect:/admin/";
    }

}
