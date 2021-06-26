package com.mcsystems.mvproductcatalog.api;

import com.mcsystems.mvproductcatalog.api.model.NewProductVersion;
import com.mcsystems.mvproductcatalog.service.ProductVersionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productVersion")
@AllArgsConstructor
public class ProductVersionEndpoint {

    private ProductVersionService productVersionService;

    @PostMapping
    public void addNewVersion(@RequestBody NewProductVersion productVersion){
        productVersionService.addNewProductVersion(productVersion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProductVersion(@PathVariable Long id){
        productVersionService.removeProductVersion(id);
    }
}
