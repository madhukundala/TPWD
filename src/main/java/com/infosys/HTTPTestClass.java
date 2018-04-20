package com.infosys;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;

public class HTTPTestClass {

	private final static String USER_AGENT = "Mozilla/5.0";

	@Scheduled(fixedDelay = 1000)
	public static void main(String[] args) throws IOException, ParseException {

		String url = "http://localhost:8888/api/url1?a=1";
		String url2 = "http://localhost:8888/api/url2?a=1";
		String response = getResponse(url);
		// print result
		JSONParser jsonParser = new JSONParser();
		Object obj1 = jsonParser.parse(response);
		JSONObject jsonObject = (JSONObject) obj1;
		JSONObject result = (JSONObject) jsonObject.get("_links");
		if (result.containsKey("compute-group")) {
			JSONObject res = (JSONObject) result.get("compute-group");
			System.out.println("valueee :: " + res.get("href"));
			JSONObject jsonObject1 = (JSONObject) jsonParser.parse(getResponse(url2));;
			if (jsonObject1.containsKey("subdomain")) {
				String res2 = (String) jsonObject1.get("subdomain");
				System.out.println("dockerrr :: " + res2);
			}
		}

	}

	public static String getResponse(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

}
