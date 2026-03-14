package com.example.stocdex.data;

public class Product {
    public String id;
    public String name;
    public String sku;
    public String category;
    public String unit;
    public int stock;
    public String warehouseId;
    public String warehouseName;

    public Product(String id, String name, String sku, String category,
                   String unit, int stock, String warehouseId, String warehouseName) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.category = category;
        this.unit = unit;
        this.stock = stock;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
    }
}
