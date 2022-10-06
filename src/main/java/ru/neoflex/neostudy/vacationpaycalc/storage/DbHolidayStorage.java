package ru.neoflex.neostudy.vacationpaycalc.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.neoflex.neostudy.vacationpaycalc.model.Day;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DbHolidayStorage implements HolidayStorage {

    private final JdbcTemplate holidays;

    public DbHolidayStorage(JdbcTemplate holidays) {
        this.holidays = holidays;
    }


    @Override
    public List<Day> getHolidays() {
        return holidays.query("SELECT month_num, day_num FROM holidays;", this::mapRowToHolidays);
    }

    private Day mapRowToHolidays(ResultSet rs, int rowNum) throws SQLException {
        return new Day(rs.getInt("month_num"), rs.getInt("day_num"));
    }
}
