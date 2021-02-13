package com.demo.featureswitches.featureswitches;

import com.demo.featureswitches.featureswitches.dto.Request;
import com.demo.featureswitches.featureswitches.entity.UserFeatures;
import com.demo.featureswitches.featureswitches.repository.UserFeaturesRepository;
import com.demo.featureswitches.featureswitches.service.UserFeaturesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserFeaturesServiceUnderTest {
    @Autowired
    private UserFeaturesService userFeaturesService;

    @MockBean
    private UserFeaturesRepository userFeaturesRepository;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public UserFeaturesService userFeaturesService() {
            return new UserFeaturesService();
        }
    }
    @Before
    public void setUp() {
        UserFeatures mubarakFeatures = new UserFeatures();
        mubarakFeatures.setEmail("Mubarak");
        mubarakFeatures.setFeatureName("login");
        mubarakFeatures.setEnable(true);
        Mockito.when(userFeaturesRepository.findByFeatureNameAndEmail(mubarakFeatures.getFeatureName(),mubarakFeatures.getEmail()))
                .thenReturn(mubarakFeatures);
    }
    @Test
    public void findByFeatureNameAndEmailTest() {

        String featureName = "login";
        String email = "Mubarak";
        UserFeatures found = userFeaturesService.findByFeatureNameAndEmail(featureName,email);
        assertThat(found.getFeatureName())
                .isEqualTo(featureName);
    }
}
