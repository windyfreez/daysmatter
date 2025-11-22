package com.itsean.days_matter.pojo;


import lombok.Data;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

//Period请求内部类
@Data
public class DateCalculateRequest {
    @NotNull(message = "开始日期 date1 不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date1;

    @NotNull(message = "结束日期 date2 不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date2;
}
