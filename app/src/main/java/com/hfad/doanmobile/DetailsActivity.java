package com.hfad.doanmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.hfad.adapter.CityAdapter;
import com.hfad.constan.Const;
import com.hfad.impl.ItemClickListener;
import com.hfad.model.City;
import com.hfad.model.CityInfo;
import com.hfad.model.Current;
import com.hfad.model.Data;
import com.hfad.model.DataCity;
import com.hfad.model.DataMyLocal;
import com.hfad.model.DataState;
import com.hfad.model.MyLocal;
import com.hfad.model.Pollution;
import com.hfad.model.State;
import com.hfad.model.Weather;
import com.hfad.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.util.ULocale.forLanguageTag;
import static android.icu.util.ULocale.getCountry;

public class DetailsActivity extends AppCompatActivity implements LocationListener {
    TabHost tabHost;
    ImageView img_close;

    TextView txt_city_my, txt_aqi_my, txt_dgree_my, txt_humi_my, txt_win_my, txt_level_my;
    ImageView img_dgree_my;

    LocationManager locationManager;

    LinearLayout ln_info_my;

    Long lat, lon;

    String nameCountry;

    ArrayList<CityInfo> cityInfos;
    RecyclerView rcl_city;
    CityAdapter cityAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        nameCountry = intent.getStringExtra("NAME");

        getState(nameCountry);

