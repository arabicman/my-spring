package com.junxiao.spring;

import com.junxiao.spring.annotation.Component;
import com.junxiao.spring.annotation.ComponentScan;
import com.junxiao.spring.annotation.Scope;

import java.io.File;
import java.net.URL;

/***
 * Spring容器类
 */
public class MyApplicationContext {

    private Class configClass;

    public MyApplicationContext(Class configClass) {
        this.configClass = configClass;
        //检查配置类是否有@ComponentScan注解
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            //如果有，获取扫描路径
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value(); //扫描路径 "com.junxiao.service"
            String pathStr = path.replace(".", "/"); //相对路径 "com/junxiao/service"

            ClassLoader classLoader = MyApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(pathStr);//传入相对路径,并获取绝对路径

            File file = new File(resource.getFile());
            //System.out.println(file);

            if (file.isDirectory()) {
                File[] files = file.listFiles();

                for (File f : files) {
                    String fileName = f.getAbsolutePath();
                    //System.out.println(fileName);

                    if (fileName.endsWith(".class")) {
                        String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                        className = className.replace("\\", ".");
                        //System.out.println(className);
                        try {
                            Class<?> clazz = classLoader.loadClass(className);
                            if (clazz.isAnnotationPresent(Component.class)) {
                                //BeanDefinition
                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setType(clazz);
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                    beanDefinition.setScope(scopeAnnotation.value());
                                } else {
                                    beanDefinition.setScope("singleton");

                                }


                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }


        }
    }

    public Object getBean(String beanName) {

        return null;
    }
}
