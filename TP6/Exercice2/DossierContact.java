package Exercice2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DossierContact {
    private static DossierContact instance = null;
    private List<Contact> contacts = new ArrayList<>();

    private DossierContact() {
    }

    public static DossierContact getInstance(File phoneBook) {
        if (instance == null) {
            instance = new DossierContact();
            instance.loadContacts(phoneBook);
        }
        return instance;
    }

    private void loadContacts(File phoneBook) {
        File[] contactFiles = phoneBook.listFiles();
        if (contactFiles != null) {
            for (File file : contactFiles) {
                String name = file.getName();
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String number = reader.readLine();
                    Contact contact = new Contact(name, number);
                    instance.contacts.add(contact);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public boolean addContact(File phoneBook, Contact contact) {
        try {
            File contactFile = new File(phoneBook, contact.getName());

            if (contactFile.exists()) {
                System.out.println("Contact already exists!");
                return false;
            }

            if (contactFile.createNewFile()) {
                try (FileWriter writer = new FileWriter(contactFile)) {
                    writer.write(contact.getNumber());
                }
                contacts.add(contact);
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean deleteContact(File phoneBook, Contact contact) {
        File contactToDeleteFile = new File(phoneBook, contact.getName());
        if (contactToDeleteFile.delete()) {
            contacts.remove(contact);
            return true;
        }
        return false;
    }

    public boolean updateContact(File phoneBook, Contact oldContact, Contact newContact) {
        File contactToUpdateFile = new File(phoneBook, oldContact.getName());
        try (FileWriter writer = new FileWriter(contactToUpdateFile, false)) {
            writer.write(newContact.getNumber());
            contacts.set(contacts.indexOf(oldContact), newContact);
            return true;
        } catch (IOException e) {
            System.err.println("An error occurred while overwriting the file: " + e.getMessage());
        }
        return false;
    }

}
