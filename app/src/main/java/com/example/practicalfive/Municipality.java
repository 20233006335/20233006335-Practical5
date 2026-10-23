package com.example.practicalfive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Municipality extends AppCompatActivity {

    private TextView nameText;
    private ImageView cityImage;
    private TextView infoText;
    private Button siteButton;
    private TextProcessor processor;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        nameText = findViewById(R.id.municipalityName);
        cityImage = findViewById(R.id.municipalityImage);
        infoText = findViewById(R.id.municipalityInfo);
        siteButton = findViewById(R.id.visitSiteButton);
        processor = new TextProcessor();

        String passed = getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY);
        city = processor.processMunicipality(passed);

        if (city == null) {
            Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        showCityInfo();

        siteButton.setOnClickListener(v -> openWebsite());
    }

    private void showCityInfo() {
        nameText.setText(city);

        String[] facts = getResources().getStringArray(R.array.municipalities_array);

        int colorId;
        int imgId;
        int index;

        if (city.equals("Beijing")) {
            colorId = R.color.municipality_beijing;
            imgId = R.drawable.beijing;
            index = 0;
        } else if (city.equals("Shanghai")) {
            colorId = R.color.municipality_shanghai;
            imgId = R.drawable.shanghai;
            index = 1;
        } else if (city.equals("Chongqing")) {
            colorId = R.color.municipality_chongqing;
            imgId = R.drawable.chongqing;
            index = 2;
        } else {
            colorId = R.color.municipality_tianjin;
            imgId = R.drawable.tianjin;
            index = 3;
        }

        int c = ContextCompat.getColor(this, colorId);
        nameText.setBackgroundColor(c);
        infoText.setTextColor(c);
        siteButton.setBackgroundTintList(android.content.res.ColorStateList.valueOf(c));
        cityImage.setImageResource(imgId);
        infoText.setText(facts[index]);
    }

    private void openWebsite() {
        String url;
        if (city.equals("Beijing")) {
            url = "https://english.beijing.gov.cn/";
        } else if (city.equals("Shanghai")) {
            url = "https://english.shanghai.gov.cn/";
        } else if (city.equals("Tianjin")) {
            url = "https://en.tj.gov.cn/";
        } else {
            url = "https://english.cq.gov.cn/";
        }

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            startActivity(i);
        } catch (Exception e) {
            Toast.makeText(this, "Cannot open website", Toast.LENGTH_SHORT).show();
        }
    }
}
