package com.mcsystems.mvproductcatalog.api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudProduct {
    private Long id;
    private String name;
    private String lastVersion;
    private String description;
    private String cloudProductLink;
    private String category;
    private List<ProductVersion> productVersions;
}
