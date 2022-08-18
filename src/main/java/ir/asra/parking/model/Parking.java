package ir.asra.parking.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String arrivalTime;
    private String departureTime;
    private Long price;
    @OneToOne(cascade = CascadeType.ALL)
    private PriceRate priceRate;
    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;
    private boolean payed ;


}

