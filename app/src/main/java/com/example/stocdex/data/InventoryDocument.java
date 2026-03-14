package com.example.stocdex.data;

public class InventoryDocument {

    public enum DocumentType { RECEIPT, DELIVERY, INTERNAL, ADJUSTMENT }
    public enum DocumentStatus { DRAFT, WAITING, READY, DONE, CANCELED }

    public String id;
    public DocumentType type;
    public DocumentStatus status;
    public String warehouseId;
    public String warehouseName;
    public String productCategory;
    public String reference;
    public String productName;
    public int quantity;

    public InventoryDocument(String id, DocumentType type, DocumentStatus status,
                             String warehouseId, String warehouseName,
                             String productCategory, String reference,
                             String productName, int quantity) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.productCategory = productCategory;
        this.reference = reference;
        this.productName = productName;
        this.quantity = quantity;
    }
}
