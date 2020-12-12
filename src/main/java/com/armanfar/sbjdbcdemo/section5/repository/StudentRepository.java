package com.armanfar.sbjdbcdemo.section5.repository;

import com.armanfar.sbjdbcdemo.section5.model.Course;
import com.armanfar.sbjdbcdemo.section5.model.Passport;
import com.armanfar.sbjdbcdemo.section5.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager em;

    public List<Student> findAll() {
        return em.createNamedQuery("find_all_students", Student.class).getResultList();
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public Student save(Student student, Passport passport) {
        em.persist(passport);
        student.setPassport(passport);
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        if(student != null) {
            em.remove(student);
        }
    }

    public void addCourse(Long studentId, Long courseId) {
        Student student = em.find(Student.class, studentId);
        Course course = em.find(Course.class, courseId);

        em.persist(student);
        em.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
    }
}
