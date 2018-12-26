package com.example.vanminh.appmobile.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vanminh.appmobile.R;
import com.example.vanminh.appmobile.database.DBManager;
import com.example.vanminh.appmobile.model.ManuFacture;
import com.example.vanminh.appmobile.present.PhoneDAO;

public class AddManuFactureActivity extends AppCompatActivity {
    private EditText edt_idSX,edt_nameSX;
    private Button btn_insert_manufacture;
    private DBManager dbManager;
    private PhoneDAO phoneDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manu_facture);
        dbManager = new DBManager(this);
        phoneDAO = new PhoneDAO(dbManager,this);
        anhxa();
        addManufacture();
    }

    private void addManufacture() {
        btn_insert_manufacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManuFacture manuFacture = createdManu();
                if (manuFacture != null){
                    phoneDAO.insertPhonSX(manuFacture);
                    Toast.makeText(AddManuFactureActivity.this,"Insert success...!",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    private ManuFacture createdManu(){
        String id = edt_idSX.getText().toString();
        int id_1 = Integer.parseInt(id);
        String name = edt_nameSX.getText().toString();
        if (id.isEmpty()){
            edt_idSX.setError("Empty ID");
        }
        else if (name.isEmpty()){
            edt_nameSX.setError("Empty Name");
        }
        ManuFacture manuFacture = new ManuFacture(id_1,name);
        return manuFacture;
    }
    private void anhxa() {
        edt_idSX = findViewById(R.id.edt_id);
        edt_nameSX = findViewById(R.id.edt_name);
        btn_insert_manufacture = findViewById(R.id.btn_insert_manufacture);
    }
}
