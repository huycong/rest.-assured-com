package com.congnh.rest.common.libraries.helper;

import com.congnh.rest.common.libraries.vendors.JsonSchemaGenerator;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
public class JsonHelper {

    public static void to_schema(String path1, String json) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonSchemaInferrer inferrer = JsonSchemaInferrer.newBuilder()
//                .setSpecVersion(SpecVersion.DRAFT_06)
//                // Requires commons-validator
//                .addFormatInferrers(FormatInferrers.email(), FormatInferrers.ip())
//                .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.notAllowed())
//                .setRequiredPolicy(RequiredPolicies.nonNullCommonFields())
//                .addEnumExtractors(EnumExtractors.validEnum(java.time.Month.class),
//                        EnumExtractors.validEnum(java.time.DayOfWeek.class))
//                .build();
//        JsonNode jnode = mapper.readTree(json);
//        JsonNode resultForSample1 = inferrer.inferForSample(jnode);
//        String schema = mapper.writeValueAsString(resultForSample1);
//        System.out.printf("schema: "+schema);
//        String path = "src/test/java/" + path1.replace(".", "/")+ "_chema.json";;
//        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
//        writer.write(schema);
//        writer.close();

    }

    public static String buildWithList(String arrKey, List<UUID> list) {
        String key = arrKey.equals("") ? "userIds" : arrKey;
        String req = "{\n" +
                "  \"" + key + "\": [\n";
        for (UUID id : list) {
            req += "\"" + id + "\",";
        }
        req += "\"9757dcfd-b9ed-4ac9-a3ca-dc7efabafd27\"";
        return req +
                "  ]\n" +
                "}";
    }

    public static String buildWithListString(String arrKey, List<String> list) {
        String key = arrKey.equals("") ? "userIds" : arrKey;
        String req = "{\n" +
                "  \"" + key + "\": [\n";
        for (String id : list) {
            req += "\"" + id + "\",";
        }
        req += "\"9757dcfd-b9ed-4ac9-a3ca-dc7efabafd27\"";
        return req +
                "  ]\n" +
                "}";
    }

    public static void validate(String classname, Response res) throws IOException {
        String json_name = classname.replace(".", "_") + "_output_schema.json";
        String path = "src/test/resources/" + json_name.toLowerCase();
        String output_json = res.asString();
        String schema = JsonSchemaGenerator.outputAsString("", "", output_json);
//        if (File)
        File file_schema = new File(path);
        if (!file_schema.exists()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(schema);
            writer.close();
        }
        else{
            res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(json_name));
        }
    }

    public static void validate(String classname, Response res, String jsonPath) throws IOException {
        String json_name = classname.replace(".", "_") + "_output_schema.json";
        String path = "src/test/resources/" + json_name.toLowerCase();
        Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider()).build();

        String output_json = JsonPath.using(conf).parse(res.asString()).read(jsonPath).toString();
//        String output_json = obj.toString();
//        LogsHelper.consoleJson(output_json);
        String schema = JsonSchemaGenerator.outputAsString("", "", output_json);
//        if (File)
        File file_schema = new File(path);
        if (!file_schema.exists()){
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(schema);
            writer.close();
        }
        else{
            res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(json_name));
        }
    }
}
