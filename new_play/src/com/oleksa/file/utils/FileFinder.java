package com.oleksa.file.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileFinder {

    public List<File> find(String dirname, String hello) {
        List<File> res = new ArrayList<>();
        
        File dir = new File(dirname);
        System.out.println(dir.exists());
        System.out.println(dir.isDirectory());
        System.out.println(dir.getAbsolutePath());
        
        
        
        
        Queue<File> queue = new LinkedList<File>();
        queue.add(dir);
        while(!queue.isEmpty()) {
            File poll = queue.poll();
            File[] listFiles = poll.listFiles();
            for (File file : listFiles) {            
                //              System.out.println(file.getAbsolutePath() + ", " + file.isDirectory());
                //              System.out.println(file.getName() + ", " + file.isDirectory());
                if (file.isDirectory()) {
                    System.out.println(file.getAbsolutePath());
                    queue.add(file);
                } else {
                    if (file.getName().contains(hello)) {
                        res.add(file);
                    }
                } 
            }
        }
        return res;
    }

}