        addControls();
        addEvents();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
    }

    private void getState(final String nameCountry) {
        ApiService.getInstance().getState(nameCountry, Const.keyAPI, new Callback<DataState>() {
            @Override
            public void onResponse(Call<DataState> call, Response<DataState> response) {
                Log.d("Error", response.toString());
                if (response.isSuccessful()) {
                    Log.d("Success", response.toString());
                    DataState dataState = response.body();
                    for (State state : dataState.getStates()) {
                        getCity(state.getNameState(), nameCountry);
                    }

                }
            }

            @Override
            public void onFailure(Call<DataState> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

    private void getCity(final String nameState, final String nameCountry) {
        ApiService.getInstance().getCity(nameState, nameCountry, Const.keyAPI, new Callback<DataCity>() {
            @Override
            public void onResponse(Call<DataCity> call, Response<DataCity> response) {
                Log.d("Error", response.toString());
                if (response.isSuccessful()) {
                    int dem = 0;
                    Log.d("Success", response.toString());
                    DataCity dataCity = response.body();

                    List<City> list = dataCity.getCities();
                    City city = list.get(0);
                    getDetailsCity(city.getNameCity(), nameState, nameCountry);

//                    Toast.makeText(DetailsActivity.this, dem + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataCity> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

    private void getDetailsCity(String nameCity, final String nameState, String nameCountry) {
        ApiService.getInstance().getDetailsCity(nameCity, nameState, nameCountry, Const.keyAPI, new Callback<DataMyLocal>() {
            @Override
            public void onResponse(Call<DataMyLocal> call, Response<DataMyLocal> response) {
                Log.d("Error", response.toString());
                if (response.isSuccessful()){
                    Log.d("Success", response.toString());
                    DataMyLocal dataMyLocal = response.body();
                    MyLocal myLocal = dataMyLocal.getMyLocal();
                    Current current = myLocal.getCurrent();
                    Pollution pollution = current.getPollution();
                    Weather weather = current.getWeather();
                    Toast.makeText(DetailsActivity.this, myLocal.getCity(), Toast.LENGTH_LONG).show();
                    cityInfos.add(new CityInfo(myLocal.getState(), myLocal.getCountry(), myLocal.getCity(), weather.getTp(), weather.getHu(), weather.getWs(), weather.getIc(), pollution.getAqius()));
                    cityAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<DataMyLocal> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }


    private void addEvents() {
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addControls() {
        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setIndicator("City");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setIndicator("My location");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);

        img_close = findViewById(R.id.img_close);
        txt_city_my = findViewById(R.id.txt_city_my);
        txt_aqi_my = findViewById(R.id.txt_aqi_my);
        txt_dgree_my = findViewById(R.id.txt_dgree_my);
        txt_humi_my = findViewById(R.id.txt_humi_my);
        txt_win_my = findViewById(R.id.txt_win_my);
        txt_level_my = findViewById(R.id.txt_level_my);
        ln_info_my = findViewById(R.id.ln_info_my);
        img_dgree_my = findViewById(R.id.img_dgree_my);

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        };

        cityInfos = new ArrayList<>();
        rcl_city = findViewById(R.id.rcl_city);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(DetailsActivity.this);
        rcl_city.setLayoutManager(manager);
        cityAdapter = new CityAdapter(DetailsActivity.this, cityInfos, itemClickListener);
        rcl_city.setAdapter(cityAdapter);
    }

    @Override
    public void onLocationChanged(Location location) {
        lon = (long) location.getLongitude();
        lat = (long) location.getLatitude();
        try {
            getWeatherMyLocal(lon, lat);
        } catch (Exception ex) {
            ex.toString();
        }

    }

    private void getWeatherMyLocal(Long lon, Long lat) {
        ApiService.getInstance().getMyLocal(String.valueOf(lat), String.valueOf(lon), Const.keyAPI, new Callback<DataMyLocal>() {
            @Override
            public void onResponse(Call<DataMyLocal> call, Response<DataMyLocal> response) {
                Log.d("Error", response.toString());
                if (response.isSuccessful()) {
                    Log.d("Access", response.toString());
                    DataMyLocal dataMyLocal = response.body();
                    MyLocal myLocal = dataMyLocal.getMyLocal();
                    txt_city_my.setText(myLocal.getCity());
                    Current current = myLocal.getCurrent();
                    Pollution pollution = current.getPollution();
                    txt_aqi_my.setText(pollution.getAqius() + "");
                    Weather weather = current.getWeather();
                    txt_dgree_my.setText(weather.getTp() + "");
                    txt_humi_my.setText(weather.getHu() + "");
                    txt_win_my.setText(weather.getWs() + "");

                    if (pollution.getAqius() >= 0 && pollution.getAqius() <= 50) {
                        ln_info_my.setBackgroundColor(Color.GREEN);
                        txt_level_my.setText("Tốt");
                    } else if (pollution.getAqius() >= 51 && pollution.getAqius() <= 100) {
                        ln_info_my.setBackgroundColor(Color.YELLOW);
                        txt_level_my.setText("Vừa phải");
                    } else if (pollution.getAqius() >= 101 && pollution.getAqius() <= 150) {
                        ln_info_my.setBackgroundColor(Color.parseColor("#FF7E00"));
                        txt_level_my.setText("Không tốt cho nhóm nhạy cảm");
                    } else if (pollution.getAqius() >= 151 && pollution.getAqius() <= 200) {
                        ln_info_my.setBackgroundColor(Color.RED);
                        txt_level_my.setText("Không tốt");
                    } else if (pollution.getAqius() >= 201 && pollution.getAqius() <= 300) {
                        ln_info_my.setBackgroundColor(Color.rgb(143, 63, 151));
                        txt_level_my.setText("Độc hại");
                    }

                    setIconWeather(weather);
                }
            }

            @Override
            public void onFailure(Call<DataMyLocal> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

    private void setIconWeather(Weather weather) {
        if (weather.getIc().equals("01d")) {
            img_dgree_my.setImageResource(R.drawable.d01);
        } else if (weather.getIc().equals("01n")) {
            img_dgree_my.setImageResource(R.drawable.n01);
        } else if (weather.getIc().equals("02d")) {
            img_dgree_my.setImageResource(R.drawable.d02);
        } else if (weather.getIc().equals("02n")) {
            img_dgree_my.setImageResource(R.drawable.n02);
        } else if (weather.getIc().equals("03d")) {
            img_dgree_my.setImageResource(R.drawable.d03);
        } else if (weather.getIc().equals("04d")) {
            img_dgree_my.setImageResource(R.drawable.d04);
        } else if (weather.getIc().equals("09d")) {
            img_dgree_my.setImageResource(R.drawable.d09);
        } else if (weather.getIc().equals("10d")) {
            img_dgree_my.setImageResource(R.drawable.d10);
        } else if (weather.getIc().equals("10n")) {
            img_dgree_my.setImageResource(R.drawable.n10);
        } else if (weather.getIc().equals("11d")) {
            img_dgree_my.setImageResource(R.drawable.d11);
        } else if (weather.getIc().equals("13d")) {
            img_dgree_my.setImageResource(R.drawable.d13);
        } else if (weather.getIc().equals("50d")) {
            img_dgree_my.setImageResource(R.drawable.d50);
        }
    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
