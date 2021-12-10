package cstjean.mobile.damier;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import cstjean.mobile.damier.jeu.Damier;

/**
 * Fragment pour les noms des joueurs.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class NomJoueurFragment extends Fragment {

    /** TextInput du joueur 1. */
    private TextInputEditText inputNomJoueur1;

    /** TextInput du joueur 2. */
    private TextInputEditText inputNomJoueur2;

    /** TextView du message d'erreur. */
    private TextView txtErreur;

    /**
     * Instanciation de l'interface des noms de joueurs.
     *
     * @param inflater Pour instancier l'interface.
     * @param container Le parent qui contiendra l'interface.
     * @param savedInstanceState Les données conservées au changement d'état.
     * @return La vue.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nom_joueur, container, false);
        inputNomJoueur1 = view.findViewById(R.id.input_nom_joueur1);
        inputNomJoueur2 = view.findViewById(R.id.input_nom_joueur2);
        Button btnCommencer = view.findViewById(R.id.btn_commencer);
        txtErreur = view.findViewById(R.id.txt_erreur);

        btnCommencer.setOnClickListener(v -> {
            if (nomCorrect()) {
                Damier.getInstance().setNomBlanc(inputNomJoueur1.getEditableText()
                        .toString().trim());
                Damier.getInstance().setNomNoir(inputNomJoueur2.getEditableText()
                        .toString().trim());
                Intent intent = DamierActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
        return view;
    }

    /**
     * Vérifie si les noms sont valides.
     *
     * @return true si les noms sont valides.
     */
    private boolean nomCorrect() {
        String nomJoueur1 = inputNomJoueur1.getEditableText().toString().toLowerCase().trim();
        String nomJoueur2 = inputNomJoueur2.getEditableText().toString().toLowerCase().trim();

        if (nomJoueur1.equals("") || nomJoueur2.equals("")) {
            txtErreur.setText(R.string.msg_erreur_nom_vide);
            return false;
        } else if (nomJoueur1.equals(nomJoueur2)) {
            txtErreur.setText(R.string.msg_erreur_nom_identique);
            return false;
        }
        return true;
    }
}