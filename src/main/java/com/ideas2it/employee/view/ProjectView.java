package com.ideas2it.employee.view;

import com.ideas2it.employee.controller.ProjectController;
import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.util.ValidationUtil;
import com.ideas2it.employee.exception.EMSException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.time.DateTimeException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Get's the Project details , organizes and saves them to database .
 * Display's and manipulates these project details.
 *
 * @version 1.8 28-10-2022
 * @author Naveenkumar R
 */
public class ProjectView {

    Scanner scanner = new Scanner(System.in);
    ProjectController projectController = new ProjectController();
    static Logger logger = LogManager.getLogger(ProjectView.class);
    ProjectDTO projectDTO = new ProjectDTO();

    /**
     * Create, read, search, update, delete and exit 
     * operations takes place by choice.
     */
    public void chooseOperation() {

        int operations = 0;
        do {
            try {
                System.out.println(EmployeeManagementConstant.PROJECT_OPERATION);
                operations = Integer.valueOf(scanner.nextLine());
                switch (operations) {
                    case 1:
                        this.createProject();
                        break;

                    case 2:
                        this.displayProject();
                        break;

                    case 3:
                        this.searchProject();
                        break;

                    case 4:
                        this.updateProject();
                        break;

                    case 5:
                        this.deleteProject();
                        break;

                    case 6:
                        System.out.println("**EXIT**");
                        break;

                    default:
                        System.out.println("Invalid choice ... Try Again");
                }
            } catch (NumberFormatException numberFormatError) {
                System.out.println("Invalid choice ... Try Again");
            }
        } while (6 != operations);
    }

