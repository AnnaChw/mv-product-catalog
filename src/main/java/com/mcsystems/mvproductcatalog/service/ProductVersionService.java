package com.mcsystems.mvproductcatalog.service;

import com.mcsystems.mvproductcatalog.api.ProductVersionEndpoint;
import com.mcsystems.mvproductcatalog.api.model.NewProductVersion;
import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.repository.CloudProductRepository;
import com.mcsystems.mvproductcatalog.repository.ProductVersionEntity;
import com.mcsystems.mvproductcatalog.repository.ProductVersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductVersionService {

    private ProductVersionRepository productVersionRepository;
    private CloudProductRepository cloudProductRepository;

    @Transactional
    public Set<ProductVersion> addNewProductVersion(NewProductVersion productVersion){
        if(productVersion == null){
            return new HashSet<>();
        }
        return cloudProductRepository.findById(productVersion.getCloudProductId()).map(cloudProduct -> {
            cloudProduct.addNewVersion(productVersion);
            return cloudProduct.getProductVersions().stream()
                    .map(this::mapToProductVersion)
                    .collect(Collectors.toSet());
        }).orElseGet(HashSet::new);
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
