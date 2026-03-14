package com.example.stocdex.ui.products;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stocdex.R;
import com.example.stocdex.data.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private final List<Product> items;

    public ProductsAdapter(List<Product> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = items.get(position);
        holder.name.setText(p.name);
        holder.skuCategory.setText(p.sku + " · " + p.category);
        holder.warehouse.setText(p.warehouseName);
        holder.stock.setText(p.stock + " " + p.unit);

        int color;
        if (p.stock == 0) {
            color = Color.parseColor("#D45D5D");
        } else if ((! "kg".equals(p.unit) && p.stock <= 20) ||
                ("kg".equals(p.unit) && p.stock <= 50)) {
            color = Color.parseColor("#E8A84A");
        } else {
            color = Color.parseColor("#6FEBBF");
        }
        holder.stock.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, skuCategory, warehouse, stock;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textProductName);
            skuCategory = itemView.findViewById(R.id.textProductSkuCategory);
            warehouse = itemView.findViewById(R.id.textProductWarehouse);
            stock = itemView.findViewById(R.id.textProductStock);
        }
    }
}