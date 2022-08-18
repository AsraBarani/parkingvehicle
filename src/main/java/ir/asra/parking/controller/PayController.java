package ir.asra.parking.controller;

import ir.asra.parking.service.PayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pay")
public class PayController {

    private PayService payService;


    public PayController(PayService payService) {
        this.payService = payService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> doPay(@PathVariable String id){
        String result = payService.doPay(id);
        return ResponseEntity.accepted().body(result);
    }

    @GetMapping("/checkPay/{id}")
    public ResponseEntity<String> checkPay(@PathVariable String id){
        String result = payService.checkPay(id);
        return ResponseEntity.accepted().body(result);
    }



}
