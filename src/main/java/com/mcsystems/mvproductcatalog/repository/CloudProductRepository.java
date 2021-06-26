package com.mcsystems.mvproductcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mcsystems.mvproductcatalog.repository.CloudProductEntity;

public interface CloudProductRepository extends JpaRepository<CloudProductEntity, Long> {
}
