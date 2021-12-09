package cstjean.mobile.damier;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
/**
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
 */
