package com.example.lab203_28.healthy.Weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab203_28.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class WeightAdaptor extends ArrayAdapter {

    List<Weight> weights = new ArrayList<Weight>();
    Context context;

    public WeightAdaptor (Context context, int resource, List<Weight> object){
        super(context, resource, object);
        this.weights = object;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View _row = LayoutInflater.from(context).inflate(R.layout.weight_,parent,false);
        TextView _t1 = (TextView) _row.findViewById(R.id.Weight_textview);
        Weight _w = weights.get(position);
        _t1.setText(_w.date);
        return _row;
    }
}
