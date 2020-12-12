package com.armanfar.sbjdbcdemo.section9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    public void insert(Employee employee) {
        em.persist(employee);
    }

    /* not possible to run this with @MappedSuperClass since Employee is not an Entity anymore
    public List<Employee> retrieveAllEmployees() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }
    */

    public List<FullTimeEmployee> retrieveFullTimeEmployees() {
        return em.createQuery("select fe from FullTimeEmployee fe", FullTimeEmployee.class).getResultList();
    }

    public List<PartTimeEmployee> retrievePartTimeEmployees() {
        return em.createQuery("select pe from PartTimeEmployee pe", PartTimeEmployee.class).getResultList();
    }

}
