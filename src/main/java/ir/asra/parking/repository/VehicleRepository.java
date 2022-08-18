package ir.asra.parking.repository;


import ir.asra.parking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    boolean existsVehicleByPlate(String plate);
    Vehicle getByPlate(String plate);

    void deleteByPlate(String plate);
}

