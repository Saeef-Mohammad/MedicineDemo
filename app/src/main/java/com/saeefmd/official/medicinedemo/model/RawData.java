
package com.saeefmd.official.medicinedemo.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RawData {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("medicines")
    @Expose
    private List<Medicine> medicines = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

}
