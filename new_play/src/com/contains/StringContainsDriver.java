package com.contains;

public class StringContainsDriver {

    public static void main(String[] args) {

        
        String red = "redirect:";
        String page = red + "/index.jsp";
        if(page.contains(red)) {
            System.out.println(page.replaceAll(red, ""));
        } else {
            System.out.println("fail");
        }

    }

}
