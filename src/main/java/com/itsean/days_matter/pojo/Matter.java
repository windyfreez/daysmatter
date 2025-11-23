package com.itsean.days_matter.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Matter {

    private LocalDate date;

    private String name;

    private boolean isBefore;

    private Repeat repeat;

}
