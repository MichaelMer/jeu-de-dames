package cstjean.mobile.damier.jeu;

import java.util.ArrayList;

/**
 * Classe pour les notations manoury.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class NotationManoury {

    /**
     * instance de NotationManoury.
     */
    private static NotationManoury instance = null;

    /**
     * liste des notations manoury.
     */
    private ArrayList<String> listeManoury;

    /**
     * le jeu de Damier.
     */
    private final Damier damier;

    /**
     * Constructeur.
     */
    private NotationManoury() {
        listeManoury = new ArrayList<>();
        damier = Damier.getInstance();
    }

    /**
     * Getter pour l'instance.
     *
     * @return l'instance de NotationManoury
     */
    public static NotationManoury getInstance() {
        if (instance == null) {
            instance = new NotationManoury();
        }
        return instance;
    }

    /**
     * Ajoute une notation.
     *
     * @param pionNoir          true si le pion est noir.
     * @param positionInitiale  ancienne postion du pion
     * @param nouvellePostition nouvelle position du pion
     * @param prise             true si il y a une prise
     */
    public void ajouterNotation(boolean pionNoir,
                                int positionInitiale,
                                int nouvellePostition,
                                boolean prise) {
        String notation = "";

        if (positionInitiale <= 9) {
            notation += '0';
        }
        notation += positionInitiale;

        if (prise) {
            notation += 'x';
        } else {
            notation += '-';
        }

        if (nouvellePostition <= 9) {
            notation += '0';
        }
        notation += nouvellePostition;

        if (pionNoir) {
            notation = '(' + notation + ')';
        }

        listeManoury.add(notation);
    }

    /**
     * Getter pour une notation.
     *
     * @param positionNotation la notation a retourner
     * @return la notation selon la position demmander
     */
    public String getNotation(int positionNotation) {
        return listeManoury.get(positionNotation - 1);
    }

    /**
     * Getter pour le nombre de notations.
     *
     * @return le nombre de notation
     */
    public int getNbNotation() {
        return listeManoury.size();
    }

    /**
     * Faire un retour à un ancien état de jeu.
     *
     * @param positionNotation la position pour recommencer à jouer.
     */
    public void retournerArriere(int positionNotation) {
        ArrayList<String> nouvelleListe = new ArrayList<>();

        if (positionNotation <= 0) {
            return;
        }

        for (int i = 0; i < positionNotation; i++) {
            nouvelleListe.add(listeManoury.get(i));
        }
        listeManoury = nouvelleListe;
        rafraichirJeu();
    }

    /**
     * Rafraichi le jeu selon les notations manoury.
     */
    public void rafraichirJeu() {
        ArrayList<String> ancienneListe = new ArrayList<>(listeManoury);
        damier.viderDamierSansNoms();
        damier.initialiser();

        for (String notation : ancienneListe) {
            int milieuNotation = (notation.length() / 2);
            int positionInitiale = convertirCharEnNombre(notation.charAt(milieuNotation - 2),
                    notation.charAt(milieuNotation - 1));

            int nouvellePosition = convertirCharEnNombre(notation.charAt(milieuNotation + 1),
                    notation.charAt(milieuNotation + 2));

            damier.selectionnerPion(positionInitiale);
            damier.bougerPionSelectionner(nouvellePosition);
        }
    }

    /**
     * Converti les caractères en nombre.
     *
     * @param positionDizane nombre pour les dizaines
     * @param positionUnite  nombre pour les unités
     * @return le nombre converti
     */
    private int convertirCharEnNombre(char positionDizane, char positionUnite) {
        return Integer.parseInt("" + positionDizane + positionUnite);
    }

    /**
     * Vide la liste.
     */
    public void viderNotations() {
        listeManoury.clear();
    }
}
