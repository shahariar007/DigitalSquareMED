
package com.mortuza.digitalsquaremed.model.dataModel.medicineList;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "Medicine")
public class Medicine implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = true)
    private int uid;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("medicine_list")
    @Expose
    @Ignore
    private List<MedicineList> medicineList = null;
    private final static long serialVersionUID = -1868518956848806138L;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<MedicineList> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<MedicineList> medicineList) {
        this.medicineList = medicineList;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
