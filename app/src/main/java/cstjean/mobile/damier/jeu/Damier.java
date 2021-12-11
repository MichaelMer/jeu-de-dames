package cstjean.mobile.damier.jeu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Classe pour les damiers.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class Damier {

    /** L'instance du damier. */
    private static Damier instance = null;

    /** Liste de pion du damier. */
    private final Map<Integer, Pion> listePion;

    /** Enum de l'état du jeu actuel. */
    private EtatJeu etatJeu = EtatJeu.ENCOURS;

    /** Enum de l'état du jeu actuel. */
    private boolean tourAuBlanc = true;

    /** Nom du joueur avec pions blancs. */
    private String nomBlanc = "Blanc";

    /** Nom du joueur avec pions noirs. */
    private String nomNoir = "Noir";

    /** Si un pion est sélectionné. */
    private boolean estPionSelectionner = false;

    /** Position du pion sélectionné. */
    private int positionPionSelectionner = 0;

    /** Liste des mouvements disponibles pour un pion. */
    private final ArrayList<Integer> mouvementDispoPion = new ArrayList<>();

    /**
     * Constructeur du damier.
     */
    private Damier() {
        listePion = new LinkedHashMap<>(40);
    }

    /**
     * Récupère l'instance du damier.
     *
     * @return L'instance du damier.
     */
    public static Damier getInstance() {
        if (instance == null) {
            instance = new Damier();
        }
        return instance;
    }

    /**
     * Retourne le nombre de pion sur le damier.
     *
     * @return le nombre de pions sur le damier.
     */
    public int getNombrePion() {
        return listePion.size();
    }

    /**
     * Modifie le nom du joueur pour les blancs.
     *
     * @param nomBlanc Le nouveau nom.
     */
    public void setNomBlanc(String nomBlanc) {
        this.nomBlanc = nomBlanc;
    }

    /**
     * Modifie le nom du joueur pour les noirs.
     *
     * @param nomNoir Le nouveau nom.
     */
    public void setNomNoir(String nomNoir) {
        this.nomNoir = nomNoir;
    }

    /**
     * Obtiens le nom du joueur des blancs.
     *
     * @return le nom du joueur pour les blancs.
     */
    public String getNomBlanc() {
        return nomBlanc;
    }

    /**
     * Obtiens le nom du joueur des noirs.
     *
     * @return le nom du joueur pour les noirs.
     */
    public String getNomNoir() {
        return nomNoir;
    }

    /**
     * Obtiens les mouvements disponibles pour le pion selectionné.
     *
     * @return les mouvements possibles.
     */
    public ArrayList<Integer> getMouvementDispoPion() {
        return mouvementDispoPion;
    }

    /**
     * Obtiens l'état courant du jeu.
     *
     * @return l'état du jeu.
     */
    public EtatJeu getEtatJeu() {
        return etatJeu;
    }

    /**
     * Obtiens le nombre de pions qui ont une couleur spécifiée.
     *
     * @param couleur la couleur des pions.
     * @return le nombre de pions de cetter couleur.
     */
    public int getNbPionParCouleur(CouleurPion couleur) {
        int nbPion = 0;
        for (Pion pion : listePion.values()) {
            if (pion.getCouleur().equals(couleur)) {
                nbPion++;
            }
        }
        return nbPion;
    }

    /**
     * Retourne la position du pion sélectionné.
     *
     * @return la position du pion sélectionné.
     */
    public int getPositionPionSelectionner() {
        return positionPionSelectionner;
    }

    /**
     * Obtiens les positions de tous les pions.
     *
     * @return ArrayList de toutes les positions.
     */
    public ArrayList<Integer> getPositionsPions() {
        ArrayList<Integer> liste = new ArrayList<>(40);
        liste.addAll(listePion.keySet());
        return liste;
    }

    /**
     * Obtiens les positions des pions d'une couleur donnée.
     *
     * @param couleur CouleurPion, la couleur des pions.
     * @return ArrayList des positions.
     */
    public ArrayList<Integer> getPositionsPionsCouleur(CouleurPion couleur) {
        ArrayList<Integer> liste = new ArrayList<>(15);
        for (int index : listePion.keySet()) {
            if (listePion.get(index) != null) {
                if (Objects.requireNonNull(listePion.get(index)).getCouleur().equals(couleur)) {
                    liste.add(index);
                }
            }
        }
        return liste;
    }

    /**
     * Obtiens la couleur des pions du tour actuel (blanc ou noir).
     *
     * @return CouleurPion CouleurPion.BLANC ou CouleurPion.NOIR
     */
    public CouleurPion getTourActuel() {
        if (tourAuBlanc) {
            return CouleurPion.BLANC;
        } else {
            return CouleurPion.NOIR;
        }
    }

    /**
     * Obtiens le nom du joueur courant.
     *
     * @return string du nom du joueur.
     */
    public String getJoueurCourant() {
        if (tourAuBlanc) {
            return nomBlanc;
        } else {
            return nomNoir;
        }
    }

    /**
     * Retourne le pion à la position spécifiée.
     *
     * @param index la case où se situe le pion.
     * @return le pion sur la case.
     */
    public Pion getPion(int index) {
        return listePion.get(index);
    }

    /**
     * Retourne vrai si le pion à l'index donné est une dame.
     *
     * @param index la case où se situe le pion.
     * @return Vrai si le pion est une dame.
     */
    public Boolean getPionEstDame(int index) {
        return Objects.requireNonNull(listePion.get(index)).getType() == TypePion.DAME;
    }

    /**
     * Ajoute un pion sur le damier.
     *
     * @param index où ajouter le pion dans le damier.
     * @param pion  le pion à ajouter.
     */
    public void ajouterPion(int index, Pion pion) {
        listePion.put(index, pion);
    }

    /**
     * Initialise le damier avec les pions et l'affichage.
     */
    public void initialiser() {
        viderDamierSansNoms();
        for (int i = 1; i <= 20; i++) {
            listePion.put(i, new Pion(CouleurPion.NOIR));
        }
        for (int i = 31; i <= 50; i++) {
            listePion.put(i, new Pion(CouleurPion.BLANC));
        }
    }

    /**
     * Vide le damier de pions sans modifier les noms.
     */
    public void viderDamierSansNoms() {
        listePion.clear();
        etatJeu = EtatJeu.ENCOURS;
        tourAuBlanc = true;
        enleverSelection();
        NotationManoury.getInstance().viderNotations();
    }

    /**
     * Vide le damier de pions et réinitialise les noms.
     */
    public void viderDamier() {
        viderDamierSansNoms();
        nomBlanc = "Blanc";
        nomNoir = "Noir";
    }

    /**
     * Selectionne les mouvements disponibles pour le pion.
     *
     * @param position la position du pion sélectionné.
     */
    public void selectionnerPion(int position) {
        if (listePion.get(position) != null) {
            mouvementDispoPion.clear();
            estPionSelectionner = true;
            positionPionSelectionner = position;
            mouvementDispoPion.addAll(chercherMouvement(position));
        }
    }

    /**
     * Cherche les mouvements possible d'un pion donné.
     *
     * @param position la position du pion sélectionné.
     * @return un liste des mouvements disponibles.
     */
    private ArrayList<Integer> chercherMouvement(int position) {
        ArrayList<Integer> resultat = new ArrayList<>();
        int[] mouvements = getSuiteMouvement(position);
        for (int i = 0; i < mouvements.length; i++) {
            resultat.addAll(getMouvement(i, position));
        }
        return resultat;
    }

    /**
     * Obtiens le mouvement selon la suite de mouvements.
     *
     * @param index    l'index de la liste de mouvement.
     * @param position la position du pion sélectionné.
     * @return ArrayList contenant les mouvements si possible, sinon la position du pion.
     */
    private ArrayList<Integer> getMouvement(int index, int position) {
        ArrayList<Integer> resultat = new ArrayList<>();
        if (listePion.get(position) != null &&
                Objects.requireNonNull(listePion.get(position)).getCouleur() ==
                Objects.requireNonNull(listePion.get(positionPionSelectionner)).getCouleur() &&
                position != positionPionSelectionner) {
            return resultat;
        } else if (listePion.get(position + getSuiteMouvement(position)[index]) != null &&
                position != positionPionSelectionner && listePion.get(position) != null) {
            return resultat;
        } else if (estMauvaiseDirectionPion(position, index)) {
            if (listePion.get(position) == null) {
                return resultat;
            } else if (estAuLimite(position, index)) {
                return resultat;
            } else {
                if (Objects.requireNonNull(listePion.get(position)).getCouleur() !=
                    Objects.requireNonNull(listePion.get(positionPionSelectionner)).getCouleur()) {
                    if (listePion.get(position + getSuiteMouvement(position)[index]) == null) {
                        resultat.add(position + getSuiteMouvement(position)[index]);
                    }
                }
            }
        } else if (estAuLimite(positionPionSelectionner, index)) {
            return resultat;
        } else if (estAuLimite(position, index)) {
            if (listePion.get(position) == null) {
                resultat.add(position);
            } else {
                return resultat;
            }
        } else {
            if (Objects.requireNonNull(listePion.get(positionPionSelectionner)).getType() ==
                    TypePion.DAME ||
                    position == positionPionSelectionner || listePion.get(position) != null) {
                resultat.addAll(
                        getMouvement(index, position + getSuiteMouvement(position)[index])
                );
            }
            if (listePion.get(position) == null) {
                resultat.add(position);
            }
        }
        return resultat;
    }

    /**
     * Vérifie si la position donnée est en arrière du pion.
     *
     * @param position la position à verifier.
     * @param index    l'index pour la direction dans la suite de mouvement.
     * @return true si la position est en arriêre du pion.
     */
    public boolean estMauvaiseDirectionPion(int position, int index) {
        if (position == positionPionSelectionner) {
            return false;
        } else if (getPionEstDame(positionPionSelectionner)) {
            return false;
        } else if (Objects.requireNonNull(listePion.get(positionPionSelectionner)).estNoir() &&
                index < 2) {
            return true;
        } else {
            return !Objects.requireNonNull(listePion.get(positionPionSelectionner)).estNoir() &&
                    index >= 2;
        }
    }

    /**
     * Vérifie si la position est aux limites de jeu.
     *
     * @param index    l'index de la liste de mouvement.
     * @param position la position du pion sélectionné.
     * @return true si le pion est au limite du jeu.
     */
    public boolean estAuLimite(int position, int index) {
        if (position % 10 == 6 && index == 0 || position % 10 == 6 && index == 2 ||
                position % 10 == 5 && index == 1 || position % 10 == 5 && index == 3) {
            return true;
        } else {
            return position >= 1 && position <= 5 && index < 2 ||
                position >= 46 && position <= 50 && index >= 2;
        }
    }

    /**
     * Donne la suite de mouvement disponible selon si la rangée est impaire où non.
     *
     * @param position La position du pion sélectionné.
     * @return Une liste des nombre à additonner selon leur directions:
     *      0: haut droit
     *      1: haut gauche
     *      2: bas droit
     *      3: bas gauche
     */
    private int[] getSuiteMouvement(int position) {
        if (position % 10 <= 5 && position % 10 > 0) {
            return new int[]{-5, -4, 5, 6};
        } else {
            return new int[]{-6, -5, 4, 5};
        }
    }

    /**
     * Enlève la sélection du pion.
     */
    public void enleverSelection() {
        mouvementDispoPion.clear();
        estPionSelectionner = false;
        positionPionSelectionner = 0;
    }

    /**
     * Déplace le pion selectionné à la position donné.
     *
     * @param nouvellePosition la nouvelle position du pion.
     */
    public void bougerPionSelectionner(int nouvellePosition) {
        if (estPionSelectionner &&
                etatJeu == EtatJeu.ENCOURS &&
                verifierTour() &&
                listePion.get(positionPionSelectionner) != null) {
            boolean unePrise = false;
            Pion pion = listePion.get(positionPionSelectionner);
            listePion.remove(positionPionSelectionner);
            for (int i = 0; i <= 3; i++) {
                if (regarderBranche(nouvellePosition, positionPionSelectionner, i)) {
                    unePrise = supprimerPion(nouvellePosition, i);
                    listePion.put(nouvellePosition, pion);
                    break;
                }
            }
            if (pion != null) {
                NotationManoury.getInstance().ajouterNotation(pion.estNoir(),
                        positionPionSelectionner,
                        nouvellePosition,
                        unePrise);
            }
            changerPion(nouvellePosition);
            changerTour();
            positionPionSelectionner = nouvellePosition;
            verifierEtatJeu();
            enleverSelection();
        }
    }

    /**
     * Modifie le pion en dame s'il a atteint la rangée au fond du damier.
     *
     * @param position position à verifer.
     */
    private void changerPion(int position) {
        if (position >= 46 && position <= 50 &&
                Objects.requireNonNull(listePion.get(position)).getType() == TypePion.PION &&
                Objects.requireNonNull(listePion.get(position)).estNoir()) {
            listePion.put(position, new Dame(CouleurPion.NOIR));
        } else if (position >= 1 && position <= 5 &&
                Objects.requireNonNull(listePion.get(position)).getType() == TypePion.PION &&
                !Objects.requireNonNull(listePion.get(position)).estNoir()) {
            listePion.put(position, new Dame(CouleurPion.BLANC));
        }
    }

    /**
     * Fonction qui vérifie si la position demandée fait partie de cette direction.
     *
     * @param positionAtrouver la position à trouver.
     * @param positionActuel   la position actuelle.
     * @param index            l'index pour la direction dans la suite de mouvement.
     * @return true si la position fait partie de cette branche.
     */
    private boolean regarderBranche(int positionAtrouver, int positionActuel, int index) {
        if (positionActuel == positionAtrouver) {
            return true;
        }
        if (estAuLimite(positionActuel, index)) {
            return false;
        }
        return regarderBranche(positionAtrouver,
                positionActuel + getSuiteMouvement(positionActuel)[index],
                index);
    }

    /**
     * Supprime les pions dans le chemin.
     *
     * @param positionaArreter la position à arreter.
     * @param index            l'index pour la direction dans la suite de mouvement.
     */
    private boolean supprimerPion(int positionaArreter, int index) {
        boolean unePrise = false;
        int positionActuel =
                positionPionSelectionner + getSuiteMouvement(positionPionSelectionner)[index];
        while (positionaArreter != positionActuel) {
            if (listePion.get(positionActuel) != null) {
                unePrise = true;
                listePion.remove(positionActuel);
            }

            positionActuel = positionActuel + getSuiteMouvement(positionActuel)[index];
        }
        return unePrise;
    }

    /**
     * Verifier l'état du jeu.
     */
    public void verifierEtatJeu() {
        boolean aucunBlanc = true;
        boolean aucunNoir = true;
        boolean pionsBlancImmobile = true;
        boolean pionsNoirImmobile = true;
        if (getNbPionParCouleur(CouleurPion.BLANC) > 0) {
            aucunBlanc = false;
        }

        if (getNbPionParCouleur(CouleurPion.NOIR) > 0) {
            aucunNoir = false;
        }

        for (int position : listePion.keySet()) {
            selectionnerPion(position);
            if (getMouvementDispoPion().size() != 0 &&
                    Objects.requireNonNull(listePion.get(position)).estNoir()) {
                pionsNoirImmobile = false;
            }

            if (getMouvementDispoPion().size() != 0 &&
                    !Objects.requireNonNull(listePion.get(position)).estNoir()) {
                pionsBlancImmobile = false;
            }
        }
        if (aucunBlanc || pionsBlancImmobile) {
            etatJeu = EtatJeu.VICTOIRENOIR;
        } else if (aucunNoir || pionsNoirImmobile) {
            etatJeu = EtatJeu.VICTOIREBLANC;
        } else {
            etatJeu = EtatJeu.ENCOURS;
        }
    }

    /**
     * Change le tour actuel du jeu.
     */
    private void changerTour() {
        tourAuBlanc = !tourAuBlanc;
    }

    /**
     * Verifie si le pion sélectionner est de la bonne couleur pour le tour.
     *
     * @return true s'il est de la même couleur.
     */
    private boolean verifierTour() {
        if (Objects.requireNonNull(listePion.get(positionPionSelectionner)).estNoir() &&
                !tourAuBlanc) {
            return true;
        } else {
            return !Objects.requireNonNull(listePion.get(positionPionSelectionner)).estNoir() &&
                tourAuBlanc;
        }
    }
}
