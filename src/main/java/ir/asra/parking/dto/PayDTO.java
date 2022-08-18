package ir.asra.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ParkingDTO parkingDTO;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String payUrl;


}