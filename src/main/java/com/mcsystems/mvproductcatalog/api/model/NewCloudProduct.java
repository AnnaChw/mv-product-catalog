package com.mcsystems.mvproductcatalog.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCloudProduct {
    private String name;
    private String lastVersion;
    private String description;
    private String cloudProductLink;
}
