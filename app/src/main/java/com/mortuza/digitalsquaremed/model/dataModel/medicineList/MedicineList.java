
package com.mortuza.digitalsquaremed.model.dataModel.medicineList;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedicineList implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Drugs_For")
    @Expose
    private String drugsFor;
    @SerializedName("Drug_Class")
    @Expose
    private String drugClass;
    @SerializedName("Brand_Name")
    @Expose
    private String brandName;
    @SerializedName("Contains")
    @Expose
    private String contains;
    @SerializedName("Dosage_Form")
    @Expose
    private String dosageForm;
    @SerializedName("Manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("Price")
    @Expose
    private String price;
    private final static long serialVersionUID = -1858764619442828948L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugsFor() {
        return drugsFor;
    }

    public void setDrugsFor(String drugsFor) {
        this.drugsFor = drugsFor;
    }

    public String getDrugClass() {
        return drugClass;
    }

    public void setDrugClass(String drugClass) {
        this.drugClass = drugClass;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
