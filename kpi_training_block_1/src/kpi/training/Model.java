package kpi.training;

public class Model {
    
    private static final String HELLO = "Hello";
    private static final String WORLD = "world!";
    private static final String HELLO_WORLD = HELLO + ' ' + WORLD;

    public static String getHello() {
        return HELLO;
    }

    public static String getWorld() {
        return WORLD;
    }

    public static boolean verifyHelloWorld(String hello, String world) {
        return HELLO.equals(hello) && WORLD.equals(world);
    }
    
    /**
     * Hello world String
     * @return HELLO_WORLD
     */
    public static String getHelloWorld(){
        return HELLO_WORLD;
    }
}
