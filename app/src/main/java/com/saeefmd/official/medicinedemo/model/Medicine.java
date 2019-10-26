
package com.saeefmd.official.medicinedemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medicine {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("drugs")
    @Expose
    private String drugs;
    @SerializedName("indications")
    @Expose
    private String indications;
    @SerializedName("therapeutic_class")
    @Expose
    private String therapeuticClass;
    @SerializedName("pharmacology")
    @Expose
    private String pharmacology;
    @SerializedName("dosage")
    @Expose
    private String dosage;
    @SerializedName("interaction")
    @Expose
    private String interaction;
    @SerializedName("contraindications")
    @Expose
    private String contraindications;
    @SerializedName("side_effects")
    @Expose
    private String sideEffects;
    @SerializedName("pregnancy")
    @Expose
    private String pregnancy;
    @SerializedName("precautions")
    @Expose
    private String precautions;
    @SerializedName("storage")
    @Expose
    private String storage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public String getPharmacology() {
        return pharmacology;
    }

    public void setPharmacology(String pharmacology) {
        this.pharmacology = pharmacology;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInteraction() {
        return interaction;
    }

    public void setInteraction(String interaction) {
        this.interaction = interaction;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getPregnancy() {
        return pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        this.pregnancy = pregnancy;
    }

    public String getPrecautions() {
        return precautions;
    }

    public void setPrecautions(String precautions) {
        this.precautions = precautions;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

}
