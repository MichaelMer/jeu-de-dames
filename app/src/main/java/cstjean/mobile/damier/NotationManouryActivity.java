package cstjean.mobile.damier;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

/**
 * Activity pour les notations Manoury.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class NotationManouryActivity extends ContainerActivity {

    /**
     * Pour créer un fragment associé à la notation Manoury.
     *
     * @return un fragment pour la notation Manoury.
     */
    @Override
    protected Fragment createFragment() {
        return new NotationManouryFragment();
    }

    /**
     * Donne l'intent à utiliser pour afficher l'activity.
     *
     * @param packageContext le context à utiliser.
     * @return l'intent à utiliser.
     */
    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, NotationManouryActivity.class);
    }
}