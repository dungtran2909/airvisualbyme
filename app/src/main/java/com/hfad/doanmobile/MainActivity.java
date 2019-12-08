package com.hfad.doanmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.hfad.adapter.CityAdapter;
import com.hfad.constan.Const;
import com.hfad.model.Country;
import com.hfad.model.Data;
import com.hfad.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn_continue;

    RecyclerView rcl_country;
    CityAdapter countryAdapter;
    ArrayList<Country> arrC = new ArrayList<>();
    ArrayAdapter<Country> adapter;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void getCity() {
        ApiService.getInstance().getCountry(Const.keyAPI, new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Log.d("Error", response.toString());
                if (response.isSuccessful()) {
                    Log.d("Access", response.toString());
                    Data data = response.body();
                    List<Country> list =data.getCountries();
                    adapter.addAll(list);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

    private void addEvents() {
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                try {
                    String namecountry = spinner.getSelectedItem().toString();
//                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    intent.putExtra("NAME", namecountry);
                }catch (Exception ex){
                    ex.toString();
                }

                startActivity(intent);
            }
        });
    }

    private void addControls() {
        spinner = findViewById(R.id.spinner);
        getCity();
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btn_continue = findViewById(R.id.btn_continue);
    }
}
