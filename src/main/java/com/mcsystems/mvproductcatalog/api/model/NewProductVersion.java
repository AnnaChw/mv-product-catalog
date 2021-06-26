package com.mcsystems.mvproductcatalog.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewProductVersion {
    private String name;
    private String amiID;
    private String category;
    private String instructionLink;
    private String jobPlanLink;
    private Long cloudProductId;
}
