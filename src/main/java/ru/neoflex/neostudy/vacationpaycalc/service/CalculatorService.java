package ru.neoflex.neostudy.vacationpaycalc.service;

import org.springframework.stereotype.Service;
import ru.neoflex.neostudy.vacationpaycalc.model.Day;
import ru.neoflex.neostudy.vacationpaycalc.storage.DbHolidayStorage;
import ru.neoflex.neostudy.vacationpaycalc.storage.HolidayStorage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class CalculatorService {

    private final double avgDaysInMonth = 29.3;
    private final HolidayStorage holidayStorage;

    public CalculatorService(DbHolidayStorage holidayStorage) {
        this.holidayStorage = holidayStorage;
    }

    public Double calculate(Double avgPay, Integer days) {
        return BigDecimal.valueOf(avgPay / avgDaysInMonth * days)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public Double calculate(Double avgPay, LocalDate firstDate, Integer days) {
        days = daysWithoutHolidays(firstDate, days);
        return calculate(avgPay, days);
    }

    private Integer daysWithoutHolidays(LocalDate firstDate, Integer days) {
        Integer result = days;
        List<Day> holidays = holidayStorage.getHolidays();
        for (int i = 0; i < days; i++) {
            Day vacationDay = new Day(firstDate.getMonthValue(), firstDate.getDayOfMonth());
            if (holidays.contains(vacationDay)) {
                --result;
            }
            firstDate = firstDate.plusDays(1);
        }
        return result;
    }

}
