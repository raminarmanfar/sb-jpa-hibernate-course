package com.armanfar.sbjdbcdemo.section5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passport")
@NamedQueries(value = {
        @NamedQuery(name = "find_all_passports", query = "select p from Passport p")
})
public class Passport {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;

    @Override
    public String toString() {
        return "\n\t>> Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", student_id=" + student.getId() +
                ", student_name=" + student.getName() +
                '}';
    }
}
