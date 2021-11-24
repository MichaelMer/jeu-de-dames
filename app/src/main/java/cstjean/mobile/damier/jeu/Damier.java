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

    private boolean estPionSelectionner = false;
    private int positionPionSelectionner = 0;
    private final ArrayList<Integer> selectionMouvementPion = new ArrayList<>();

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
        if (listePion.get(position) != null) {
            positionPionSelectionner = position;
            selectionMouvementPion.addAll(chercherMovement(position));
        }

        return selectionMouvementPion;
    }

    private ArrayList<Integer> chercherMovement(int position) {
        estPionSelectionner = true;
        ArrayList<Integer> resultat = new ArrayList<>();
        int [] mouvements = getSuiteMouvement(position);
        for (int i =0; i < mouvements.length; i++) {
            if (listePion.get(position).estNoir() &&
                    listePion.get(position).getType() == TypePion.PION &&
                    position < position + mouvements[i]) {

                resultat.add(getMouvement(i,position));
            } else if (!listePion.get(position).estNoir() &&
                    listePion.get(position).getType() == TypePion.PION &&
                    position > position + mouvements[i]) {
                resultat.add(getMouvement(i,position));
            }
            if (listePion.get(position).getType() == TypePion.DAME) {
                resultat.add(getMouvement(i,position));
            }
        }
        return filtrer(resultat, position);
    }

    private ArrayList<Integer> filtrer(ArrayList<Integer> listeAFiltrer, int nbAEnlever) {
        ArrayList<Integer> listeFiltrer = new ArrayList<>();
        for (int nb: listeAFiltrer) {
            if(nb != nbAEnlever){
                if(nb >= 1 && nb <= 50) {
                    listeFiltrer.add(nb);
                }
            }
        }
        return listeFiltrer;
    }

    private int getMouvement(int index, int position) {
        int nouvellePosition = position + getSuiteMouvement(position)[index];

        if(estAuLimite(index, position)) {
            return position;
        }
        if (listePion.get(nouvellePosition) == null) {
            return nouvellePosition;
        }

        if (listePion.get(position).getCouleur()!= listePion.get(nouvellePosition).getCouleur()) {
            int positionDePrise = nouvellePosition + getSuiteMouvement(nouvellePosition)[index];

            if(estAuLimite(index, nouvellePosition)) {
                return position;
            }
            if(listePion.get(positionDePrise) == null) {
                return positionDePrise;
            }
        } else if (listePion.get(nouvellePosition) == null) {
            return nouvellePosition;
        }
        return position;
    }

    private int[] getSuiteMouvement(int position) {
        if (position % 10 <= 5 && position % 10 > 0) {
            return new int[]{-5, -4, 5, 6};
        }
        else {
            return new int[]{-6, -5, 4, 5};
        }
    }

    private boolean estAuLimite(int index, int position) {
        return position % 10 == 6 && index == 0 ||
                position % 10 == 6 && index == 2 ||
                position % 10 == 5 && index == 1 ||
                position % 10 == 5 && index == 3;

    }

    public void enleverSelection(){
        selectionMouvementPion.clear();
        estPionSelectionner = false;
        positionPionSelectionner = 0;
    }

    public void bougerPionSelectionner(int nouvellePosition) {
        if(estPionSelectionner){
            Pion pion = listePion.get(positionPionSelectionner);
            listePion.remove(positionPionSelectionner);
            listePion.put(nouvellePosition, pion);
            enleverSelection();
        }
    }
}
