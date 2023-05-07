package fr.greta.domes.service;

import fr.greta.domes.controller.client.ClientSearchQuery;
import fr.greta.domes.model.client.Client;
import fr.greta.domes.model.client.ClientPage;
import fr.greta.domes.model.client.ClientPutDTO;

import java.io.IOException;
import java.util.Optional;

public interface ClientService {
    Optional<ClientPage> getClientPage(ClientSearchQuery clientSearchQuery) throws IOException;
    Optional<ClientPage> searchBarGetClients(ClientSearchQuery clientSearchQuery) throws IOException;

    Optional<Boolean> editClient(ClientPutDTO client) throws IOException;
}
