package com.example.Inventory_Service.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ApiResponse <T>{

    private boolean success;
    private String message;
    private T data;
    private Object o;




    public ApiResponse(T data, String message, boolean success) {
        this.data=data;
        this.message=message;
        this.success=success;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ApiResponse(String message, T data)
    {
        this.success=true;
        this.message=message;
        this.data=data;
    }

    public ApiResponse(String message)
    {
        this.success=false;
        this.message=message;
    }


}
