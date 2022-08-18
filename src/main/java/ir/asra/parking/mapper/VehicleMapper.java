package ir.asra.parking.mapper;

import ir.asra.parking.dto.VehicleDTO;
import ir.asra.parking.model.Vehicle;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  VehicleMapper {
    @Mappings({
            @Mapping(target = "carTypes" ,source = "vehicle.carType"),
    })
    VehicleDTO vehicleTovehicleDto(Vehicle vehicle);

    @InheritInverseConfiguration
    Vehicle vehicleDtoToVehicle(VehicleDTO vehicleDTO);
    @InheritInverseConfiguration
    List<VehicleDTO> toListDTO(List<Vehicle> vehicles);

}
