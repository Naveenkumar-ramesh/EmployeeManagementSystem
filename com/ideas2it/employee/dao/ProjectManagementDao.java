package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.util.connection.ConnectionUtil;
import com.ideas2it.employee.exception.EMSException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;


/**
 * Implements transfer of employee details from database and
 * provides the data to be displayed.
 *
 * @version 1.8 13-09-2022
 * @author Naveenkumar R
 */
public class ProjectManagementDao {
    SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
    static Logger logger = LogManager.getLogger(ProjectManagementDao.class);

    /**
     * Saves the employee details in above database
     * and return true if the process is successful.
     * 
     * @param employee from controller
     * @return Return the boolean value.
     */
    public int addProject(Project project) throws EMSException {
        Session session = sessionFactory.openSession();
        int projectId;
        try {

            Transaction transaction = session.beginTransaction();
            projectId = (Integer) session.save(project);
            transaction.commit();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
             ("Error occured in inserting data, Try again", "ErrorCode 101");
        } finally {
            session.close();
        }
        return projectId;
    }

    /**
     * Returns ProjectDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    public List<Project> getProjects() throws EMSException {
        List<Project> projects = new ArrayList();
        Session session = sessionFactory.openSession();
        try {
            projects = session.createQuery("FROM Project").list();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
             ("Error occured while retreving data, Try again", 
               "ErrorCode 102");
        } finally {
            session.close();
        }
        return projects;
    }

    /**
     * Updates the Project detail and returns true if successful.
     *
     * @param project
     * @return true if project is updated
     */
    public void updateProject(Project project) throws EMSException {
        Session session = sessionFactory.openSession();        
        try {

            Transaction transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
             ("Error occured in updating data, Try again", "ErrorCode 103");
        } finally {
            session.close();
        }
    }

    /**
     * Search the Project detail and returns the Project details.
     *
     * @param firstName
     * @return Project details.
     */
    public List<Project> searchProject(String projectName) throws EMSException {
        List<Project> projects = new ArrayList();
        Session session = sessionFactory.openSession();
        try {

            Criteria criteria = session.createCriteria(Project.class);
            projects = (List<Project>) criteria.add(Restrictions.like
                                  ("projectName", (projectName + "%"))).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
             ("Error occured while retreving data, Try again", 
               "ErrorCode 102");
        } finally {
            session.close();
        }
        return projects;
    }

    /**
     * Deletes the project details and returns true if successful.
     *
     * @param project name
     * @return true if project details are deleted.
     */
    public void deleteProject(int projectId) throws EMSException {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Project project = (Project)session.get(Project.class,projectId);
            session.delete(project);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage() + "Project Id = " + projectId);
            throw new EMSException
             ("Error occured while deleting data, Try again", "ErrorCode 105");
        } finally {
            session.close();
        }
    }

}