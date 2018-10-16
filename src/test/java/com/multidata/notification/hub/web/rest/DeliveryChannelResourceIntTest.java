package com.multidata.notification.hub.web.rest;

import com.multidata.notification.hub.NotificationHubApp;

import com.multidata.notification.hub.domain.DeliveryChannel;
import com.multidata.notification.hub.repository.DeliveryChannelRepository;
import com.multidata.notification.hub.service.DeliveryChannelService;
import com.multidata.notification.hub.service.dto.DeliveryChannelDTO;
import com.multidata.notification.hub.service.mapper.DeliveryChannelMapper;
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

import com.multidata.notification.hub.domain.enumeration.Channel;
/**
 * Test class for the DeliveryChannelResource REST controller.
 *
 * @see DeliveryChannelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotificationHubApp.class)
public class DeliveryChannelResourceIntTest {

    private static final Channel DEFAULT_TYPE = Channel.MAIL;
    private static final Channel UPDATED_TYPE = Channel.SLACK;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private DeliveryChannelRepository deliveryChannelRepository;

    @Autowired
    private DeliveryChannelMapper deliveryChannelMapper;
    
    @Autowired
    private DeliveryChannelService deliveryChannelService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDeliveryChannelMockMvc;

    private DeliveryChannel deliveryChannel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DeliveryChannelResource deliveryChannelResource = new DeliveryChannelResource(deliveryChannelService);
        this.restDeliveryChannelMockMvc = MockMvcBuilders.standaloneSetup(deliveryChannelResource)
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
    public static DeliveryChannel createEntity() {
        DeliveryChannel deliveryChannel = new DeliveryChannel()
            .type(DEFAULT_TYPE)
            .description(DEFAULT_DESCRIPTION);
        return deliveryChannel;
    }

    @Before
    public void initTest() {
        deliveryChannelRepository.deleteAll();
        deliveryChannel = createEntity();
    }

    @Test
    public void createDeliveryChannel() throws Exception {
        int databaseSizeBeforeCreate = deliveryChannelRepository.findAll().size();

        // Create the DeliveryChannel
        DeliveryChannelDTO deliveryChannelDTO = deliveryChannelMapper.toDto(deliveryChannel);
        restDeliveryChannelMockMvc.perform(post("/api/delivery-channels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryChannelDTO)))
            .andExpect(status().isCreated());

        // Validate the DeliveryChannel in the database
        List<DeliveryChannel> deliveryChannelList = deliveryChannelRepository.findAll();
        assertThat(deliveryChannelList).hasSize(databaseSizeBeforeCreate + 1);
        DeliveryChannel testDeliveryChannel = deliveryChannelList.get(deliveryChannelList.size() - 1);
        assertThat(testDeliveryChannel.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testDeliveryChannel.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createDeliveryChannelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deliveryChannelRepository.findAll().size();

        // Create the DeliveryChannel with an existing ID
        deliveryChannel.setId("existing_id");
        DeliveryChannelDTO deliveryChannelDTO = deliveryChannelMapper.toDto(deliveryChannel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliveryChannelMockMvc.perform(post("/api/delivery-channels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryChannelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryChannel in the database
        List<DeliveryChannel> deliveryChannelList = deliveryChannelRepository.findAll();
        assertThat(deliveryChannelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDeliveryChannels() throws Exception {
        // Initialize the database
        deliveryChannelRepository.save(deliveryChannel);

        // Get all the deliveryChannelList
        restDeliveryChannelMockMvc.perform(get("/api/delivery-channels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliveryChannel.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    public void getDeliveryChannel() throws Exception {
        // Initialize the database
        deliveryChannelRepository.save(deliveryChannel);

        // Get the deliveryChannel
        restDeliveryChannelMockMvc.perform(get("/api/delivery-channels/{id}", deliveryChannel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(deliveryChannel.getId()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingDeliveryChannel() throws Exception {
        // Get the deliveryChannel
        restDeliveryChannelMockMvc.perform(get("/api/delivery-channels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDeliveryChannel() throws Exception {
        // Initialize the database
        deliveryChannelRepository.save(deliveryChannel);

        int databaseSizeBeforeUpdate = deliveryChannelRepository.findAll().size();

        // Update the deliveryChannel
        DeliveryChannel updatedDeliveryChannel = deliveryChannelRepository.findById(deliveryChannel.getId()).get();
        updatedDeliveryChannel
            .type(UPDATED_TYPE)
            .description(UPDATED_DESCRIPTION);
        DeliveryChannelDTO deliveryChannelDTO = deliveryChannelMapper.toDto(updatedDeliveryChannel);

        restDeliveryChannelMockMvc.perform(put("/api/delivery-channels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryChannelDTO)))
            .andExpect(status().isOk());

        // Validate the DeliveryChannel in the database
        List<DeliveryChannel> deliveryChannelList = deliveryChannelRepository.findAll();
        assertThat(deliveryChannelList).hasSize(databaseSizeBeforeUpdate);
        DeliveryChannel testDeliveryChannel = deliveryChannelList.get(deliveryChannelList.size() - 1);
        assertThat(testDeliveryChannel.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testDeliveryChannel.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingDeliveryChannel() throws Exception {
        int databaseSizeBeforeUpdate = deliveryChannelRepository.findAll().size();

        // Create the DeliveryChannel
        DeliveryChannelDTO deliveryChannelDTO = deliveryChannelMapper.toDto(deliveryChannel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliveryChannelMockMvc.perform(put("/api/delivery-channels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(deliveryChannelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DeliveryChannel in the database
        List<DeliveryChannel> deliveryChannelList = deliveryChannelRepository.findAll();
        assertThat(deliveryChannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDeliveryChannel() throws Exception {
        // Initialize the database
        deliveryChannelRepository.save(deliveryChannel);

        int databaseSizeBeforeDelete = deliveryChannelRepository.findAll().size();

        // Get the deliveryChannel
        restDeliveryChannelMockMvc.perform(delete("/api/delivery-channels/{id}", deliveryChannel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DeliveryChannel> deliveryChannelList = deliveryChannelRepository.findAll();
        assertThat(deliveryChannelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryChannel.class);
        DeliveryChannel deliveryChannel1 = new DeliveryChannel();
        deliveryChannel1.setId("id1");
        DeliveryChannel deliveryChannel2 = new DeliveryChannel();
        deliveryChannel2.setId(deliveryChannel1.getId());
        assertThat(deliveryChannel1).isEqualTo(deliveryChannel2);
        deliveryChannel2.setId("id2");
        assertThat(deliveryChannel1).isNotEqualTo(deliveryChannel2);
        deliveryChannel1.setId(null);
        assertThat(deliveryChannel1).isNotEqualTo(deliveryChannel2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryChannelDTO.class);
        DeliveryChannelDTO deliveryChannelDTO1 = new DeliveryChannelDTO();
        deliveryChannelDTO1.setId("id1");
        DeliveryChannelDTO deliveryChannelDTO2 = new DeliveryChannelDTO();
        assertThat(deliveryChannelDTO1).isNotEqualTo(deliveryChannelDTO2);
        deliveryChannelDTO2.setId(deliveryChannelDTO1.getId());
        assertThat(deliveryChannelDTO1).isEqualTo(deliveryChannelDTO2);
        deliveryChannelDTO2.setId("id2");
        assertThat(deliveryChannelDTO1).isNotEqualTo(deliveryChannelDTO2);
        deliveryChannelDTO1.setId(null);
        assertThat(deliveryChannelDTO1).isNotEqualTo(deliveryChannelDTO2);
    }
}
