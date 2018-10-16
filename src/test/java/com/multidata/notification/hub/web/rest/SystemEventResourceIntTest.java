package com.multidata.notification.hub.web.rest;

import com.multidata.notification.hub.NotificationHubApp;

import com.multidata.notification.hub.domain.SystemEvent;
import com.multidata.notification.hub.repository.SystemEventRepository;
import com.multidata.notification.hub.service.SystemEventService;
import com.multidata.notification.hub.service.dto.SystemEventDTO;
import com.multidata.notification.hub.service.mapper.SystemEventMapper;
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
 * Test class for the SystemEventResource REST controller.
 *
 * @see SystemEventResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotificationHubApp.class)
public class SystemEventResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SystemEventRepository systemEventRepository;

    @Autowired
    private SystemEventMapper systemEventMapper;
    
    @Autowired
    private SystemEventService systemEventService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restSystemEventMockMvc;

    private SystemEvent systemEvent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SystemEventResource systemEventResource = new SystemEventResource(systemEventService);
        this.restSystemEventMockMvc = MockMvcBuilders.standaloneSetup(systemEventResource)
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
    public static SystemEvent createEntity() {
        SystemEvent systemEvent = new SystemEvent()
            .description(DEFAULT_DESCRIPTION);
        return systemEvent;
    }

    @Before
    public void initTest() {
        systemEventRepository.deleteAll();
        systemEvent = createEntity();
    }

    @Test
    public void createSystemEvent() throws Exception {
        int databaseSizeBeforeCreate = systemEventRepository.findAll().size();

        // Create the SystemEvent
        SystemEventDTO systemEventDTO = systemEventMapper.toDto(systemEvent);
        restSystemEventMockMvc.perform(post("/api/system-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemEventDTO)))
            .andExpect(status().isCreated());

        // Validate the SystemEvent in the database
        List<SystemEvent> systemEventList = systemEventRepository.findAll();
        assertThat(systemEventList).hasSize(databaseSizeBeforeCreate + 1);
        SystemEvent testSystemEvent = systemEventList.get(systemEventList.size() - 1);
        assertThat(testSystemEvent.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createSystemEventWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = systemEventRepository.findAll().size();

        // Create the SystemEvent with an existing ID
        systemEvent.setId("existing_id");
        SystemEventDTO systemEventDTO = systemEventMapper.toDto(systemEvent);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemEventMockMvc.perform(post("/api/system-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemEventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemEvent in the database
        List<SystemEvent> systemEventList = systemEventRepository.findAll();
        assertThat(systemEventList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllSystemEvents() throws Exception {
        // Initialize the database
        systemEventRepository.save(systemEvent);

        // Get all the systemEventList
        restSystemEventMockMvc.perform(get("/api/system-events?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemEvent.getId())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    public void getSystemEvent() throws Exception {
        // Initialize the database
        systemEventRepository.save(systemEvent);

        // Get the systemEvent
        restSystemEventMockMvc.perform(get("/api/system-events/{id}", systemEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(systemEvent.getId()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingSystemEvent() throws Exception {
        // Get the systemEvent
        restSystemEventMockMvc.perform(get("/api/system-events/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateSystemEvent() throws Exception {
        // Initialize the database
        systemEventRepository.save(systemEvent);

        int databaseSizeBeforeUpdate = systemEventRepository.findAll().size();

        // Update the systemEvent
        SystemEvent updatedSystemEvent = systemEventRepository.findById(systemEvent.getId()).get();
        updatedSystemEvent
            .description(UPDATED_DESCRIPTION);
        SystemEventDTO systemEventDTO = systemEventMapper.toDto(updatedSystemEvent);

        restSystemEventMockMvc.perform(put("/api/system-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemEventDTO)))
            .andExpect(status().isOk());

        // Validate the SystemEvent in the database
        List<SystemEvent> systemEventList = systemEventRepository.findAll();
        assertThat(systemEventList).hasSize(databaseSizeBeforeUpdate);
        SystemEvent testSystemEvent = systemEventList.get(systemEventList.size() - 1);
        assertThat(testSystemEvent.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingSystemEvent() throws Exception {
        int databaseSizeBeforeUpdate = systemEventRepository.findAll().size();

        // Create the SystemEvent
        SystemEventDTO systemEventDTO = systemEventMapper.toDto(systemEvent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemEventMockMvc.perform(put("/api/system-events")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(systemEventDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SystemEvent in the database
        List<SystemEvent> systemEventList = systemEventRepository.findAll();
        assertThat(systemEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteSystemEvent() throws Exception {
        // Initialize the database
        systemEventRepository.save(systemEvent);

        int databaseSizeBeforeDelete = systemEventRepository.findAll().size();

        // Get the systemEvent
        restSystemEventMockMvc.perform(delete("/api/system-events/{id}", systemEvent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SystemEvent> systemEventList = systemEventRepository.findAll();
        assertThat(systemEventList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemEvent.class);
        SystemEvent systemEvent1 = new SystemEvent();
        systemEvent1.setId("id1");
        SystemEvent systemEvent2 = new SystemEvent();
        systemEvent2.setId(systemEvent1.getId());
        assertThat(systemEvent1).isEqualTo(systemEvent2);
        systemEvent2.setId("id2");
        assertThat(systemEvent1).isNotEqualTo(systemEvent2);
        systemEvent1.setId(null);
        assertThat(systemEvent1).isNotEqualTo(systemEvent2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SystemEventDTO.class);
        SystemEventDTO systemEventDTO1 = new SystemEventDTO();
        systemEventDTO1.setId("id1");
        SystemEventDTO systemEventDTO2 = new SystemEventDTO();
        assertThat(systemEventDTO1).isNotEqualTo(systemEventDTO2);
        systemEventDTO2.setId(systemEventDTO1.getId());
        assertThat(systemEventDTO1).isEqualTo(systemEventDTO2);
        systemEventDTO2.setId("id2");
        assertThat(systemEventDTO1).isNotEqualTo(systemEventDTO2);
        systemEventDTO1.setId(null);
        assertThat(systemEventDTO1).isNotEqualTo(systemEventDTO2);
    }
}
