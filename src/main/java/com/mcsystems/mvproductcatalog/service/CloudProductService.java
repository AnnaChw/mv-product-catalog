package com.mcsystems.mvproductcatalog.service;

import com.mcsystems.mvproductcatalog.api.model.CloudProduct;
import com.mcsystems.mvproductcatalog.repository.CloudProductRepository;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void addNewCloudProduct(CloudProduct cloudProduct){
        CloudProductEntity entity = CloudProductEntity.builder()
                .name(cloudProduct.getName())
                .description(cloudProduct.getDescription())
                .latestVersion(cloudProduct.getLastVersion())
                .cloudProductLink(cloudProduct.getCloudProductLink())
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
                .build();
    }
}
