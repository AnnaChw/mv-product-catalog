package com.mcsystems.mvproductcatalog.repository;

import com.mcsystems.mvproductcatalog.api.model.CloudProduct;
import com.mcsystems.mvproductcatalog.api.model.CloudProductSearchParams;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomCloudProductRepositoryImpl implements CustomCloudProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CloudProductEntity> findWithSearchParams(CloudProductSearchParams searchParams) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CloudProductEntity> query = cb.createQuery(CloudProductEntity.class);
        Root<CloudProductEntity> root = query.from(CloudProductEntity.class);
//        Join<CloudProductEntity, ProductVersionEntity> versionEntityJoin = root.join("cloudProduct");
        List<Predicate> predicates = new ArrayList<>();

        if(searchParams.getCategory() != null && !searchParams.getCategory().isEmpty()){
            predicates.add(cb.equal(root.get("category"), searchParams.getCategory()));
        }
        if(searchParams.getName() != null && !searchParams.getName().isEmpty()){
            predicates.add(cb.equal(root.get("name"), searchParams.getName()));
        }
//        if(searchParams.isDefaultVersionOnly()){
//            predicates.add(cb.equal())
//        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(query).getResultList();
    }
}
