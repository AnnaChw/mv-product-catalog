package com.mcsystems.mvproductcatalog.api.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewProductVersion {
    private String name;
    private String amiID;
    private String instructionLink;
    private String jobPlanLink;
    private Long cloudProductId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean defaultVersion;
}
