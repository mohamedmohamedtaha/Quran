package com.MohamedTaha.Imagine.Quran;

import com.MohamedTaha.Imagine.Quran.model.azan.Azan;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {
    @GET("calendar")
    Call<Azan> getAzan(@Query("latitude") String latitude, @Query("longitude") String longitude, @Query("method") int method);

}
