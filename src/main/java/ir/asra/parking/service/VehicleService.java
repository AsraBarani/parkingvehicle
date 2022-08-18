package ir.asra.parking.service;


import ir.asra.parking.dto.VehicleDTO;
import ir.asra.parking.mapper.VehicleMapper;
import ir.asra.parking.model.Vehicle;
import ir.asra.parking.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static ir.asra.parking.myutility.MyUtility.checkPlate;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleService(VehicleRepository vehicleRepository, ir.asra.parking.mapper.VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }


    public VehicleDTO create(VehicleDTO vehicleDTO) {
        if (!checkPlate(vehicleDTO.getPlate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad plate Format !");
        if (vehicleDTO.getCarTypes() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad CarTypes Format !");
        Vehicle vehicle = vehicleMapper.vehicleDtoToVehicle(vehicleDTO);
        return vehicleMapper.vehicleTovehicleDto(vehicleRepository.save(vehicle));
    }

    public VehicleDTO get(String plate) {
        return vehicleMapper.vehicleTovehicleDto(vehicleRepository.getByPlate(plate));
    }

    public List<VehicleDTO> getAll() {
        List<Vehicle> all = vehicleRepository.findAll();
        return vehicleMapper.toListDTO(all);
    }


    public VehicleDTO update(VehicleDTO vehicleDTO) {
        if (!checkPlate(vehicleDTO.getPlate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad plate Format !");
        if (vehicleDTO.getCarTypes() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad CarTypes Format !");
        Vehicle vehicle = vehicleMapper.vehicleDtoToVehicle(vehicleDTO);

        Optional<Vehicle> byId = vehicleRepository.findById(vehicle.getId());
        Vehicle vehicle1 = byId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No vehicle Find With this ID !"));
        vehicle1.setPlate(vehicle.getPlate());
        vehicle1.setCarType(vehicle.getCarType());

        return vehicleMapper.vehicleTovehicleDto(vehicleRepository.save(vehicle1));
    }
}

