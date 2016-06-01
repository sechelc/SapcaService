package com.rogames.engine.controller;

import org.hibernate.validator.constraints.NotBlank;

public class RoundForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = RoundForm.NOT_BLANK_MESSAGE)
	private String roundText;

	private Long points;

	@NotBlank(message = RoundForm.NOT_BLANK_MESSAGE)
	private String possibleActions;

	public String getRoundText() {
		return roundText;
	}

	public void setRoundText(String roundText) {
		this.roundText = roundText;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public String getPossibleActions() {
		return possibleActions;
	}

	public void setPossibleActions(String possibleActions) {
		this.possibleActions = possibleActions;
	}
}
