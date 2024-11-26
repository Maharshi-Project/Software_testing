package com.testing.Converters.controller;

import com.testing.Converters.DTO.Req;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrequencyConverterTest {

    private final FrequencyConverter frequencyConverter = new FrequencyConverter();

    @Test
    public void convertFrequency_ValidRequest_ReturnsOk() {

        Req req = new Req("kilohertz","hertz",1.0);
        BigDecimal expected_1 = new BigDecimal("1000.0");
        ResponseEntity<?> response = frequencyConverter.convertFrequency(req);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, new BigDecimal(String.valueOf(response.getBody())).compareTo(expected_1));
    }

    @Test
    public void convertFrequency_InvalidRequest_ReturnsBadRequest() {

        Req req = new Req("invalid","meter",1.0);
        
        ResponseEntity<?> response = frequencyConverter.convertFrequency(req);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid fromUnit "+req.getFromUnit(), response.getBody());
    }


//    private MockMvc mockMvc;
//
//    @Mock
//    private FrequencyConverterService frequencyConverterService;
//
//    @InjectMocks
//    private FrequencyConverter frequencyConverter;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(frequencyConverter).build();
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void testSuccessfulFrequencyConversion() throws Exception {
//        // Prepare test data
//        Req request = new Req();
//        request.setFromUnit("hertz");
//        request.setToUnit("kilohertz");
//        request.setValue(1000.0);
//
//        // Mock service response
//        when(frequencyConverterService.convertFrequency(
//                any(String.class),
//                any(String.class),
//                any(BigDecimal.class)
//        )).thenReturn(BigDecimal.ONE);
//
//        // Perform the request and validate response
//        mockMvc.perform(post("/convertFrequency")
//                        .contentType(MediaType.APPLICATION_JSON)
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().string("1"));
//    }
//
//    @Test
//    void testFailedFrequencyConversion() throws Exception {
//        // Prepare test data
//        Req request = new Req();
//        request.setFromUnit("invalid");
//        request.setToUnit("hertz");
//        request.setValue(100.0);
//
//        // Mock service response for failure
//        when(frequencyConverterService.convertFrequency(
//                any(String.class),
//                any(String.class),
//                any(BigDecimal.class)
//        )).thenReturn(BigDecimal.valueOf(-1));
//
//        // Perform the request and validate response
//        mockMvc.perform(post("/convertFrequency")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("-1.0"));
//    }
//
//    @Test
//    void testMissingRequestBody() throws Exception {
//        // Attempt to send request without body
//        mockMvc.perform((RequestBuilder) post("/convertFrequency")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void testInvalidRequestBody() throws Exception {
//        // Send invalid JSON
//        mockMvc.perform(post("/convertFrequency")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"invalid\": \"data\"}"))
//                .andExpect(status().isBadRequest());
//    }
//
//    // Integration Test with Actual Service
//    @Test
//    void testRealFrequencyConversion() {
//        FrequencyConverter realConverter = new FrequencyConverter();
//        Req request = new Req();
//        request.setFromUnit("hertz");
//        request.setToUnit("kilohertz");
//        request.setValue(1000.0);
//
//        ResponseEntity<?> response = realConverter.convertFrequency(request);
//
//        assert response.getStatusCode() == HttpStatus.OK;
//        assert response.getBody() != null;
//    }

}