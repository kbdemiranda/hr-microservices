package io.github.hrpayroll.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Payment {

    private String name;
    private BigDecimal dailyIncome;
    private Integer days;

    public BigDecimal getTotal(){
        BigDecimal multiply = dailyIncome.multiply(new BigDecimal(days));
        return multiply;
    }
}
