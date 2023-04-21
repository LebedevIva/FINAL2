package com.example.springsecurityapplication.models;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Product product;

    public Image() {
    }
    public Image(String fileName, Product product) {
        this.fileName = fileName;
        this.product = product;
    }

    public String getFileName() {
        return fileName;
    }

    public Product getProduct() {
        return product;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}



