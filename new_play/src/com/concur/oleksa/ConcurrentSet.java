package com.concur.oleksa;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

/*
* Java Program to remove key value pair from Map while 
* iteration. 
*/
public class ConcurrentSet {

    public static void main(String[] args) throws Exception {

    ConcurrentHashMap<String, Integer> certificationCosts = new ConcurrentHashMap<>();
    certificationCosts.put("OCAJP", 246);
    certificationCosts.put("OCPJP", 246);
    certificationCosts.put("Spring Core", 200);
    certificationCosts.put("Spring Web", 200);
    certificationCosts.put("OCMJEA", 300);
    System.out.println(certificationCosts);


//    Set concurrentSet = certificationCosts.keySet();

//    System.out.println("before adding element into concurrent set: " + concurrentSet);
//     concurrentSet.add("OCEJWCD"); // will throw UnsupportedOperationExcetpion
//    System.out.println("after adding element into concurrent set: " + concurrentSet);

    // creating concurrent hash set in Java 8 using newKeySet() method
//    Set concurrentHashSet = certificationCosts.newKeySet();
//
//
//    concurrentHashSet.add("OCEJWCD");
//    concurrentHashSet.contains("OCEJWCD");
//    concurrentHashSet.remove("OCEJWCD");
//    
//    System.out.println("after adding element into concurrent HashSet: " + concurrentSet);

    
    // you can also use keySet(defaultValue) method to add element into Set
    KeySetView<String, Integer> concurrentSet = certificationCosts.keySet(-1); 
    concurrentSet.add("Spring enterprise"); // value will be -1 but no error
    concurrentSet.add("hello");
    System.out.println(concurrentSet.contains("hello"));
    concurrentSet.remove("OCPJP");

    System.out.println(certificationCosts);
    

    }


}
