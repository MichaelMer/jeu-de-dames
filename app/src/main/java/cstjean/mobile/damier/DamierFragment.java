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

import java.util.LinkedHashMap;
import java.util.Map;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_damier, container, false);

        txt_joueur = view.findViewById(R.id.txt_tour_joueur);
        txt_gagnant = view.findViewById(R.id.txt_message_vitoire);
        btn_recommencer = view.findViewById(R.id.btn_recommencer);
        btn_notation = view.findViewById(R.id.btn_afficher_Notation);

        GridLayout gridBoutons = view.findViewById(R.id.grid_boutons);
        int position = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                ImageButton bouton = new ImageButton(this.getContext());

                bouton.setImageResource(R.drawable.pion_noir);
                bouton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                bouton.setPadding(8,8,8,8);

                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    bouton.setBackgroundColor(Color.rgb(195, 141, 83));
                    position++;
                    bouton.setTag(position);

                } else {
                    bouton.setBackgroundColor(Color.rgb(244, 208, 165));
                    bouton.setEnabled(false);
                }

                GridLayout.LayoutParams parametres = new GridLayout.LayoutParams();
                if (getOrientation()) {
                    parametres.height = parametres.width = GROSSEUR;
                } else {
                    parametres.height = parametres.width = (int) Math.abs(GROSSEUR * 0.65);
                }
                bouton.setLayoutParams(parametres);

                gridBoutons.addView(bouton);
            }
            rafraichirJeu();
        }
        return view;
    }

    private void rafraichirJeu() {

        if(damier.getEtatJeu() == EtatJeu.VICTOIREBLANC) {
            txt_gagnant.setText(getString(R.string.txt_victoire,damier.getNomBlanc()));
            txt_joueur.setText(getString(R.string.vider));
        } else if (damier.getEtatJeu() == EtatJeu.VICTOIRENOIR) {
            txt_gagnant.setText(getString(R.string.txt_victoire,damier.getNomNoir()));
            txt_joueur.setText(getString(R.string.vider));
        } else {
            txt_gagnant.setText(getString(R.string.vider));
        }

        Log.d("Size damier", damier.getNombrePion() + "");

//        for (int i = 1; i <= 50; i++) {
//            if (damier.getPion(i).estNoir()){
//                //aller chercher le bouton et lui mettre un pion noir
//            } else if (!damier.getPion(i).estNoir()) {
//                // aller chercher le bouton et lui mettre un blanc
//            }
//
//        }
    }

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
