package com.concur.oleksa;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HashMapInitCap {

    public static void main(String[] args) {
        
        int n = 16;
//        HashMap<Integer, Object> map = new HashMap<>(n, 1f);
//        HashMap<Integer, Object> map = new HashMap<>(n);
        HashMap<Integer, Object> map = new HashMap<>((int) (n / 0.75f));
        System.out.println(map.size());
        for (int i = 0; i < n; i++) {
            map.put(i, new Object());
        }
        System.out.println(map.size());

    }

}
