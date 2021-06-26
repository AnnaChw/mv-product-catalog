package com.mcsystems.mvproductcatalog.api.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVersion {
    private Long id;
    private String name;
    private String amiID;
    private String category;
    private String instructionLink;
    private String jobPlanLink;
    private boolean defaultVersion;
    private LocalDate date;
}
