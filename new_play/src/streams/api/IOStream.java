package streams.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;

public class IOStream {

    
    static transient int st;
    
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        InputStream is;
        
//        is.
        Reader r;
//        r.
        Serializable s; // see serialization in java
        // transient fields !!
        
        
        BufferedInputStream bis;
//        bis.
        bis = new BufferedInputStream(System.in);
        
        
        int c;
        while((c = System.in.read()) != -1) {
            System.out.write((char)c);
        }
        
//        BufferedReader br = new BufferedReader(new Reader(System.in));
//                System.out.println(br.readLine());
//        int a = bis.read();
//        System.out.println(a);
//        System.err.format("%d", a);
        
    }

}
