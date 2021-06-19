package com.mcsystems.mvproductcatalog.api;

import com.mcsystems.mvproductcatalog.api.model.CloudProduct;
import com.mcsystems.mvproductcatalog.hibernateUtils.HibernateUtils;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductEndpoint {

    @GetMapping
    public List<CloudProduct> getAll(){

        final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        HibernateUtils.shutdownSessionFactory();

        //initial test values just to make sure postman is able to request it
        return Arrays.asList(CloudProduct.builder().id(1L).name("WebSphere").lastVersion("1.0").build(),
                CloudProduct.builder().id(5L).name("MQ").lastVersion("3.0").build());
    }
}
