package cstjean.mobile.damier.jeu;

import java.util.ArrayList;

public class NotationManoury {

    private static NotationManoury instance = null;
    private ArrayList<String> listeManoury;
    private final Damier damier;

    private NotationManoury() {
        listeManoury = new ArrayList<>();
        damier = Damier.getInstance();
    }

    public static NotationManoury getInstance() {
        if (instance == null) {
            instance = new NotationManoury();
        }
        return instance;
    }

    public void ajouterNotation(boolean pionNoir, int ancienne_pos, int nouv_pos, boolean prise) {

        String notation = "";

        if(ancienne_pos <= 9) {
           notation += '0';
        }
        notation += ancienne_pos;

        if(prise){
           notation += 'x';
        } else {
            notation += '-';
        }

        if(nouv_pos <= 9) {
            notation += '0';
        }
        notation += nouv_pos;

        if(pionNoir){
            notation = '(' + notation + ')';
        }

        listeManoury.add(notation);
    }

    public String getNotation(int positionNotation) {
        return listeManoury.get(positionNotation - 1);
    }

    public int getNbNotation() {
        return listeManoury.size();
    }

    public void retournerArriere(int positionNotation) {
        ArrayList<String> nouvelleListe = new ArrayList<>();

        for (int i =0; i<positionNotation; i++){
            nouvelleListe.add(listeManoury.get(i));
        }
        listeManoury = nouvelleListe;
        rafraichirJeu();
    }

    public void rafraichirJeu() {
        damier.viderDamier();

        for (String notation:listeManoury) {
            int milieuNotation = (int)(notation.length()/2);
            int positionInitiale = convertirCharEnNombre(notation.charAt(milieuNotation-2),
                    notation.charAt(milieuNotation-1));

            int nouvellePosition = convertirCharEnNombre(notation.charAt(milieuNotation + 1),
                    notation.charAt(milieuNotation + 2));

            damier.selectionnerPion(positionInitiale);
            damier.bougerPionSelectionner(nouvellePosition);
        }
    }

    private int convertirCharEnNombre (char positionDizane, char positionUnite) {
       return Integer.parseInt("" + positionDizane + positionUnite);
    }

    public void viderNotations() {
        listeManoury.clear();
    }

}
