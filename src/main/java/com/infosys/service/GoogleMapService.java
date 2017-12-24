package com.infosys.service;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.client.RestTemplate;

import com.infosys.pojo.Parameters;

public class GoogleMapService {

	private Parameters location;

	private RestTemplate restTemplate;

	/**
	 * @param fromLocation
	 * @param toLocation
	 * @param transportMode
	 * @param restTemplate
	 */
	public GoogleMapService(Parameters location) {
		super();
		this.location = location;
		this.restTemplate = new RestTemplate();
	}

	public String callGoogleService() {
		String googleMapData = restTemplate.getForObject(
				"https://maps.googleapis.com/maps/api/directions/json?origin="
						+ this.location.getFrom() + "&destination="
						+ this.location.getTo() + "&sensor=false&mode="
						+ this.location.getMode()
						+ "&key=AIzaSyCQcUV7LgddS7Iv_qL3skV1e12RIaM25wE",
				String.class);

		return parseData(googleMapData);
	}

	private String parseData(String googleMapData) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;

		String returnString = "The distance between " + location.getFrom()
				+ " & " + location.getTo()
				+ " is %s. It will take around %s by " + location.getMode();
		try {
			jsonObject = (JSONObject) jsonParser.parse(googleMapData);

			if (((String) jsonObject.get("status")).equals("OK")) {
				Iterator legsIterator = getLegsIterator(jsonObject);
				returnString = this.getLegsInnerObject(legsIterator,
						returnString);
			} else {
				returnString = "There has been error while fetching the data.";
				System.out.println(googleMapData);
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnString = "There has been error while fetching the data.";
		}

		return returnString;
	}

	private String getLegsInnerObject(Iterator legsIterator, String returnString) {
		String durationString = null;
		String distanceString = null;

		while (legsIterator.hasNext()) {
			JSONObject innerObj = (JSONObject) legsIterator.next();
			JSONObject duration = (JSONObject) innerObj.get("duration");
			durationString = (String) duration.get("text");

			JSONObject distance = (JSONObject) innerObj.get("distance");
			distanceString = (String) distance.get("text");
		}

		return String.format(returnString, distanceString, durationString);
	}

	private Iterator getLegsIterator(JSONObject jsonObject) {

		JSONArray routes = (JSONArray) jsonObject.get("routes");
		JSONArray legs = null;
		Iterator routeIterator = routes.iterator();

		while (routeIterator.hasNext()) {
			JSONObject innerObj = (JSONObject) routeIterator.next();
			legs = (JSONArray) innerObj.get("legs");
		}

		return legs.iterator();
	}
}