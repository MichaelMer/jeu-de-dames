package cstjean.mobile.damier;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

/**
 * Activity pour le jeu de dames, Damier.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class DamierActivity extends ContainerActivity {

    /**
     * Pour créer un fragment associé au jeu de dames.
     *
     * @return un fragment pour le jeu de dames.
     */
    @Override
    protected Fragment createFragment() {
        return DamierFragment.newInstance();
    }

    /**
     * Création de l'intent à utiliser pour afficher l'activity.
     *
     * @param packageContext le context à utiliser.
     * @return l'intent à utiliser.
     */
    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, DamierActivity.class);
    }
}
