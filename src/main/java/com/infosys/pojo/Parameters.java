/**
 * 
 */
package com.infosys.pojo;

import java.util.Map;

/**
 * @author id844497
 *
 */
public class Parameters {

	private String from;
	private String to;
	private String mode;

	public Parameters() {

	}

	public Parameters(Parameters params) {
		this.from = params.from;
		this.to = params.to;
	}

	public Parameters(String fromLocation, String toLocation, String transportMode) {
		this.from = fromLocation;
		this.to = toLocation;
		this.mode = transportMode;
	}

	public Parameters(Map<String, Object> parameters) {
		this.from = (String) parameters.get("from");
		this.to = (String) parameters.get("to");
		this.mode = (String) parameters.get("mode");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parameters [From=");
		builder.append(from);
		builder.append(" to=");
		builder.append(to);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
}