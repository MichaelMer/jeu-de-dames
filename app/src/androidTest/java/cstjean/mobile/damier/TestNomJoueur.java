package cstjean.mobile.damier;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.ComponentName;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test instrumenté pour le fragment des noms de joueurs
 *
 * @author Xavier Gagnon
 * @author Michaël Mercier
 */
@RunWith(AndroidJUnit4.class)
public class TestNomJoueur {
    @Rule
    public ActivityScenarioRule<NomJoueurActivity> rule = new ActivityScenarioRule<>(NomJoueurActivity.class);

    /**
     * Test qui s'assure que les noms des joueurs ne sont pas égaux
     */
    @Test
    public void TestNomSimilaire() {
        final String nom = "test";
        onView(withId(R.id.input_nom_joueur1)).perform(typeText(nom), closeSoftKeyboard());
        onView(withId(R.id.input_nom_joueur2)).perform(typeText(nom), closeSoftKeyboard());
        onView(withId(R.id.btn_commencer)).perform(click());
        onView(withId(R.id.txt_erreur)).check(matches(withText(R.string.msg_erreur_nom_identique)));
    }

    /**
     * Test qui s'assure que les noms des joueurs ne sont pas vide
     */
    @Test
    public void TestNomVide() {
        final String nom = "test";
        onView(withId(R.id.input_nom_joueur1)).perform(typeText(nom), closeSoftKeyboard());
        onView(withId(R.id.btn_commencer)).perform(click());
        onView(withId(R.id.txt_erreur)).check(matches(withText(R.string.msg_erreur_nom_vide)));
    }

    /**
     * Test qui s'assure que l'Activity du damier se lance lorsque vérification est bonne
     */
    @Test
    public void TestLancerDamier() {
        final String nom_1 = "lesBlancs";
        final String nom_2 = " joueur2 ";
        onView(withId(R.id.input_nom_joueur1)).perform(typeText(nom_1), closeSoftKeyboard());
        onView(withId(R.id.input_nom_joueur2)).perform(typeText(nom_2), closeSoftKeyboard());
        Intents.init();
        onView(withId(R.id.btn_commencer)).perform(click());
        intended(hasComponent(new ComponentName(getApplicationContext(), DamierActivity.class)));
        Intents.release();
    }
}