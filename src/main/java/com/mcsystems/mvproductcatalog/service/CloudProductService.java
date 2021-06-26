package com.mcsystems.mvproductcatalog.service;

import com.mcsystems.mvproductcatalog.api.model.CloudProduct;
import com.mcsystems.mvproductcatalog.api.model.NewCloudProduct;
import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.repository.CloudProductRepository;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;
import com.mcsystems.mvproductcatalog.repository.ProductVersionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CloudProductService {

    private CloudProductRepository cloudProductRepository;

    public List<CloudProduct> getAllCloudProducts(){
        return cloudProductRepository.findAll()
                .stream()
                .map(this::mapToCloudProduct)
                .collect(Collectors.toList());
    }

    public CloudProduct getCloudProduct(Long id){
        return cloudProductRepository.findById(id)
                .map(this::mapToCloudProduct)
                .orElseThrow(() -> new IllegalStateException("CloudProduct doesn't exist"));
    }

    public void addNewCloudProduct(NewCloudProduct cloudProduct){
        CloudProductEntity entity = CloudProductEntity.builder()
                .name(cloudProduct.getName())
                .description(cloudProduct.getDescription())
                .latestVersion(cloudProduct.getLastVersion())
                .cloudProductLink(cloudProduct.getCloudProductLink())
                .productVersions(new HashSet<>())
                .build();
        cloudProductRepository.save(entity);
    }

    private CloudProduct mapToCloudProduct(CloudProductEntity entity){
        return CloudProduct.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .cloudProductLink(entity.getCloudProductLink())
                .lastVersion(entity.getLatestVersion())
                .productVersions(entity.getProductVersions().stream()
                    .map(version -> new ProductVersion(version.getId(), version.getName(), version.getAmiID(), version.getCategory(), version.getInstructionLink(), version.getJobPlanLink()))
                    .collect(Collectors.toList()))
                .build();
    }

    public ProductVersion mapToProductVersion(ProductVersionEntity productVersionEntity){
        return ProductVersion.builder()
                .name(productVersionEntity.getName())
                .amiID(productVersionEntity.getAmiID())
                .category(productVersionEntity.getCategory())
                .instructionLink(productVersionEntity.getInstructionLink())
                .jobPlanLink(productVersionEntity.getJobPlanLink())
                .build();
    }
}
