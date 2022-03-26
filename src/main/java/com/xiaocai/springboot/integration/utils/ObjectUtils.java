package com.xiaocai.springboot.integration.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/24 17:24
 */
public class ObjectUtils {

    public static Object[] asArray(Object ... args){
        return args;
    }

    public static Object toObject(Map<String, Object> map, Class<?> clz) {
        Object o = null;

        try {
            o = clz.newInstance();
            if (map != null && map.size() > 0) {
                Map<String, Object> lowerMap = new HashMap();
                Iterator iterator = map.keySet().iterator();

                while(iterator.hasNext()) {
                    String key = (String)iterator.next();
                    Object value = map.get(key);
                    lowerMap.put(key.toLowerCase(), value);
                }

                Method[] ms = clz.getMethods();
                if (ms != null && ms.length > 0) {
                    Method[] methodArr = ms;
                    int length = ms.length;

                    for(int i = 0; i < length; ++i) {
                        Method m = methodArr[i];
                        String name = m.getName();
                        if (name.startsWith("set")) {
                            String lowerKey = name.substring(3).toLowerCase();
                            if (lowerMap.containsKey(lowerKey)) {
                                Object value = lowerMap.get(lowerKey);
                                BeanUtils.setProperty(o, name.substring(3), value);
                                //这里可以直接用反射
//                                m.invoke(o,value);
                            }
                        }
                    }
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return o;
    }
}
