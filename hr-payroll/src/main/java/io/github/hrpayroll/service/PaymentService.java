package io.github.hrpayroll.service;

import io.github.hrpayroll.domain.Payment;
import io.github.hrpayroll.domain.Worker;
import io.github.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient feignClient;


    public Payment getPayment(Long workId, Integer days){

        Worker worker = feignClient.findById(workId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
