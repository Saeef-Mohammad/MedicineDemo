package com.saeefmd.official.medicinedemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.saeefmd.official.medicinedemo.R;
import com.saeefmd.official.medicinedemo.adapter.MedicineAdapter;
import com.saeefmd.official.medicinedemo.model.RawData;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_API = "https://brsbd.org/services/medicineList.php";

    private RequestQueue mQueue;
    private String mData;
    private RawData mRawData;

    private RecyclerView mRecyclerViewMedicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(MainActivity.this);

        mRecyclerViewMedicines = findViewById(R.id.recycler_view_medicines);
        mRecyclerViewMedicines.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button buttonParse = findViewById(R.id.button_parse);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                        MedicineAdapter medicineAdapter = new MedicineAdapter(mRawData.getMedicines(), MainActivity.this);
                        mRecyclerViewMedicines.setAdapter(medicineAdapter);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });

        mQueue.add(objectRequest);
    }
}
