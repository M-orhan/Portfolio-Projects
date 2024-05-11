package com.example.checkweather;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    SharedPreferences GET;
    SharedPreferences.Editor SET;

    private String str_gorsel;

    private String str_tarih;
    private String str_havaDurumuYazisi;
    private String str_derece;
    private String str_nem;
    private String str_ruzgar_hizi;
    private String str_basinc;
    private EditText edt_sehir_ismi;
    private TextView txt_sehir, txt_derece, txt_nem,txt_ruzgar_hizi,txt_basinc,tarihDegiskeni,havaDurumuYaziDegiskeni;
    private ImageView img_ara;
    private ImageView img_resim;
    LinearLayout layoutDegiskeni;

    private void init() {

        edt_sehir_ismi = findViewById(R.id.edt_sehir_ismi);
        txt_sehir = findViewById(R.id.txt_sehir);
        txt_derece = findViewById(R.id.txt_derece);
        img_ara = findViewById(R.id.img_ara);
        img_resim = findViewById(R.id.image_resim);
        txt_nem = findViewById(R.id.nem_text);
        txt_basinc=findViewById(R.id.basinc_text);
        txt_ruzgar_hizi = findViewById(R.id.ruzgar_hizi_text);
        layoutDegiskeni=(LinearLayout)findViewById(R.id.linear_layout_1);
        tarihDegiskeni=findViewById(R.id.tarih);
        havaDurumuYaziDegiskeni=findViewById(R.id.hava_durumu_yazisi);

        GET = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SET = GET.edit();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul"));
        calendar.setTime(date);
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy", new Locale("tr", "TR"));
        String formattedDate = formatter.format(calendar.getTime());
        tarihDegiskeni.setText(formattedDate);

        edt_sehir_ismi.setText(GET.getString("sehirIsmi", "Düzce"));
        txt_derece.setText(GET.getString("derece", "16°C"));

        arat();



        img_ara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arat();

            }
        });

        edt_sehir_ismi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arat();
            }
        });



    }

    public void arat() {

        if (edt_sehir_ismi.getText().toString().equals("")) {
            Toast.makeText(this, "Bir şehir ismi giriniz", Toast.LENGTH_SHORT).show();
        } else {

            String alinan_sehir_ismi = edt_sehir_ismi.getText().toString().trim();
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + alinan_sehir_ismi + "&appid=" + "287ce4827ec2b85d3747d2b5a5043e53"+"&lang=tr";


            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(String response) {


                    try {
                        JSONObject jsonObject = new JSONObject(response);


                        // Şehir İsmi Al
                        String str_sehir = jsonObject.getString("name");
                        txt_sehir.setText("" + str_sehir.toUpperCase());

                        JSONArray jsonArray = jsonObject.getJSONArray("weather");
                        JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                        JSONObject jsonObject3 = jsonArray.getJSONObject(0);
                        str_havaDurumuYazisi = jsonObject3.getString("description");
                        havaDurumuYaziDegiskeni.setText(str_havaDurumuYazisi.toUpperCase());
                        str_gorsel = jsonObject2.getString("icon");
                        Picasso.get().load("https://openweathermap.org/img/wn/" + str_gorsel + "@2x.png").into(img_resim);

                        // Sıcaklık Bilgisini Al
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(1);
                        JSONObject jsonObject4 = jsonObject.getJSONObject("main");
                        double dbl_sicaklik = jsonObject4.getDouble("temp");
                        double celcius = (dbl_sicaklik - 272.15);
                        str_derece = df.format(celcius);
                        txt_derece.setText(str_derece + "°C");

                        // Nem Bilgisi Al
                        str_nem = jsonObject4.getString("humidity");
                        txt_nem.setText(str_nem + "%");

                        // Rüzgar Hızı Bilgisi Al
                        JSONObject jsonObject6 = jsonObject.getJSONObject("wind");
                        str_ruzgar_hizi = jsonObject6.getString("speed");
                        txt_ruzgar_hizi.setText(str_ruzgar_hizi + " km/s");

                        JSONObject jsonObject7 = jsonObject.getJSONObject("main");
                        str_basinc = jsonObject7.getString("pressure");
                        txt_basinc.setText(str_basinc + " hPa");

                        Log.d(TAG, "onResponse: Çalıştı");

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "onResponse: Çalışmadı");
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(MainActivity.this, "HATA", Toast.LENGTH_SHORT).show();

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);

            SET.putString("sehirIsmi", alinan_sehir_ismi);
            SET.putString("havaGorseli", str_gorsel);
            SET.putString("derece", str_derece);
            SET.commit();

        }

    }

    public void fab(View view) {

        arat();

    }
}