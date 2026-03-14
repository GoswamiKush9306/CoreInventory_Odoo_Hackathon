package com.stocdex.ui.products;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stocdex.R;
import com.stocdex.data.InventoryRepository;
import com.stocdex.data.Product;
import com.stocdex.data.ThemeUtils;

import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        RecyclerView recyclerProducts = findViewById(R.id.recyclerProducts);
        recyclerProducts.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products = InventoryRepository.getInstance().getProducts();
        ProductsAdapter adapter = new ProductsAdapter(products);
        recyclerProducts.setAdapter(adapter);

        Button buttonAddProduct = findViewById(R.id.buttonAddProduct);
        buttonAddProduct.setOnClickListener(v -> {
            startActivity(new Intent(this, AddProductActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        });
    }
}