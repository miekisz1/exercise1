package selenium.framework.common.others;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Ioc {
    private static ThreadLocal<Map<Type, Object>> threadContainer;

    static{
        threadContainer = new InheritableThreadLocal<>();
    }

    public static synchronized <T> T getInstance(Class<T> clazz){
        Map<Type, Object> map = getMap();

        Object object = map.get(clazz);
        T t = null;
        if(null == object){
            try{
                object = clazz.getConstructor().newInstance();
            } catch(Exception e){}
        }
        try{
            if(null != object)
                map.put(clazz, object);
            t = (T) object;
        } catch (Exception e){}

        return t;
    }

    public static synchronized void setInstance(Object object){
        Map<Type, Object> map = getMap();

        if(null == map.get(object.getClass()))
            map.put(object.getClass(), object);
        else{
            try{
                map.remove(object.getClass());
            } catch (Exception e){}
            map.put(object.getClass(), object);
        }
    }

    public static synchronized void clearCurrentThread(){
        threadContainer.remove();
    }

    private static synchronized Map<Type, Object> getMap(){
        Map<Type, Object> map = threadContainer.get();
        if(null == map){
            map = new HashMap<>();
            threadContainer.set(map);
        }
        return map;
    }
}
