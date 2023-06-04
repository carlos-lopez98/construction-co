package com.solvd.constructionco.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceRegistry {


    Map<Class<?>, Object> services = new HashMap<Class<?>, Object>;


    public void registerService(Object object){

        if(services.containsKey(object.getClass())){
            break;
        }else{
            services.put(object.getClass(), object);
        }
    }

}
