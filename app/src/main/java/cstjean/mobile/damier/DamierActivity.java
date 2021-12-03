package cstjean.mobile.damier;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class DamierActivity extends ContainerActivity {
    // Si on fonctionne avec un singleton qui va stocker nos données
    // Il faut positionsPion et nomsJoueurs

    @Override
    protected Fragment createFragment() {
        //Retrouver nos données avec getIntent
        //Passer à newInstance les données
        return DamierFragment.newInstance();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, DamierActivity.class);
        //Rajouter dans notre intent les données
        return intent;
    }
}
