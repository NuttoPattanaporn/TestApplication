package com.example.searchtest.service.data;

import com.example.searchtest.service.age.Age;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QueryService {

    @GET("age")
    Call<List<Age>> getAge();
}
