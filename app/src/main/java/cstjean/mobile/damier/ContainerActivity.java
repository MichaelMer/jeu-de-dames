package cstjean.mobile.damier;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Activity qui agira à titre de conteneur, il contiendra qu'un seul Fragment.
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
public abstract class ContainerActivity extends AppCompatActivity {

    /**
     * Création du fragment pour associer à l'Activity.
     *
     * @return un fragment à associer à l'Activity
     */
    protected abstract Fragment createFragment();

    /**
     * Se lance lorsque l'Activity est initialisée.
     *
     * @param savedInstanceState Les données conservées
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_jeu_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_jeu_container, fragment).commit();
        }
    }
}