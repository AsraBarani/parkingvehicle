package ir.asra.parking.service;


import ir.asra.parking.dto.PriceRateDTO;
import ir.asra.parking.mapper.PriceRateMapper;
import ir.asra.parking.model.PriceRate;
import ir.asra.parking.repository.PriceRateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PriceRateService {
    private final PriceRateRepository priceRateRepository;
    private PriceRateMapper priceRateMapper;

    public PriceRateService(PriceRateRepository priceRateRepository, PriceRateMapper priceRateMapper) {
        this.priceRateRepository = priceRateRepository;
        this.priceRateMapper = priceRateMapper;
    }

    public PriceRateDTO createPriceRate(PriceRateDTO priceRateDTO) {
        PriceRate priceRate = priceRateMapper.priceRateDtoToPriceRate(priceRateDTO);
        PriceRate save = priceRateRepository.save(priceRate);
        return priceRateMapper.priceRateToPriceRateDTO(save);
    }

    public PriceRateDTO get(String id) {
        Optional<PriceRate> byId = priceRateRepository.findById(Long.parseLong(id));
        PriceRate priceRate = byId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no price rate find whith this prace rate !"));
        return priceRateMapper.priceRateToPriceRateDTO(priceRate);
    }

    public List<PriceRateDTO> getAll() {
        List<PriceRate> all = priceRateRepository.findAll();
        return priceRateMapper.toListDTO(all);
    }

    public PriceRateDTO updatePriceRate(PriceRateDTO priceRateDTO) {
        Optional<PriceRate> byId = priceRateRepository.findById(priceRateDTO.getId());
        PriceRate priceRate = byId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no price rate find with this id !"));
        priceRate = priceRateMapper.priceRateDtoToPriceRate(priceRateDTO);
        PriceRate save = priceRateRepository.save(priceRate);
        return priceRateMapper.priceRateToPriceRateDTO(save);
    }
}
