package cstjean.mobile.damier.jeu;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Classe pour les damiers.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class Damier {

    private static Damier instance = null;
    /**
     * Liste de pion du damier.
     */
    private final Map<Integer, Pion> listePion;

    /**
     * Constructeur.
     */
    private Damier() {
        listePion = new LinkedHashMap<>();
    }

    public static Damier getInstance() {
        if(instance == null) {
            return new Damier();
        }
        return instance;
    }

    /**
     * retourne le nombre de pion sur le damier.
     *
     * @return le nombre de pio sur le damier.
     */
    int getNombrePion() {
        return listePion.size();
    }

    /**
     * Ajoute un pion sur le damier.
     *
     * @param index où ajouter le pion dans le damier.
     * @param pion  le pion à ajouter.
     */
    void ajouterPion(int index, Pion pion) {
        listePion.put(index, pion);
    }

    /**
     * retourne le pion a la place spécifier.
     *
     * @param index la case où se situe le pions.
     * @return le pion sur la case.
     */
    Pion getPion(int index) {
        return listePion.get(index);
    }

    /**
     * Initialise le damier avec les pions et l'affichage.
     */
    void initialiser() {
        listePion.clear();
        for (int i = 1; i <= 20; i++) {
            listePion.put(i, new Pion(CouleurPion.NOIR));
        }
        for (int i = 31; i <= 50; i++) {
            listePion.put(i, new Pion(CouleurPion.BLANC));
        }
    }

    /**
     * donne le nombre de pions qui on une certaine couleur.
     *
     * @param couleur la couleur des pions.
     * @return le nombre de pion de la couleur
     */
    int getNbPionParCouleur(CouleurPion couleur) {
        int nbPion = 0;
        for (Pion pion : listePion.values()) {
            if (pion.getCouleur().equals(couleur)) {
                nbPion++;
            }
        }
        return nbPion;
    }
}
