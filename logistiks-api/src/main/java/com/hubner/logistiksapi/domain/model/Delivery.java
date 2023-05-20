package com.hubner.logistiksapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "delivery")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Delivery {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Client client;
	
	@Embedded
	private Receiver receiver;
	
	private BigDecimal tax;
	
	@Enumerated(EnumType.STRING)
	private DeliveyStatus status;
	
	@Column(name = "order_date")
	private LocalDateTime orderDate;
	
	@Column(name = "finish_order_date")
	private LocalDateTime finishOrderDate;

}
