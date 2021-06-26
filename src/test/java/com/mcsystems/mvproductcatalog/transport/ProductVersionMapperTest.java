package com.mcsystems.mvproductcatalog.transport;

import com.mcsystems.mvproductcatalog.api.model.ProductVersion;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;
import com.mcsystems.mvproductcatalog.repository.ProductVersionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ProductVersionMapperTest {
    ProductVersionMapper productVersionMapper = new ProductVersionMapper();

    @Test
    public void shouldMapAllValuesFromEntityObjectToTransportObject(){
        //given
        LocalDate currentTime = LocalDate.now();
        ProductVersionEntity productVersionEntity = ProductVersionEntity.builder()
                .id(1L)
                .name("9056")
                .amiID("ami-id")
                .instructionLink("instructionLink")
                .jobPlanLink("jobPlankLink")
                .defaultVersion(true)
                .date(currentTime)
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
        Assertions.assertEquals(productVersionEntity.getDate(), productVersion.getDate());
        Assertions.assertEquals(productVersionEntity.isDefaultVersion(), productVersion.isDefaultVersion());
    }

}
