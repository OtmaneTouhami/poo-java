package Exercice3.impl;

import Exercice3.IService;
import Exercice3.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IService<Product> {
    private List<Product> products = new ArrayList<>();
    private final File productFile = new File("Exercice3/Storage/products.dat");

    @Override
    public Product add(Product product) {
        if (product != null) {
            products.add(product);
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        if (productFile.length() == 0) {
            return products;
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(productFile))) {
            products = (List<Product>) objectInputStream.readObject();
            return products;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error while loading products: " + e.getMessage(), e);
        }
    }

    @Override
    public Product findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        return products.stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        return products.removeIf(product -> product.getName().equalsIgnoreCase(name));
    }

    @Override
    public void saveAll() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(productFile))) {
            objectOutputStream.writeObject(products);
        } catch (IOException e) {
            throw new RuntimeException("Error while saving products: " + e.getMessage(), e);
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
