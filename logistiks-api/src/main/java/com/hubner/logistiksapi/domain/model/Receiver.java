package com.hubner.logistiksapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Receiver {
	@Column(name = "receiver_name")
	private String name;
	
	@Column(name = "receiver_public_area")
	private String publicArea;
	
	@Column(name = "receiver_number")
	private String number;
	
	@Column(name = "receiver_complement")
	private String complement;
	
	@Column(name = "receiver_neighbourhood")
	private String neighbourhood;
}
