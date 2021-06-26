package com.mcsystems.mvproductcatalog.service;

import com.mcsystems.mvproductcatalog.api.model.NewProductVersion;
import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;
import com.mcsystems.mvproductcatalog.repository.CloudProductRepository;
import com.mcsystems.mvproductcatalog.repository.ProductVersionRepository;
import com.mcsystems.mvproductcatalog.transport.ProductVersionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

public class ProductVersionServiceTest {

    private ProductVersionRepository productVersionRepository = Mockito.mock(ProductVersionRepository.class);
    private CloudProductRepository cloudProductRepository = Mockito.mock(CloudProductRepository.class);
    private ProductVersionMapper productVersionMapper = Mockito.mock(ProductVersionMapper.class);
    private ProductVersionService productVersionService = new ProductVersionService(productVersionRepository, cloudProductRepository, productVersionMapper);

    @Test
    public void shouldNotAddVersionWhenProductIsNotPresent(){
        //given
        NewProductVersion newProductVersion = NewProductVersion.builder().cloudProductId(6L).build();
        Mockito.when(cloudProductRepository.findById(6L)).thenReturn(Optional.ofNullable(null));
        //when
        Set<ProductVersion> allProductVersions = productVersionService.addNewProductVersion(newProductVersion);
        //then
        Assertions.assertEquals(0, allProductVersions.size());

    }

    @Test
    public void shouldReturnEmptySetWhenObjectToBeAddedIsNull(){
        //given
        NewProductVersion nullProductVersion = null;
        //when
        Set<ProductVersion> allProductVersions = productVersionService.addNewProductVersion(nullProductVersion);
        //then
        Assertions.assertEquals(0, allProductVersions.size());
    }

    @Test
    public void shouldAddNewVersionIfProductIsPresent(){
        //given
        NewProductVersion newProductVersion = NewProductVersion.builder()
                .cloudProductId(6L)
                .name("latest")
                .amiID("ami-id")
                .build();
        CloudProductEntity entity = CloudProductEntity.builder()
                .id(6L)
                .name("WAS")
                .build();
        Mockito.when(cloudProductRepository.findById(6L)).thenReturn(Optional.of(entity));
        //when
        Set<ProductVersion> allProductVersions = productVersionService.addNewProductVersion(newProductVersion);
        //then
        Assertions.assertEquals(1, allProductVersions.size());
    }

}
