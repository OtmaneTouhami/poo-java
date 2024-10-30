package Exercice4;

import java.util.List;
/**
 * Interface définissant les opérations de base pour la gestion des produits.
 * Cette interface fournit les méthodes nécessaires pour manipuler une collection de produits.
 */
public interface IMetierProduit {
    /**
     * Ajoute un nouveau produit à la collection ou met à jour un produit existant.
     * @param p Le produit à ajouter ou à mettre à jour
     * @return Le produit ajouté ou mis à jour
     */
    Produit add(Produit p);
    /**
     * Récupère tous les produits de la collection.
     * @return Une liste contenant tous les produits
     */
    List<Produit> getAll();
    /**
     * Recherche des produits par leur nom.
     * @param motCle Le mot-clé à rechercher dans le nom des produits
     * @return Une liste des produits dont le nom contient le mot-clé
     */
    List<Produit> findByNom(String motCle);
    /**
     * Recherche un produit par son identifiant.
     * @param id L'identifiant du produit recherché
     * @return Le produit correspondant à l'id ou null si non trouvé
     */
    Produit findById(long id);
    /**
     * Supprime un produit de la collection.
     * @param id L'identifiant du produit à supprimer
     */
    void delete(long id);
}
