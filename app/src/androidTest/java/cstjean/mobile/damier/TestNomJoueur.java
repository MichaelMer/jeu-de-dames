package cstjean.mobile.damier;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test instrumenté pour le fragment des nom des joueurs
 */
@RunWith(AndroidJUnit4.class)
public class TestNomJoueur {
    @Rule
    public ActivityScenarioRule<NomJoueurActivity> rule = new ActivityScenarioRule<>(NomJoueurActivity.class);
    @Test
    public void TestNomSimilaire() {
        final String nom = "test";
        onView(withId(R.id.input_nom_joueur1)).perform(typeText(nom), closeSoftKeyboard());
        onView(withId(R.id.input_nom_joueur2)).perform(typeText(nom), closeSoftKeyboard());
        onView(withId(R.id.btn_commencer)).perform(click());
        onView(withId(R.id.txt_erreur)).check(matches(withText(R.string.msg_erreur_nom_identique)));
    }
    @Test
    public void TestNomVide() {
        final String nom = "test";
        onView(withId(R.id.input_nom_joueur1)).perform(typeText(nom), closeSoftKeyboard());
        onView(withId(R.id.btn_commencer)).perform(click());
        onView(withId(R.id.txt_erreur)).check(matches(withText(R.string.msg_erreur_nom_vide)));
    }
}