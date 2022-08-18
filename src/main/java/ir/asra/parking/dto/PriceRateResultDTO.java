package ir.asra.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

@Getter
@Setter
@ToString
public class PriceRateResultDTO {
    @Min(1)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long baseCost;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long hourlyRate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long dailyRate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long monthlyRate;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creationDate;
}