package com.sample.inventory.Service;

import com.sample.inventory.Entity.Product;
import com.sample.inventory.Repository.ProductRespository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class ProductService {

    @Autowired
    private ProductRespository productRespository;

    private String[] fileHeaaders;

    public void populateDummyProduct(Product product) {
        product.setProductName("Electric Fan");
        product.setProductPrice(1200.0);
        product.setProductSerialNumber("123456dfghjgfvb");
        product.setWarrantyRegistrationDate(LocalDate.now());

    }

    public boolean registerProduct(Product product) throws IOException {
        boolean isProductPosted = true;
        Workbook workbook;
        Sheet sheet;
        int rowCount = 0;
        int columnCount = 0;
        Row row;

        String filePath = "inventory.xlsx";

        File inventoryFile = new File(filePath);
        if (inventoryFile.exists()) {
            FileInputStream fileInputStream = new FileInputStream(inventoryFile);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheet("WarrantyData");
            rowCount = sheet.getLastRowNum() + 1;
        } else {
            fileHeaaders = new String[]{"Name", "Price", "Serial Number", "Warranty Registration Date"};
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("WarrantyData");
            row = sheet.createRow(rowCount++);
            for (String field : fileHeaaders) {
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(field);
            }
        }
        row = sheet.createRow(rowCount);
        columnCount = 0;
        Cell productNameCell = row.createCell(columnCount++);
        productNameCell.setCellValue(product.getProductName());

        Cell productPriceCell = row.createCell(columnCount++);
        productPriceCell.setCellValue(product.getProductPrice());

        Cell productSerialNumberCell = row.createCell(columnCount++);
        productSerialNumberCell.setCellValue(product.getProductSerialNumber());

        Cell productWarrantyRegistrationDateCell = row.createCell(columnCount);
        productWarrantyRegistrationDateCell.setCellValue(product.getWarrantyRegistrationDate().toString());


        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            workbook.write(fileOutputStream);
        } catch (Exception exception) {
            isProductPosted = false;
        } finally {
            workbook.close();
        }
        return isProductPosted;
    }


}
