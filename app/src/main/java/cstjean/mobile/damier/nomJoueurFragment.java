package cstjean.mobile.damier;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class nomJoueurFragment extends Fragment {
    private TextInputEditText input_nomJoueur1;
    private TextInputEditText input_nomJoueur2;
    private TextView txt_erreur;
    private Button btn_commencer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nom_joueur, container, false);
        input_nomJoueur1 = view.findViewById(R.id.input_nom_joueur1);
        input_nomJoueur2 = view.findViewById(R.id.input_nom_joueur2);
        btn_commencer = view.findViewById(R.id.btn_commencer);
        txt_erreur = view.findViewById(R.id.txt_erreur);


        btn_commencer.setOnClickListener(v -> {
            if(nomCorrrect()) {
                //commencer partie
            }
        });
        return view;
    }
    private boolean nomCorrrect() {
        if(input_nomJoueur1.getEditableText().toString().equals("") ||
                input_nomJoueur2.getEditableText().toString().equals("")) {

            txt_erreur.setText(R.string.msg_erreur_nom_vide);
            return false;
        } else if (input_nomJoueur1.getEditableText().toString()
                .equals(input_nomJoueur2.getEditableText().toString())) {

            txt_erreur.setText(R.string.msg_erreur_nom_identique);
            return false;
        }
        return true;
    }
}