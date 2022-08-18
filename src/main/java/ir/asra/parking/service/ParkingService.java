package ir.asra.parking.service;


import ir.asra.parking.dto.ParkingDTO;
import ir.asra.parking.dto.PayDTO;
import ir.asra.parking.dto.VehicleReportDTO;
import ir.asra.parking.mapper.ParkingMapper;
import ir.asra.parking.model.Parking;
import ir.asra.parking.model.Pay;
import ir.asra.parking.model.PriceRate;
import ir.asra.parking.repository.ParkingRepository;
import ir.asra.parking.repository.PriceRateRepository;
import ir.asra.parking.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static ir.asra.parking.myutility.MyUtility.*;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final PriceRateRepository priceRateRepository;
    private final VehicleRepository vehicleRepository;
    private final PayService payService;
    private ParkingMapper parkingMapper;


    public ParkingService(ParkingRepository parkingRepository, PriceRateRepository priceRateRepository, VehicleRepository vehicleRepository, PayService payService, ParkingMapper parkingMapper) {
        this.parkingRepository = parkingRepository;
        this.priceRateRepository = priceRateRepository;
        this.vehicleRepository = vehicleRepository;
        this.payService = payService;
        this.parkingMapper = parkingMapper;
    }


    public ParkingDTO entry(ParkingDTO parkingDTO) {
        String plate = parkingDTO.getPlate().trim();
        if (!checkPlate(plate))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad plate Format !");

        Optional<Parking> duplicatorVehicle = parkingRepository.findByVehiclePlateAndPayedIsFalse(plate);
        if (duplicatorVehicle.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "this plate still is in the parking ! you cannot insert again !");


        Parking parking = parkingMapper.parkingDtoToParking(parkingDTO);
        if (vehicleRepository.existsVehicleByPlate(plate))
            parking.setVehicle(vehicleRepository.getByPlate(plate));

        Optional<PriceRate> priceRate = Optional.ofNullable(priceRateRepository.findTopByOrderByIdDesc());
        PriceRate pr = priceRate.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_EXTENDED, "plz insert price rate first !"));

        parking.setPriceRate(pr);
        parking.setPayed(false);
        Parking save = parkingRepository.save(parking);
        return parkingMapper.parkingToParkingDTO(save);

    }

    public ParkingDTO getBill(String plate) {
        if (!checkPlate(plate))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad plate Format !");
        Optional<Parking> parking = parkingRepository.findByVehiclePlateAndPayedIsFalse(plate);
        Parking result = parking.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Vehicle find With This Plate !"));
        long totalMinutes = calculateExpense(result);
        setPrice(result, totalMinutes);
        return parkingMapper.parkingToParkingDTO(result);
    }

    private void setPrice(Parking parking, long totalMinutes) {
        PriceRate priceRate = parking.getPriceRate();
        parking.setDepartureTime(MYFORMATTER.format(Instant.now()));
        if (totalMinutes < 60) {
            parking.setPrice(priceRate.getBaseCost());
        } else if (totalMinutes < 60 * 24) {
            parking.setPrice(priceRate.getBaseCost() + priceRate.getHourlyRate() * (totalMinutes / 60));
        } else if (totalMinutes < 60 * 24 * 30) {
            parking.setPrice(priceRate.getBaseCost() + priceRate.getDailyRate() * (totalMinutes / (60 * 24)));
        } else if (totalMinutes > 60 * 24 * 30) {
            parking.setPrice(priceRate.getBaseCost() + priceRate.getDailyRate() * (totalMinutes / (60 * 24 * 30)));
        }
    }

    public PayDTO exitVehicle(String plate) {
        ParkingDTO bill = getBill(plate);
        Pay pay = payService.createPay(parkingMapper.parkingDtoToParking(bill));
        PayDTO payDTO = new PayDTO();
        payDTO.setParkingDTO(parkingMapper.parkingToParkingDTO(pay.getParking()));


        final String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        payDTO.setPayUrl(baseUrl + "/api/v1/pay/" + pay.getId());
        return payDTO;
    }

    public List<VehicleReportDTO> vehicleReport(String plate) {
        Optional<List<Parking>> allByVehiclePlate = parkingRepository.findAllByVehiclePlate(plate);
        allByVehiclePlate.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"we cant find any report for this plate !"));
        return parkingMapper.parkingsToVehicleReportDTOs(allByVehiclePlate.get());
    }
}

