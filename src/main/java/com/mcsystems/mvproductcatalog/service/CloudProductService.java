package com.mcsystems.mvproductcatalog.service;

import com.mcsystems.mvproductcatalog.api.model.CloudProduct;
import com.mcsystems.mvproductcatalog.api.model.NewCloudProduct;
import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.api.model.UpdateCloudProduct;
import com.mcsystems.mvproductcatalog.repository.CloudProductRepository;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;
import com.mcsystems.mvproductcatalog.repository.ProductVersionEntity;
import com.mcsystems.mvproductcatalog.transport.CloudProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CloudProductService {

    private CloudProductRepository cloudProductRepository;
    private CloudProductMapper cloudProductMapper;

    public List<CloudProduct> getAllCloudProducts(){
        return cloudProductRepository.findAll()
                .stream()
                .map(entity -> cloudProductMapper.mapToCloudProduct(entity))
                .collect(Collectors.toList());
    }
    @Transactional
    public void updateCloudProduct(UpdateCloudProduct updateCloudProduct){
        cloudProductRepository.findById(updateCloudProduct.getId())
                .map(prod -> prod.updateCloudProduct(updateCloudProduct))
                .orElseThrow(() -> new IllegalStateException("Cloud Product doesn't exist"));
    }

    public CloudProduct getCloudProduct(Long id){
        return cloudProductRepository.findById(id)
                .map(entity -> cloudProductMapper.mapToCloudProduct(entity))
                .orElseThrow(() -> new IllegalStateException("CloudProduct doesn't exist"));
    }

    public void addNewCloudProduct(NewCloudProduct cloudProduct){
        CloudProductEntity entity = cloudProductMapper.mapToCloudProductEntity(cloudProduct);
        cloudProductRepository.save(entity);
    }
}
