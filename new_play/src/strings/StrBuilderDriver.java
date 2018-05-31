package strings;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StrBuilderDriver {

    public static void main(String[] args) {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedInputStream bis = new BufferedInputStream(System.in);
        
//        String pathname = "/home/oleksiiubuntu/Public/java_programming/primestxt/first_generated_ten_thousand_primes.txt";
        // sb 12 s 384
        String pathname = "/home/oleksiiubuntu/Public/java_programming/primestxt/first_generated_million_primes.txt";
        // sb 208 s INF
        BufferedReader br;
        StringBuilder sb = new StringBuilder();
        String res = "";
        try {
            br = new BufferedReader(new FileReader(new File(pathname)));
      
      
//            System.out.println("enter: ");
//            String s = br.readLine();
//            System.out.println(s);
//            int b = bis.read();
//            System.out.println(b);  
            
            long start = System.nanoTime();
            while(br.ready()) {
//                System.out.println(br.readLine());
                sb.append(br.readLine());
//                res += br.readLine();         /// very slow !
            }
            long fin = System.nanoTime();
            System.out.println((fin - start)/ 1000000);
//            System.out.println(sb);
//            System.out.println(res);
            
        } catch (IOException e) {
            e.printStackTrace();
        }  
            
        
    }

}
