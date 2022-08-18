package ir.asra.parking.mapper;


import ir.asra.parking.dto.PriceRateDTO;
import ir.asra.parking.dto.PriceRateResultDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceRateResultMapper {

    PriceRateDTO orgToDto(PriceRateResultDTO priceRateResultDTO);

    PriceRateResultDTO dtoToOrg(PriceRateDTO priceRateDTO);
}

