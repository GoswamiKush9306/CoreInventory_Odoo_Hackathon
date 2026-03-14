package com.example.stocdex.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stocdex.R;
import com.example.stocdex.data.DashboardKpis;
import com.example.stocdex.data.InventoryRepository;
import com.example.stocdex.data.ThemeUtils;
import com.example.stocdex.ui.documents.DocumentsActivity;
import com.example.stocdex.ui.operations.AdjustmentsActivity;
import com.example.stocdex.ui.operations.DeliveryActivity;
import com.example.stocdex.ui.operations.ReceiptsActivity;
import com.example.stocdex.ui.operations.TransfersActivity;
import com.example.stocdex.ui.products.ProductsActivity;
import com.example.stocdex.ui.profile.ProfileActivity;
import com.example.stocdex.ui.settings.SettingsActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.applyTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        updateKpis();

        Button buttonProducts = findViewById(R.id.buttonProducts);
        Button buttonDocuments = findViewById(R.id.buttonDocuments);
        Button buttonReceipts = findViewById(R.id.buttonReceipts);
        Button buttonDelivery = findViewById(R.id.buttonDelivery);
        Button buttonTransfers = findViewById(R.id.buttonTransfers);
        Button buttonAdjustments = findViewById(R.id.buttonAdjustments);
        Button buttonSettings = findViewById(R.id.buttonSettings);
        Button buttonProfile = findViewById(R.id.buttonProfile);

        buttonProducts.setOnClickListener(v -> open(ProductsActivity.class));
        buttonDocuments.setOnClickListener(v -> open(DocumentsActivity.class));
        buttonReceipts.setOnClickListener(v -> open(ReceiptsActivity.class));
        buttonDelivery.setOnClickListener(v -> open(DeliveryActivity.class));
        buttonTransfers.setOnClickListener(v -> open(TransfersActivity.class));
        buttonAdjustments.setOnClickListener(v -> open(AdjustmentsActivity.class));
        buttonSettings.setOnClickListener(v -> open(SettingsActivity.class));
        buttonProfile.setOnClickListener(v -> open(ProfileActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateKpis();
    }

    private void updateKpis() {
        TextView textTotalStockValue = findViewById(R.id.textTotalStockValue);
        TextView textLowStockValue = findViewById(R.id.textLowStockValue);

        if (textTotalStockValue != null && textLowStockValue != null) {
            DashboardKpis kpis = InventoryRepository.getInstance().getDashboardKpis();
            textTotalStockValue.setText(String.valueOf(kpis.totalProductsInStock));
            textLowStockValue.setText(String.valueOf(kpis.lowStockCount));
        }
    }

    private void open(Class<?> cls) {
        startActivity(new Intent(this, cls));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
    }
}