package com.example.stocdex.ui.documents;

import android.view.View;
import android.widget.AdapterView;

public class SimpleOnItemSelectedAdapter implements AdapterView.OnItemSelectedListener {

    private final Runnable onChange;

    public SimpleOnItemSelectedAdapter(Runnable onChange) {
        this.onChange = onChange;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        onChange.run();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        onChange.run();
    }
}