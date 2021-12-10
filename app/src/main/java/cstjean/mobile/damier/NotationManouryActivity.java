package cstjean.mobile.damier;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

/**
 * Activity pour les notations manoury
 */
public class NotationManouryActivity extends ContainerActivity {
    /**
     *Créer le fragment.
     * @return le fragment créer
     */
    @Override
    protected Fragment createFragment() {
        return new NotationManouryFragment();
    }

    /**
     * Donne l'intent à utilisé pour afficher l'activity.
     * @param packageContext le context à utiliser
     * @return l'intent à utiliser
     */
    public static Intent newIntent(Context packageContext){
        return new Intent(packageContext, NotationManouryActivity.class);
    }
}