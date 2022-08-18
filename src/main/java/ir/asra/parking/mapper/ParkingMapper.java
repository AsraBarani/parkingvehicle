package ir.asra.parking.mapper;


import ir.asra.parking.dto.ParkingDTO;
import ir.asra.parking.dto.VehicleReportDTO;
import ir.asra.parking.model.Parking;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkingMapper {

    @Mappings({
            @Mapping(target = "carType", source = "vehicle.carType"),
            @Mapping(target = "plate", source = "vehicle.plate"),
    })
    ParkingDTO parkingToParkingDTO(Parking parking);


    Parking parkingDtoToParking(ParkingDTO parkingDTO);




    @Mappings({
            @Mapping(target = "carType", source = "vehicle.carType"),
            @Mapping(target = "plate", source = "vehicle.plate"),
    })
    VehicleReportDTO parkingToVehicleReportDTO(Parking parking);

    @InheritInverseConfiguration
    List<VehicleReportDTO> parkingsToVehicleReportDTOs(List<Parking> parkings);


}

