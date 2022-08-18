package ir.asra.parking.controller;


import ir.asra.parking.dto.VehicleDTO;
import ir.asra.parking.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody @Validated VehicleDTO vehicleDTO){
        VehicleDTO result = vehicleService.create(vehicleDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{plate}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable String plate){
        VehicleDTO result = vehicleService.get(plate);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> getAllVehicle(){
        List<VehicleDTO> result = vehicleService.getAll();
        return ResponseEntity.ok(result);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody @Validated VehicleDTO vehicleDTO){
        VehicleDTO result = vehicleService.update(vehicleDTO);
        return ResponseEntity.ok(result);
    }





}

