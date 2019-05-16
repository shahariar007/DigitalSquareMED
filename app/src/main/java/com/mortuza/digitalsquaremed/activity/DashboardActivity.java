package com.mortuza.digitalsquaremed.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mortuza.digitalsquaremed.Adapters.RecycleAdapter;
import com.mortuza.digitalsquaremed.R;
import com.mortuza.digitalsquaremed.model.dataModel.medicineList.Medicine;
import com.mortuza.digitalsquaremed.model.dataModel.medicineList.MedicineList;
import com.mortuza.digitalsquaremed.model.network.APIClient;
import com.mortuza.digitalsquaremed.model.network.APIServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    APIServices apiServices;
    RecycleAdapter recycleAdapter;
    List<MedicineList> medicineLists;
    static int page = 1;
    private static final String TAG = "DashboardActivity";
    private static boolean allReadyBool = true;
    FrameLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.menuRecycler);
        apiServices = APIClient.getInstance().create(APIServices.class);
        linearLayout=findViewById(R.id.mainLayout);
        setRecyclerView();
        getData(page);
    }

    public void setRecyclerView() {
        medicineLists = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recycleAdapter = new RecycleAdapter(this);
        recyclerView.setAdapter(recycleAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int totalItemCount = layoutManager.getItemCount();
                int lastVisible = layoutManager.findLastVisibleItemPosition();

                boolean endHasBeenReached = lastVisible + 1 >= totalItemCount;
                if (totalItemCount > 0 && endHasBeenReached) {

                    if (!allReadyBool) {
                        Log.d(TAG, "onScrollStateChanged: totalItemCount > 0 && endHasBeenReached");
                        getData(page);
                    }
                }
                Log.d(TAG, "onScrollStateChanged: " + totalItemCount);
                Log.d(TAG, "onScrollStateChanged: " + lastVisible);


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void getData(int pages) {
        allReadyBool = true;
        Call<Medicine> response = apiServices.getDataServer(pages);
        response.enqueue(new Callback<Medicine>() {
            @Override
            public void onResponse(Call<Medicine> call, Response<Medicine> response) {
                if (response != null && response.isSuccessful() && response.body().getCount() != 0) {
                    medicineLists.addAll(response.body().getMedicineList());
                    recycleAdapter.setMedicineLists(medicineLists);
                    recycleAdapter.notifyDataSetChanged();
                    page += 1;
                    allReadyBool = false;
                }


            }

            @Override
            public void onFailure(Call<Medicine> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
                allReadyBool = false;
            }
        });
    }


}
