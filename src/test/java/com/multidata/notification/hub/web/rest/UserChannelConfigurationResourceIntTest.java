package com.multidata.notification.hub.web.rest;

import com.multidata.notification.hub.NotificationHubApp;

import com.multidata.notification.hub.domain.UserChannelConfiguration;
import com.multidata.notification.hub.repository.UserChannelConfigurationRepository;
import com.multidata.notification.hub.service.UserChannelConfigurationService;
import com.multidata.notification.hub.service.dto.UserChannelConfigurationDTO;
import com.multidata.notification.hub.service.mapper.UserChannelConfigurationMapper;
import com.multidata.notification.hub.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static com.multidata.notification.hub.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserChannelConfigurationResource REST controller.
 *
 * @see UserChannelConfigurationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotificationHubApp.class)
public class UserChannelConfigurationResourceIntTest {

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SLACK_TOKEN_1 = "AAAAAAAAAA";
    private static final String UPDATED_SLACK_TOKEN_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SLACK_TOKEN_2 = "AAAAAAAAAA";
    private static final String UPDATED_SLACK_TOKEN_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SLACK_TOKEN_3 = "AAAAAAAAAA";
    private static final String UPDATED_SLACK_TOKEN_3 = "BBBBBBBBBB";

    @Autowired
    private UserChannelConfigurationRepository userChannelConfigurationRepository;

    @Autowired
    private UserChannelConfigurationMapper userChannelConfigurationMapper;
    
    @Autowired
    private UserChannelConfigurationService userChannelConfigurationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restUserChannelConfigurationMockMvc;

