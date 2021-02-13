package com.demo.featureswitches.featureswitches.service;

import com.demo.featureswitches.featureswitches.dto.Request;
import com.demo.featureswitches.featureswitches.entity.UserFeatures;
import com.demo.featureswitches.featureswitches.repository.UserFeaturesRepository;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserFeaturesService {
    @Autowired
    private UserFeaturesRepository userFeaturesRepository;

    public UserFeatures findByFeatureNameAndEmail (String featureName, String email){
        return userFeaturesRepository.findByFeatureNameAndEmail(featureName,email);
    }

    public void saveFeatureRequest(Request request) {
        UserFeatures userFeatures = userFeaturesRepository.findByFeatureNameAndEmail(request.getFeatureName(), request.getEmail());
        if(null != userFeatures){// if the user already exist we just need to update
            userFeatures.setEnable(request.isEnable());
            userFeatures.setUpdateDate(new Date());
            userFeaturesRepository.save(userFeatures);
        }else{// otherwise create the user
            Mapper mapper = new DozerBeanMapper(); // map the request to the entity
            userFeaturesRepository.save( mapper.map(request,UserFeatures.class));
        }

    }
}
