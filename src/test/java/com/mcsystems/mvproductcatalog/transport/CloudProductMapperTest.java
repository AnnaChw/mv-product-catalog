package com.mcsystems.mvproductcatalog.transport;

import com.mcsystems.mvproductcatalog.api.model.CloudProduct;
import com.mcsystems.mvproductcatalog.api.model.NewCloudProduct;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;
import com.mcsystems.mvproductcatalog.repository.ProductVersionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;

public class CloudProductMapperTest {

    ProductVersionMapper productVersionMapper = new ProductVersionMapper();
    CloudProductMapper cloudProductMapper = new CloudProductMapper(productVersionMapper);

    @Test
    public void shouldMapAllValuesFromEntityObjectToTransportObject(){
        //given
        CloudProductEntity cloudProductEntity = CloudProductEntity.builder()
                .id(1L)
                .name("WASBASEINITIAL")
                .category("AWS Amazon")
                .cloudProductLink("oldLink")
                .description("oldDescription")
                .latestVersion("9055")
                .productVersions(new HashSet<>(Arrays.asList(ProductVersionEntity.builder().name("1").amiID("ami-1").build())))
                .build();
        //when
        CloudProduct cloudProduct = cloudProductMapper.mapToCloudProduct(cloudProductEntity);
        //then
        Assertions.assertEquals(cloudProduct.getId(), cloudProductEntity.getId());
        Assertions.assertEquals(cloudProduct.getName(), cloudProductEntity.getName());
        Assertions.assertEquals(cloudProduct.getCategory(), cloudProductEntity.getCategory());
        Assertions.assertEquals(cloudProduct.getCloudProductLink(), cloudProductEntity.getCloudProductLink());
        Assertions.assertEquals(cloudProduct.getDescription(), cloudProductEntity.getDescription());
        Assertions.assertEquals(cloudProduct.getLastVersion(), cloudProductEntity.getLatestVersion());
        Assertions.assertEquals(cloudProduct.getProductVersions().size(), cloudProductEntity.getProductVersions().size());
    }

    @Test
    public void shouldMapAllValuesFromModelToEntity(){
        //given
        NewCloudProduct newCloudProduct = NewCloudProduct.builder()
                .name("WAS")
                .cloudProductLink("cloudProductLink")
                .description("description")
                .category("category")
                .lastVersion("lastVersion")
                .build();
        //when
        CloudProductEntity entity = cloudProductMapper.mapToCloudProductEntity(newCloudProduct);
        //thebn
        Assertions.assertEquals(entity.getName(), newCloudProduct.getName());
        Assertions.assertEquals(entity.getCloudProductLink(), newCloudProduct.getCloudProductLink());
        Assertions.assertEquals(entity.getDescription(), newCloudProduct.getDescription());
        Assertions.assertEquals(entity.getCategory(), newCloudProduct.getCategory());
        Assertions.assertEquals(entity.getLatestVersion(), newCloudProduct.getLastVersion());
        Assertions.assertEquals(0, entity.getProductVersions().size());
    }


}
