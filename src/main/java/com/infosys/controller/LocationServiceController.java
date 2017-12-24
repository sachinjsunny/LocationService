/**
 * 
 */
package com.infosys.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.pojo.Directions;
import com.infosys.pojo.Parameters;
import com.infosys.service.GoogleMapService;

/**
 * @author id844497
 * 
 */

@RestController
@RequestMapping("/location/*")
public class LocationServiceController {

	private static final Logger log = LoggerFactory
			.getLogger(LocationServiceController.class);

	private GoogleMapService googleMapService;

	@RequestMapping(value = "/location/getLocation", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody
	Parameters returnLocation(
			@RequestParam(value = "fromLocation", required = false, defaultValue = "India") String fromLocation,
			@RequestParam(value = "toLocation", required = false, defaultValue = "United States") String toLocation) {
		log.info("Get Called");
		return new Parameters(fromLocation, toLocation, "");
	}

	@RequestMapping(value = "/location/getLocation", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	Parameters returnLocation(@RequestBody(required = true) Parameters location) {
		System.out.println("Post Called");
		return new Parameters(location);
	}

	@RequestMapping(value = "/location/mapDirection", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody
	Directions returnMapDirections(
			@RequestBody(required = true) Map<String, Object> payload) {
		log.info("Post Map Direction Called ");
		googleMapService = new GoogleMapService(getParametersFromJSON(payload));

		return new Directions(googleMapService.callGoogleService());
	}

	private Parameters getParametersFromJSON(Map<String, Object> payload) {
		Map<String, Object> result = null;
		for (String key : payload.keySet()) {
			System.out.println(key);
			if (key.equals("result")) {
				result = (Map<String, Object>) payload.get(key);
				break;
			}
		}

		Map<String, Object> parameters = (Map<String, Object>) result
				.get("parameters");

		return new Parameters(parameters);
	}

	@RequestMapping(value = "/location/mapDirection", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody
	Directions returnMapDirections(
			@RequestParam(value = "from", required = true, defaultValue = "Brussels") String fromLocation,
			@RequestParam(value = "to", required = true, defaultValue = "London") String toLocation,
			@RequestParam(value = "mode", required = true, defaultValue = "Driving") String transportMode) {
		System.out.println("Get Map Direction Called" + fromLocation
				+ toLocation + transportMode);
		googleMapService = new GoogleMapService(new Parameters(fromLocation,
				toLocation, transportMode));

		return new Directions(googleMapService.callGoogleService());
	}

}