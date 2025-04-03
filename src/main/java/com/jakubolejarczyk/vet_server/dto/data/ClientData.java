package com.jakubolejarczyk.vet_server.dto.data;

import com.jakubolejarczyk.vet_server.model.dependent.Client;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ClientData {
    List<Client> clients;
}
