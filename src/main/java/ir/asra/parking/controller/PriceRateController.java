package ir.asra.parking.controller;


import ir.asra.parking.dto.PriceRateDTO;
import ir.asra.parking.service.PriceRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/priceRate")
public class PriceRateController {

    private final PriceRateService priceRateService;

    public PriceRateController(PriceRateService priceRateService) {
        this.priceRateService = priceRateService;
    }


    @PostMapping
    public ResponseEntity<PriceRateDTO> createPriceRate(@RequestBody @Validated PriceRateDTO priceRateDTO){
        PriceRateDTO result = priceRateService.createPriceRate(priceRateDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceRateDTO> getPriceRate(@PathVariable String id){
        PriceRateDTO result = priceRateService.get(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PriceRateDTO>> getAllPriceRate(){
        List<PriceRateDTO> result = priceRateService.getAll();
        return ResponseEntity.ok(result);
    }

    @PatchMapping
    public ResponseEntity<PriceRateDTO> updatePriceRate(@RequestBody @Validated  PriceRateDTO priceRateDTO){
        PriceRateDTO result = priceRateService.updatePriceRate(priceRateDTO);
        return ResponseEntity.ok(result);
    }



}
