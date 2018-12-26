package com.example.vanminh.appmobile.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vanminh.appmobile.R;

public class ViewDetailActivity extends AppCompatActivity {
    private TextView txt_id,txt_name,txt_price,txt_pro_id;
    private String id,name,price,pro_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);
        anhxa();
        setData();
    }

    private void setData() {
        Intent intent = getIntent();
        if (intent != null){
            id = intent.getStringExtra("ID");
            name = intent.getStringExtra("NAME");
            price = intent.getStringExtra("PRICE");
            pro_id = intent.getStringExtra("PRO_ID");
        }
        txt_id.setText(id);
        txt_name.setText(name);
        txt_price.setText(price);
        txt_pro_id.setText(pro_id);
    }

    private void anhxa() {
        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_name);
        txt_price = findViewById(R.id.txt_price);
        txt_pro_id = findViewById(R.id.txt_pro_id);
    }
}
