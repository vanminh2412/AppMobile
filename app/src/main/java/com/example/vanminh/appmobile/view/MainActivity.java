package com.example.vanminh.appmobile.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.vanminh.appmobile.R;
import com.example.vanminh.appmobile.adapter.PhoneAdapter;
import com.example.vanminh.appmobile.database.DBManager;
import com.example.vanminh.appmobile.model.ManuFacture;
import com.example.vanminh.appmobile.model.Phone;
import com.example.vanminh.appmobile.present.PhoneDAO;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private PhoneDAO phoneDAO;
    private List<Phone> phoneList;
    private ListView rcv_list_phone;
    private Button btn_add_phone,btn_add_manufacture;
    private PhoneAdapter adapter;
    private List<ManuFacture> list;
    private String maSX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        phoneDAO = new PhoneDAO(dbManager,this);
        anhxa();
        phoneList = phoneDAO.getAllPhone();
        setAdapter();
        clickButton();
        registerForContextMenu(rcv_list_phone);
        listviewItemClick();
    }

    private void listviewItemClick() {
        rcv_list_phone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Phone phone = phoneList.get(position);
                Intent intent = new Intent(MainActivity.this,ViewDetailActivity.class);
                intent.putExtra("ID",String.valueOf(phone.getId()));
                intent.putExtra("NAME",phone.getName());
                intent.putExtra("PRICE",String.valueOf(phone.getPrice()));
                intent.putExtra("PRO_ID",phone.getPro_id());
                startActivity(intent);
            }
        });
    }

    private void setAdapter() {
        if (adapter == null){
            adapter = new PhoneAdapter(this,R.layout.item_list_phone,phoneList);
            rcv_list_phone.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.item_update:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final Phone phone = phoneList.get(info.position);
                builder.setTitle("Update Phone");
                builder.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View view = inflater.inflate(R.layout.dialog_update,null);
                final EditText edt_name = view.findViewById(R.id.edt_name);
                final EditText edt_price = view.findViewById(R.id.edt_price);
                Spinner spn_SX = view.findViewById(R.id.spn_pro_id);

                edt_name.setText(phone.getName());
                edt_price.setText(String.valueOf(phone.getPrice()));
                list = phoneDAO.getListManu();
                final ArrayAdapter<ManuFacture> adapterSP = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
                adapterSP.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spn_SX.setAdapter(adapterSP);
                spn_SX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ManuFacture manuFacture = (ManuFacture) parent.getItemAtPosition(position);
                        maSX = manuFacture.getNameSX();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                builder.setView(view);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        phone.setName(edt_name.getText().toString());
                        phone.setPro_id(edt_price.getText().toString());
                        phone.setPro_id(maSX);
                        phoneDAO.updatePhone(phone);
                        phoneList.set(info.position,phone);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.item_delete:
                final Phone phone1 = phoneList.get(info.position);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setTitle("Delete Phone");
                builder1.setMessage("Are you sure delete \"" + phone1.getName() + "\" ?");
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        phoneDAO.deletePhone(phone1);
                        phoneList.remove(phone1);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder1.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog1 = builder1.create();
                dialog1.show();
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void clickButton() {
        btn_add_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddPhoneActivity.class);
                startActivity(intent);
            }
        });
        btn_add_manufacture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,AddManuFactureActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void anhxa() {
        rcv_list_phone = findViewById(R.id.rcv_mobile);
        btn_add_manufacture = findViewById(R.id.btn_manufacturer);
        btn_add_phone = findViewById(R.id.btn_add_phone);
    }
}
