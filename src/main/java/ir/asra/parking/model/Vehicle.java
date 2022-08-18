package ir.asra.parking.model;


import ir.asra.parking.model.enums.CarTypes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String plate;

    @Enumerated(EnumType.STRING)
    private CarTypes carType;

}
