package com.itsean.days_matter.Service;

import com.itsean.days_matter.pojo.DateCalculateRequest;
import com.itsean.days_matter.pojo.PeriodOut;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Service
public class DateCalculateServiceImpl implements DateCalculateService {

    @Override
    public PeriodOut dateCalculate(DateCalculateRequest request) {
        //获取请求参数
        LocalDate date1=request.getDate1();
        LocalDate date2=request.getDate2();

        //准备计算开始和结束日期的间隔天数
        LocalDate beginDay = date1.isBefore(date2) ? date1 : date2;
        LocalDate endDay = date1.isBefore(date2) ? date2 : date1;
        Period period = Period.between(beginDay, endDay);
        long totalDays = Math.abs(ChronoUnit.DAYS.between(date1, date2));

        //封装响应结果
        PeriodOut periodOut = new PeriodOut();
        periodOut.setWeekPeriod_week((int) (totalDays / 7));
        periodOut.setWeekPeriod_day((int) (totalDays % 7));
        periodOut.setYearMonthDayPeriod_year(period.getYears());
        periodOut.setYearMonthDayPeriod_month(period.getMonths());
        periodOut.setYearMonthDayPeriod_day(period.getDays());
        return periodOut;
    }
}