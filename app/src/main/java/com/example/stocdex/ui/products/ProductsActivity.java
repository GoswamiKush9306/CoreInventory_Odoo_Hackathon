package com.example.stocdex.ui.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stocdex.R;
import com.example.stocdex.data.InventoryRepository;
import com.example.stocdex.data.Product;
import com.example.stocdex.data.ThemeUtils;

import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        RecyclerView recyclerProducts = findViewById(R.id.recyclerProducts);
        recyclerProducts.setLayoutManager(new LinearLayoutManager(this));

        loadProducts();

        View buttonAddProduct = findViewById(R.id.buttonAddProduct);
        if (buttonAddProduct != null) {
            buttonAddProduct.setOnClickListener(v -> {
                startActivity(new Intent(this, AddProductActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    private void loadProducts() {
        List<Product> products = InventoryRepository.getInstance().getProducts();
        RecyclerView recyclerProducts = findViewById(R.id.recyclerProducts);
        if (recyclerProducts != null) {
            adapter = new ProductsAdapter(products);
            recyclerProducts.setAdapter(adapter);
        }
    }
}