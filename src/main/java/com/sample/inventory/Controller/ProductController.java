package com.sample.inventory.Controller;

import com.sample.inventory.Entity.Product;
import com.sample.inventory.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/register")
    public String registerProduct() throws IOException {

        Product dummyProduct = new Product();
        productService.populateDummyProduct(dummyProduct);

//        System.out.println(product);

        if (productService.registerProduct(dummyProduct)) {
            return "Product Registered Successfully";
        }
        return "Product has not been registered. Try Again Later.";
    }

}
