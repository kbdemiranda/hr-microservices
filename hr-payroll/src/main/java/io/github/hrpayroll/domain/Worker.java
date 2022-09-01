package io.github.hrpayroll.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Worker {

    private Long id;
    private String name;
    private BigDecimal dailyIncome;

}
