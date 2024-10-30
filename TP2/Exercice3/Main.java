package Exercice3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Classe principale démontrant le système de gestion de commandes d'ordinateurs.
 *
 * Le programme illustre :
 * 1. La création et configuration de trois ordinateurs différents :
 *    - Lenovo ThinkPad (pour développeurs)
 *    - Dell Latitude (pour travail simple)
 *    - MacBook Pro (haut de gamme)
 *
 * 2. La gestion d'une catégorie :
 *    - Création d'une catégorie "Laptop"
 *    - Ajout des ordinateurs à cette catégorie
 *
 * 3. La gestion des clients :
 *    - Création et configuration d'un client avec ses informations personnelles
 *
 * 4. La gestion des commandes :
 *    - Création d'une commande avec référence
 *    - Association avec un client
 *    - Ajout de plusieurs lignes de commande
 *
 * Exemple d'utilisation :
 * - Crée trois ordinateurs avec leurs caractéristiques
 * - Les regroupe dans une catégorie "Laptop"
 * - Crée un client et sa commande
 * - Ajoute trois lignes de commande différentes
 *
 */
public class Main {
    public static void main(String[] args) {
        Ordinateur ord1 = new Ordinateur();
        Ordinateur ord2 = new Ordinateur();
        Ordinateur ord3 = new Ordinateur();

        ord1.setNom("ThinkPad");
        ord1.setMarque("Lenovo");
        ord1.setDescription("For Developers");
        ord1.setPrix(4500f);
        ord1.setNbStock(15);

        ord2.setNom("Letitude");
        ord2.setMarque("Dell");
        ord2.setDescription("For Simple Work");
        ord2.setPrix(5500f);
        ord2.setNbStock(20);

        ord3.setNom("Macbook Pro");
        ord3.setMarque("Apple");
        ord3.setDescription("If You Don't Know What To Do With Money Buy Me");
        ord3.setPrix(45000f);
        ord3.setNbStock(10);

        List<Ordinateur> ords = new ArrayList<>();
        ords.add(ord1);
        ords.add(ord2);
        ords.add(ord3);

        Categorie categorie = new Categorie();
        categorie.setNom("Laptop");
        categorie.setDescription("Mobile Computers For Everyday Job");
        for (Ordinateur ord : ords) {
            categorie.ajouterOrdinateur(ord);
        }

        Client client = new Client();
        client.setNom("Motawakil");
        client.setPrenom("Hassan");
        client.setEmail("motawakil@hassan.com");
        client.setAdresse("IMM 12 APT 5 ALAZHAR MADINATI");
        client.setVille("Casablanca");
        client.setTelephone("0615423897");

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        Commande commande = new Commande();
        commande.setReference("R1234");
        commande.setClient(client);
        commande.setDateCommande(date);
        commande.setEtatCommande("Liverer");

        client.ajouterCommande(commande);

        LigneCommande lc1 = new LigneCommande();
        LigneCommande lc2 = new LigneCommande();
        LigneCommande lc3 = new LigneCommande();

        lc1.setQuantite(2);
        lc1.setCommande(commande);
        lc1.setOrdinateur(ord1);

        lc2.setQuantite(1);
        lc2.setCommande(commande);
        lc2.setOrdinateur(ord2);

        lc3.setQuantite(4);
        lc3.setCommande(commande);
        lc3.setOrdinateur(ord3);

        System.out.println(commande);
    }
}
