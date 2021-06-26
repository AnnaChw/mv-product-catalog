package com.mcsystems.mvproductcatalog.service;

import com.mcsystems.mvproductcatalog.api.model.NewCloudProduct;
import com.mcsystems.mvproductcatalog.api.model.UpdateCloudProduct;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;
import com.mcsystems.mvproductcatalog.repository.CloudProductRepository;
import com.mcsystems.mvproductcatalog.transport.CloudProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class CloudProductServiceTest {

    private CloudProductRepository cloudProductRepository = Mockito.mock(CloudProductRepository.class);
    private CloudProductMapper cloudProductMapper = Mockito.mock(CloudProductMapper.class);
    private CloudProductService cloudProductService = new CloudProductService(cloudProductRepository, cloudProductMapper);

    @Test
    public void shouldNotUpdateCloudProductIfProductIsNotPresent(){
        //given
        UpdateCloudProduct updateCloudProduct = UpdateCloudProduct.builder()
                .name("WAS BASE")
                .category("AWS")
                .cloudProductLink("updatedLink")
                .lastVersion("9056")
                .description("newDescription")
                .id(1L)
                .build();
        CloudProductEntity cloudProductEntity = null;
        Mockito.when(cloudProductRepository.findById(1L)).thenReturn(Optional.ofNullable(cloudProductEntity));
        //when
        Assertions.assertThrows(IllegalStateException.class, () -> cloudProductService.updateCloudProduct(updateCloudProduct));

    }

    @Test
    public void shouldThroWExceptionWhenProductIsNotPresent(){
        //given
        Mockito.when(cloudProductRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        //when
        Assertions.assertThrows(IllegalStateException.class, () -> cloudProductService.getCloudProduct(1L));

    }
}
