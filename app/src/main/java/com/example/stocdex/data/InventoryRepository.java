package com.stocdex.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryRepository {

    private static InventoryRepository INSTANCE;

    public static InventoryRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InventoryRepository();
        }
        return INSTANCE;
    }

    private final List<Warehouse> warehouses = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<InventoryDocument> documents = new ArrayList<>();
    private boolean loggedIn = false;

    private InventoryRepository() {
        warehouses.add(new Warehouse("wh1", "Main Warehouse", "MW"));
        warehouses.add(new Warehouse("wh2", "Production Floor", "PF"));
        warehouses.add(new Warehouse("wh3", "Rack A", "RA"));

        products.add(new Product("1", "Steel Rods", "STR-001", "Raw Material",
                "kg", 450, "wh1", "Main Warehouse"));
        products.add(new Product("2", "Office Chairs", "CHR-002", "Furniture",
                "units", 12, "wh1", "Main Warehouse"));
        products.add(new Product("3", "Packaging Boxes", "BOX-004", "Supplies",
                "units", 0, "wh1", "Main Warehouse"));

        documents.add(new InventoryDocument(
                "d1",
                InventoryDocument.DocumentType.RECEIPT,
                InventoryDocument.DocumentStatus.WAITING,
                "wh1",
                "Main Warehouse",
                "Raw Material",
                "REC-001",
                "Steel Rods",
                50
        ));
        documents.add(new InventoryDocument(
                "d2",
                InventoryDocument.DocumentType.DELIVERY,
                InventoryDocument.DocumentStatus.DRAFT,
                "wh1",
                "Main Warehouse",
                "Furniture",
                "DO-001",
                "Office Chairs",
                10
        ));
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void login(String email, String password) {
        loggedIn = true;
    }

    public void logout() {
        loggedIn = false;
    }

    public List<Warehouse> getWarehouses() {
        return new ArrayList<>(warehouses);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product createProduct(String name, String sku, String category,
                                 String unit, int stock, Warehouse wh) {
        String id = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        Product p = new Product(
                id,
                name != null && !name.isEmpty() ? name : "New Product",
                sku != null && !sku.isEmpty() ? sku : "SKU-" + (System.currentTimeMillis() % 10000),
                category != null && !category.isEmpty() ? category : "Uncategorized",
                unit != null && !unit.isEmpty() ? unit : "units",
                stock,
                wh != null ? wh.id : "wh1",
                wh != null ? wh.name : "Main Warehouse"
        );
        addProduct(p);
        return p;
    }

    public DashboardKpis getDashboardKpis() {
        int totalStock = 0;
        int lowStock = 0;
        int pendingReceipts = 0;
        int pendingDeliveries = 0;
        int internalTransfers = 0;

        for (Product p : products) {
            totalStock += p.stock;
            boolean isLow = (!"kg".equals(p.unit) && p.stock > 0 && p.stock <= 20)
                    || ("kg".equals(p.unit) && p.stock > 0 && p.stock <= 50);
            if (p.stock == 0 || isLow) {
                lowStock++;
            }
        }

        for (InventoryDocument d : documents) {
            if (d.type == InventoryDocument.DocumentType.RECEIPT
                    && d.status != InventoryDocument.DocumentStatus.DONE
                    && d.status != InventoryDocument.DocumentStatus.CANCELED) {
                pendingReceipts++;
            }
            if (d.type == InventoryDocument.DocumentType.DELIVERY
                    && d.status != InventoryDocument.DocumentStatus.DONE
                    && d.status != InventoryDocument.DocumentStatus.CANCELED) {
                pendingDeliveries++;
            }
            if (d.type == InventoryDocument.DocumentType.INTERNAL
                    && d.status != InventoryDocument.DocumentStatus.DONE
                    && d.status != InventoryDocument.DocumentStatus.CANCELED) {
                internalTransfers++;
            }
        }

        return new DashboardKpis(totalStock, lowStock, pendingReceipts, pendingDeliveries, internalTransfers);
    }

    public List<String> getProductCategories() {
        List<String> result = new ArrayList<>();
        for (Product p : products) {
            if (!result.contains(p.category)) {
                result.add(p.category);
            }
        }
        return result;
    }

    public List<InventoryDocument> getDocuments() {
        return new ArrayList<>(documents);
    }

    public List<InventoryDocument> getFilteredDocuments(
            String warehouseId,
            InventoryDocument.DocumentType type,
            InventoryDocument.DocumentStatus status,
            String category
    ) {
        List<InventoryDocument> result = new ArrayList<>();
        for (InventoryDocument d : documents) {
            boolean matchWarehouse = warehouseId == null || warehouseId.isEmpty() || warehouseId.equals(d.warehouseId);
            boolean matchType = type == null || d.type == type;
            boolean matchStatus = status == null || d.status == status;
            boolean matchCategory = category == null || category.isEmpty()
                    || "All".equals(category)
                    || (d.productCategory != null && d.productCategory.equals(category));
            if (matchWarehouse && matchType && matchStatus && matchCategory) {
                result.add(d);
            }
        }
        return result;
    }

    public List<InventoryDocument> searchDocuments(List<InventoryDocument> source, String query) {
        if (query == null || query.trim().isEmpty()) return source;
        String q = query.toLowerCase();
        List<InventoryDocument> result = new ArrayList<>();
        for (InventoryDocument d : source) {
            String ref = d.reference != null ? d.reference.toLowerCase() : "";
            String name = d.productName != null ? d.productName.toLowerCase() : "";
            if (ref.contains(q) || name.contains(q)) {
                result.add(d);
            }
        }
        return result;
    }
}


