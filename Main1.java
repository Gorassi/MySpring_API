package com.kciray;

import com.kciray.beans.factory.stereotype.Resource;

import java.io.File;

public class Main1 {

    public static void main(String[] args) {
        String myPackage = "com.kciray.";
        File folder = new File("C://www/MySpringAPI/src/com/kciray");

        BeanContainer beanContainer = new BeanContainer();

        for(File file : folder.listFiles()) {
            if (file.toString().endsWith(".java") ) {
                int len = file.getName().toString().length();
                String fileName = file.getName();
                String fullNameFile = myPackage + file.getName().substring(0, len - 5);
                try {
                    Class clazz = Class.forName(fullNameFile);
                    boolean annotationPresent = clazz.isAnnotationPresent(Resource.class);

                    if(annotationPresent){
                        String beanName = fileName.substring(0,1).toLowerCase() + fileName.substring(1, len - 5);
                        Object bean = clazz.newInstance();
                        beanContainer.addBean(beanName, bean);
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println("Programme added to BeanContainer the next beans with name: ");
        for(String name : beanContainer.getContainer().keySet()) System.out.println(name);

        beanContainer.injectBeans();

        System.out.println("Object : " + beanContainer.getObject("suit") + " has dependency : " + ((Suit) beanContainer.getObject("suit")).getPocket());
    }
}
