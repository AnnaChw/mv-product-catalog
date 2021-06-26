package com.mcsystems.mvproductcatalog.repository;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "cloudProduct")
public class CloudProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String latestVersion;
    private String description;
    private String cloudProductLink;



}
