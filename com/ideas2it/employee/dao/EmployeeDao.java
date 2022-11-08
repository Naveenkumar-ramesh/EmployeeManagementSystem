package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
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
public class EmployeeDao {
    SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
    static Logger logger = LogManager.getLogger(EmployeeDao.class);

    /**
     * Saves the employee details in above database
     * and return true if the process is successful.
     * 
     * @param employee from controller
     * @return Return the boolean value.
     */
    public int addEmployee(Employee employee) throws EMSException {

        int employeeId;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            employeeId = (Integer) session.save(employee);
            transaction.commit();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
             ("Error occured in inserting data, Try again", "ErrorCode 101");
        } finally {
            session.close();
        }
        return employeeId;
    }

    /**
     * Returns EmployeeDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    public List<Employee> getEmployees() throws EMSException {
        List<Employee> employees = new ArrayList();
        Session session = sessionFactory.openSession();
        try {
            employees = session.createQuery("FROM Employee").list();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
             ("Error occured while retreving data, Try again", 
               "ErrorCode 102");
        } finally {
            session.close();
        }
        return employees;
    }


    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    public void updateEmployee(Employee employee) throws EMSException {
        Session session = sessionFactory.openSession();        
        try {

            Transaction transaction = session.beginTransaction();
            session.update(employee);
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
     * Search the employee detail and returns the employee details.
     *
     * @param firstName
     * @return employee details wiith address.
     */
    public List<Employee> searchEmployee(String firstName) throws EMSException {
        List<Employee> employees = new ArrayList();
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(Employee.class);
            employees = (List<Employee>) criteria.add(Restrictions.like
                                  ("firstName", (firstName + "%"))).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
             ("Error occured while retreving data, Try again", 
               "ErrorCode 102");
        } finally {
            session.close();
        }
        return employees;
    }

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    public void deleteEmployee(int employeeId) throws EMSException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class,employeeId);
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage() + " Employee Id = " + employeeId);
            throw new EMSException
             ("Error occured while deleting data, Try again", "ErrorCode 105");
        } finally {
            session.close();
        }
    }

}