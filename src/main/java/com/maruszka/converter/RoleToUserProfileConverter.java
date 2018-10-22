package com.maruszka.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.maruszka.entity.Malt;
import com.maruszka.services.MaltService;
 
 
/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, Malt>{
 
    @Autowired
    MaltService maltService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Malt convert(Object element) {
        Integer theId = Integer.parseInt((String)element);
        Malt malt= maltService.getMalt(theId);
        System.out.println("Profile : "+ malt);
        return malt;
    }
     
}