    /**
     * Project details are received and added to database
     * and Shows if they are added or not.
     */
    public void createProject() {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        System.out.println(EmployeeManagementConstant.VALID_DETAILS);
        boolean isRepeat;
        try {
            int projectId = 1;
            String projectName = getProjectName();
            String technology = getTechnology();
            String clientName = getClientName();
            String clientEmail = getClientEmail();
            LocalDate startDate = getStartDate();
            LocalDate dueDate = getDueDate(startDate);
            LocalDate endDate = getEndDate(startDate);
            isRepeat = getResponse();
            if (isRepeat)   {
                employeeDTOs = getEmployee(employeeDTOs);
            }
            projectDTO = new ProjectDTO(projectId, projectName, technology,
                                          clientName, clientEmail, startDate,
                                          dueDate, endDate, employeeDTOs);

            projectId = projectController.addProject(projectDTO);
            logger.info("Project Details Added for ID = " + projectId);
            System.out.println("Project Details Added");
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Display's all the employee details that are 
     * saved in the the database.
     */
    public void displayProject() {

        try {
            List<ProjectDTO> projects = projectController.getProjects();
            if(!(projects.isEmpty())) {
                for (ProjectDTO project : projects) {
                    System.out.println(project.toString());
                }
            } else {
                logger.info("No Project details found ");
                System.out.println("No Project details found ");
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Searches for the Project by name and returns if it exists
     * or returns no record found.
     */
    public void searchProject() {
        String projectName = getProjectName();

        try {
            List<ProjectDTO> projects = projectController.searchProject(projectName);
            if(!(projects.isEmpty())) {
                 for (ProjectDTO project : projects) {
                    System.out.println(project.toString());
                }
                logger.info("Project details found for Name = " +
                             projectName);
            } else {
                logger.info("No Project details found for Name = " +
                             projectName);
                System.out.println("No Project details found ");
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Transfers the project details to check if the name exists
     * and if exists it updates the project details details by choice.
     *
     * If the given name not found it shows record not found.
     */
    public void updateProject() {
        boolean isValid;
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        int projectId;
        int operations;
        boolean isUpdated = false;
        try {
            do {
                System.out.println("Enter existing Project Id");
                projectId = getId();
            } while(!(projectController.isProjectPresent(projectId)));

            ProjectDTO projectDTO = projectController.getProjectById(projectId);

            do {
                System.out.println(EmployeeManagementConstant.PROJECT_UPDATE);
                operations = Integer.valueOf(scanner.nextLine());

                switch (operations) {
                    case 1:
                        projectDTO.setProjectName(getProjectName());
                        break;

                    case 2:
                        projectDTO.setTechnology(getTechnology());
                        break;

                    case 3:
                        projectDTO.setClientName(getClientName());
                        break;

                    case 4:
                        projectDTO.setClientEmail(getClientEmail());
                        break;

                    case 5:
                        projectDTO.setStartDate(getStartDate());
                        break;

                    case 6:
                        projectDTO.setDueDate(getDueDate(projectDTO.getStartDate()));
                        break;

                    case 7:
                        projectDTO.setEndDate(getEndDate(projectDTO.getStartDate()));
                        break;

                    case 8:
                        projectDTO.setEmployeesDTO(getEmployee(projectDTO.getEmployeesDTO()));
                        break;

                    case 9:
                        isUpdated = true;
                        break;

                    default:
                        System.out.println("Wrong choice ,Try again ");
                }
            } while (!(isUpdated));

            projectController.updateProject(projectDTO);
            logger.info("Project details has been updated");
            System.out.println("Project details has been updated");
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        } catch (NumberFormatException numberFormatError) {
            logger.error("Error while choosing update");
            System.out.println("Error while choosing update , Try again ");
        }
    }

    /**
     * Here we get the project id to which
     * relevant details of the project are deleted.
     */
    public void deleteProject() {

        int projectId = getId();
        try {
            if (projectController.isProjectPresent(projectId)) {
                projectController.deleteProject(projectId);
                logger.info("Project details deleted for ID = " + projectId);
                System.out.println("Project details deleted for ID = "
                                    + projectId);
            } else {
                System.out.println("This Project ID does not exist");
            }
        } catch (EMSException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
    }

    /**
     * Gets the project Id of the project and checks if
     * it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return Id of the project.
     */
    public int getId() {
        String projectId = null;
        boolean isValid;
        do {
            System.out.print("Enter the project id to update : ");
            try {
                projectId = scanner.nextLine();
                isValid = projectController.validateField(
                                  EmployeeManagementConstant.
                                  REGEX_ID,projectId);
                if (!(isValid)) {
                    System.out.println("Enter valid employee id");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Enter valid employee id");
                isValid = false;
            }
        }while (!(isValid));
        return Integer.parseInt(projectId);
    }

    /**
     * Gets the project name and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return name of the project.
     */
    public String getProjectName() {
        boolean isValid = true;
        String projectName;

        do {
            System.out.println("Enter project name : ");
            projectName = scanner.nextLine();

            if(projectController.validateField(EmployeeManagementConstant.VALID_PROJECT_NAME, projectName)) {
                isValid = false;
            } else {
                System.out.println("Invalid Project Name ... Try again");   
            }
        } while (isValid);
        return projectName;
    }

    /**
     * Gets the technology name and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return name of the technology.
     */
    public String getTechnology() {
        boolean isValid = true;
        System.out.println("Enter name of the technology");
        String technology = scanner.nextLine();
        return technology;
    }

    /**
     * Gets the client name and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return name of the client.
     */
    public String getClientName() {
        boolean isValid = true;
        String clientName;

        do {
            System.out.println("Enter client name : ");
            clientName = scanner.nextLine();

            if(projectController.validateField(EmployeeManagementConstant.VALID_CLIENT_NAME, clientName)) {
                isValid = false;
            } else {
                System.out.println("Invalid Client Name ... Try again");   
            }
        } while (isValid);
        return clientName;
    }

    /**
     * Gets the client email and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return client email.
     */
    public String getClientEmail() throws EMSException{
        boolean isValid = true;
        String clientEmail;

        do {
            System.out.println("Enter client email id : ");
            clientEmail = scanner.nextLine();

            if (projectController.validateField(EmployeeManagementConstant.REGEX_EMAIL,
                                               clientEmail)) {
                isValid = false;
            } else {
                System.out.println("Invalid Email Id ... Try again");   
            }
        } while (isValid);
        return clientEmail;
    }

    /**
     * Gets the project starting date and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return starting date of project.
     */
    public LocalDate getStartDate() {
        boolean isValid = true;
        String startDate;
        LocalDate localDate = null;

        do {
            System.out.println(EmployeeManagementConstant.START_DATE);
            startDate = scanner.nextLine();

            if (projectController.isValidStartDate(startDate)) {
                localDate = LocalDate.parse(startDate);
                isValid = false;
            } else {
                System.out.println("Invalid Starting date ... Try again");
            }
        } while (isValid);
        return localDate;
    }

    /**
     * Gets the project due date and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return due date of project.
     */
    public LocalDate getDueDate(LocalDate startDate) {
        boolean isValid = true;
        String date;
        LocalDate localDate = null;

        do {

            System.out.println(EmployeeManagementConstant.DUE_DATE);
            date = scanner.nextLine();

            if (projectController.isValidDate(startDate ,date)) {
                localDate = LocalDate.parse(date); 
                isValid = false;
            } else {
                System.out.println("Invalid Due date ... Try again");
            }

        } while (isValid);
        return localDate;
    }

    /**
     * Gets the project ending date and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return ending date of project.
     */
    public LocalDate getEndDate(LocalDate startDate) {
        boolean isValid = true;
        String date;
        LocalDate localDate = null;

        do {
            System.out.println(EmployeeManagementConstant.END_DATE);
            date = scanner.nextLine();

            if (projectController.isValidDate(startDate ,date)) {
                localDate = LocalDate.parse(date); 
                isValid = false;
            } else {
                System.out.println("Invalid Ending date ... Try again");
            }
        } while (isValid);
        return localDate;
    }

    /**
     * Get's the employee details for the employee id.
     *
     * @return employee details.
     */
    public List<EmployeeDTO> getEmployee(List<EmployeeDTO> employeeDTOs) {
       EmployeeDTO employeeDTO = null;
       boolean isRepeat;
       try {
           do {
               employeeDTO = projectController.getEmployee(getEmployeeID());
               if (null != employeeDTO) {
                   employeeDTOs.add(employeeDTO);
               }
               isRepeat = getResponse();
           }while(isRepeat);
       }catch (EMSException e) {
            logger.error(e.getErrorCode() + " " + e.getMessage());
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
       return employeeDTOs;
    }

    /**
     * Get's the employee id and and checks if it is valid .
     * If it is invald repeat this process until valid.
     *
     * @return employee id.
     */
    public int getEmployeeID() {
        boolean isValid = true;
        String employeeId;
        System.out.println("Enter employee id");
        do {
            employeeId = scanner.nextLine();

            if(projectController.validateField(EmployeeManagementConstant.REGEX_ID,
                                             employeeId)) {
                isValid = false;
            } else {
                System.out.println("Invalid Id ... Try again");   
            }
        } while (isValid);
        return Integer.valueOf(employeeId);
    }

    /**
     * Get's the response for the continuation of the addition
     * of employee details .
     *
     * @return boolean
     */
    public boolean getResponse() {
        boolean isRepeat = false;
        boolean isValid;
        System.out.println("You want to add employee, press y for yes/ n for no");
        do {
            isValid = true;

            switch (scanner.nextLine()) {
                case "y":
                    isRepeat = true;
                    break;

                case "n":
                    isRepeat = false;
                    break;

                default:
                    isValid = false;
                    System.out.println("Invalid choice ... Try again"); 
            }
        } while (!isValid);
        return isRepeat;
    }

}