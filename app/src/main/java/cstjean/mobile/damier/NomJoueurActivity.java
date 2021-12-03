package cstjean.mobile.damier;

import androidx.fragment.app.Fragment;

/**
 * Activity des noms de joueurs
 *
 * @author Xavier Gagnon, Michaël Mercier
 */
public class NomJoueurActivity extends ContainerActivity {

    @Override
    protected Fragment createFragment() {
        return new NomJoueurFragment();
    }
}
