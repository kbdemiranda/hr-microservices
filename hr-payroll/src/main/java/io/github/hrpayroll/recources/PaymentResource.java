package io.github.hrpayroll.recources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.github.hrpayroll.domain.Payment;
import io.github.hrpayroll.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    @Autowired
    public PaymentService service;

    @HystrixCommand(fallbackMethod = "getPaymentFallback")
    @GetMapping("/{workID}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workID, @PathVariable Integer days){
        Payment payment = service.getPayment(workID, days);
        return ResponseEntity.ok(payment);
    }

    public ResponseEntity<Payment> getPaymentFallback(Long workID, Integer days){
        Payment payment = new Payment("Brian", new BigDecimal("100"), days);
        return ResponseEntity.ok(payment);
    }




}
