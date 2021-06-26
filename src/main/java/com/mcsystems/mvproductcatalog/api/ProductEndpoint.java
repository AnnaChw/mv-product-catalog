package com.mcsystems.mvproductcatalog.api;

import com.mcsystems.mvproductcatalog.api.model.CloudProduct;
import com.mcsystems.mvproductcatalog.api.model.Errors;
import com.mcsystems.mvproductcatalog.api.model.NewCloudProduct;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.mcsystems.mvproductcatalog.service.CloudProductService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductEndpoint {


    private CloudProductService cloudProductService;

    @GetMapping
    public List<CloudProduct> getAll(){

        return cloudProductService.getAllCloudProducts();
    }

    @GetMapping("/{id}")
    public CloudProduct getOne(@PathVariable Long id) {
        return cloudProductService.getCloudProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addCloudProduct(@RequestBody NewCloudProduct newProduct, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errorsMsgs = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            Errors errors = Errors.builder().errors(errorsMsgs).build();

            return ResponseEntity.status(400).body(errors);

        }else{
            cloudProductService.addNewCloudProduct(newProduct);
            return ResponseEntity.status(201).build();
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCloudProduct(@PathVariable Long id){
        cloudProductService.deleteCloudProduct(id);
    }
}
