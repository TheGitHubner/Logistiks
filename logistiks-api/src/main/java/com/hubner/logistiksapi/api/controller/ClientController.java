package com.hubner.logistiksapi.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hubner.logistiksapi.domain.model.Client;
import com.hubner.logistiksapi.domain.repository.ClientRepository;
import com.hubner.logistiksapi.domain.service.ClientCatalogService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
	
//	@PersistenceContext
//	private EntityManager manager;
	
	@Autowired
	private ClientRepository clientRepository;
	private ClientCatalogService clientCatalogService;

	@GetMapping()
	public List<Client> getClients() {
		return clientRepository.findAll();
		
//		List<Client> clients = new ArrayList<>();
//		return manager.createQuery("from Client", Client.class)
//				.getResultList();

	}
	
	@GetMapping("/clients-by-name")
	public List<Client> getClientByName(@Param(value = "name") String name) {
		return clientRepository.findByNameContaining(name);
	}
	
	@GetMapping("/{clientId}")
	public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
		return clientRepository.findById(clientId)
				.map(client -> ResponseEntity.ok(client))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Client saveClient(@Valid @RequestBody Client client) {
		return clientCatalogService.save(client);
	}
	
	@PutMapping("/{clientId}")
	public ResponseEntity<Client> updateClient(@Valid 
			                                   @PathVariable Long clientId, 
											   @RequestBody Client client){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		client.setId(clientId);
		client = clientCatalogService.save(client);
		
		return ResponseEntity.ok(client);
	}
	
	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> updateClient(@PathVariable Long clientId){
		if(!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		
		clientCatalogService.delete(clientId);
		
		return ResponseEntity.noContent().build();
	}
	
}
