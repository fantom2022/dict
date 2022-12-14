package net.vniia.dictionaries.services;

import lombok.extern.log4j.Log4j2;
import net.vniia.common.exception.BusinessLogicException;
import net.vniia.dictionaries.dto.CalendarDto;
import net.vniia.dictionaries.entities.enums.CalendarType;
import net.vniia.dictionaries.readers.SkyReader;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class CalendarService {

    public static final String CALENDAR_CACHE_NAME = "calendar";

    public static final ZoneId DEFAULT_ZONE = ZoneId.of("UTC");

    public static LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(DEFAULT_ZONE).toLocalDate();
    }

    private static Date toDate(LocalDate locaDate) {
        return Date.from(locaDate.atStartOfDay(DEFAULT_ZONE).toInstant());
    }

    @Autowired
    @Qualifier("dictionariesCacheManager")
    CacheManager cacheManager;

    @Autowired
    SkyReader skyReader;

    @Value("${calendar.high-days:#{T(java.util.Collections).emptyList()}}")
    private List<String> highDays;


    public boolean isDayWorkDay(Date date, boolean throwExceptionIfCalendarEnded) {
        return this.isDayWorkDay(toLocalDate(date), throwExceptionIfCalendarEnded);
    }

    public boolean isDayWorkDay(LocalDate date, boolean throwExceptionIfCalendarEnded) {
        List<CalendarDto> calendar = this.prepareCalendar(date, date, throwExceptionIfCalendarEnded);
        List<CalendarDto> days = this.filterCalendarByType(calendar, date, date, CalendarType.WORK_DAY);

        return days.size() > 0;
    }

    public List<CalendarDto> getWorkDaysBetweenDates(Date startDate, Date endDate, boolean throwExceptionIfCalendarEnded) {
        return this.getWorkDaysBetweenDates(toLocalDate(startDate), toLocalDate(endDate), throwExceptionIfCalendarEnded);
    }

    public List<CalendarDto> getWorkDaysBetweenDates(LocalDate startDate, LocalDate endDate, boolean throwExceptionIfCalendarEnded) {
        List<CalendarDto> calendar = this.prepareCalendar(startDate, endDate, throwExceptionIfCalendarEnded);
        return this.filterCalendarByType(calendar, startDate, endDate, CalendarType.WORK_DAY);
    }

    public List<CalendarDto> getWorkDaysAfterDay(Date startDate, int workDays, boolean throwExceptionIfCalendarEnded) {
        return this.getWorkDaysAfterDay(toLocalDate(startDate), workDays, throwExceptionIfCalendarEnded);
    }

    public List<CalendarDto> getWorkDaysAfterDay(LocalDate startDate, int workDays, boolean throwExceptionIfCalendarEnded) {
        List<CalendarDto> result = new ArrayList<>();
        while (result.size() < workDays) {
            List<CalendarDto> calendar = this.getCalendarByYear(startDate.getYear());
            calendar = this.checkCalendar(calendar, startDate.getYear(), throwExceptionIfCalendarEnded);

            LocalDate yaerEnd = toLocalDate(getEndOfTheYear(startDate.getYear()));
            calendar = this.filterCalendarByType(calendar, startDate, yaerEnd, CalendarType.WORK_DAY);
            result.addAll(calendar);
            startDate = toLocalDate(getBeginOfTheYear(startDate.getYear() + 1));
        }
        //удалим лишнее
        if (result.size() > workDays) result = result.subList(0, workDays);
        return result;
    }

    public List<CalendarDto> getWorkDaysBeforeDay(Date startDate, int workDays, boolean throwExceptionIfCalendarEnded) {
        return this.getWorkDaysBeforeDay(toLocalDate(startDate), workDays, throwExceptionIfCalendarEnded);
    }

    public List<CalendarDto> getWorkDaysBeforeDay(LocalDate startDate, int workDays, boolean throwExceptionIfCalendarEnded) {
        List<CalendarDto> result = new ArrayList<>();
        while (result.size() < workDays) {
            List<CalendarDto> calendar = this.getCalendarByYear(startDate.getYear());
            calendar = this.checkCalendar(calendar, startDate.getYear(), throwExceptionIfCalendarEnded);

            LocalDate yearBegin = toLocalDate(getBeginOfTheYear(startDate.getYear()));
            calendar = this.filterCalendarByType(calendar, startDate, yearBegin, CalendarType.WORK_DAY);
            calendar.addAll(result);
            result = calendar;

            startDate = toLocalDate(getEndOfTheYear(startDate.getYear() - 1));
        }
        Collections.reverse(result);
        //удалим лишнее
        if (result.size() > workDays) result = result.subList(0, workDays);
        return result;
    }

    public Integer getWorkDaysDeltaBetweenDates(Date startDate, Date endDate, boolean throwExceptionIfCalendarEnded) {
        return this.getWorkDaysDeltaBetweenDates(toLocalDate(startDate), toLocalDate(endDate), throwExceptionIfCalendarEnded);
    }

    public Integer getWorkDaysDeltaBetweenDates(LocalDate startDate, LocalDate endDate, boolean throwExceptionIfCalendarEnded) {
        int delta;
        if (startDate.isAfter(endDate)) {
            delta = -1 * this.getWorkDaysBetweenDates(endDate, startDate, throwExceptionIfCalendarEnded)
                    .size();
        } else {
            delta = this.getWorkDaysBetweenDates(startDate, endDate, throwExceptionIfCalendarEnded)
                    .size();
        }
        return delta;
    }

    private List<CalendarDto> filterCalendarByType(List<CalendarDto> calendar, LocalDate startDate, LocalDate endDate, CalendarType type) {
        // log.warn("filterCalendarByType" + Integer.signum(startDate.compareTo(endDate)));
        switch (Integer.signum(startDate.compareTo(endDate))) {
            case 0:
                return calendar.stream().filter(c -> c.getType().equals(type) &&
                        toLocalDate(c.getDate()).compareTo(startDate) == 0
                ).collect(Collectors.toList());
            case -1:
                return calendar.stream().filter(c -> c.getType().equals(type) &&
                        toLocalDate(c.getDate()).compareTo(startDate) >= 0 &&
                        toLocalDate(c.getDate()).compareTo(endDate) <= 0
                ).collect(Collectors.toList());
            case 1:
                return calendar.stream().filter(c -> c.getType().equals(type) &&
                        toLocalDate(c.getDate()).compareTo(endDate) >= 0 &&
                        toLocalDate(c.getDate()).compareTo(startDate) <= 0
                ).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    private List<CalendarDto> prepareCalendar(LocalDate startDate, LocalDate endDate, boolean throwExceptionIfCalendarEnded) {
        if (startDate.compareTo(endDate) > 0)
            throw new BusinessLogicException("Дата начала не может быть позже даты конца");

        List<CalendarDto> result = new ArrayList<>();

        int startYear = startDate.getYear();
        int endYear = endDate.getYear();
        for (int year = startYear; year < endYear + 1; year++) {
            List<CalendarDto> calendar = this.getCalendarByYear(year);
            calendar = this.checkCalendar(calendar, year, throwExceptionIfCalendarEnded);

            result.addAll(calendar);
        }
        return result;
    }

    private List<CalendarDto> checkCalendar(List<CalendarDto> calendar, int year, boolean throwExceptionIfCalendarEnded) {
        //Если календарь кончился, и не просят ошибку, генерируем свой
        if (calendar.size() == 0) {
            if (throwExceptionIfCalendarEnded) {
                throwNoCalendarDataException(year);
            } else {
                Objects.requireNonNull(cacheManager.getCache(CALENDAR_CACHE_NAME)).evict(String.valueOf(year));
                return this.generateCalendarByYear(year);
            }
        }

        return calendar;
    }

    private List<CalendarDto> getCalendarByYear(int year) {
        return Objects.requireNonNull(cacheManager.getCache(CALENDAR_CACHE_NAME))
                .get(String.valueOf(year), () -> this.readCalendarYear(year));
    }

    private List<CalendarDto> generateCalendarByYear(int year) {
        return Objects.requireNonNull(cacheManager.getCache(CALENDAR_CACHE_NAME))
                .get(String.valueOf(year), () -> this.generateCalendarYear(year));
    }

    private List<CalendarDto> readCalendarYear(int year) {
        Date startDate = getBeginOfTheYear(year);
        Date endDate = getEndOfTheYear(year);

        return this.skyReader.getDaysBetweenDates(startDate, endDate);
    }

    private List<CalendarDto> generateCalendarYear(int year) {
        List<CalendarDto> result = new ArrayList<>();
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0);
        while (calendar.get(Calendar.YEAR) == year) {
            result.add(new CalendarDto(getDateByCalendar(calendar), this.getCalendarTypeByCalendar(calendar)));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    private CalendarType getCalendarTypeByCalendar(Calendar calendar) {
        String day = toFixed(calendar.get(Calendar.DAY_OF_MONTH));
        String month = toFixed(calendar.get(Calendar.MONTH) + 1);
        if (this.highDays.contains(day + "." + month)) {
            return CalendarType.HIGH_DAY;
        } else {
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SATURDAY:
                case Calendar.SUNDAY:
                    return CalendarType.OFF_DAY;
                default:
                    return CalendarType.WORK_DAY;
            }
        }
    }

    private static String toFixed(int value) {
        return value < 10 ? ("0" + value) : String.valueOf(value);
    }

    private static Date getDateByCalendar(Calendar calendar) {
        return new DateTime(
                calendar.get(Calendar.YEAR) +
                        "-" + toFixed(calendar.get(Calendar.MONTH) + 1) +
                        "-" + toFixed(calendar.get(Calendar.DAY_OF_MONTH)) +
                        "T00:00:00", DateTimeZone.UTC
        ).toDate();
    }

    private static Date getBeginOfTheYear(int year) {
        //менять осторожно, потом проверить год
        return new DateTime(year + "-01-01T00:00:00", DateTimeZone.UTC).toDate();
    }

    private static Date getEndOfTheYear(int year) {
        //менять осторожно, потом проверить год
        return new DateTime(year + "-12-31T00:00:00", DateTimeZone.UTC).toDate();
    }

    private static void throwNoCalendarDataException(int year) {
        throw new BusinessLogicException("Календарь не заполнен на % год".replace("%", String.valueOf(year)));
    }

}
