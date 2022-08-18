package ir.asra.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.asra.parking.model.enums.CarTypes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VehicleDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String plate;
    private CarTypes carTypes;
}

