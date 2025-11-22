package com.itsean.days_matter.Service;

import com.itsean.days_matter.pojo.DateCalculateRequest;
import com.itsean.days_matter.pojo.PeriodCalculateRequest;
import com.itsean.days_matter.pojo.PeriodOut;

import java.time.LocalDate;

/**
 * 日期计算器2服务接口
 */
public interface PeriodCalculateService {

    /**
     * 计算一个日期后/前若干天的日期
     * @param request 前端请求参数（date + isPlus+periodDay）
     * @return 计算结果（PeriodOut）
     */
    LocalDate periodCalculate(PeriodCalculateRequest request);
}
