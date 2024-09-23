package com.sample.inventory.Controller;

import com.sample.inventory.Entity.Product;
import com.sample.inventory.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public String registerProduct(@RequestBody Product product) throws IOException {

//        Product dummyProduct = new Product();
//        productService.populateDummyProduct(dummyProduct);

        System.out.println(product);
        String response = "";
        if (product != null) {
            if (productService.registerProduct(product)) {
                response = "Product Registered Successfully";
            } else {
                response = "Product has not been registered. Try Again Later.";
            }
        } else {
            response = "Null Product";
        }
        return response;
    }

}
