package cstjean.mobile.damier;

import androidx.fragment.app.Fragment;

/**
 * Activity des noms de joueurs.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public class NomJoueurActivity extends ContainerActivity {

    /**
     * Pour créer un fragment associé aux noms des joueurs.
     *
     * @return un fragment pour les noms de joueurs.
     */
    @Override
    protected Fragment createFragment() {
        return new NomJoueurFragment();
    }
}
