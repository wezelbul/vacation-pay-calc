package ru.neoflex.neostudy.vacationpaycalc.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CalculatorServiceTest {

    private final CalculatorService calculatorService;

    @Test
    void calculateWithoutHolidaysTest() {
        double avgPay = 29300.0;
        int days = 14;
        double avgDaysInMonth = 29.3;
        Assertions.assertEquals(calculatorService.calculate(avgPay, days),
                (avgPay / avgDaysInMonth * days), "Расчёты неверны");
    }

    @Test
    void calculateWithHolidaysTest() {
        double avgPay = 29300.0;
        LocalDate date = LocalDate.of(2022, 5, 1);
        int days = 14;
        double avgDaysInMonth = 29.3;
        Assertions.assertEquals(calculatorService.calculate(avgPay, date, days),
                (avgPay / avgDaysInMonth * (days - 2)), "Расчёты неверны");
    }

}
