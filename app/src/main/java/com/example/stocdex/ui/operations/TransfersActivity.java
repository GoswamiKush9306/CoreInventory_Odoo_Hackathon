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

public class TransfersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers);

        RecyclerView recycler = findViewById(R.id.recyclerTransfers);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        DocumentsAdapter adapter = new DocumentsAdapter();
        recycler.setAdapter(adapter);

        List<InventoryDocument> transfers = InventoryRepository.getInstance().getFilteredDocuments(
                null, InventoryDocument.DocumentType.INTERNAL, null, null
        );
        adapter.setItems(transfers);
    }
}