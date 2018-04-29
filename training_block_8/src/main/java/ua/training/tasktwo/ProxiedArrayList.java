package ua.training.tasktwo;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;


/**
 * 
 * @author atpt34
 *
 * @param <T>
 */
public class ProxiedArrayList<T> implements InvocationHandler {

    private static final Set<String> ILLEGAL_METHOD_NAMES = new HashSet<>();
    static {
        ILLEGAL_METHOD_NAMES.add("remove");
        ILLEGAL_METHOD_NAMES.add("set");
        ILLEGAL_METHOD_NAMES.add("removeAll");
        ILLEGAL_METHOD_NAMES.add("replace");
    }
    
    private final List<T> list;
    
    public static <T> List<T> newInstance() {
        return newInstance(new ArrayList<>());
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> newInstance(List<T> list) {
        return (List<T>) Proxy.newProxyInstance(ArrayList.class.getClass().getClassLoader(), 
                new Class<?>[]{List.class, RandomAccess.class, Cloneable.class, Serializable.class}, 
                new ProxiedArrayList<T>(list));
    }
    
    private ProxiedArrayList() {
        this.list = new ArrayList<>();
    }
    
    private ProxiedArrayList(List<T> list) {
        this.list = list;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (ILLEGAL_METHOD_NAMES.contains(methodName)) {
            throw new UnsupportedOperationException("illegal method!");
        }
        return method.invoke(list, args);
    }
    
    

}
