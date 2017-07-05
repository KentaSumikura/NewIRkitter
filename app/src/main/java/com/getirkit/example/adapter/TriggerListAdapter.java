package com.getirkit.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.getirkit.example.R;
import com.getirkit.irkit.IRPeripheral;

import java.util.ArrayList;

/**
 * Created by 博之 on 2017/07/04.
 */
//ああああ


public class TriggerListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList data;

    public TriggerListAdapter(Activity activity, ArrayList data) {
        this.activity = activity;
        this.data = data;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

public static class ViewHolder {
    public ImageView image;
    public TextView name;
}

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        DeviceListAdapter.ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_trigger, parent, false);

            holder = new DeviceListAdapter.ViewHolder();
            holder.image = (ImageView) view.findViewById(R.id.item_image);
            holder.name = (TextView) view.findViewById(R.id.item_name);

            view.setTag(holder);
        } else {
            holder = (DeviceListAdapter.ViewHolder) view.getTag();
        }

        IRPeripheral peripheral = (IRPeripheral) data.get(position);
        holder.name.setText(peripheral.getCustomizedName());
        holder.details.setText( peripheral.getHostname() );

        return view;
    }
}
