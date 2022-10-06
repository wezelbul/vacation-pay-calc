package ru.neoflex.neostudy.vacationpaycalc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.neostudy.vacationpaycalc.service.CalculatorService;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class CalculatorController {

    private final CalculatorService calculatorService;

    CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(params = {"avgPay", "days"})
    public ResponseEntity<Double> calculate(@Valid @RequestParam(value = "avgPay") Double avgPay,
                                             @Valid @RequestParam(value = "days") Integer days) {
        return ResponseEntity.ok(calculatorService.calculate(avgPay, days));
    }

    @GetMapping(params = {"avgPay", "firstDate", "days"})
    public ResponseEntity<Double> calculate(@Valid @RequestParam(value = "avgPay") Double avgPay,
                                            @Valid @RequestParam(value = "firstDate")
                                            @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate firstDate,
                                            @Valid @RequestParam(value = "days") Integer days) {
        return ResponseEntity.ok(calculatorService.calculate(avgPay, firstDate, days));
    }
}
