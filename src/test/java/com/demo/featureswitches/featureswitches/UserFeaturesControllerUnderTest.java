package com.demo.featureswitches.featureswitches;

import com.demo.featureswitches.featureswitches.controller.UserFeaturesController;
import com.demo.featureswitches.featureswitches.entity.UserFeatures;
import com.demo.featureswitches.featureswitches.service.UserFeaturesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserFeaturesController.class)
public class UserFeaturesControllerUnderTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFeaturesService userFeaturesService;

    @Test
    public void getFeatureStatusByEmailAndNameTest() throws Exception {
        UserFeatures mubarakFeatures = new UserFeatures();
        mubarakFeatures.setEmail("User3");
        mubarakFeatures.setFeatureName("login");
        mubarakFeatures.setEnable(true);
        String featureName = "login";
        String email = "User3";
        when(userFeaturesService.findByFeatureNameAndEmail(featureName,email)).thenReturn(mubarakFeatures);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/feature?email=User3&featureName=login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("canAccess").value("true"));

    }
    @Test
    public void createFeature() throws Exception {
        UserFeatures mubarakFeatures = new UserFeatures();
        mubarakFeatures.setEmail("Mubarak");
        mubarakFeatures.setFeatureName("login");
        mubarakFeatures.setEnable(true);
        String name = "login";
        String email = "Mubarak";
        when(userFeaturesService.findByFeatureNameAndEmail(name,email)).thenReturn(mubarakFeatures);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/feature")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"featureName\": \"feature1\",\"email\": \"email\",\"enable\": \"true\"}")
                ).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
