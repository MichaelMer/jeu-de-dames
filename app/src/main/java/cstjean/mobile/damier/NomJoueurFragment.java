package cstjean.mobile.damier;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import cstjean.mobile.damier.jeu.Damier;

public class NomJoueurFragment extends Fragment {
    private TextInputEditText input_nomJoueur1;
    private TextInputEditText input_nomJoueur2;
    private TextView txt_erreur;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nom_joueur, container, false);
        input_nomJoueur1 = view.findViewById(R.id.input_nom_joueur1);
        input_nomJoueur2 = view.findViewById(R.id.input_nom_joueur2);
        Button btn_commencer = view.findViewById(R.id.btn_commencer);
        txt_erreur = view.findViewById(R.id.txt_erreur);


        btn_commencer.setOnClickListener(v -> {
            if(nomCorrect()) {
                Damier.getInstance().
                        setNomNoir(input_nomJoueur1.
                                getEditableText().
                                toString().
                                trim());

                Damier.getInstance().
                        setNomBlanc(input_nomJoueur2.
                                getEditableText().
                                toString().
                                trim());

                Intent intent = DamierActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
        // À enlever(pour skipper le menu)
        Intent intent = DamierActivity.newIntent(getActivity());
        startActivity(intent);
        return view;
    }

    private boolean nomCorrect() {
        String nomJoueur1 = input_nomJoueur1.getEditableText().toString().toLowerCase().trim();
        String nomJoueur2 = input_nomJoueur2.getEditableText().toString().toLowerCase().trim();

        if(nomJoueur1.equals("") || nomJoueur2.equals("")) {
            txt_erreur.setText(R.string.msg_erreur_nom_vide);
            return false;
        } else if (nomJoueur1.equals(nomJoueur2)) {
            txt_erreur.setText(R.string.msg_erreur_nom_identique);
            return false;
        }
        return true;
    }
}