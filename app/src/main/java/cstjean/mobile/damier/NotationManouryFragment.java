package cstjean.mobile.damier;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cstjean.mobile.damier.jeu.NotationManoury;


public class NotationManouryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_notation_manoury, container, false);

        return view;
    }

    public class NotationManouryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView notationView;

        NotationManouryHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            notationView = itemView.findViewById(R.id.item_notation);
        }

        void bindCoursSession(String notation) {
            notationView.setText(notation);
        }

        @Override
        public void onClick(View v) {
            Intent intent = DamierActivity.newIntent(getActivity());
            startActivity(intent);
        }
    }

    public class NotationManouryAdapter extends RecyclerView.Adapter<NotationManouryHolder> {

        @NonNull
        @Override
        public NotationManouryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_notation_manoury, parent, false);
            return new NotationManouryHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull NotationManouryHolder holder, int position) {
            holder.bindCoursSession(NotationManoury.getInstance().getNotation(position));

        }
        @Override
        public int getItemCount() {
            return NotationManoury.getInstance().getNbNotation();
        }
    }
}