package com.mcsystems.mvproductcatalog.repository;

import com.mcsystems.mvproductcatalog.api.model.NewProductVersion;
import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.api.model.UpdateCloudProduct;
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
    private String category;
    private String description;
    private String cloudProductLink;
    @OneToMany(mappedBy = "cloudProduct", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<ProductVersionEntity> productVersions = new HashSet<>();


    public void removeAllVersions(){
        this.productVersions = new HashSet<>();
    }

    public CloudProductEntity updateCloudProduct(UpdateCloudProduct updateCloudProduct){
        this.name = updateCloudProduct.getName();
        this.latestVersion = updateCloudProduct.getLastVersion();
        this.cloudProductLink = updateCloudProduct.getCloudProductLink();
        this.category = updateCloudProduct.getCategory();
        this.description = updateCloudProduct.getDescription();
        return this;
    }

    public void addNewVersion(NewProductVersion productVersion){
        if(productVersions == null){
            productVersions = new HashSet<>();
        }
        productVersions.add(ProductVersionEntity.builder()
                .name(productVersion.getName())
                .amiID(productVersion.getAmiID())
                .instructionLink(productVersion.getInstructionLink())
                .jobPlanLink(productVersion.getJobPlanLink())
                .cloudProduct(this)
                .build());

    }


}
