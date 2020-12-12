package com.armanfar.sbjdbcdemo.section5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
@NamedQueries(value = {
        @NamedQuery(name = "find_all_students", query = "select s from Student s")
})
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    private List<Course> courses;

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public String toString() {
        return "\n\t>> Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport_id=" + passport.getId() +
                ", passport_number=" + passport.getNumber() +
                '}';
    }
}
