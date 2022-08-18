package ir.asra.parking.repository;

import ir.asra.parking.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Long> {
}
