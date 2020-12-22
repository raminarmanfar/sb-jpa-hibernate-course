package com.armanfar.sbjdbcdemo.section5.repository;

import com.armanfar.sbjdbcdemo.section5.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "course", path = "course")
public interface CourseJpaRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    List<Course> findAllByOrderByIdDesc();
    List<Course> findTop4ByOrderByIdDesc();
    List<Course> findByNameAndId(String name, Long id);
    Long countAllByName(String name);

    List<Course> deleteCourseByName(String name);

    @Query("select c from Course c where c.name like '%100 Steps'")
    List<Course> courseWith100StepsInName();

    @Query(value = "select * from course where name like '%100 Steps'", nativeQuery = true)
    List<Course> courseWith100StepsInNameWithNativeQuery();

    @Query(name = "find_all_courses_100_steps")
    List<Course> courseWith100StepsInNameWithNamedQuery();}
