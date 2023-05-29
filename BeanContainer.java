package com.kciray;

import com.kciray.beans.factory.annotation.MyInjection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanContainer {

    private Map<String, Object> container = new HashMap<>();

    public void addBean(String beanName, Object bean) {
        container.put(beanName, bean);
    }

    public Map<String, Object> getContainer() {
        return container;
    }

    public Object getObject(String beanName) { return container.get(beanName);}

    public void injectBeans() {
        System.out.println("Injecting beans ...");
        for (Object bean : container.values()) {

            for (Field field : bean.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(MyInjection.class)) {

                    for (Object dependency : container.values()) {
                        if (dependency.getClass().equals(field.getType())) {

                            String setterName = "set" + field.getName().substring(0,1).toUpperCase()
                                    + field.getName().substring(1);

                            try {
                                Method setter = bean.getClass().getMethod(setterName, dependency.getClass());
                                setter.invoke(bean, dependency);
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
