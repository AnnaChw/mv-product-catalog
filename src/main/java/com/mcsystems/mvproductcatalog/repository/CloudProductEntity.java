package com.mcsystems.mvproductcatalog.repository;

import com.mcsystems.mvproductcatalog.api.model.NewProductVersion;
import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "cloudProducts")
public class CloudProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String latestVersion;
    private String description;
    private String cloudProductLink;
    @OneToMany(mappedBy = "cloudProduct", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<ProductVersionEntity> productVersions = new HashSet<>();


    public void removeAllVersions(){
        this.productVersions = new HashSet<>();
    }

    public void addNewVersion(NewProductVersion productVersion){
        productVersions.add(ProductVersionEntity.builder()
                .name(productVersion.getName())
                .amiID(productVersion.getAmiID())
                .category(productVersion.getCategory())
                .instructionLink(productVersion.getInstructionLink())
                .jobPlanLink(productVersion.getJobPlanLink())
                .cloudProduct(this)
                .build());

    }


}
