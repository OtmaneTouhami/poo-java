package Exercice3;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String brand;
    private float price;
    private String description;
    private int stockQuantity;

    public Product(String name, String brand, float price, String description, int stockQuantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }
    public Product() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return String.format(
                """
                        ----------------------------
                        Product: %s
                        Brand: %s
                        Price: %.2f USD
                        Description: %s
                        Stock Quantity: %d
                        ----------------------------""",
                name, brand, price, description, stockQuantity
        );
    }

}
