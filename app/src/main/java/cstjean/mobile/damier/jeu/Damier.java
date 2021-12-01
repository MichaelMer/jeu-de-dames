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

    private boolean tourAuBlanc = true;
    private String nomBlanc = "Blanc";
    private String nomNoir = "Noir";

    private boolean estPionSelectionner = false;
    private int positionPionSelectionner = 0;
    private final ArrayList<Integer> mouvementDispoPion = new ArrayList<>();


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
     * change le nom du joueur pour les blancs
     * @param nomBlanc ne nouveau nom
     */
    public void setNomBlanc(String nomBlanc) {
        this.nomBlanc = nomBlanc;
    }
    /**
     * change le nom du joueur pour les noirs
     * @param nomNoir ne nouveau nom
     */
    public void setNomNoir(String nomNoir) {
        this.nomNoir = nomNoir;
    }

    /**
     * getter pour le nom du joueur pour les blancs
     * @return le nom du joueur pour les blancs
     */
    public String getNomBlanc() {
        return nomBlanc;
    }
    /**
     * getter pour le nom du joueur pour les noirs
     * @return le nom du joueur pour les noirs
     */
    public String getNomNoir() {
        return nomNoir;
    }

    public ArrayList<Integer> getMouvementDispoPion() {
        return mouvementDispoPion;
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

    /**
     * Vide le damier de pions
     */
    public void viderDamier() {
        listePion.clear();
        nomBlanc = "Blanc";
        nomNoir = "Noir";
    }

    /**
     * Selectionne les mouvement disponible pour le pion.
     *
     * @param position la position du pion sélectionné
     * @return une liste des mouvements possibles
     */
    public void selectionnerPion(int position) {
        if (listePion.get(position) != null) {
            positionPionSelectionner = position;
            mouvementDispoPion.addAll(chercherMovement(position));
        }
    }

    /**
     * Cherche les mouvements possible.
     *
     * @param position la position du pion sélectionné
     * @return un liste des mouvements disponibles
     */
    private ArrayList<Integer> chercherMovement(int position) {
        estPionSelectionner = true;
        ArrayList<Integer> resultat = new ArrayList<>();
        int[] mouvements = getSuiteMouvement(position);
        for (int i = 0; i < mouvements.length; i++) {
            resultat.addAll(getMouvement(i,position));
        }
        return resultat;
    }
    /**
     * Donne le mouvement selon la suite de mouvement.
     *
     * @param index    l'index de la liste de mouvement
     * @param position la position du pion sélectionné
     * @return le nouveaux mouvement si possible sinon la position du pion
     */
    private ArrayList<Integer> getMouvement(int index, int position) {
            ArrayList<Integer> resultat = new ArrayList<>();
        if (listePion.get(position)!= null &&
                listePion.get(position).getCouleur() == listePion.get(positionPionSelectionner).getCouleur() &&
                position!= positionPionSelectionner) {
            return resultat;
        } else if(listePion.get(position + getSuiteMouvement(position)[index]) != null &&
                position != positionPionSelectionner && listePion.get(position) != null) {
            return resultat;
        } else if (estMauvaiseDirectionPion(position, index)) {
            if(listePion.get(position) == null) {
                return resultat;
            } else {
                if(listePion.get(position).getCouleur() != listePion.get(positionPionSelectionner).getCouleur()) {
                    if (listePion.get(position + getSuiteMouvement(position)[index]) == null) {
                        resultat.add(position + getSuiteMouvement(position)[index]);
                    }
                }
            }
        } else if (estAuLimite(positionPionSelectionner, index))  {
            return resultat;
        } else if (estAuLimite(position,index)) {
            if (listePion.get(position) == null){
                resultat.add(position);
            } else {
                return resultat;
            }
        } else {
            if (listePion.get(positionPionSelectionner).getType() == TypePion.DAME ||
                    position == positionPionSelectionner || listePion.get(position) != null ) {
                resultat.addAll(getMouvement(index, position + getSuiteMouvement(position)[index]));
            }
            if(listePion.get(position) == null) {
                resultat.add(position);
            }
        }
        return resultat;
    }

    /**
     * regarde si la position donner est en arriere du pion
     * @param position la position a verifier
     * @param index l'index pour la direction dans la suite de mouvement
     * @return true si la position est en arriere du pion
     */
    public boolean estMauvaiseDirectionPion(int position, int index) {
        if (position == positionPionSelectionner) {
            return false;
        } else if (listePion.get(positionPionSelectionner).getType() == TypePion.DAME) {
            return false;
        } else if (listePion.get(positionPionSelectionner).estNoir() &&
                index < 2) {
            return true;
        } else return !listePion.get(positionPionSelectionner).estNoir() &&
                index >= 2;
    }
    /**
     * Regarde si la position est au limite du jeu.
     *
     * @param index    l'index de la liste de mouvement
     * @param position la position du pion sélectionné
     * @return oui si le pion est au limite du jeu
     */
    public boolean estAuLimite(int position, int index) {
        if(position % 10 == 6 && index == 0 || position % 10 == 6 && index == 2 ||
                position % 10 == 5 && index == 1 || position % 10 == 5 && index == 3) {
            return true;
        } else return position >= 1 && position <= 5  && index < 2||
                position >= 46 && position <= 50 && index >=2;
    }



    /**
     * Donne la suite de mouvement disponible selon si la rangée est impaire où non.
     *
     * @param position La position du pion sélectionné.
     * @return Une liste des nombre à additonner selon leur directions:
     * 0: haut droit
     * 1: haut gauche
     * 2: bas droit
     * 3: bas gauche
     */
    private int[] getSuiteMouvement(int position) {
        if (position % 10 <= 5 && position % 10 > 0) {
            return new int[]{-5, -4, 5, 6};
        } else {
            return new int[]{-6, -5, 4, 5};
        }
    }

    /**
     * Enlève la selection d'un pion.
     */
    public void enleverSelection() {
        mouvementDispoPion.clear();
        estPionSelectionner = false;
        positionPionSelectionner = 0;
    }

    /**
     * bouge le pion selectionner à la position donné
     *
     * @param nouvellePosition la nouvlle position du pion
     */
    public void bougerPionSelectionner(int nouvellePosition) {
        if (estPionSelectionner) {
            Pion pion =  listePion.get(positionPionSelectionner);
                listePion.remove(positionPionSelectionner);
                for (int i= 0; i <= 3; i++) {
                 if(regarderBranche(nouvellePosition,positionPionSelectionner, i)) {
                     supprimerPion(nouvellePosition,i);
                     listePion.put(nouvellePosition, pion);
                     break;
                 }
            }
            enleverSelection();
        }
    }

    /**
     * fonction qui regarde si la postion demmander fait partie de cet direction
     * @param positionAtrouver la poisiton a trouver
     * @param positionActuel la postion actuel
     * @param index l'index pour la direction dans la suite de mouvement
     * @return true si la position fait parti de cet branche
     */
    private boolean regarderBranche(int positionAtrouver,int positionActuel, int index) {
        if(estAuLimite(positionActuel, index)) {
            return false;
        }
        if (positionActuel == positionAtrouver) {
            return true;
        }
        return regarderBranche(positionAtrouver,
                positionActuel + getSuiteMouvement(positionActuel)[index],
                index);
    }

    /**
     * supprimer les pion dans le chemin
     * @param positionaArreter la position a arreter
     * @param index l'index pour la direction dans la suite de mouvement
     */
    private void supprimerPion(int positionaArreter, int index) {
        int positionActuel =
                positionPionSelectionner + getSuiteMouvement(positionPionSelectionner)[index];
        while (positionaArreter != positionActuel) {
            listePion.remove(positionActuel);
            positionActuel = positionActuel + getSuiteMouvement(positionActuel)[index];
        }
    }
}
