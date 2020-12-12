package com.armanfar.sbjdbcdemo.section5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review")
@NamedQueries(value = {
        @NamedQuery(name = "find_all_reviews", query = "select r from Review r")
})
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String rating;

    private String description;

    @ManyToOne
    private Course course;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @Override
    public String toString() {
        return "\n\t>> Review{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
