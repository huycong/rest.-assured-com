package com.congnh.rest.common.libraries.helper;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class LogsHelper {

    public static ResourceBundle rb = ResourceBundle.getBundle("settings");

    public static void write_to_file(String classname, String content) throws IOException {
        String path = "src/test/java/" + classname.replace(".", "/") + ".json";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(content);
        writer.close();

        String output_name = classname.replace(".", "_") + "_"+ TimeHelper.timeFormat(System.currentTimeMillis(), "yyyy_MM_dd_HH_mm_ss")+  ".json";
        String output_path = "src/test/output/" + output_name.trim();
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(output_path));
        writer2.write(content);
        writer2.close();

    }


    public static void write_to_file(String classname, String content, Boolean append) throws IOException {
        String path = "src/test/java/" + classname.replace(".", "/") + ".json";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, append));
        writer.write(content);
        writer.close();

        String output_name = classname.replace(".", "_") + "_" + TimeHelper.timeFormat(System.currentTimeMillis(), "yyyy_MM_dd_HH_mm_ss") + ".json";
        String output_path = "src/test/output/" + output_name.trim();
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(output_path));
        writer2.write(content);
        writer2.close();

    }

    private static void clearFile(String path) throws IOException {
        BufferedWriter writer0 = new BufferedWriter(new FileWriter(path));
        writer0.write("");
        writer0.close();
    }

    /*
    jsonPath = "$.data.rows"
     */
    public static void show_as_csv(String resString, String jsonPath, String fileName, List<String> keys) throws IOException {
        String path = "src/test/java/" + fileName.replace(".", "/") + ".txt";
        clearFile(path);
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));

        String jsonArray = JsonPath.parse(resString).read(jsonPath).toString();

        JSONArray arr = new JSONArray(jsonArray);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject data = (JSONObject) arr.get(i);
            String content = "";
            for (String k : keys) {
                if (data.has(k)) {
                    content += data.get(k).toString() + "|";
                }
            }
            writer.write(content + "\n");
        }
        writer.close();

    }


    public static void print_keys(String json_arr, String in_view, String in_commnet) {
        JSONArray arr = new JSONArray(json_arr);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject data = (JSONObject) arr.get(i);
            if (in_commnet.equals("")) {
                System.out.println("\"" + data.getString(in_view) + "\",");
            } else {

                System.out.println("\"" + data.getString(in_view) + "\",//" + data.get(in_commnet).toString());
            }
        }
    }

    public static void console(String classname, String method, Response res) throws IOException {
        if (rb.getString("logging.pretty_print").equals("yes")) {
            res.prettyPrint();
        }
//        if (method.equals(Constants.METHOD_GET) && rb.getString("logging.write_get_to_file").equals("yes")) {
        if (rb.getString("logging.write_get_to_file").equals("yes")) {
            String content = res.asString();
            if (rb.getString("logging.remove_audit_infos").equals("yes")) {
                content = content.replaceAll("\n(.*?)\"createdAt(.*?)\n", "\n");
                content = content.replaceAll("\n(.*?)\"updatedAt(.*?)\n", "\n");
                content = content.replaceAll("\n(.*?)\"createdBy(.*?)\n", "\n");
                content = content.replaceAll("\n(.*?)\"updatedBy(.*?)\n", "\n");
            }
            write_to_file(classname, content);
        }
    }

    public static void console(String classname, Response res) throws IOException {
        if (rb.getString("logging.pretty_print").equals("yes")) {
            res.prettyPrint();
        }
        if (rb.getString("logging.write_get_to_file").equals("yes")) {
            String content = res.asString();
            if (rb.getString("logging.remove_audit_infos").equals("yes")) {
                content = content.replaceAll("\n(.*?)\"createdAt(.*?)\n", "\n");
                content = content.replaceAll("\n(.*?)\"updatedAt(.*?)\n", "\n");
                content = content.replaceAll("\n(.*?)\"createdBy(.*?)\n", "\n");
                content = content.replaceAll("\n(.*?)\"updatedBy(.*?)\n", "\n");
            }
            write_to_file(classname, content);
        }
    }

    public static void console(Response res) {
        if (rb.getString("logging.pretty_print").equals("yes")) {
            res.prettyPrint();
        }
    }

    public static void consoleJson(String json) {
        System.out.println(beautiful(json));
    }

    public static String beautiful(String input) {
        int tabCount = 0;

        StringBuilder inputBuilder = new StringBuilder();
        char[] inputChar = input.toCharArray();

        for (int i = 0; i < inputChar.length; i++) {
            String charI = String.valueOf(inputChar[i]);
            if (charI.equals("}") || charI.equals("]")) {
                tabCount--;
                if (!String.valueOf(inputChar[i - 1]).equals("[") && !String.valueOf(inputChar[i - 1]).equals("{"))
                    inputBuilder.append(newLine(tabCount));
            }
            inputBuilder.append(charI);

            if (charI.equals("{") || charI.equals("[")) {
                tabCount++;
                if (String.valueOf(inputChar[i + 1]).equals("]") || String.valueOf(inputChar[i + 1]).equals("}"))
                    continue;

                inputBuilder.append(newLine(tabCount));
            }

            if (charI.equals(",")) {
                inputBuilder.append(newLine(tabCount));
            }
        }

        return inputBuilder.toString();
    }

    private static String newLine(int tabCount) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        for (int j = 0; j < tabCount; j++)
            builder.append("  ");

        return builder.toString();
    }

    public static void cOut(String text){
        System.out.println(text);
    }
}
