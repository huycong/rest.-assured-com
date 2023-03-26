package com.congnh.rest.common.libraries.validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonMinh {
    public static boolean jsonHaveKeys(String[] keys, JSONObject jobj){
        if (keys.length>0){
            boolean all_has = true;
            for (String k: keys) {
                if (!jobj.has(k)){
                    all_has = false;
                    break;
                }
            }
            return all_has;
        }
        else{
            return false;
        }
    }

    public static void jsonlackKeys( String[] keys, JSONObject  jobj){
        if (keys.length>0){
            String lackKeys="";
            List<String> ls = new ArrayList<>();
            for (String k: keys) {
                if (!jobj.has(k)){
//                    lackKeys += k+",";
                    ls.add(k);
                }
            }
            System.out.println("response json lack Keys: "+ String.join(",", ls));
        }
    }


    public static boolean jsonCompare(String lvl, JSONObject json_exp, JSONObject json_real){
        List<String> kstr_exp = jsonKeysToString(json_exp);
        List<String> kstr_real = jsonKeysToString(json_real);
        boolean summary = true;
        boolean isEqual = kstr_real.equals(kstr_exp);
        if (isEqual){
            System.out.println(lvl+" all keys are same ");
        }
        else{
            summary = false;
            System.out.println(lvl+" all keys are different ");
            System.out.println("json_exp keys: "+String.join(",", kstr_exp));
            System.out.println("json_real keys: "+String.join(",", kstr_real));
        }

        for (String ke: kstr_exp) {
            if (kstr_real.contains(ke)){
                boolean isSame = jsonCompare(ke, new JSONObject(json_exp.get(ke)), new JSONObject(json_real.get(ke)));
                if (!isSame){
                    summary = false;
                }
            }
            else{
                System.out.println("kstr_real does not contain key: "+ke);
            }
        }

        return summary;
//        jsonToMap(json_exp);
//        if (keys.length>0){
//            String lackKeys="";
//            List<String> ls = new ArrayList<>();
//            for (String k: keys) {
//                if (!jobj.has(k)){
////                    lackKeys += k+",";
//                    ls.add(k);
//                }
//            }
//            System.out.println("response json lack Keys: "+ String.join(",", ls));
//        }
    }

    public static List<String> jsonKeysToString(JSONObject json) throws JSONException {
        Iterator<?> keys = json.keys();
        List<String> kstr = new ArrayList<>();
        while( keys.hasNext() ){
            String key = (String)keys.next();
            kstr.add(key);
        }
        return kstr;
    }
//        HashMap<String, String> map = new HashMap<String, String>();
//        JSONObject jObject = new JSONObject(t);
//        Iterator<?> keys = jObject.keys();
//        while( keys.hasNext() ){
//            String key = (String)keys.next();
//            String value = jObject.get(key).toString();
//            map.put(key, value);
//        }
//        System.out.println("json : "+jObject);
//        System.out.println("map : "+map);
//    }
}
