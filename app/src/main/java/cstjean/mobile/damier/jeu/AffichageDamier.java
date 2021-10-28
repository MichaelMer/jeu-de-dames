package cstjean.mobile.damier.jeu;

/**
 * Classe pour afficher le damier en console.
 */
public class AffichageDamier {

    /**
     * Donne l'affichage du damier dans une console.
     *
     * @param damier le damier à afficher.
     * @return l'affichage
     */
    public static String afficherDamier(Damier damier) {
        StringBuilder builder = new StringBuilder();
        int numCase = 1;
        for (int i = 0; i != 10; i++) {
            for (int k = 0; k != 10; k++) {
                if (i % 2 == 0 && k % 2 != 0 || i % 2 != 0 && k % 2 == 0) {
                    Pion pion = damier.getPion(numCase);
                    if (pion != null) {
                        builder.append(pion.getRepresentation());

                    } else {
                        builder.append('-');
                    }
                    numCase++;
                } else {
                    builder.append('-');
                }
            }
            builder.append(Util.SAUT_LIGNE);
        }

        return builder.toString();
    }
}
