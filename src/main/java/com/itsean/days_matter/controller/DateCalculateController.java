package com.itsean.days_matter.controller;

import com.itsean.days_matter.pojo.DateCalculateRequest;
import com.itsean.days_matter.pojo.PeriodOut;
import com.itsean.days_matter.Service.DateCalculateService;
import com.itsean.days_matter.pojo.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * 日期计算控制层（仅接收请求、调用 Service，无业务逻辑）
 */
@RestController
@RequestMapping("/date")
public class DateCalculateController {


    //面向接口编程，利用多态创建私有化变量，调用Service接口
    @Autowired
    private DateCalculateService dateCalculateService;
    /**
     * 日期间隔计算接口（POST + JSON 传参）
     * @param request 前端请求参数（通过 @RequestBody 接收）
     * @return 计算结果
     */

    @PostMapping("/calculatePeriod")
    public Result<PeriodOut> calculateDate(@Valid @RequestBody DateCalculateRequest request) {
        PeriodOut result = dateCalculateService.dateCalculate(request);
        return Result.success(result);
    }
}