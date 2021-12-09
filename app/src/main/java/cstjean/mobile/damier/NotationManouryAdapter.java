package cstjean.mobile.damier;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cstjean.mobile.damier.jeu.NotationManoury;
/**
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
 */
