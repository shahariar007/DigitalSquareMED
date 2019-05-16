package com.mortuza.digitalsquaremed.model.network;


import com.mortuza.digitalsquaremed.model.dataModel.medicineList.Medicine;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface APIServices {

    @Streaming
    @POST
    Observable<Response<ResponseBody>> downloadFile(@Url String fileUrl);

    @POST("getMedicineList")
    @FormUrlEncoded
    Call<Medicine> getDataServer(@Field("page") int page);
}
