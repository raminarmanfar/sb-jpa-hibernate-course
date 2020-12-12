package com.armanfar.sbjdbcdemo.section9;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.math.BigDecimal;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class FullTimeEmployee extends Employee {
    private BigDecimal salary;
}
