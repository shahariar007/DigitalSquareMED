package com.mortuza.digitalsquaremed.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mortuza.digitalsquaremed.R;
import com.mortuza.digitalsquaremed.model.dataModel.medicineList.MedicineList;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<MedicineList> medicineLists = new ArrayList<>();
    private static final String TAG = "RecycleAdapter";

    public RecycleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMedicineLists(List<MedicineList> medicineLists) {
        this.medicineLists = medicineLists;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: " + i);
        if (i == 0)
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.root_view_layout, viewGroup, false));
        else
            return new ViewHolder2(LayoutInflater.from(mContext).inflate(R.layout.footer, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder.getItemViewType() == 0) {
            ViewHolder viewHolder1 = (ViewHolder) viewHolder;
            viewHolder1.medicineName.setText(medicineLists.get(i).getBrandName());
            viewHolder1.manufacturer.setText(medicineLists.get(i).getManufacturer());
        } else if (viewHolder.getItemViewType() == 1) {
            ViewHolder2 viewHolder2 = (ViewHolder2) viewHolder;
        }
    }


    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: " + position + " size= " + medicineLists.size());
       // if (medicineLists.size() > 0) {
            if (medicineLists.get(position) == null) {
                return 1;
            }
      //  }
        return 0;
    }

    @Override
    public int getItemCount() {
        return medicineLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView medicineName;
        TextView manufacturer;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            medicineName = itemView.findViewById(R.id.medicineName);
            manufacturer = itemView.findViewById(R.id.manufacturer);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        ViewHolder2(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
