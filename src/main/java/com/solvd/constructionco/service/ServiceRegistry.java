package com.solvd.constructionco.service;

import com.solvd.constructionco.exceptions.ServiceAlreadyInRegistry;
import com.solvd.constructionco.exceptions.ServiceNotFoundException;

import java.util.HashMap;
import java.util.Map;

/*Creates a way to bundle my services into a single class*/
public class ServiceRegistry {


    private Map<Class<?>, Object> services = new HashMap<Class<?>, Object>();

    public void registerService(Object object){
        if(services.containsKey(object.getClass())){
          throw new ServiceAlreadyInRegistry("Service is already instantiated, please call ServiceRegistry.getService()");
        }else{
            services.put(object.getClass(), object);
        }
    }

    public Object getService(Class<?> clazz){

        if(services.containsKey(clazz)){
            return services.get(clazz);
        }else{
            throw new ServiceNotFoundException("Service has not been registered, please add to registry");
        }
    }

}
