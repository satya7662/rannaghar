package com.plabon.rannaghar.interfaces;

import com.plabon.rannaghar.api.clients.RestClient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeBannerApiInterface {

  String JSONURL = RestClient.BASE_URL + "api/v1/";

  // @GET("json_parsing.php")
  @GET("readbanner.php")
  Call<String> getString(@Query("s") String inputStuid);

}
