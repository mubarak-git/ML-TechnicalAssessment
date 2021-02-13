package com.demo.featureswitches.featureswitches;

import com.demo.featureswitches.featureswitches.entity.UserFeatures;
import com.demo.featureswitches.featureswitches.repository.UserFeaturesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserFeaturesRepositoryUnderTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserFeaturesRepository userFeaturesRepository;

    @Test
    public void testFindByFeatureNameAndEmail() {
        // given
        UserFeatures mubarakFeatures = new UserFeatures();
        mubarakFeatures.setEmail("User2");
        mubarakFeatures.setFeatureName("login");
        mubarakFeatures.setEnable(true);
        entityManager.persist(mubarakFeatures);
        entityManager.flush();
        // when
        UserFeatures found = userFeaturesRepository.findByFeatureNameAndEmail(mubarakFeatures.getFeatureName(),mubarakFeatures.getEmail());
        // then
        assertThat(found.getFeatureName())
                .isEqualTo(mubarakFeatures.getFeatureName());
    }
}
