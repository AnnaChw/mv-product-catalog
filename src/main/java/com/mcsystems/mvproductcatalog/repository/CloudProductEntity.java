package com.mcsystems.mvproductcatalog.repository;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class CloudProductEntity {
    @Setter
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String latestVersion;


}
