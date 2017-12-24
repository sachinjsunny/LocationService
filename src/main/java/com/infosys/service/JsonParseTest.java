package com.infosys.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParseTest {

	private static final String filePath = "C:\\Users\\id844497\\JSONs\\GoogleMapWaypoint.json";

	public static void main(String[] args) {

		try {
			// read the json file
			FileReader reader = new FileReader(filePath);

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

			// get a String from the JSON object
			JSONArray routes = (JSONArray) jsonObject.get("routes");
			//System.out.println("The first name is: " + routes);
			JSONArray legs = null;
			Iterator routeIterator = routes.iterator();
			while (routeIterator.hasNext()) {
				JSONObject innerObj = (JSONObject) routeIterator.next();
				//System.out.println("language " + innerObj.get("legs"));
				legs = (JSONArray) innerObj.get("legs");

			}

			Iterator legsIterator = legs.iterator();
			
			while(legsIterator.hasNext()){
				JSONObject innerObj = (JSONObject) legsIterator.next();
				System.out.println("Duration " + innerObj.get("duration"));
				System.out.println("Distance" + innerObj.get("distance"));
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}
}