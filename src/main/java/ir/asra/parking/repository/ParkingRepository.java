package ir.asra.parking.repository;


import ir.asra.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<Parking,Long> {
    Optional<Parking> findByVehiclePlate(String plate);
    Optional<Parking> findByVehiclePlateAndPayedIsFalse(String plate);

    Optional<List<Parking>> findAllByVehiclePlate(String plate);
}

