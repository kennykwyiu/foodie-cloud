package com.kenny.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @Title: IMOOCJSONResult.java
 * @Package com.kenny.utils
 * @Description: Custom response data structure
 * This class can be provided to H5/ios/Android/WeChat Official Account/Mini Program
 * After receiving this type of data (JSON object), the frontend can implement related functions based on business logic
 * 200: Indicates success
 * 500: Indicates an error, with the error message in the msg field
 * 501: Bean validation error, returns all errors in a map format
 * 502: Interceptor intercepts user token error
 * 555: Exception throw information
 * 556: User qq verification exception
 * @Copyright: Copyright (c) 2020
 * @Company: www.kenny.com
 * @author Kenny
 * @version V1.0
 */
public class IMOOCJSONResult {

    // Define jackson object
//    private static final ObjectMapper MAPPER = new ObjectMapper();

    // Response business status
    private Integer status;

    // Response message
    private String msg;

    // Response data
    private Object data;
    
    @JsonIgnore
    private String ok;	// Not used

    public static IMOOCJSONResult build(Integer status, String msg, Object data) {
        return new IMOOCJSONResult(status, msg, data);
    }

    public static IMOOCJSONResult build(Integer status, String msg, Object data, String ok) {
        return new IMOOCJSONResult(status, msg, data, ok);
    }
    
    public static IMOOCJSONResult ok(Object data) {
        return new IMOOCJSONResult(data);
    }

    public static IMOOCJSONResult ok() {
        return new IMOOCJSONResult(null);
    }
    
    public static IMOOCJSONResult errorMsg(String msg) {
        return new IMOOCJSONResult(500, msg, null);
    }
    
    public static IMOOCJSONResult errorMap(Object data) {
        return new IMOOCJSONResult(501, "error", data);
    }
    
    public static IMOOCJSONResult errorTokenMsg(String msg) {
        return new IMOOCJSONResult(502, msg, null);
    }
    
    public static IMOOCJSONResult errorException(String msg) {
        return new IMOOCJSONResult(555, msg, null);
    }
    
    public static IMOOCJSONResult errorUserQQ(String msg) {
        return new IMOOCJSONResult(556, msg, null);
    }

    public IMOOCJSONResult() {

    }

    public IMOOCJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    public IMOOCJSONResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public IMOOCJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

}
