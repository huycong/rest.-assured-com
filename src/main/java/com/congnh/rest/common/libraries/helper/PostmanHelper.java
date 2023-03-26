package com.congnh.rest.common.libraries.helper;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostmanHelper {


    @Test
    public void RunPostman() {

//


        String tkType = "prod_mng_login_hungnk2";
        String[] tkTypes = {
                "uat_portal_login",
                "uat_mng_login",
                "prod_mng_login",
                "prod_portal_login",
        };
        String token = getAccessToken(tkType);

//        String outres = "\n" +
//                "  │ 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiIwOGQ5Y2YzYi01NDAwLTg3OGItODkyOC1kZDk2MzI3NTBkMTkiLCJyb2x" +
//                "  │ lIjoiNWY5MWMwZDktNGI3Yy00ODIzLTgyM2QtMDZjNjJjMzEyYWY4IiwianRpIjoiNjlhM2NiMzktNzdjMC00NjljLTg3ZGItYmNkNjQ2NmU4ZmIyI" +
//                "  │ iwiRGF0YVR5cGVzIjoiMiIsIlRpbWV6b25lT2Zmc2V0IjoiLTQyMCIsIm5iZiI6MTY0NTc1NzgzNCwiZXhwIjoxNjQ1NzY1MDM0LCJpYXQiOjE2NDU" +
//                "  │ 3NTc4MzQsImlzcyI6Imh0dHBzOi8vdWF0LWFwaS1tYmEudmluaG9tZXMudm4iLCJhdWQiOiJodHRwczovL3VhdC1hcGktbWJhLnZpbmhvbWVzLnZuI" +
//                "  │ n0.GukHNPHUzPm3WiMzRTuPStoBHdCrbN8KapwBRXYOQpU'\n" +
//                "  └";
//        String tkn = extractToken(outres);
//        System.out.println(tkn);
    }

    public static String getAccessToken(String tkType) {
//        System.out.println("tkType: " +tkType);
        String response = runNewMan(tkType);
//        System.out.println("response: "+response);

        return extractToken(response);
    }

    public static String runNewMan(String filename) {
        try {
//            String filen = "newman run src/test/resources/mba_"+filename+".json";
//            System.out.println(filen);
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "newman run src/test/resources/mba_"+filename+".json --insecure");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            String outp="";
            while (true) {
                line = r.readLine();
                outp +=line;
//                System.out.println(line);
                if (line == null) { break; }
            }
//
            return outp;
        }
        catch (IOException ioe){
            System.out.println("IOException");
            return "";
        }

    }

    public static String extractToken(String response) {
        Pattern pattern = Pattern.compile("'ey(.*?)'");
        Matcher matcher = pattern.matcher(response);
        String token = "";
        if (matcher.find())
        {
            token = "ey"+matcher.group(1);
            token = token.replace("│","");
            token = token.replace(" ","");
            token = token.replace("\n", "").replace("\r", "");

            token = token.replace(" ","");
        }
        System.out.println("Token: "+ token);
        return token;

    }

}

