package cstjean.mobile.damier.jeu;

import java.util.ArrayList;
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
        if (instance == null) {
            instance = new Damier();
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

    public void viderDamier() {
        listePion.clear();
    }

    public ArrayList<Integer> selectionnerPion(int position) {
        ArrayList<Integer> reslutat = new ArrayList<>();
        if (listePion.get(position) != null) {
            reslutat.addAll(chercherMovement(position));
        }
        return reslutat;
    }

    private ArrayList<Integer> chercherMovement(int position) {
        ArrayList<Integer> reslutat = new ArrayList<>();
        int [] mouvements = getSuiteMouvement(position);
        for (int i =0; i < mouvements.length; i++) {
                if (listePion.get(position).estNoir() &&
                        listePion.get(position).getType() == TypePion.PION &&
                        position < position + mouvements[i]) {

                    reslutat.add(getMovement(i,position));
                } else if (!listePion.get(position).estNoir() &&
                        listePion.get(position).getType() == TypePion.PION &&
                        position > position + mouvements[i]) {
                    reslutat.add(getMovement(i,position));
                }
                if (listePion.get(position).getType() == TypePion.DAME) {
                    reslutat.add(getMovement(i,position));
                }
        }
        return reslutat;
    }

    private int getMovement(int index, int position) {
        int nouvellePosition = position + getSuiteMouvement(position)[index];
        if (listePion.get(nouvellePosition) == null) {
            return nouvellePosition;
        }
        if (listePion.get(position).getCouleur()!= listePion.get(nouvellePosition).getCouleur()) {
            int positionDePrise = nouvellePosition + getSuiteMouvement(nouvellePosition)[index];
            if(listePion.get(positionDePrise) == null) {
                return positionDePrise;
            }
        } else if (listePion.get(nouvellePosition) == null) {
            return nouvellePosition;
        } else if (position % 10 == 6 && index == 0 || index == 3){
            return 0;
        } else if (position % 10 == 5 && index == 2 || index == 4){
            return 0;
        }
        return 0;
    }

    private int[] getSuiteMouvement(int position) {
        if (position % 10 <= 5 && position % 10 > 0) {
            return new int[]{-5, -4, 5, 6};
        }
        else {
            return new int[]{-6, -5, 4, 5};
        }
    }
}
