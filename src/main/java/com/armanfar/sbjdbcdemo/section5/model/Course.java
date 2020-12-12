package com.armanfar.sbjdbcdemo.section5.model;

import lombok.*;
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
@Table(name = "Course")
@NamedQueries(value = {
        @NamedQuery(name = "find_all_courses", query = "select c from Course c"),
        @NamedQuery(name = "find_all_courses_100_steps", query = "select c from Course c where c.name like '%100 Steps'")
})
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @Setter(AccessLevel.NONE)
    @Getter
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    @Override
    public String toString() {
        return "\n\t>> Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number of reviews=" + reviews.size() +
                '}';
    }
}
