package ir.asra.parking.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VehicleReportDTO {
    private String plate;
    private String carType;
    private String arrivalTime;
    private String departureTime;
    private Long price;


}

