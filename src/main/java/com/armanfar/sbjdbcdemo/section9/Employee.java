package com.armanfar.sbjdbcdemo.section9;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
// using this creates an independent table for each sub-class with no relation between them.
// also this super class will be neither an entity anymore nor table in the DB.
// therefore we should not use @Entity with this annotation.
@MappedSuperclass
// @Entity
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "EmployeeType")
public abstract class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String name;

}
