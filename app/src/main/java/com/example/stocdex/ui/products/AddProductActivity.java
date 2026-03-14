package com.stocdex.ui.products;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.stocdex.R;
import com.stocdex.data.InventoryRepository;
import com.stocdex.data.ThemeUtils;
import com.stocdex.data.Warehouse;

import java.util.List;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        EditText inputName = findViewById(R.id.inputName);
        EditText inputSku = findViewById(R.id.inputSku);
        EditText inputCategory = findViewById(R.id.inputCategory);
        EditText inputUnit = findViewById(R.id.inputUnit);
        EditText inputInitialStock = findViewById(R.id.inputInitialStock);
        Button buttonSave = findViewById(R.id.buttonSaveProduct);

        buttonSave.setOnClickListener(v -> {
            String name = inputName.getText().toString();
            String sku = inputSku.getText().toString();
            String category = inputCategory.getText().toString();
            String unit = inputUnit.getText().toString();
            int stock = 0;
            try {
                stock = Integer.parseInt(inputInitialStock.getText().toString());
            } catch (NumberFormatException ignored) {}

            InventoryRepository repo = InventoryRepository.getInstance();
            List<Warehouse> warehouses = repo.getWarehouses();
            Warehouse wh = warehouses.isEmpty() ? null : warehouses.get(0);
            repo.createProduct(name, sku, category, unit, stock, wh);

            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }
}