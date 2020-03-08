package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Client;

public interface ClientService {
	int save(Client client);
	int modify(Client client);
	int remove(Client client);
	List<Client> findAll();
}
