package Exercice3.impl;

import Exercice3.Client;
import Exercice3.IService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements IService<Client> {
    private List<Client> clients = new ArrayList<>();
    private final File clientsFile = new File("Exercice3/Storage/clients.dat");

    @Override
    public Client add(Client client) {
        if (client != null) {
            clients.add(client);
        }
        return client;
    }

    @Override
    public List<Client> getAll() {
        if (clientsFile.length() == 0) {
            return clients;
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(clientsFile))) {
            clients = (List<Client>) objectInputStream.readObject();
            return clients;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error while loading clients: " + e.getMessage(), e);
        }
    }

    @Override
    public Client findByName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Last name cannot be null.");
        }
        return clients.stream()
                .filter(client -> client.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Last name cannot be null.");
        }
        return clients.removeIf(client -> client.getLastName().equalsIgnoreCase(lastName));
    }

    @Override
    public void saveAll() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(clientsFile))) {
            objectOutputStream.writeObject(clients);
        } catch (IOException e) {
            throw new RuntimeException("Error while saving clients: " + e.getMessage(), e);
        }
    }

    public List<Client> getClients() {
        return clients;
    }
}
