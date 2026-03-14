package com.example.stocdex.ui.documents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stocdex.R;
import com.stocdex.data.InventoryDocument;

import java.util.ArrayList;
import java.util.List;

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.DocumentViewHolder> {

    private final List<InventoryDocument> items = new ArrayList<>();

    public void setItems(List<InventoryDocument> newItems) {
        items.clear();
        if (newItems != null) items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_document, parent, false);
        return new DocumentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        InventoryDocument d = items.get(position);
        holder.title.setText(d.type.name() + " · " + d.reference);
        String subtitle = d.warehouseName;
        if (d.productCategory != null) {
            subtitle += " · " + d.productCategory;
        }
        holder.subtitle.setText(subtitle);
        holder.status.setText(d.status.name());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class DocumentViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle, status;

        DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textDocTitle);
            subtitle = itemView.findViewById(R.id.textDocSubtitle);
            status = itemView.findViewById(R.id.textDocStatus);
        }
    }
}