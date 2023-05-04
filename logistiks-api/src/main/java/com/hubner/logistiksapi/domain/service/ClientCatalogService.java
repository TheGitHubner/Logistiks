package com.hubner.logistiksapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hubner.logistiksapi.domain.exception.BusinessException;
import com.hubner.logistiksapi.domain.model.Client;
import com.hubner.logistiksapi.domain.repository.ClientRepository;


@Service
public class ClientCatalogService {
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public Client save(Client client) {
		boolean emailAlreadyExists = clientRepository.findByEmail(client.getEmail())
				.stream()
				.anyMatch(existingClient -> !existingClient.equals(client));
		
		if (emailAlreadyExists) {
			throw new BusinessException("Já existe um cliente cadastrado com esse e-mail.");
		}
		
		return clientRepository.save(client);
	}
	
	@Transactional
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}
}
