package com.mcsystems.mvproductcatalog.api.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloudProduct {
    private Long id;
    private String name;
    private String lastVersion;
}
