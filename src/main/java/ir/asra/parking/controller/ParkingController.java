package ir.asra.parking.controller;

import ir.asra.parking.dto.ParkingDTO;
import ir.asra.parking.dto.PayDTO;
import ir.asra.parking.dto.VehicleReportDTO;
import ir.asra.parking.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking")
public class ParkingController {

    private ParkingService parkingService;


    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }


    @PostMapping("/entry")
    public ResponseEntity<ParkingDTO> entryVehicle(@RequestBody @Validated ParkingDTO parkingDTO) {
        ParkingDTO result = parkingService.entry(parkingDTO);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getBill/{plate}")
    public ResponseEntity<ParkingDTO> getBill(@PathVariable String plate){
        ParkingDTO result = parkingService.getBill(plate);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/exit/{plate}")
    public ResponseEntity<PayDTO> exitVehicle(@PathVariable String plate){
        PayDTO payDTO = parkingService.exitVehicle(plate);
        return ResponseEntity.ok(payDTO);
    }

    @GetMapping("/report/{plate}")
    public ResponseEntity<List<VehicleReportDTO>> vehicleReport(@PathVariable String plate){
        List<VehicleReportDTO> vehicleReport = parkingService.vehicleReport(plate);
        return ResponseEntity.ok(vehicleReport);
    }







}
