package com.example.stocdex.ui.operations;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stocdex.R;
import com.example.stocdex.data.InventoryDocument;
import com.example.stocdex.data.InventoryRepository;
import com.example.stocdex.data.ThemeUtils;
import com.example.stocdex.ui.documents.DocumentsAdapter;

import java.util.List;

public class ReceiptsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipts);

        RecyclerView recycler = findViewById(R.id.recyclerReceipts);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        DocumentsAdapter adapter = new DocumentsAdapter();
        recycler.setAdapter(adapter);

        List<InventoryDocument> receipts = InventoryRepository.getInstance().getFilteredDocuments(
                null, InventoryDocument.DocumentType.RECEIPT, null, null
        );
        adapter.setItems(receipts);
    }
}