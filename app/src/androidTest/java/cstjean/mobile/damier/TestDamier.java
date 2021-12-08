package cstjean.mobile.damier;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test pour l'interface du damier
 */
@RunWith(AndroidJUnit4.class)
public class TestDamier {
    @Rule
    public ActivityScenarioRule<DamierActivity> rule = new ActivityScenarioRule<>(DamierActivity.class);

    @Test
    public void testMouvement() {

    }

}
