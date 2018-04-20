package com.infosys.controllers;

import com.infosys.exceptions.ServiceException;
import com.infosys.services.AssignmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import javax.ws.rs.QueryParam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
@Api(value = "assignmentServices")
public class AssignmentRestController {
	
	

	private static final Logger logger = LoggerFactory.getLogger(AssignmentRestController.class);

	@Autowired
	AssignmentService assignmentService;

	@GetMapping(value = "/fibonacci", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get fibonacci Response", response = ResponseEntity.class)
	public ResponseEntity<BigInteger> getFibonacciSeries(@RequestParam(value = "n", required = true) Integer inputValue)
			throws ServiceException {
		BigInteger resultValue = assignmentService.getFibonacciSeries(inputValue);
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resultValue);
	}

	@GetMapping(value = "/reverseWords", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Reverse Words Response", response = ResponseEntity.class)
	public ResponseEntity<String> getReverseWords(@QueryParam("sentence") String sentence)
			throws ServiceException, Exception {
		String resultValue = assignmentService.getReverseWords(UriUtils.decode(sentence, "UTF-8"));
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resultValue);
	}

	@GetMapping(value = "/triangleType", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get triangle Type Response", response = ResponseEntity.class)
	public ResponseEntity<String> getTriangleType(@RequestParam(value = "a", required = true) Integer a,
			@RequestParam(value = "b", required = true) Integer b,
			@RequestParam(value = "c", required = true) Integer c) throws ServiceException {
		String resultValue = assignmentService.getTriangleType(a, b, c);
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resultValue);
	}

	@GetMapping(value = "/url1", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Single Array Response", response = ResponseEntity.class)
	public ResponseEntity<String> url1(@RequestParam(value = "a", required = true) Integer a)
			throws ServiceException, FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader("C:\\Users\\DELL\\Documents\\GitHub\\2\\TWCP\\src\\main\\resources\\first_json.json"));

		JSONObject jsonObject = (JSONObject) obj;

		String resultValue = jsonObject.toString();
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resultValue);
	}

	@GetMapping(value = "/url2", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Reverse Words Response", response = ResponseEntity.class)
	public ResponseEntity<String> url2(@RequestParam(value = "a", required = true) Integer a) throws ServiceException, Exception {
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader("C:\\Users\\DELL\\Documents\\GitHub\\2\\TWCP\\src\\main\\resources\\second_json.json"));

		JSONObject jsonObject = (JSONObject) obj;

		String resultValue = jsonObject.toString();
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(resultValue);
	}
	
	


}
