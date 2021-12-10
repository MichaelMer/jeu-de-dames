package cstjean.mobile.damier;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cstjean.mobile.damier.jeu.NotationManoury;

/**
 * Frgament pour les notations manoury.
 */
public class NotationManouryFragment extends Fragment {
    private RecyclerView recyclerView;
    NotationManouryAdapter notationManouryAdapter;

    /**
     * Fonction appeler à la créations du fragment
     * @param inflater l'inflater
     * @param container le container
     * @param savedInstanceState le savedInstanceState
     * @return la view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_notation_manoury, container, false);
        TextView txt_notation_titre = view.findViewById(R.id.txt_notation_manoury_titre);
        recyclerView = view.findViewById(R.id.recycler_view_notation);

        txt_notation_titre.setText(R.string.txt_titre_notation);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        notationManouryAdapter = new NotationManouryAdapter();
        recyclerView.setAdapter(notationManouryAdapter);
        return view;
    }

    /**
     * Classe interne pour afficher la liste des notations
     */
    public class NotationManouryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView notationView;
        private int index;

        /**
         * Constructeur.
         * @param itemView la view qui contien la liste.
         */
        NotationManouryHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            notationView = itemView.findViewById(R.id.item_notation);
        }

        /**
         * Bind la notation a la liste.
         * @param notation la notation à bind
         * @param position sa position
         */
        void bindNotations(String notation, int position) {
            notationView.setText(notation);
            index = position;
        }

        /**
         * Fonction qui est exucuter losque on click sur une notation.
         * @param v la view qui performe le click
         */
        @Override
        public void onClick(View v) {
            Intent intent = DamierActivity.newIntent(getActivity());
            NotationManoury.getInstance().retournerArriere(index);
            startActivity(intent);
        }
    }

    /**
     * L'adapter pour utiliser le holder dnas le fragment.
     */
    public class NotationManouryAdapter extends RecyclerView.Adapter<NotationManouryHolder> {
        /**
         * Fonction qui est appeler à la création du adapter
         * @param parent le parent le l'adapter
         * @param viewType le viewType
         * @return le holder à utiliser.
         */
        @NonNull
        @Override
        public NotationManouryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_notation_manoury, parent, false);
            return new NotationManouryHolder(view);
        }

        /**
         * Fonction utiliser pour donner la bonne notation aux holder
         * @param holder le holder a utiliser
         * @param position la position de la postion
         */
        @Override
        public void onBindViewHolder(@NonNull NotationManouryHolder holder, int position) {
            holder.bindNotations(NotationManoury.getInstance().getNotation(position + 1), position + 1);
        }

        /**
         * donne le nombre total de notations
         * @return le nombre total de notations
         */
        @Override
        public int getItemCount() {
            return NotationManoury.getInstance().getNbNotation();
        }
    }
}