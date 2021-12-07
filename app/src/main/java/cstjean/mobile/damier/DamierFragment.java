package cstjean.mobile.damier;

import android.content.res.Configuration;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import cstjean.mobile.damier.jeu.CouleurPion;
import cstjean.mobile.damier.jeu.Damier;
import cstjean.mobile.damier.jeu.EtatJeu;

public class DamierFragment extends Fragment {

    private final int GROSSEUR = 100;
    private final Map<Integer, Button> listeBouton = new LinkedHashMap<>();
    private final Damier damier = Damier.getInstance();
    private TextView txt_joueur;
    private TextView txt_gagnant;
    private Button btn_recommencer;
    private Button btn_notation;
    private final int COULEUR_BLANC = Color.rgb(244, 208, 165);
    private final int COULEUR_NOIR = Color.rgb(195, 141, 83);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_damier, container, false);

        txt_joueur = view.findViewById(R.id.txt_tour_joueur);
        txt_gagnant = view.findViewById(R.id.txt_message_victoire);
        btn_recommencer = view.findViewById(R.id.btn_recommencer);
        btn_notation = view.findViewById(R.id.btn_afficher_Notation);

        GridLayout gridBoutons = view.findViewById(R.id.grid_boutons);
        int position = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                ImageButton bouton = new ImageButton(this.getContext());

                bouton.setPadding(8,8,8,8);
                bouton.setEnabled(false);

                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    bouton.setBackgroundColor(COULEUR_NOIR);
                    position++;
                    bouton.setTag(position);

                } else {
                    bouton.setBackgroundColor(COULEUR_BLANC);
                }

                GridLayout.LayoutParams parametres = new GridLayout.LayoutParams();
                if (getOrientation()) {
                    parametres.height = parametres.width = GROSSEUR;
                } else {
                    parametres.height = parametres.width = (int) Math.abs(GROSSEUR * 0.65);
                }
                bouton.setLayoutParams(parametres);

                bouton.setOnClickListener(v -> {
                    caseAppuyee(gridBoutons, bouton);
                });

                gridBoutons.addView(bouton);
            }
        }
        rafraichirJeu(gridBoutons);
        return view;
    }

    private void rafraichirJeu(GridLayout gridBoutons) {

       if(damier.getEtatJeu() == EtatJeu.VICTOIREBLANC) {
            txt_gagnant.setText(getString(R.string.txt_victoire,damier.getNomBlanc()));
            txt_joueur.setText(getString(R.string.vider));
        } else if (damier.getEtatJeu() == EtatJeu.VICTOIRENOIR) {
            txt_gagnant.setText(getString(R.string.txt_victoire,damier.getNomNoir()));
            txt_joueur.setText(getString(R.string.vider));
        } else {
            txt_gagnant.setText(getString(R.string.vider));
            txt_joueur.setText("C'est le tour à " + damier.getJoueurCourant() + " (" + damier.getTourActuel().toString().toLowerCase() + ")");

           Log.d("yo", txt_joueur.getText().toString());
        }

        if (damier.getNombrePion() <= 0) {
            damier.initialiser();
        }

        activerBoutons(gridBoutons);
        initialiserPions(gridBoutons);
    }

    /**
     * Initialise les pions sur les cases appropriés
     * @param gridBoutons Le layout des boutons
     */
    private void initialiserPions(GridLayout gridBoutons) {
        for (int index : damier.getPositionsPions()) {
            CouleurPion couleur = damier.getPion(index).getCouleur();
            ImageButton bouton = (ImageButton)gridBoutons.findViewWithTag(index);

            if (couleur == CouleurPion.NOIR) {
                bouton.setImageResource(R.drawable.pion_noir);
            } else {
                bouton.setImageResource(R.drawable.pion_blanc);
            }
            bouton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    /**
     * Affiche le pion sélectionné (en jaune) et affiche les cases disponibles (en bleu)
     * Active les boutons des cases disponibles
     * @param gridBoutons Le layout des boutons
     * @param bouton Le pion sélectionné
     */
    private void caseAppuyee(GridLayout gridBoutons, ImageButton bouton) {
        enleverArtefact(gridBoutons);

        int position = (int)bouton.getTag();
        damier.selectionnerPion(position);
        ArrayList<Integer> mouvements = damier.getMouvementDispoPion();

        Log.d("pionAvant", position + "");
        if (mouvements.isEmpty()) return;
        Log.d("pionApres",  "oui " + position);

        bouton.setBackgroundColor(Color.rgb(230, 184, 37));

        // Surpasse le setOnClickListener de base
        for (int mouvement : mouvements) {
            ImageButton caseDispo = (ImageButton)gridBoutons.findViewWithTag(mouvement);
            caseDispo.setEnabled(true);
            caseDispo.setBackgroundColor(Color.rgb(13, 167, 209));
            caseDispo.setOnClickListener(v -> {
                rafraichirAffichage(gridBoutons);
                damier.bougerPionSelectionner((int)caseDispo.getTag());
                rafraichirJeu(gridBoutons);
            });
        }
    }

    /**
     * Désactive tous les boutons et active les boutons du joueur actuel
     * @param gridBoutons Le layout des boutons
     */
    private void activerBoutons(GridLayout gridBoutons) {
        for (int position : damier.getPositionsPions()) {
            ImageButton pion = (ImageButton)gridBoutons.findViewWithTag(position);
            pion.setEnabled(false);
        }
        for (int position : damier.getPositionsPionsCouleur(damier.getTourActuel())) {
            ImageButton pion = (ImageButton)gridBoutons.findViewWithTag(position);
            pion.setEnabled(true);
        }
    }

    /**
     * Affiche le pion sélectionné et ses mouvements disponibles
     * @param gridBoutons Le layout des boutons
     */
    private void rafraichirAffichage(GridLayout gridBoutons) {
        int pionActuel = damier.getPositionPionSelectionner();
        ArrayList<Integer> mouvements = damier.getMouvementDispoPion();
        mouvements.add(pionActuel);

        if (mouvements.contains(0)) return;

        Log.d("pion", pionActuel + "");
        Log.d("mouv", mouvements.toString());

        for (int mouvement : mouvements) {
            ImageButton caseDispo = (ImageButton)gridBoutons.findViewWithTag(mouvement);
            caseDispo.setImageResource(android.R.color.transparent);
            caseDispo.setBackgroundColor(COULEUR_NOIR);
            caseDispo.setEnabled(false);
        }
    }

    /**
     * Supprime la case sélectionnée et les mouvements disponibles de l'ancien pion sélectionné
     * @param gridBoutons Le layout des boutons
     */
    private void enleverArtefact(GridLayout gridBoutons) {
        for (int position : damier.getPositionsPionsCouleur(damier.getTourActuel())) {
            Log.d("Artefact 1", position + "");
            ImageButton pion = (ImageButton)gridBoutons.findViewWithTag(position);
            pion.setBackgroundColor(COULEUR_NOIR);
        }
        for (int position : damier.getMouvementDispoPion()) {
            if (position == 0) return;
            ImageButton pion = (ImageButton)gridBoutons.findViewWithTag(position);
            pion.setBackgroundColor(COULEUR_NOIR);
            pion.setEnabled(false);
        }
    }

    /**
     * Obtiens l'orientation de l'appareil
     * @return Vrai si l'orientation est en mode portrait, sinon faux
     */
    private Boolean getOrientation() {
        int orientation = this.getResources().getConfiguration().orientation;
        return orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static DamierFragment newInstance() {
        //Bundle bundle = new Bundle();
        DamierFragment fragment = new DamierFragment();

        return fragment;
    }
}
