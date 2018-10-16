package com.multidata.notification.hub.web.rest;

import com.multidata.notification.hub.NotificationHubApp;

import com.multidata.notification.hub.domain.EventAttach;
import com.multidata.notification.hub.repository.EventAttachRepository;
import com.multidata.notification.hub.service.EventAttachService;
import com.multidata.notification.hub.service.dto.EventAttachDTO;
import com.multidata.notification.hub.service.mapper.EventAttachMapper;
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
import org.springframework.util.Base64Utils;

import java.util.List;


import static com.multidata.notification.hub.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EventAttachResource REST controller.
 *
 * @see EventAttachResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotificationHubApp.class)
public class EventAttachResourceIntTest {

    private static final byte[] DEFAULT_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FILE_CONTENT_TYPE = "image/png";

    @Autowired
    private EventAttachRepository eventAttachRepository;

    @Autowired
    private EventAttachMapper eventAttachMapper;
    
    @Autowired
    private EventAttachService eventAttachService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restEventAttachMockMvc;

    private EventAttach eventAttach;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventAttachResource eventAttachResource = new EventAttachResource(eventAttachService);
        this.restEventAttachMockMvc = MockMvcBuilders.standaloneSetup(eventAttachResource)
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
    public static EventAttach createEntity() {
        EventAttach eventAttach = new EventAttach()
            .file(DEFAULT_FILE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE);
        return eventAttach;
    }

    @Before
    public void initTest() {
        eventAttachRepository.deleteAll();
        eventAttach = createEntity();
    }

    @Test
    public void createEventAttach() throws Exception {
        int databaseSizeBeforeCreate = eventAttachRepository.findAll().size();

        // Create the EventAttach
        EventAttachDTO eventAttachDTO = eventAttachMapper.toDto(eventAttach);
        restEventAttachMockMvc.perform(post("/api/event-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventAttachDTO)))
            .andExpect(status().isCreated());

        // Validate the EventAttach in the database
        List<EventAttach> eventAttachList = eventAttachRepository.findAll();
        assertThat(eventAttachList).hasSize(databaseSizeBeforeCreate + 1);
        EventAttach testEventAttach = eventAttachList.get(eventAttachList.size() - 1);
        assertThat(testEventAttach.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testEventAttach.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
    }

    @Test
    public void createEventAttachWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventAttachRepository.findAll().size();

        // Create the EventAttach with an existing ID
        eventAttach.setId("existing_id");
        EventAttachDTO eventAttachDTO = eventAttachMapper.toDto(eventAttach);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventAttachMockMvc.perform(post("/api/event-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventAttachDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventAttach in the database
        List<EventAttach> eventAttachList = eventAttachRepository.findAll();
        assertThat(eventAttachList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllEventAttaches() throws Exception {
        // Initialize the database
        eventAttachRepository.save(eventAttach);

        // Get all the eventAttachList
        restEventAttachMockMvc.perform(get("/api/event-attaches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventAttach.getId())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))));
    }
    
    @Test
    public void getEventAttach() throws Exception {
        // Initialize the database
        eventAttachRepository.save(eventAttach);

        // Get the eventAttach
        restEventAttachMockMvc.perform(get("/api/event-attaches/{id}", eventAttach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eventAttach.getId()))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.file").value(Base64Utils.encodeToString(DEFAULT_FILE)));
    }

    @Test
    public void getNonExistingEventAttach() throws Exception {
        // Get the eventAttach
        restEventAttachMockMvc.perform(get("/api/event-attaches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEventAttach() throws Exception {
        // Initialize the database
        eventAttachRepository.save(eventAttach);

        int databaseSizeBeforeUpdate = eventAttachRepository.findAll().size();

        // Update the eventAttach
        EventAttach updatedEventAttach = eventAttachRepository.findById(eventAttach.getId()).get();
        updatedEventAttach
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE);
        EventAttachDTO eventAttachDTO = eventAttachMapper.toDto(updatedEventAttach);

        restEventAttachMockMvc.perform(put("/api/event-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventAttachDTO)))
            .andExpect(status().isOk());

        // Validate the EventAttach in the database
        List<EventAttach> eventAttachList = eventAttachRepository.findAll();
        assertThat(eventAttachList).hasSize(databaseSizeBeforeUpdate);
        EventAttach testEventAttach = eventAttachList.get(eventAttachList.size() - 1);
        assertThat(testEventAttach.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testEventAttach.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
    }

    @Test
    public void updateNonExistingEventAttach() throws Exception {
        int databaseSizeBeforeUpdate = eventAttachRepository.findAll().size();

        // Create the EventAttach
        EventAttachDTO eventAttachDTO = eventAttachMapper.toDto(eventAttach);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEventAttachMockMvc.perform(put("/api/event-attaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventAttachDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventAttach in the database
        List<EventAttach> eventAttachList = eventAttachRepository.findAll();
        assertThat(eventAttachList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEventAttach() throws Exception {
        // Initialize the database
        eventAttachRepository.save(eventAttach);

        int databaseSizeBeforeDelete = eventAttachRepository.findAll().size();

        // Get the eventAttach
        restEventAttachMockMvc.perform(delete("/api/event-attaches/{id}", eventAttach.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EventAttach> eventAttachList = eventAttachRepository.findAll();
        assertThat(eventAttachList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventAttach.class);
        EventAttach eventAttach1 = new EventAttach();
        eventAttach1.setId("id1");
        EventAttach eventAttach2 = new EventAttach();
        eventAttach2.setId(eventAttach1.getId());
        assertThat(eventAttach1).isEqualTo(eventAttach2);
        eventAttach2.setId("id2");
        assertThat(eventAttach1).isNotEqualTo(eventAttach2);
        eventAttach1.setId(null);
        assertThat(eventAttach1).isNotEqualTo(eventAttach2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventAttachDTO.class);
        EventAttachDTO eventAttachDTO1 = new EventAttachDTO();
        eventAttachDTO1.setId("id1");
        EventAttachDTO eventAttachDTO2 = new EventAttachDTO();
        assertThat(eventAttachDTO1).isNotEqualTo(eventAttachDTO2);
        eventAttachDTO2.setId(eventAttachDTO1.getId());
        assertThat(eventAttachDTO1).isEqualTo(eventAttachDTO2);
        eventAttachDTO2.setId("id2");
        assertThat(eventAttachDTO1).isNotEqualTo(eventAttachDTO2);
        eventAttachDTO1.setId(null);
        assertThat(eventAttachDTO1).isNotEqualTo(eventAttachDTO2);
    }
}
