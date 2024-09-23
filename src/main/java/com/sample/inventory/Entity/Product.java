package com.sample.inventory.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Product {

    @Id
    private String productSerialNumber;
    private String productName;
    private Double productPrice;
    private LocalDate warrantyRegistrationDate;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSerialNumber() {
        return productSerialNumber;
    }

    public void setProductSerialNumber(String productSerialNumber) {
        this.productSerialNumber = productSerialNumber;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public LocalDate getWarrantyRegistrationDate() {
        return warrantyRegistrationDate;
    }

    public void setWarrantyRegistrationDate() {
        this.warrantyRegistrationDate = LocalDate.now();
    }

    public void setWarrantyRegistrationDate(LocalDate warrantyRegistrationDate) {
        this.warrantyRegistrationDate = warrantyRegistrationDate;
    }


}
