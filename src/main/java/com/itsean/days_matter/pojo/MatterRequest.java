package com.itsean.days_matter.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MatterRequest {

    @NotNull(message = "目标日期 date 不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "事件名称 name 不能为空")
    private String name;

    @NotNull(message = "是否重复标识 repeat 不能为空")
    private Repeat repeat;
}
