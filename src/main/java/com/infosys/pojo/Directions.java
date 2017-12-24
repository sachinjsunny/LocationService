/**
 * 
 */
package com.infosys.pojo;

/**
 * @author id844497
 *
 */
public class Directions {
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("speech:");
		builder.append(speech);
		builder.append(", displayText:");
		builder.append(displayText);
		return builder.toString();
	}
	
	public Directions(String text){
		speech= text;
		displayText=text;
	}

	/**
	 * @return the speechText
	 */
	public String getSpeech() {
		return speech;
	}

	/**
	 * @param speechText the speechText to set
	 */
	public void setSpeech(String speech) {
		this.speech = speech;
	}

	/**
	 * @return the displayText
	 */
	public String getDisplayText() {
		return displayText;
	}

	/**
	 * @param displayText the displayText to set
	 */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	private String speech;
	
	private String displayText;
}
