package clazz.init;

import java.util.HashMap;
import java.util.Map;

class User {
    private String name;
    private int age;
    public User(String name, int age) { this.name = name; this.age = age; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

class Resource {
    static {            System.out.println("Resource loaded.");        }
//    private Map<String, String> map;
    private Map<String, User> map;
//    private final Map<String, User> map;
    public Resource() {
        map = new HashMap<>();
//        map.put("1", "one");
//        map.put("2", "two");
//        map.put("3", "three");
//        map.put("4", "four");
//        map.put("5", "five");
//        map.put("6", "six");
//        map.put("7", "seven");
//        map.put("8", "eight");
//        map.put("9", "nine");
//        map.put("10", "ten");
        map.put("1", new User("Anton", 23));
        map.put("2", new User("Dima", 14));
          map.put("3", new User("Vita", 19));
          map.put("4", new User("Danya", 20));
          map.put("5", new User("Sonya", 14));
          map.put("6", new User("Jora", 24));
          map.put("7", new User("Lexa", 19));
          map.put("8", new User("Vova", 29));
          map.put("9", new User("Vica", 20));
          map.put("10", new User("Lesya", 24));
          try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Map<String, User> getMap() {
        return map;
    }
}

public class LazyInit {
    private static final int LIMIT = 10;

    static {            System.out.println("LazyInit loaded.");        }    
    
    
    /**
     * No guarantee that this is thread-safe, so it is NOT thread-safe.
     * Implementation of VM is free to reorder instructions s.t.
     * reference to resource will be obtained earlier than resource ctor finishes or called.
     * @author atpt34
     *
     */
    private static class DoubleChecked {
        static {            System.out.println("ResourceChecked loaded.");        }
        private static Resource resource;
        public static Resource getInstance() {
            if (resource == null) {
                synchronized (DoubleChecked.class) {
                    if (resource == null) {
                        resource = new Resource();
                    }
                }
            } else {
                // so already init?
                Map<String, User> map = resource.getMap();
                System.out.println(map.get("1") == null);
                System.out.println(map.get("10") == null);
            }
            return resource;
        }        
    }
    
    // ThreadLocal also possible
    private static class Foo {
        /** If perThreadInstance.get() returns a non-null value, this thread
           has done synchronization needed to see initialization
           of helper */
            private final ThreadLocal perThreadInstance = new ThreadLocal();
            private Resource helper = null;
            public Resource getRes() {
                if (perThreadInstance.get() == null) createHelper();
                return helper;
            }
            private final void createHelper() {
                synchronized(this) {
                    if (helper == null)
                        helper = new Resource();
                }
            // Any non-null value would do as the argument here
                perThreadInstance.set(perThreadInstance);
            }
       }
    
    /*
     *  preferable approach, since
     *  class loading and init are synchronized
     */
    private static class ResourceFactory {        
        static {            System.out.println("ResourceFactory loaded.");        }
        private static class ResourceHolder {
            public static Resource resource = new Resource();
        }
        public static Resource getInstance() {
            return ResourceHolder.resource;
        }
    }

    public static void main(String[] args) {

        System.out.println("main");
//        Resource instance = DoubleChecked.getInstance();
//        Resource instance = ResourceFactory.getInstance();
//        Map<String, String> map = instance.getMap();
//        for (int i = 1; i <= LIMIT; i++) {
//            System.out.println(map.get(Integer.toString(i)));
//        }
        
        class Runner implements Runnable {
            // Runnable runner = new Runnable() { // anonymous in
            @Override
            public void run() {     
//              Map<String, String> map = DoubleChecked.getInstance().getMap();
//              Map<String, String> map = ResourceFactory.getInstance().getMap();
//              Map<String, User> map = ResourceFactory.getInstance().getMap();
                Map<String, User> map = DoubleChecked.getInstance().getMap();
//                Map<String, User> map = new Foo().getRes().getMap();
              for (int i = LIMIT; i > 0; i--) {
//                  System.out.println(map.get(Integer.toString(i)).getName());
                  System.out.println(map.get(Integer.toString(i)));
                  System.out.println(DoubleChecked.getInstance());
              }
            }
        };
        
        System.out.println("after Runnable declaration");
              
        try {            
            Thread[] ta = new Thread[LIMIT];
            for (int i = 0; i < LIMIT; i++) {
//                ta[i] = new Thread(runner);
                ta[i] = new Thread(new Runner());
            }
            for (int i = 0; i < LIMIT; i++) {
                ta[i].start();
            }
            for (int i = 0; i < LIMIT; i++) {
                ta[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
    }

}
