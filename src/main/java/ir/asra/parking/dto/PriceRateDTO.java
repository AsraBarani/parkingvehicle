package ir.asra.parking.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.time.Instant;

import static ir.asra.parking.myutility.MyUtility.MYFORMATTER;

@Getter
@Setter
@ToString
public class PriceRateDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Min(1)
    private Long baseCost;
    @Min(1)
    private Long hourlyRate;
    @Min(1)
    private Long dailyRate;
    @Min(1)
    private Long monthlyRate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creationDate = MYFORMATTER.format(Instant.now());
}
