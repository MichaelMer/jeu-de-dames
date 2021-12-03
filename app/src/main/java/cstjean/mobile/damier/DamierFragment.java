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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DamierFragment extends Fragment {

    private final int GROSSEUR = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_damier, container, false);

        //TextView txtJoueur = view.findViewById(R.id.tour_joueur);
        GridLayout gridBoutons = view.findViewById(R.id.grid_boutons);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button bouton = new Button(this.getContext());
                bouton.setTag(i + "" + j);

                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    bouton.setBackgroundColor(Color.rgb(195, 141, 83));
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
        }
        return view;
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
