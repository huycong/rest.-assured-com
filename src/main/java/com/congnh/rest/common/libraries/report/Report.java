package com.congnh.rest.common.libraries.report;

import com.congnh.rest.common.constants.Constants;
import com.congnh.rest.common.libraries.helper.FileHelper;
import com.congnh.rest.common.libraries.helper.LogsHelper;
import com.congnh.rest.common.libraries.request.Request;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

public class Report {

    @Test
    public void send() throws IOException {
        String json_path = Constants.FOLDER_ROOT + "resources/swagger_2021_12_18.json";
        String json_body = FileHelper.file_content(json_path);
        String _url = "http://10.148.40.166/atms/swagger/save_swagger_apis";
        Response res = Request.send(_url, Constants.METHOD_POST, Constants.TOKEN_NULL, json_body);
        LogsHelper.console(res);
    }

    @Test
    public void submit() throws IOException {
        String json_path = Constants.FOLDER_ROOT + "src/test/java/libraries/external/reports.json";
        String json_body = FileHelper.file_content(json_path);
        String _url = "http://10.148.40.166/atms/swagger/submit_multi";
        Response res = Request.send(_url, Constants.METHOD_POST, Constants.TOKEN_NULL, json_body);
        LogsHelper.console(res);
    }


    @Test
    public void clearResult() throws IOException {
        String module = "access-control";
        String base_url= "uat2-api-grandpark.vsmart.net/gp/"+module+"/api/v0";
        String body_json = "{\n" +
                "    \"base_url\": \""+base_url+"\"\n" +
                "  }\n" ;
        String _url = "http://10.148.40.166/atms/swagger/clear_result";
        Response res = Request.send(_url, Constants.METHOD_POST, Constants.TOKEN_NULL, body_json);
        LogsHelper.console(res);
    }


    public static void submit(String module, String resource, String path, String method, long resTime) throws IOException {
        module = module.replace("_","-");
        module = module.toLowerCase();
        String base_url= "uat2-api-grandpark.vsmart.net/gp/"+module+"/api/v0";
        String body_json = "{\n" +
                "    \"base_url\": \""+base_url+"\",\n" +
                "    \"resource\": \""+resource+"\",\n" +
                "    \"path\": \""+path+"\",\n" +
                "    \"method\": \""+method+"\",\n" +
                "    \"response_time\": "+resTime+"\n" +
                "  }\n" ;
        String _url = "http://10.148.40.166/atms/swagger/submit_single";
        Response res = Request.send(_url, Constants.METHOD_POST, Constants.TOKEN_NULL, body_json);
        LogsHelper.console(res);
    }
}
