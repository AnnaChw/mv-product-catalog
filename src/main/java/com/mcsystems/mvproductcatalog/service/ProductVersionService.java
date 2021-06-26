package com.mcsystems.mvproductcatalog.service;

import com.mcsystems.mvproductcatalog.api.ProductVersionEndpoint;
import com.mcsystems.mvproductcatalog.api.model.NewProductVersion;
import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.repository.CloudProductRepository;
import com.mcsystems.mvproductcatalog.repository.ProductVersionEntity;
import com.mcsystems.mvproductcatalog.repository.ProductVersionRepository;
import com.mcsystems.mvproductcatalog.transport.CloudProductMapper;
import com.mcsystems.mvproductcatalog.transport.ProductVersionMapper;
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
    private ProductVersionMapper productVersionMapper;

    @Transactional
    public Set<ProductVersion> addNewProductVersion(NewProductVersion productVersion){
        if(productVersion == null){
            return new HashSet<>();
        }
        return cloudProductRepository.findById(productVersion.getCloudProductId()).map(cloudProduct -> {
            cloudProduct.addNewVersion(productVersion);
            return cloudProduct.getProductVersions().stream()
                    .map(entity -> productVersionMapper.mapToProductVersion(entity))
                    .collect(Collectors.toSet());
        }).orElseGet(HashSet::new);
    }


}
