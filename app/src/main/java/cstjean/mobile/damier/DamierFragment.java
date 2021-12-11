package cstjean.mobile.damier;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import cstjean.mobile.damier.jeu.CouleurPion;
import cstjean.mobile.damier.jeu.Damier;
import cstjean.mobile.damier.jeu.EtatJeu;
import java.util.ArrayList;

/**
 * Fragment pour le jeu de dame.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class DamierFragment extends Fragment {

    /** Le gridLayout des boutons. */
    private GridLayout gridBoutons;

    /** L'instance du jeu de dames. */
    private final Damier damier = Damier.getInstance();

    /** Le TextView du tour du joueur. */
    private TextView txtJoueur;

    /** Le TextView du joueur ayant gagné. */
    private TextView txtGagnant;

    /** La liste des anciens pions affichés sur l'écran. */
    private ArrayList<Integer> listeAnciensPions = new ArrayList<>();

    /** La couleur des cases noires. */
    private final int couleurNoir = Color.rgb(195, 141, 83);

    /**
     * Instanciation de l'interface du jeu de dame.
     *
     * @param inflater Pour instancier l'interface.
     * @param container Le parent qui contiendra l'interface.
     * @param savedInstanceState Les données conservées au changement d'état.
     * @return La vue.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final int grosseur = 100;
        int position = 0;
        View view = inflater.inflate(R.layout.fragment_damier, container, false);
        txtJoueur = view.findViewById(R.id.txt_tour_joueur);
        txtGagnant = view.findViewById(R.id.txt_message_victoire);
        gridBoutons = view.findViewById(R.id.grid_boutons);
        Button btnRecommencer = view.findViewById(R.id.btn_recommencer);
        Button btnNotation = view.findViewById(R.id.btn_afficher_Notation);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                ImageButton bouton = new ImageButton(this.getContext());

                bouton.setPadding(8, 8, 8, 8);
                bouton.setEnabled(false);

                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    bouton.setBackgroundColor(couleurNoir);
                    position++;
                    bouton.setTag(position);

                } else {
                    bouton.setBackgroundColor(Color.rgb(244, 208, 165));
                }

                GridLayout.LayoutParams parametres = new GridLayout.LayoutParams();
                if (getOrientation()) {
                    parametres.height = parametres.width = grosseur;
                } else {
                    parametres.height = parametres.width = (int) Math.abs(grosseur * 0.65);
                }
                bouton.setLayoutParams(parametres);

                gridBoutons.addView(bouton);
            }
        }

        btnRecommencer.setOnClickListener(v -> {
            enleverArtefact();
            damier.initialiser();
            rafraichirJeu();
        });

        btnNotation.setOnClickListener(v -> {
            Intent intent = NotationManouryActivity.newIntent(getActivity());
            startActivity(intent);
        });

        rafraichirJeu();
        return view;
    }

    /**
     * Rafraichît le jeu à chaque tour joué.
     *      Vérifie si le jeu est toujours en cours,
     *      Change le joueur, active les boutons, affiche les pions
     */
    private void rafraichirJeu() {
        if (damier.getEtatJeu() == EtatJeu.VICTOIREBLANC) {
            txtGagnant.setText(getString(R.string.txt_victoire, damier.getNomBlanc()));
            txtJoueur.setText(getString(R.string.vider));
        } else if (damier.getEtatJeu() == EtatJeu.VICTOIRENOIR) {
            txtGagnant.setText(getString(R.string.txt_victoire, damier.getNomNoir()));
            txtJoueur.setText(getString(R.string.vider));
        } else {
            txtGagnant.setText(getString(R.string.vider));
            txtJoueur.setText(getString(R.string.txt_tour_joueur, damier.getJoueurCourant(),
                    damier.getTourActuel().toString().toLowerCase()));
        }
        if (damier.getNombrePion() <= 0) {
            damier.initialiser();
        }

        activerBoutons();
        initialiserPions();
        enleverPrise();
    }

    /**
     * Initialise les pions sur les cases appropriés.
     */
    private void initialiserPions() {
        for (int index : damier.getPositionsPions()) {
            CouleurPion couleur = damier.getPion(index).getCouleur();
            ImageButton bouton = gridBoutons.findViewWithTag(index);

            if (couleur == CouleurPion.NOIR) {
                if (damier.getPionEstDame(index)) {
                    bouton.setImageResource(R.drawable.dame_noir);
                } else {
                    bouton.setImageResource(R.drawable.pion_noir);
                }
            } else {
                if (damier.getPionEstDame(index)) {
                    bouton.setImageResource(R.drawable.dame_blanc);
                } else {
                    bouton.setImageResource(R.drawable.pion_blanc);
                }
            }
            bouton.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    /**
     * Supprime les pions qui ont été "mangés" de l'affichage.
     */
    private void enleverPrise() {
        for (int position : listeAnciensPions) {
            if (!damier.getPositionsPions().contains(position)) {
                ImageButton pion = gridBoutons.findViewWithTag(position);
                pion.setImageResource(android.R.color.transparent);
            }
        }

        listeAnciensPions = damier.getPositionsPions();
    }

    /**
     * Affiche le pion sélectionné (en jaune) et affiche les cases disponibles (en bleu).
     *      Active les boutons des cases disponibles
     *
     * @param bouton Le pion sélectionné
     */
    private void caseAppuyee(ImageButton bouton) {
        int position = (int) bouton.getTag();

        enleverArtefact();
        damier.selectionnerPion(position);
        ArrayList<Integer> mouvements = damier.getMouvementDispoPion();

        bouton.setBackgroundColor(Color.rgb(230, 184, 37));

        for (int mouvement : mouvements) {
            ImageButton caseDispo = gridBoutons.findViewWithTag(mouvement);
            caseDispo.setEnabled(true);
            caseDispo.setBackgroundColor(Color.rgb(13, 167, 209));
            caseDispo.setOnClickListener(v -> {
                rafraichirAffichage();
                damier.bougerPionSelectionner((int) caseDispo.getTag());
                rafraichirJeu();
            });
        }
    }

    /**
     * Désactive tous les boutons et active les boutons du joueur actuel.
     */
    private void activerBoutons() {
        for (int position : damier.getPositionsPions()) {
            ImageButton pion = gridBoutons.findViewWithTag(position);
            pion.setEnabled(false);
        }

        for (int position : damier.getPositionsPionsCouleur(damier.getTourActuel())) {
            ImageButton pion = gridBoutons.findViewWithTag(position);
            pion.setEnabled(true);

            pion.setOnClickListener(v -> caseAppuyee(pion));
        }
    }

    /**
     * Affiche le pion sélectionné et ses mouvements disponibles.
     */
    private void rafraichirAffichage() {
        int pionActuel = damier.getPositionPionSelectionner();
        ArrayList<Integer> mouvements = damier.getMouvementDispoPion();
        mouvements.add(pionActuel);

        if (mouvements.contains(0)) {
            return;
        }

        for (int mouvement : mouvements) {
            ImageButton caseDispo = gridBoutons.findViewWithTag(mouvement);
            caseDispo.setImageResource(android.R.color.transparent);
            caseDispo.setBackgroundColor(couleurNoir);
            caseDispo.setEnabled(false);
        }
    }

    /**
     * Supprime la case sélectionnée et les mouvements disponibles de l'ancien pion sélectionné.
     */
    private void enleverArtefact() {
        for (int position : damier.getPositionsPionsCouleur(damier.getTourActuel())) {
            ImageButton pion = gridBoutons.findViewWithTag(position);
            pion.setBackgroundColor(couleurNoir);
        }
        for (int position : damier.getMouvementDispoPion()) {
            if (position == 0) {
                return;
            }
            ImageButton pion = gridBoutons.findViewWithTag(position);
            pion.setBackgroundColor(couleurNoir);
            pion.setEnabled(false);
        }
    }

    /**
     * Obtiens l'orientation de l'appareil.
     *
     * @return Vrai si l'orientation est en mode portrait, sinon faux
     */
    private Boolean getOrientation() {
        int orientation = this.getResources().getConfiguration().orientation;
        return orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * Crée une nouvelle instance du Fragment du damier.
     *
     * @return une nouvelle instance
     */
    public static DamierFragment newInstance() {
        return new DamierFragment();
    }
}
