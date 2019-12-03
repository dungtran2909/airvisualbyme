package com.hfad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.doanmobile.R;
import com.hfad.impl.ItemClickListener;
import com.hfad.model.CityInfo;
import com.hfad.model.Country;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ItemHolder> {
    Context context;
    ArrayList<CityInfo> arrCity;
    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CityAdapter(Context context, ArrayList<CityInfo> arrCity, ItemClickListener itemClickListener) {
        this.context = context;
        this.arrCity = arrCity;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, null);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        CityInfo cityInfo = arrCity.get(position);

        holder.txt_aqi.setText(cityInfo.getAqi());
        holder.txt_city.setText(cityInfo.getNameCity());
        holder.txt_country.setText(cityInfo.getNameState()+"," + cityInfo.getNameCountry());
        holder.txt_dgree.setText(cityInfo.getTp()+"");
        holder.txt_humi.setText(cityInfo.getHu()+"");
        holder.txt_win.setText(cityInfo.getWs()+"");
    }

    @Override
    public int getItemCount() {
        return arrCity.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView txt_level, txt_aqi, txt_dgree, txt_humi, txt_win, txt_city, txt_country;
        public ImageView img_dgree;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txt_level = itemView.findViewById(R.id.txt_level);
            txt_aqi = itemView.findViewById(R.id.txt_aqi);
            txt_dgree = itemView.findViewById(R.id.txt_dgree);
            txt_humi = itemView.findViewById(R.id.txt_humi);
            txt_win = itemView.findViewById(R.id.txt_win);
            txt_city = itemView.findViewById(R.id.txt_city);
            txt_country = itemView.findViewById(R.id.txt_country);
            img_dgree = itemView.findViewById(R.id.img_dgree);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }
}