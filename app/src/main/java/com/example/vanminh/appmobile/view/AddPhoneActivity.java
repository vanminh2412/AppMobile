package com.example.vanminh.appmobile.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vanminh.appmobile.R;
import com.example.vanminh.appmobile.database.DBManager;
import com.example.vanminh.appmobile.model.ManuFacture;
import com.example.vanminh.appmobile.model.Phone;
import com.example.vanminh.appmobile.present.PhoneDAO;

import java.util.ArrayList;
import java.util.List;

public class AddPhoneActivity extends AppCompatActivity {
    private DBManager dbManager;
    private EditText edt_id,edt_name,edt_price;
    private Spinner spn_pro_id;
    private Button btn_insert;
    private PhoneDAO phoneDAO;
    private List<ManuFacture> list;
    private String maSX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);
        dbManager = new DBManager(this);
        phoneDAO = new PhoneDAO(dbManager,this);
        anhxa();
        clickSpinner();
        addPhone();
    }

    private void addPhone() {
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText().toString();
                String name = edt_name.getText().toString();
                String price = edt_price.getText().toString();
                if (id.isEmpty()){
                    edt_id.setError("Empty ID");
                    return;
                }
                else if (name.isEmpty()){
                    edt_name.setError("Empty Name");
                    return;
                }
                else if (price.isEmpty()){
                    edt_price.setError("Empty Price");
                    return;
                }else {
                    Phone phone = new Phone(Integer.parseInt(id),name,Float.parseFloat(price),maSX);
                    if (phone!=null){
                        phoneDAO.insertPhone(phone);
                        Toast.makeText(AddPhoneActivity.this,"Insert success...!!!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddPhoneActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    /*private Phone createdPhone(){
        String id = edt_id.getText().toString();
        String name = edt_name.getText().toString();
        String price = edt_price.getText().toString();
        if (id.isEmpty()){
            edt_id.setError("Empty ID");
        }
        else if (name.isEmpty()){
            edt_name.setError("Empty Name");
        }
        else if (price.isEmpty()){
            edt_price.setError("Empty Price");
        }
        Phone phone = new Phone(Integer.parseInt(id),name,Float.parseFloat(price),maSX);
        return phone;

    }*/
    private void clickSpinner() {
        list = phoneDAO.getListManu();
        final ArrayAdapter<ManuFacture> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn_pro_id.setAdapter(adapter);
        spn_pro_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ManuFacture manuFacture = (ManuFacture) parent.getItemAtPosition(position);
                maSX = manuFacture.getNameSX();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void anhxa() {
        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_price = findViewById(R.id.edt_price);
        spn_pro_id = findViewById(R.id.spn_pro_id);
        btn_insert = findViewById(R.id.btn_insert_phone);
    }
}
