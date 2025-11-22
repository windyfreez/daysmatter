package com.itsean.days_matter.Service;

import com.itsean.days_matter.pojo.PeriodCalculateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PeriodCalculateServiceImpl implements PeriodCalculateService{

    @Override
    public LocalDate periodCalculate(PeriodCalculateRequest request) {
        //接受请求内部类传来的参数
        LocalDate date=request.getDate();
        long periodDay=request.getPeriodDay();
        boolean isPlus= request.isPlus();

        //计算目标日期
        if(isPlus==true){
            date.plusDays(periodDay);
        }else{
            date.minusDays(periodDay);
        }
        return date;
    }
}
