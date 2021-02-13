package com.demo.featureswitches.featureswitches.repository;

import com.demo.featureswitches.featureswitches.entity.UserFeatures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeaturesRepository extends JpaRepository<UserFeatures,Long> {
public UserFeatures findByFeatureNameAndEmail(String featureName ,String email);
}
