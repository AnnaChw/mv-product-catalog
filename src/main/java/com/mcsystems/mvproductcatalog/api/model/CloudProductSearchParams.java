package com.mcsystems.mvproductcatalog.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CloudProductSearchParams {
    private String name;
    private String category;
    private boolean defaultVersionOnly;

}
