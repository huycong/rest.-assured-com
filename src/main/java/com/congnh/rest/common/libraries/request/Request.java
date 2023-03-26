package com.congnh.rest.common.libraries.request;

import com.congnh.rest.common.constants.Constants;
import com.congnh.rest.common.libraries.helper.TextHelper;
import com.congnh.rest.common.libraries.validator.JsonMinh;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import com.github.dzieciou.testing.curl.Platform;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang.ArrayUtils;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Request {

    public static JSONObject send_get_data(String url, String method, String token, String body_json, Map<String, Object> params) {
        Response res = Request.send(url, method, token
                , body_json, params);
        System.out.println("Response:");
        JSONObject json_res = new JSONObject(res.asString());
        return json_res;
    }

    public static void send_validate(String url, String method, String token, String body_json, Map<String, Object> params, Integer statusCode, String[] keys) {
        send_validate(url, method, token, body_json, params, statusCode, keys, false);
    }

    public static void send_validate(String url, String method, String token, String body_json, Map<String, Object> params, Integer statusCode, String[] keys, Boolean isValidateBodyCode) {
        Response res = Request.send(url, method, token
                , body_json, params);
        System.out.println("Response:");
        res.prettyPrint();

        if (isValidateBodyCode) {

            JSONObject json_res = new JSONObject(res.asString());
            Integer bd_code = json_res.getInt("code");
            Assert.assertTrue(bd_code.equals(statusCode));
        } else {
            res.then().statusCode(statusCode);
        }
        if (Objects.nonNull(keys)) {

            JSONObject json_res = new JSONObject(res.asString());
            JsonMinh.jsonlackKeys(keys, json_res);
            Assert.assertTrue(JsonMinh.jsonHaveKeys(keys, json_res));
        }
    }

    private static RestAssuredConfig getConfig() {
        Options options = Options
                .builder()
                .printMultiliner()
                .targetPlatform(Platform.UNIX)  //để không nháy đôi giá kí tự "" trong data-binary
                .useLongForm() //để hiện --header thay vì -H
                .build();
        RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options);
        config.httpClient(HttpClientConfig.httpClientConfig()
                .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 100000)
                .setParam(CoreConnectionPNames.SO_TIMEOUT, 100000));
        return config;
    }

    public static Response send(String url, String method, String token) {

        return send(url, method, token, "", new HashMap<>(), "application/json", "");

    }

    public static Response send(String url, String method, String token, Map<String, Object> params) {
        return send(url, method, token, "", params, "application/json", "*/*");

    }

    public static Response send(String url, String method, String token, String body_json) {
        return send(url, method, token,body_json, new HashMap<>(), "application/json", "*/*");

    }

    public static Response send(String url, String method, String token, String body_json, Map<String, Object> params) {
        return send(url, method, token,body_json, params, "application/json", "*/*");
    }

    public static Response send(String url, String method, String token, String body_json, Map<String, Object> params, String accept) {

        return send(url, method, token,body_json, params, "application/json", accept);

    }
    public static Response send(String url, String method, String token, String body_json, Map<String, Object> params, Map<String, Object> headers ){

        return send(url, method, token,body_json, params, "application/json", headers);

    }

    public static Response send(String url, String method, String token, String body_json, Map<String, Object> params, String contentType, String accept) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", accept);
        return send(url, method, token,body_json, params, contentType, headers);

    }
    public static Response send(String url, String method, String token, String body_json, Map<String, Object> params, String contentType, Map<String, Object> headers) {
        RestAssuredConfig config = getConfig();
        RequestSpecification rs = RestAssured.given()
                .log()
                .all()
                .config(config)
                .relaxedHTTPSValidation()
                .urlEncodingEnabled(false);
        if (!token.equals("")){
            rs.header("Authorization", token);
        }
        if (!contentType.equals("")){
            rs.contentType(contentType);
        }
        if (!headers.isEmpty()){
            for (String key : headers.keySet()) {
                rs.header(key, headers.get(key));
            }
        }
        if (!body_json.equals("")) {
            body_json = TextHelper.escapeSpace(body_json);
            body_json = TextHelper.escapeNewLine(body_json);
            rs.body(body_json);
        }
        if (!params.isEmpty()) {
            rs.queryParams(params);
        }
        if (ArrayUtils.contains(Constants.METHODS, method)) {
            return rs.request(method, url);
        } else {
            return null;
        }

    }

    public static Response upload(String url, String method, String token, String body_json, Map<String, Object> params, String contentType, Map<String, Object> headers, File multipartData) {
        return upload(url, method, token, body_json, params, contentType, headers, List.of(multipartData));

    }
    public static Response upload(String url, String method, String token, String body_json, Map<String, Object> params, String contentType, Map<String, Object> headers, List<File> multipartDatas) {
        RestAssuredConfig config = getConfig();
        RequestSpecification rs = RestAssured.given()
                .log()
                .all()
                .config(config)
                .relaxedHTTPSValidation()
                .urlEncodingEnabled(false);
        if (!token.equals("")){
            rs.header("Authorization", token);
        }
        if (!headers.isEmpty()){
            for (String key : headers.keySet()) {
                rs.header(key, headers.get(key));
            }
        }
        if (!body_json.equals("")) {
            body_json = TextHelper.escapeSpace(body_json);
            body_json = TextHelper.escapeNewLine(body_json);
            rs.body(body_json);
        }
        if (!params.isEmpty()) {
            rs.queryParams(params);
        }
        if (multipartDatas.size()>0) {
            String id = multipartDatas.size()==1 ? "file":"files";
            rs.contentType("multipart/form-data");
            for (File file : multipartDatas) {
                rs.multiPart(id, file);
            }
        }

        if (!contentType.equals("")){
            rs.contentType(contentType);
        }

        if (ArrayUtils.contains(Constants.METHODS, method)) {
            return rs.request(method, url);
        } else {
            return null;
        }

    }
}

