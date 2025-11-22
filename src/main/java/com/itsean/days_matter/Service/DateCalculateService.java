package com.itsean.days_matter.Service;

import com.itsean.days_matter.pojo.DateCalculateRequest;
import com.itsean.days_matter.pojo.PeriodOut;

/**
 * 日期计算器1服务接口
 */
public interface DateCalculateService {

    /**
     * 计算两个日期的间隔（年月日 + 周+天）
     * @param request 前端请求参数（date1 + date2）
     * @return 计算结果（PeriodOut）
     */
    PeriodOut dateCalculate(DateCalculateRequest request);
}
