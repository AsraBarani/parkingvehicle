package ir.asra.parking.mapper;


import ir.asra.parking.dto.PriceRateDTO;
import ir.asra.parking.model.PriceRate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceRateMapper {

    PriceRate priceRateDtoToPriceRate(PriceRateDTO priceRateDTO);

    PriceRateDTO priceRateToPriceRateDTO(PriceRate priceRate);

    List<PriceRateDTO> toListDTO(List<PriceRate> priceRate);
}

