package br.com.springboot.workshop.workshopspringboot.enumerated;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CarType {

	@JsonProperty("hatch")
	HATCH,

	@JsonProperty("seda")
	SEDAN;

}
