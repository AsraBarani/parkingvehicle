package ir.asra.parking.repository;


import ir.asra.parking.model.PriceRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRateRepository extends JpaRepository<PriceRate,Long> {
    PriceRate findTopByOrderByIdDesc();
}