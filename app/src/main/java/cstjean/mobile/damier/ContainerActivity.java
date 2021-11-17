package cstjean.mobile.damier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_jeu_container);

        if (fragment == null) {
            fragment = new nomJoueurFragment();
            fm.beginTransaction().add(R.id.fragment_jeu_container, fragment).commit();
        }
    }
}