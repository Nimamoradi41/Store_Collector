package com.example.store_collector;


import com.example.Models.Change_Pass_Model;
import com.example.Models.Change_Pass_Response;
import com.example.Models.LoginResponse;
import com.example.Models.Model_Action;
import com.example.Models.ResGetOrderItem;
import com.example.Models.ResStartCollect;
import com.example.Models.Res_SetAction;
import com.example.Models.ResponseOreder;
import com.example.Models.Response_Safir;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    @Multipart
    @POST("/api/Account/Login")
    Call<LoginResponse> login(@Part("body") RequestBody body);


    @Multipart
    @POST("/api/Account/ConfirmUser")
    Call<ConfirmSmsResponse> ConfirmUser(@Part("body") RequestBody body);

    @Multipart
    @POST("/api/Account/ChangePassword")
    Call<Change_Pass_Response> Change_Pass(@Header("Authorization") String token,@Part("body") Change_Pass_Model body);



    @Multipart
    @POST("/api/Jorchin/StartCollect")
    Call<ResStartCollect> StartCollect(@Header("Authorization") String token,@Part("body") RequestBody body);


    @Multipart
    @POST("/api/Jorchin/EndCollect")
    Call<ResStartCollect>  EndCollect(@Header("Authorization") String token,@Part("body") RequestBody body);


    @Multipart
    @POST("/api/Jorchin/GetOrderItem")
    Call<ResGetOrderItem> GetOrderItem(@Header("Authorization") String token, @Part("body") RequestBody body);

    @Multipart
    @POST("/api/safir/GetOrderItem")
    Call<ResGetOrderItem> GetOrderItem_Safir(@Header("Authorization") String token, @Part("body") RequestBody body);



    @Multipart
    @POST("/api/safir/SetAction")
    Call<Res_SetAction> SetAction(@Header("Authorization") String token, @Part("body") Model_Action body);






    @POST("/api/Jorchin/GetOrder")
    Call<ResponseOreder> GetOrder(@Header("Authorization") String token);


    @POST("/api/safir/GetOrder")
    Call<Response_Safir> GetOrder_Safir(@Header("Authorization") String token);




}
