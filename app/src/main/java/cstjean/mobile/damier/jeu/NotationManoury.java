package cstjean.mobile.damier.jeu;

import java.util.ArrayList;

public class NotationManoury {

    private static NotationManoury instance = null;
    private final ArrayList<String> listeManoury;

    private NotationManoury() {
        listeManoury = new ArrayList<>();
    }

    public ArrayList<String> getListeNotation() {
        return listeManoury;
    }

    public static NotationManoury getInstance() {
        if (instance == null) {
            instance = new NotationManoury();
        }
        return instance;
    }


    public void ajouterNotation(Pion pion, int ancienne_pos, int nouv_pos, boolean prise) {
        String notation = ancienne_pos + "-" + nouv_pos;
        if (pion.estNoir()) {
            notation =  '(' + notation + ')';
        }
        if (prise) {
            notation = notation.replace('-', 'x');
        }

        listeManoury.add(notation);
    }

}
