package com.hfad.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        ItemHolder itemHolder = new ItemHolder(view);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        CityInfo cityInfo = arrCity.get(position);

        holder.txt_aqi.setText(cityInfo.getAqi()+"");
        holder.txt_city.setText(cityInfo.getNameCity());
        holder.txt_country.setText(cityInfo.getNameState()+"," + cityInfo.getNameCountry());
        holder.txt_dgree.setText(cityInfo.getTp()+"");
        holder.txt_humi.setText(cityInfo.getHu()+"");
        holder.txt_win.setText(cityInfo.getWs()+"");
        if (cityInfo.getAqi() >= 0 && cityInfo.getAqi() <= 50) {
            holder.ln_info.setBackgroundColor(Color.GREEN);
            holder.txt_level.setText("Tốt");
        } else if (cityInfo.getAqi() >= 51 && cityInfo.getAqi() <= 100) {
            holder.ln_info.setBackgroundColor(Color.YELLOW);
            holder.txt_level.setText("Vừa phải");
        } else if (cityInfo.getAqi() >= 101 && cityInfo.getAqi() <= 150) {
            holder.ln_info.setBackgroundColor(Color.parseColor("#FF7E00"));
            holder.txt_level.setText("Không tốt cho nhóm nhạy cảm");
        } else if (cityInfo.getAqi() >= 151 && cityInfo.getAqi() <= 200) {
            holder.ln_info.setBackgroundColor(Color.RED);
            holder.txt_level.setText("Không tốt");
        } else if (cityInfo.getAqi() >= 201 && cityInfo.getAqi() <= 300) {
            holder.ln_info.setBackgroundColor(Color.rgb(143, 63, 151));
            holder.txt_level.setText("Độc hại");
        }
        setIconWeather(cityInfo.getIc(), holder);
    }

    private void setIconWeather(String ic, ItemHolder holder) {
        if (ic.equals("01d")) {
            holder.img_dgree.setImageResource(R.drawable.d01);
        } else if (ic.equals("01n")) {
            holder.img_dgree.setImageResource(R.drawable.n01);
        } else if (ic.equals("02d")) {
            holder.img_dgree.setImageResource(R.drawable.d02);
        } else if (ic.equals("02n")) {
            holder.img_dgree.setImageResource(R.drawable.n02);
        } else if (ic.equals("03d")) {
            holder.img_dgree.setImageResource(R.drawable.d03);
        } else if (ic.equals("04d")) {
            holder.img_dgree.setImageResource(R.drawable.d04);
        } else if (ic.equals("09d")) {
            holder.img_dgree.setImageResource(R.drawable.d09);
        } else if (ic.equals("10d")) {
            holder.img_dgree.setImageResource(R.drawable.d10);
        } else if (ic.equals("10n")) {
            holder.img_dgree.setImageResource(R.drawable.n10);
        } else if (ic.equals("11d")) {
            holder.img_dgree.setImageResource(R.drawable.d11);
        } else if (ic.equals("13d")) {
            holder.img_dgree.setImageResource(R.drawable.d13);
        } else if (ic.equals("50d")) {
            holder.img_dgree.setImageResource(R.drawable.d50);
        }
    }

    @Override
    public int getItemCount() {
        return arrCity.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView txt_level, txt_aqi, txt_dgree, txt_humi, txt_win, txt_city, txt_country;
        public ImageView img_dgree;
        public LinearLayout ln_info;

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