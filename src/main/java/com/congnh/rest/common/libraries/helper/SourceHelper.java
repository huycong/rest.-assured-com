package com.congnh.rest.common.libraries.helper;

import java.io.File;
import java.io.IOException;

public class SourceHelper {

    public static void deleteTxtFiles(String dir) throws IOException {
        final File folder = new File(dir);
        recursiveDelete(folder);
    }

    private static void recursiveDelete( File dir) throws IOException {

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File f : files) {
                recursiveDelete(f);
            }
        }
        if (dir.getName().contains(".json")) {
            System.out.println("Deleted: " + dir.getName());
            dir.delete();
        }
    }

    public static void showJavaFiles(String dir) throws IOException {
        final File folder = new File(dir);
//        final File folder = new File("src/test/java/automation/access_control");
        recursiveScan(folder);
    }

    private static void recursiveScan( File dir) throws IOException {

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File f : files) {
                recursiveScan(f);
            }
        }
        if (dir.getName().contains(".java")) {
            System.out.println(getClassPath(dir.getPath()));
        }
    }

    private static String getClassPath(String path){
        path = path.replace("src\\test\\java\\","");
        path = path.replace(".java","");
        path = path.replace("\\",".");
        return "<class name=\""+path+"\"></class>";
    }
}
