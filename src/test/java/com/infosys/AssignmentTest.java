package com.infosys;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infosys.services.AssignmentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Unit test for AssignmentTest App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssignmentTest
{
    @Mock
    AssignmentService assignmentService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void testMakeoneArray() throws Exception
    {
        LinkedHashMap<String, ArrayList> arrayList = mapper.readValue(new File("src/test/resources/makeoneArrayInput.json"),
                new TypeReference<LinkedHashMap<String, ArrayList>>()
                {
                });
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/makeoneArray").content(jsonInString)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        LinkedHashMap<String, ArrayList> arrayOutput = mapper.readValue(new File("src/test/resources/makeoneArrayOutput.json"),
                new TypeReference<LinkedHashMap<String, ArrayList>>()
                {
                });
        String jsonOutputString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayOutput);

        JSONAssert.assertEquals(jsonOutputString, response.getContentAsString(), JSONCompareMode.NON_EXTENSIBLE);
        Mockito.verify(assignmentService, Mockito.times(0)).getMakeoneArray(Mockito.anyList());
    }

    @Test
    public void testFibonacci() throws Exception
    {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/fibonacci?n=10")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        JSONAssert.assertEquals("55", response.getContentAsString(), JSONCompareMode.NON_EXTENSIBLE);
        Mockito.verify(assignmentService, Mockito.times(0)).getFibonacciSeries(Mockito.anyInt());
    }

    @Test
    public void testReverseWords() throws Exception
    {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/reverseWords?sentence=how are you")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals("woh era uoy ", response.getContentAsString());
        Mockito.verify(assignmentService, Mockito.times(0)).getReverseWords(Mockito.anyString());
    }

    @Test
    public void testTrainagleTypes() throws Exception
    {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/triangleType?a=1&b=3&c=3")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals("ISOSCELES", response.getContentAsString());
        Mockito.verify(assignmentService, Mockito.times(0)).getTriangleType(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
    }


    @Test
    public void testTrainagleTypesBadRequest() throws Exception
    {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/triangleType?a=1&b=3")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Mockito.verify(assignmentService, Mockito.times(0)).getTriangleType(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
    }
}
