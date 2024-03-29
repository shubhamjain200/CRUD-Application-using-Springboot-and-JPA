package in.bushansirgur.springbootcrud.springbootcrudapi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.bushansirgur.springbootcrud.springbootcrudapi.model.Employee;
import jakarta.persistence.EntityManager;

@Repository

public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public List<Employee> get() {
		// TODO Auto-generated method stub
		Session currentSession=entityManager.unwrap(Session.class);
		Query<Employee>query=currentSession.createQuery("from Employee",Employee.class);
		List<Employee>list=query.getResultList();
		return list;
	}

	@Override
	public Employee get(int id) {
		// TODO Auto-generated method stub
		Session currentSession=entityManager.unwrap(Session.class);

		Employee employeeObj=currentSession.get(Employee.class,id);
		return employeeObj;
	}

	//@Override
	//public void save(Employee employee) {
		@Override
		public void save(Employee employee) {
		    Session currentSession = entityManager.unwrap(Session.class);
		    if (employee.getId() == null) {
		        // If the ID is null, it means it's a new entity, so persist it
		        currentSession.persist(employee);
		    } else {
		        // If the ID is not null, it means it's an existing entity, so update it
		        currentSession.merge(employee);
		    }
		}

		// TODO Auto-generated method stub
		//Session currentSession=entityManager.unwrap(Session.class);
		//currentSession.persist(employee);
//}
	

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session currentSession=entityManager.unwrap(Session.class);
		Employee employeeObj=currentSession.get(Employee.class,id);
		currentSession.remove(employeeObj);
	}



}
