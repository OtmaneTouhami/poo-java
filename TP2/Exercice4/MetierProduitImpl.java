package Exercice4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Implémentation de l'interface IMetierProduit qui fournit une gestion complète des produits.
 * Cette classe gère une collection de produits et implémente toutes les opérations CRUD
 * (Create, Read, Update, Delete) définies dans l'interface IMetierProduit.
 */
public class MetierProduitImpl implements IMetierProduit{
    /**
     * Liste stockant tous les produits de l'application.
     * Utilise ArrayList pour une gestion dynamique de la collection.
     */
    List<Produit> produits = new ArrayList<>();

    /**
     * Ajoute ou met à jour un produit dans la liste.
     * Si l'ID du produit existe déjà, le produit est mis à jour.
     * Si l'ID n'existe pas, le produit est ajouté à la liste.
     *
     * @param p Le produit à ajouter ou à mettre à jour
     * @return Le produit qui a été ajouté ou mis à jour
     */
    @Override
    public Produit add(Produit p) {
        int index = IntStream.range(0, produits.size())
                .filter(i -> produits.get(i).getId() == p.getId())
                .findFirst()
                .orElse(-1);

        if (index != -1) {
            produits.set(index, p);
            System.out.println(
                    "Le produit avec l'id " +
                            p.getId() +
                            " existe déjà ! Il a été modifié avec les nouvelles informations."
            );
        } else {
            produits.add(p);
            System.out.println("Produit ajouté avec succès !");
        }
        return p;
    }

    /**
     * Récupère une copie de la liste complète des produits.
     * Retourne une nouvelle ArrayList pour éviter la modification directe
     * de la liste interne des produits.
     *
     * @return Une nouvelle liste contenant tous les produits
     */
    @Override
    public List<Produit> getAll() {
        return new ArrayList<>(produits);
    }

    /**
     * Recherche et retourne tous les produits dont le nom contient le mot clé spécifié.
     * La recherche est insensible à la casse.
     *
     * @param motCle Le mot clé à rechercher dans les noms des produits
     * @return Une liste des produits correspondant au critère de recherche
     */
    @Override
    public List<Produit> findByNom(String motCle) {
        return produits.stream()
                .filter(produit -> produit.getNom().toLowerCase().contains(motCle.toLowerCase()))
                .toList();
    }

    /**
     * Recherche et retourne un produit spécifique par son ID.
     *
     * @param id L'identifiant du produit recherché
     * @return Le produit correspondant à l'ID ou null si aucun produit n'est trouvé
     */
    @Override
    public Produit findById(long id) {
        return produits.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Supprime un produit de la liste à partir de son ID.
     * Affiche un message de confirmation ou d'erreur selon le résultat.
     *
     * @param id L'identifiant du produit à supprimer
     */
    @Override
    public void delete(long id) {
        boolean isDeleted = produits.removeIf(p -> p.getId() == id);
        if (isDeleted) {
            System.out.println("Le produit a été supprimé !");
        } else {
            System.out.println("Le produit avec l'id " + id + " n'existe pas !");
        }
    }
}
