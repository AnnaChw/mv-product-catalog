package com.mcsystems.mvproductcatalog.transport;

import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;
import com.mcsystems.mvproductcatalog.repository.ProductVersionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductVersionMapperTest {
    ProductVersionMapper productVersionMapper = new ProductVersionMapper();

    @Test
    public void shouldMapAllValuesFromEntityObjectToTransportObject(){
        //given
        ProductVersionEntity productVersionEntity = ProductVersionEntity.builder()
                .id(1L)
                .name("9056")
                .amiID("ami-id")
                .instructionLink("instructionLink")
                .jobPlanLink("jobPlankLink")
                .cloudProduct(CloudProductEntity.builder().id(6L).build()) //is it necessary?
                .build();
        //when
        ProductVersion productVersion = productVersionMapper.mapToProductVersion(productVersionEntity);
        //then
        Assertions.assertEquals(productVersionEntity.getId(), productVersion.getId());
        Assertions.assertEquals(productVersionEntity.getName(), productVersion.getName());
        Assertions.assertEquals(productVersionEntity.getAmiID(), productVersion.getAmiID());
        Assertions.assertEquals(productVersionEntity.getInstructionLink(), productVersion.getInstructionLink());
        Assertions.assertEquals(productVersionEntity.getJobPlanLink(), productVersion.getJobPlanLink());

    }

}
