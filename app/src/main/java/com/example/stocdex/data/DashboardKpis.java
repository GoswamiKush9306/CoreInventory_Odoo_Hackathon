package com.example.stocdex.data;

public class DashboardKpis {
    public int totalProductsInStock;
    public int lowStockCount;
    public int pendingReceipts;
    public int pendingDeliveries;
    public int internalTransferScheduled;

    public DashboardKpis(int totalProductsInStock, int lowStockCount,
                         int pendingReceipts, int pendingDeliveries,
                         int internalTransferScheduled) {
        this.totalProductsInStock = totalProductsInStock;
        this.lowStockCount = lowStockCount;
        this.pendingReceipts = pendingReceipts;
        this.pendingDeliveries = pendingDeliveries;
        this.internalTransferScheduled = internalTransferScheduled;
    }
}
