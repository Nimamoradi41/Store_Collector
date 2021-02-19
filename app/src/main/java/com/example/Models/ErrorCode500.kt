package com.example.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorCode500 {

    @SerializedName("IsSuccess")
    @Expose
    private var isSuccess: Boolean? = null

    @SerializedName("StatusCode")
    @Expose
    private var statusCode: Int? = null

    @SerializedName("Message")
    @Expose
    private var message: String? = null

    fun ErrorCode500(isSuccess: Boolean?, statusCode: Int?, message: String?) {
        this.isSuccess = isSuccess
        this.statusCode = statusCode
        this.message = message
    }

    fun getSuccess(): Boolean? {
        return isSuccess
    }

    fun setSuccess(success: Boolean?) {
        isSuccess = success
    }

    fun getStatusCode(): Int? {
        return statusCode
    }

    fun setStatusCode(statusCode: Int?) {
        this.statusCode = statusCode
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }
}