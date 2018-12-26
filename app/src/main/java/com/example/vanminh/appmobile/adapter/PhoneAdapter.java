package com.example.vanminh.appmobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vanminh.appmobile.R;
import com.example.vanminh.appmobile.model.Phone;

import java.util.List;

public class PhoneAdapter extends ArrayAdapter {
    private Context mContext;
    private List<Phone> phoneList;
    private int resource;

    public PhoneAdapter(Context context, int resource, List<Phone> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.resource = resource;
        this.phoneList = objects;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_phone,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txt_id = convertView.findViewById(R.id.txt_id);
            viewHolder.txt_name = convertView.findViewById(R.id.txt_name);
            viewHolder.txt_price = convertView.findViewById(R.id.txt_price);
            viewHolder.txt_pro_id = convertView.findViewById(R.id.txt_pro_id);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Phone phone = phoneList.get(position);
        viewHolder.txt_id.setText(String.valueOf(phone.getId()));
        viewHolder.txt_name.setText(phone.getName());
        viewHolder.txt_price.setText(String.valueOf(phone.getPrice()));
        viewHolder.txt_pro_id.setText(phone.getPro_id());

        return convertView;
    }
    public class ViewHolder{
        private TextView txt_id,txt_name,txt_price,txt_pro_id;
    }
}
