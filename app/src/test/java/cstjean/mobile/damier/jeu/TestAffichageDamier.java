package cstjean.mobile.damier.jeu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test pour l'affichage du damier.
 */
public class TestAffichageDamier {
    /**
     * Test affichage pour le damier.
     */
    @Test
    public void testAffichageDamier() {
        String representationDamier =
                "-P-P-P-P-P" + Util.SAUT_LIGNE +
                        "P-P-P-P-P-" + Util.SAUT_LIGNE +
                        "-P-P-P-P-P" + Util.SAUT_LIGNE +
                        "P-P-P-P-P-" + Util.SAUT_LIGNE +
                        "----------" + Util.SAUT_LIGNE +
                        "----------" + Util.SAUT_LIGNE +
                        "-p-p-p-p-p" + Util.SAUT_LIGNE +
                        "p-p-p-p-p-" + Util.SAUT_LIGNE +
                        "-p-p-p-p-p" + Util.SAUT_LIGNE +
                        "p-p-p-p-p-" + Util.SAUT_LIGNE;

        for (int i = 1; i <= 50; i++){
            System.out.print(i +": "+i%10);
            if(i%10 <= 5 && i%10 > 0) {
                System.out.println(" impaire");
            } else {
                System.out.println(" paire");
            }
        }
        Damier damier = Damier.getInstance();
        damier.initialiser();
        assertEquals(representationDamier, AffichageDamier.afficherDamier(damier));
    }
}
