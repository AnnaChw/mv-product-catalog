package com.mcsystems.mvproductcatalog.api;

import com.mcsystems.mvproductcatalog.api.model.NewProductVersion;
import com.mcsystems.mvproductcatalog.service.ProductVersionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productVersion")
@AllArgsConstructor
public class ProductVersionEndpoint {

    private ProductVersionService productVersionService;

    @PostMapping
    public void addNewVersion(@RequestBody NewProductVersion productVersion){
        productVersionService.addNewProductVersion(productVersion);
    }
}
