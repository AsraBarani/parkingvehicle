package ir.asra.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static ir.asra.parking.myutility.MyUtility.MYFORMATTER;

@Getter
@Setter
@ToString
public class ParkingDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String arrivalTime = MYFORMATTER.format(Instant.now());
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String departureTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private PriceRateResultDTO priceRate;
    @NotBlank(message = "plate is blank !")
    private String plate;
    @NotBlank(message = "CarTypes is null !")
    private String carType;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean payed;
}
