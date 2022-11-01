package com.ideas2it.employee.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * It presents project details.
 *
 * @version 2.0 27-10-2022
 * @author Naveenkumar R
 */
public class ProjectDTO {
    private int projectId;
    private String projectName;
    private String technology;
    private String clientName;
    private String clientEmail;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate endDate;
    private List<EmployeeDTO> employees;

    public ProjectDTO() {
    }

    public ProjectDTO(int projectId, String projectName, String technology, String clientName,
                   String clientEmail, LocalDate startDate, LocalDate dueDate,
                   LocalDate endDate, List<EmployeeDTO> employees) {
        this.projectName = projectName;
        this.technology = technology;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.endDate = endDate;
        this.employees = employees;
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

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<EmployeeDTO> getEmployeesDTO() {
        return employees;
    }

    public void setEmployeesDTO(List<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public String toString() {
        StringBuilder stringBuilderAddress = new StringBuilder();
        stringBuilderAddress.append("\n       Project Details\n")
                            .append("\nProject ID          : ").append(projectId)
                            .append("\nProject Name          : ").append(projectName)
                            .append("\nTechnology            : ").append(technology)
                            .append("\nClientName            : ").append(clientName)
                            .append("\nClientEmail           : ").append(clientEmail)
                            .append("\nStartDate             : ").append(startDate)
                            .append("\nDueDate               : ").append(dueDate)
                            .append("\nEndDate               : ").append(endDate)
                            .append(employees);

        return stringBuilderAddress.toString();
    }
}