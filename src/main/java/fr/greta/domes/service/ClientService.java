package fr.greta.domes.service;

import fr.greta.domes.controller.client.ClientSearchQuery;
import fr.greta.domes.model.client.ClientPage;

import java.io.IOException;

public interface ClientService {
    ClientPage getClientPage(ClientSearchQuery clientSearchQuery) throws IOException;
    ClientPage searchBarGetClients(ClientSearchQuery clientSearchQuery) throws IOException;
}
