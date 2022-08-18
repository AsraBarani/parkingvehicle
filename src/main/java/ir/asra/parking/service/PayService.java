package ir.asra.parking.service;

import ir.asra.parking.model.Parking;
import ir.asra.parking.model.Pay;
import ir.asra.parking.repository.PayRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;

@Service
public class PayService {

    private final PayRepository payRepository;


    public PayService(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    public String doPay(String id) {
        Random random = new Random();
        boolean b = random.nextBoolean();

        Optional<Pay> byId = payRepository.findById(Long.valueOf(id));
        Pay pay = byId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " no bill find for payment with this id !"));

        if (pay.isPayed())
            return "already paid";
        pay.setPayed(b);
        pay.getParking().setPayed(b);
        payRepository.save(pay);

        if (b)
            return "Pay Is Successfully !";
        else
            return "Pay Not Completed !";

    }


    public String checkPay(String id) {
        Optional<Pay> byId = payRepository.findById(Long.valueOf(id));
        Pay pay = byId.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " no bill find for payment with this id !"));

        if (pay.isPayed())
            return "Payment made";
        else
            return "Payment not made";
    }

    public Pay createPay(Parking parking) {
        Pay pay = new Pay();
        pay.setParking(parking);
        pay.setPrice(parking.getPrice());
        pay.setPayed(false);
        Pay save = payRepository.save(pay);
        return save;
    }


}
