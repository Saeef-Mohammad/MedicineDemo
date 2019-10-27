package com.saeefmd.official.medicinedemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.saeefmd.official.medicinedemo.R;
import com.saeefmd.official.medicinedemo.adapter.MedicineAdapter;
import com.saeefmd.official.medicinedemo.database.DatabaseHelper;
import com.saeefmd.official.medicinedemo.model.Medicine;
import com.saeefmd.official.medicinedemo.model.RawData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_API = "https://brsbd.org/services/medicineList.php";

    private RequestQueue mQueue;
    private String mData;
    private RawData mRawData;
    private DatabaseHelper mDatabaseHelper;
    private List<Medicine> mMedicineList;
    private MedicineAdapter mMedicineAdapter;

    private RecyclerView mRecyclerViewMedicines;
    private TextView mTextViewNoData;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewNoData = findViewById(R.id.text_view_no_data);
        mProgressBar = findViewById(R.id.progress_bar);

        mQueue = Volley.newRequestQueue(MainActivity.this);

        mMedicineList = new ArrayList<>();

        mDatabaseHelper = new DatabaseHelper(MainActivity.this);

        mRecyclerViewMedicines = findViewById(R.id.recycler_view_medicines);
        mRecyclerViewMedicines.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button buttonParse = findViewById(R.id.button_parse);

        displayMedicines();

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgressBar.setVisibility(View.VISIBLE);

                mDatabaseHelper.deleteAllMedicines();

                JsonParse();
            }
        });
    }

    private void JsonParse() {

        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET, BASE_API, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("RawData: ", response.toString());

                        mData = response.toString();

                        mRawData = new Gson().fromJson(mData, RawData.class);

                        for (int i=0; i<mRawData.getMedicines().size(); i++) {

                            mDatabaseHelper.insertMedicine(mRawData.getMedicines().get(i).getId(),
                                    mRawData.getMedicines().get(i).getDrugs(),
                                    mRawData.getMedicines().get(i).getIndications(),
                                    mRawData.getMedicines().get(i).getTherapeuticClass(),
                                    mRawData.getMedicines().get(i).getPharmacology(),
                                    mRawData.getMedicines().get(i).getDosage(),
                                    mRawData.getMedicines().get(i).getInteraction(),
                                    mRawData.getMedicines().get(i).getContraindications(),
                                    mRawData.getMedicines().get(i).getSideEffects(),
                                    mRawData.getMedicines().get(i).getPregnancy(),
                                    mRawData.getMedicines().get(i).getPrecautions(),
                                    mRawData.getMedicines().get(i).getStorage());
                        }

                        displayMedicines();

                        /*MedicineAdapter medicineAdapter = new MedicineAdapter(mRawData.getMedicines(), MainActivity.this);
                        mRecyclerViewMedicines.setAdapter(medicineAdapter);*/

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });

        mQueue.add(objectRequest);
    }

    private void displayMedicines() {

        if (mDatabaseHelper.getMedicinesCount() > 0) {

            mTextViewNoData.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);

            mMedicineAdapter = new MedicineAdapter(mDatabaseHelper.getAllMediciness(), MainActivity.this);
            mRecyclerViewMedicines.setAdapter(mMedicineAdapter);
        } else {

            mTextViewNoData.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
