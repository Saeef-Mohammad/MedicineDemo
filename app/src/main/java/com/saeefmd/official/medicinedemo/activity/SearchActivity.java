package com.saeefmd.official.medicinedemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.saeefmd.official.medicinedemo.R;
import com.saeefmd.official.medicinedemo.database.DatabaseHelper;
import com.saeefmd.official.medicinedemo.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private TextView textDrug;
    private TextView textIndications;
    private TextView textTherapeuticClass;
    private TextView textPharmacology;
    private TextView textDosage;
    private TextView textInteraction;
    private TextView textContraindications;
    private TextView textSideEffects;
    private TextView textPregnancy;
    private TextView textPrecautions;
    private TextView textStorage;

    private AutoCompleteTextView autoTextSearchKey;

    private DatabaseHelper mDatabaseHelper;

    private List<String> drugNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        autoTextSearchKey = findViewById(R.id.auto_text_search_key);

        textDrug = findViewById(R.id.text_details_drug);
        textIndications = findViewById(R.id.text_details_indications);
        textTherapeuticClass = findViewById(R.id.text_details_therapeutic_class);
        textPharmacology = findViewById(R.id.text_details_pharmacology);
        textDosage = findViewById(R.id.text_details_dosage);
        textInteraction = findViewById(R.id.text_details_interaction);
        textContraindications = findViewById(R.id.text_details_contraindications);
        textSideEffects = findViewById(R.id.text_details_side_effects);
        textPregnancy = findViewById(R.id.text_details_pregnancy);
        textPrecautions = findViewById(R.id.text_details_precautions);
        textStorage = findViewById(R.id.text_details_storage);

        mDatabaseHelper = new DatabaseHelper(SearchActivity.this);

        drugNamesList = new ArrayList<>();

        setAutoSuggestions();

        Button buttonSearch = findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query = autoTextSearchKey.getText().toString();

                Medicine medicine = mDatabaseHelper.getMedicine(query);

                if (medicine != null) {

                    textDrug.setText(medicine.getDrugs());
                    textIndications.setText("Indications: " + medicine.getIndications());
                    textTherapeuticClass.setText("Therapeutic Class: " + medicine.getTherapeuticClass());
                    textPharmacology.setText("Pharmacology: " + medicine.getPharmacology());
                    textDosage.setText("Dosage: " + medicine.getDosage());
                    textInteraction.setText("Interaction: " + medicine.getInteraction());
                    textContraindications.setText("Contraindications: " + medicine.getContraindications());
                    textSideEffects.setText("Side Effects: " + medicine.getSideEffects());
                    textPregnancy.setText("Pregnancy: " + medicine.getPregnancy());
                    textPrecautions.setText("Precautions: " + medicine.getPrecautions());
                    textStorage.setText("Storage: " + medicine.getStorage());
                } else {

                    Toast.makeText(SearchActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setAutoSuggestions() {

        drugNamesList = mDatabaseHelper.getAllDrugNames();

        ArrayAdapter<String> drugsAdapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1, drugNamesList);

        autoTextSearchKey.setAdapter(drugsAdapter);
        autoTextSearchKey.setThreshold(1);
    }
}
