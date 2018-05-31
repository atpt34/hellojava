package com.oleksa.file.utils;

import java.io.File;
import java.util.List;

public class FileFinderDriver {

    public static void main(String[] args) {

        String dirname = "/home/oleksiiubuntu/Public/java_programming";
        String hello = "Load";
        List<File> result = new FileFinder().find(dirname, hello);
        System.out.println("Results:");
        for (File file : result) {
            System.out.println(file.getAbsolutePath());
        }

    }

}
