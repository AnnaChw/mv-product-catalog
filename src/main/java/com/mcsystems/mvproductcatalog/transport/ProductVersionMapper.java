package com.mcsystems.mvproductcatalog.transport;

import com.mcsystems.mvproductcatalog.api.model.NewProductVersion;
import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.repository.ProductVersionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductVersionMapper {

    public ProductVersion mapToProductVersion(ProductVersionEntity productVersionEntity){
        return ProductVersion.builder()
                .id(productVersionEntity.getId())
                .name(productVersionEntity.getName())
                .amiID(productVersionEntity.getAmiID())
                .instructionLink(productVersionEntity.getInstructionLink())
                .jobPlanLink(productVersionEntity.getJobPlanLink())
                .date(productVersionEntity.getDate())
                .defaultVersion(productVersionEntity.isDefaultVersion())
                .build();
    }

    public ProductVersionEntity mapToProductVersionEntity(NewProductVersion productVersion){
        return ProductVersionEntity.builder()
                .name(productVersion.getName())
                .amiID(productVersion.getAmiID())
                .instructionLink(productVersion.getInstructionLink())
                .jobPlanLink(productVersion.getJobPlanLink())
                .defaultVersion(productVersion.isDefaultVersion())
                .date(productVersion.getDate())
                .build();
    }
}
