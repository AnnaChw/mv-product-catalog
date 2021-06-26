package com.mcsystems.mvproductcatalog.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "productVersions")
public class ProductVersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String amiID;
    private String instructionLink;
    private String jobPlanLink;
    private LocalDate date;
    private boolean defaultVersion;
    @ManyToOne
    private CloudProductEntity cloudProduct;
}
