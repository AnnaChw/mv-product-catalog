package com.mcsystems.mvproductcatalog.repository;

import com.mcsystems.mvproductcatalog.api.model.CloudProduct;
import com.mcsystems.mvproductcatalog.api.model.CloudProductSearchParams;

import java.util.List;

public interface CustomCloudProductRepository {
    List<CloudProductEntity> findWithSearchParams(CloudProductSearchParams searchParams);
}
