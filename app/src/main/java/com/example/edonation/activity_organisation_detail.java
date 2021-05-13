package com.example.edonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class activity_organisation_detail extends AppCompatActivity {

    TextView name, location, descriptionDetail, emailDetail;
    Button callBtn, websiteBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_detail);
        name = findViewById(R.id.orgNameDetail);
        location = findViewById(R.id.locationDetail);

        descriptionDetail = findViewById(R.id.descriptionDetail);
        websiteBtn = findViewById(R.id.btnWebsiteDetail);
        callBtn = findViewById(R.id.callBtnDetail);
        emailDetail = findViewById(R.id.emailDetail);
        getIntents();

    }

    private void getIntents() {
        if (getIntent().hasExtra("organisationName") && getIntent().hasExtra("organisationLocation")
                && getIntent().hasExtra("organisationEmail")
                // && getIntent().hasExtra("currentRequirement")
                && getIntent().hasExtra("description")
                && getIntent().hasExtra("website") && getIntent().hasExtra("phone")) {

            String orgName = getIntent().getStringExtra("organisationName");
            System.out.println("hello"+orgName);
            Log.d("namess", orgName);
            String orgLocation = getIntent().getStringExtra("organisationLocation");
            String email = getIntent().getStringExtra("organisationEmail");
            //String currentReq = getIntent().getStringExtra("currentRequirement");
            String description = getIntent().getStringExtra("description");
            String website = getIntent().getStringExtra("website");
            Long phone = getIntent().getLongExtra("phone", 0);

            setDetails(orgName, orgLocation, email, description,
                    website, phone);
        }
    }

    private void setDetails(String orgName, String orgLocation, String email,
                            String description,
                            final String website, final long phone) {
        name.setText(orgName);
        location.setText(orgLocation);
        emailDetail.setText(email);


        if (description.equals("")) {
            descriptionDetail.setText("We aren't accepting any donation at this time. " +
                    "Thank you!!!!!");
        } else {
            descriptionDetail.setText("We are currently looking for " + description);
        }


        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://" + website));
                startActivity(intent);
            }
        });
        final String phoneNo = String.valueOf(phone);

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNo.length() == 10) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse("tel: " + String.valueOf(phone)));

                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                    }
                } else if (phoneNo.length() >= 7 && phoneNo.length() < 10) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse("tel: 01" + String.valueOf(phone)));

                    try {
                        startActivity(intent);
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    private void initComponents() {
        name = findViewById(R.id.orgNameDetail);
        location = findViewById(R.id.locationDetail);

        descriptionDetail = findViewById(R.id.descriptionDetail);
        websiteBtn = findViewById(R.id.btnWebsiteDetail);
        callBtn = findViewById(R.id.callBtnDetail);
        emailDetail = findViewById(R.id.emailDetail);
        toolbar = findViewById(R.id.toolbar);
    }
}