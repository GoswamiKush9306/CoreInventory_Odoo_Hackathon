package com.stocdex.ui.documents;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stocdex.R;
import com.stocdex.data.InventoryDocument;
import com.stocdex.data.InventoryRepository;
import com.stocdex.data.ThemeUtils;
import com.stocdex.data.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class DocumentsActivity extends AppCompatActivity {

    private Spinner spinnerType, spinnerStatus, spinnerWarehouse, spinnerCategory;
    private EditText inputSearch;
    private DocumentsAdapter adapter;

    private InventoryDocument.DocumentType selectedType = null;
    private InventoryDocument.DocumentStatus selectedStatus = null;
    private String selectedWarehouseId = null;
    private String selectedCategory = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        spinnerType = findViewById(R.id.spinnerType);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        spinnerWarehouse = findViewById(R.id.spinnerWarehouse);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        inputSearch = findViewById(R.id.inputSearch);

        RecyclerView recycler = findViewById(R.id.recyclerDocuments);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DocumentsAdapter();
        recycler.setAdapter(adapter);

        setupSpinners();
        setupSearch();
        applyFilters();
    }

    private void setupSpinners() {
        List<String> typeItems = new ArrayList<>();
        typeItems.add("All");
        typeItems.add("Receipts");
        typeItems.add("Delivery");
        typeItems.add("Internal");
        typeItems.add("Adjustments");
        spinnerType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeItems));

        List<String> statusItems = new ArrayList<>();
        statusItems.add("All");
        statusItems.add("Draft");
        statusItems.add("Waiting");
        statusItems.add("Ready");
        statusItems.add("Done");
        statusItems.add("Canceled");
        spinnerStatus.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusItems));

        InventoryRepository repo = InventoryRepository.getInstance();
        List<Warehouse> warehouses = repo.getWarehouses();
        List<String> warehouseItems = new ArrayList<>();
        warehouseItems.add("All");
        for (Warehouse w : warehouses) {
            warehouseItems.add(w.name);
        }
        spinnerWarehouse.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, warehouseItems));

        List<String> categories = repo.getProductCategories();
        List<String> categoryItems = new ArrayList<>();
        categoryItems.add("All");
        categoryItems.addAll(categories);
        spinnerCategory.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryItems));

        spinnerType.setOnItemSelectedListener(new SimpleOnItemSelectedAdapter(() -> {
            String sel = (String) spinnerType.getSelectedItem();
            if ("Receipts".equals(sel)) selectedType = InventoryDocument.DocumentType.RECEIPT;
            else if ("Delivery".equals(sel)) selectedType = InventoryDocument.DocumentType.DELIVERY;
            else if ("Internal".equals(sel)) selectedType = InventoryDocument.DocumentType.INTERNAL;
            else if ("Adjustments".equals(sel)) selectedType = InventoryDocument.DocumentType.ADJUSTMENT;
            else selectedType = null;
            applyFilters();
        }));

        spinnerStatus.setOnItemSelectedListener(new SimpleOnItemSelectedAdapter(() -> {
            String sel = (String) spinnerStatus.getSelectedItem();
            if ("Draft".equals(sel)) selectedStatus = InventoryDocument.DocumentStatus.DRAFT;
            else if ("Waiting".equals(sel)) selectedStatus = InventoryDocument.DocumentStatus.WAITING;
            else if ("Ready".equals(sel)) selectedStatus = InventoryDocument.DocumentStatus.READY;
            else if ("Done".equals(sel)) selectedStatus = InventoryDocument.DocumentStatus.DONE;
            else if ("Canceled".equals(sel)) selectedStatus = InventoryDocument.DocumentStatus.CANCELED;
            else selectedStatus = null;
            applyFilters();
        }));

        spinnerWarehouse.setOnItemSelectedListener(new SimpleOnItemSelectedAdapter(() -> {
            int pos = spinnerWarehouse.getSelectedItemPosition();
            if (pos <= 0) {
                selectedWarehouseId = null;
            } else {
                Warehouse w = warehouses.get(pos - 1);
                selectedWarehouseId = w.id;
            }
            applyFilters();
        }));

        spinnerCategory.setOnItemSelectedListener(new SimpleOnItemSelectedAdapter(() -> {
            selectedCategory = (String) spinnerCategory.getSelectedItem();
            applyFilters();
        }));
    }

    private void setupSearch() {
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { applyFilters(); }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void applyFilters() {
        InventoryRepository repo = InventoryRepository.getInstance();
        List<InventoryDocument> base = repo.getFilteredDocuments(
                selectedWarehouseId,
                selectedType,
                selectedStatus,
                selectedCategory
        );
        String q = inputSearch.getText().toString();
        List<InventoryDocument> filtered = repo.searchDocuments(base, q);
        adapter.setItems(filtered);
    }
}