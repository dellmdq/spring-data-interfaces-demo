package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//define field for entity manager
	private EntityManager entityManager;
		
	//set up constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}	
	
	@Override
	//@Transactional we comment it cause the service is gonna take care of the call to the DAO
	public List<Employee> findAll() {
		
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		//create a query
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);//using native hibernate API. the table first letter name goes uppercase
		
		//execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		//return the results
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {

		//get hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		//get the employee
		Employee theEmployee = currentSession.get(Employee.class, theId);
		//return it
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//get hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		//save or update employee
		currentSession.saveOrUpdate(theEmployee);//if id=0 hibernate add the employee to the table, else it updates the entity
	}

	@Override
	public void deleteById(int theId) {
		//get hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		//delete object with primary key
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId"); // :employee is a form to type parameters into hibernate queries
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();

	}

}