    private UserChannelConfiguration userChannelConfiguration;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserChannelConfigurationResource userChannelConfigurationResource = new UserChannelConfigurationResource(userChannelConfigurationService);
        this.restUserChannelConfigurationMockMvc = MockMvcBuilders.standaloneSetup(userChannelConfigurationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserChannelConfiguration createEntity() {
        UserChannelConfiguration userChannelConfiguration = new UserChannelConfiguration()
            .mail(DEFAULT_MAIL)
            .slackToken1(DEFAULT_SLACK_TOKEN_1)
            .slackToken2(DEFAULT_SLACK_TOKEN_2)
            .slackToken3(DEFAULT_SLACK_TOKEN_3);
        return userChannelConfiguration;
    }

    @Before
    public void initTest() {
        userChannelConfigurationRepository.deleteAll();
        userChannelConfiguration = createEntity();
    }

    @Test
    public void createUserChannelConfiguration() throws Exception {
        int databaseSizeBeforeCreate = userChannelConfigurationRepository.findAll().size();

        // Create the UserChannelConfiguration
        UserChannelConfigurationDTO userChannelConfigurationDTO = userChannelConfigurationMapper.toDto(userChannelConfiguration);
        restUserChannelConfigurationMockMvc.perform(post("/api/user-channel-configurations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userChannelConfigurationDTO)))
            .andExpect(status().isCreated());

        // Validate the UserChannelConfiguration in the database
        List<UserChannelConfiguration> userChannelConfigurationList = userChannelConfigurationRepository.findAll();
        assertThat(userChannelConfigurationList).hasSize(databaseSizeBeforeCreate + 1);
        UserChannelConfiguration testUserChannelConfiguration = userChannelConfigurationList.get(userChannelConfigurationList.size() - 1);
        assertThat(testUserChannelConfiguration.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testUserChannelConfiguration.getSlackToken1()).isEqualTo(DEFAULT_SLACK_TOKEN_1);
        assertThat(testUserChannelConfiguration.getSlackToken2()).isEqualTo(DEFAULT_SLACK_TOKEN_2);
        assertThat(testUserChannelConfiguration.getSlackToken3()).isEqualTo(DEFAULT_SLACK_TOKEN_3);
    }

    @Test
    public void createUserChannelConfigurationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userChannelConfigurationRepository.findAll().size();

        // Create the UserChannelConfiguration with an existing ID
        userChannelConfiguration.setId("existing_id");
        UserChannelConfigurationDTO userChannelConfigurationDTO = userChannelConfigurationMapper.toDto(userChannelConfiguration);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserChannelConfigurationMockMvc.perform(post("/api/user-channel-configurations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userChannelConfigurationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserChannelConfiguration in the database
        List<UserChannelConfiguration> userChannelConfigurationList = userChannelConfigurationRepository.findAll();
        assertThat(userChannelConfigurationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllUserChannelConfigurations() throws Exception {
        // Initialize the database
        userChannelConfigurationRepository.save(userChannelConfiguration);

        // Get all the userChannelConfigurationList
        restUserChannelConfigurationMockMvc.perform(get("/api/user-channel-configurations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userChannelConfiguration.getId())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].slackToken1").value(hasItem(DEFAULT_SLACK_TOKEN_1.toString())))
            .andExpect(jsonPath("$.[*].slackToken2").value(hasItem(DEFAULT_SLACK_TOKEN_2.toString())))
            .andExpect(jsonPath("$.[*].slackToken3").value(hasItem(DEFAULT_SLACK_TOKEN_3.toString())));
    }
    
    @Test
    public void getUserChannelConfiguration() throws Exception {
        // Initialize the database
        userChannelConfigurationRepository.save(userChannelConfiguration);

        // Get the userChannelConfiguration
        restUserChannelConfigurationMockMvc.perform(get("/api/user-channel-configurations/{id}", userChannelConfiguration.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userChannelConfiguration.getId()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.slackToken1").value(DEFAULT_SLACK_TOKEN_1.toString()))
            .andExpect(jsonPath("$.slackToken2").value(DEFAULT_SLACK_TOKEN_2.toString()))
            .andExpect(jsonPath("$.slackToken3").value(DEFAULT_SLACK_TOKEN_3.toString()));
    }

    @Test
    public void getNonExistingUserChannelConfiguration() throws Exception {
        // Get the userChannelConfiguration
        restUserChannelConfigurationMockMvc.perform(get("/api/user-channel-configurations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateUserChannelConfiguration() throws Exception {
        // Initialize the database
        userChannelConfigurationRepository.save(userChannelConfiguration);

        int databaseSizeBeforeUpdate = userChannelConfigurationRepository.findAll().size();

        // Update the userChannelConfiguration
        UserChannelConfiguration updatedUserChannelConfiguration = userChannelConfigurationRepository.findById(userChannelConfiguration.getId()).get();
        updatedUserChannelConfiguration
            .mail(UPDATED_MAIL)
            .slackToken1(UPDATED_SLACK_TOKEN_1)
            .slackToken2(UPDATED_SLACK_TOKEN_2)
            .slackToken3(UPDATED_SLACK_TOKEN_3);
        UserChannelConfigurationDTO userChannelConfigurationDTO = userChannelConfigurationMapper.toDto(updatedUserChannelConfiguration);

        restUserChannelConfigurationMockMvc.perform(put("/api/user-channel-configurations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userChannelConfigurationDTO)))
            .andExpect(status().isOk());

        // Validate the UserChannelConfiguration in the database
        List<UserChannelConfiguration> userChannelConfigurationList = userChannelConfigurationRepository.findAll();
        assertThat(userChannelConfigurationList).hasSize(databaseSizeBeforeUpdate);
        UserChannelConfiguration testUserChannelConfiguration = userChannelConfigurationList.get(userChannelConfigurationList.size() - 1);
        assertThat(testUserChannelConfiguration.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testUserChannelConfiguration.getSlackToken1()).isEqualTo(UPDATED_SLACK_TOKEN_1);
        assertThat(testUserChannelConfiguration.getSlackToken2()).isEqualTo(UPDATED_SLACK_TOKEN_2);
        assertThat(testUserChannelConfiguration.getSlackToken3()).isEqualTo(UPDATED_SLACK_TOKEN_3);
    }

    @Test
    public void updateNonExistingUserChannelConfiguration() throws Exception {
        int databaseSizeBeforeUpdate = userChannelConfigurationRepository.findAll().size();

        // Create the UserChannelConfiguration
        UserChannelConfigurationDTO userChannelConfigurationDTO = userChannelConfigurationMapper.toDto(userChannelConfiguration);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserChannelConfigurationMockMvc.perform(put("/api/user-channel-configurations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userChannelConfigurationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserChannelConfiguration in the database
        List<UserChannelConfiguration> userChannelConfigurationList = userChannelConfigurationRepository.findAll();
        assertThat(userChannelConfigurationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteUserChannelConfiguration() throws Exception {
        // Initialize the database
        userChannelConfigurationRepository.save(userChannelConfiguration);

        int databaseSizeBeforeDelete = userChannelConfigurationRepository.findAll().size();

        // Get the userChannelConfiguration
        restUserChannelConfigurationMockMvc.perform(delete("/api/user-channel-configurations/{id}", userChannelConfiguration.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserChannelConfiguration> userChannelConfigurationList = userChannelConfigurationRepository.findAll();
        assertThat(userChannelConfigurationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserChannelConfiguration.class);
        UserChannelConfiguration userChannelConfiguration1 = new UserChannelConfiguration();
        userChannelConfiguration1.setId("id1");
        UserChannelConfiguration userChannelConfiguration2 = new UserChannelConfiguration();
        userChannelConfiguration2.setId(userChannelConfiguration1.getId());
        assertThat(userChannelConfiguration1).isEqualTo(userChannelConfiguration2);
        userChannelConfiguration2.setId("id2");
        assertThat(userChannelConfiguration1).isNotEqualTo(userChannelConfiguration2);
        userChannelConfiguration1.setId(null);
        assertThat(userChannelConfiguration1).isNotEqualTo(userChannelConfiguration2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserChannelConfigurationDTO.class);
        UserChannelConfigurationDTO userChannelConfigurationDTO1 = new UserChannelConfigurationDTO();
        userChannelConfigurationDTO1.setId("id1");
        UserChannelConfigurationDTO userChannelConfigurationDTO2 = new UserChannelConfigurationDTO();
        assertThat(userChannelConfigurationDTO1).isNotEqualTo(userChannelConfigurationDTO2);
        userChannelConfigurationDTO2.setId(userChannelConfigurationDTO1.getId());
        assertThat(userChannelConfigurationDTO1).isEqualTo(userChannelConfigurationDTO2);
        userChannelConfigurationDTO2.setId("id2");
        assertThat(userChannelConfigurationDTO1).isNotEqualTo(userChannelConfigurationDTO2);
        userChannelConfigurationDTO1.setId(null);
        assertThat(userChannelConfigurationDTO1).isNotEqualTo(userChannelConfigurationDTO2);
    }
}
