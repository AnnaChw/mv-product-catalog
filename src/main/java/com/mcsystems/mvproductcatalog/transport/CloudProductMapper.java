package com.mcsystems.mvproductcatalog.transport;

import com.mcsystems.mvproductcatalog.api.model.CloudProduct;
import com.mcsystems.mvproductcatalog.api.model.NewCloudProduct;
import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;
import com.mcsystems.mvproductcatalog.repository.ProductVersionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CloudProductMapper {

    private ProductVersionMapper productVersionMapper ;

    public CloudProduct mapToCloudProduct(CloudProductEntity entity){
        return CloudProduct.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .cloudProductLink(entity.getCloudProductLink())
                .lastVersion(entity.getLatestVersion())
                .category(entity.getCategory())
                .productVersions(entity.getProductVersions().stream()
                        .map(version -> productVersionMapper.mapToProductVersion(version))
                        .collect(Collectors.toList()))
                .build();
    }

    public CloudProductEntity mapToCloudProductEntity(NewCloudProduct cloudProduct){
        return CloudProductEntity.builder()
                .name(cloudProduct.getName())
                .description(cloudProduct.getDescription())
                .latestVersion(cloudProduct.getLastVersion())
                .cloudProductLink(cloudProduct.getCloudProductLink())
                .category(cloudProduct.getCategory())
                .productVersions(new HashSet<>())
                .build();
    }


}
