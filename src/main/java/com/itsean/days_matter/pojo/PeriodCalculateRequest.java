package com.itsean.days_matter.pojo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PeriodCalculateRequest {
    @NotNull(message = "基准日期 date 不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "增减标识 isPlus 不能为空")
    private boolean isPlus;

    @NotNull(message = "增减天数 periodDay 不能为空")
    @Min(value = 1, message = "增减天数不能小于 1") // 禁止负数
    @Max(value = 10000, message = "增减天数不能超过 10000") // 限制最大范围
    private long periodDay;
}
