package com.armanfar.sbjdbcdemo.section9;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
public class PartTimeEmployee extends Employee {
    private BigDecimal hourlyWage;
}
