import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean rejouer = true;

        System.out.println("Bienvenue dans le jeu MasterMind !");

        while (rejouer) {
            int[] sequenceSecrete = genererSequenceSecrete();

            boolean gagne = false;
            int essaisRestants = 12;
            System.out.println("Une séquence secrète de 5 chiffres (entre 1 et 9, sans répétition) a été générée.");

            while (essaisRestants > 0 && !gagne) {
                System.out.println("\nIl vous reste " + essaisRestants + " tentatives. Entrez votre proposition (5 chiffres, sans espaces) :");
                String proposition = scanner.nextLine();

                if (proposition.length() != 5 || !estValide(proposition)) {
                    System.out.println("Entrée invalide. Veuillez entrer exactement 5 chiffres (entre 1 et 9, sans répétition).\n");
                    continue;
                }

                int[] propositionJoueur = convertirProposition(proposition);

                int bienPlaces = 0;
                int malPlaces = 0;
                for (int i = 0; i < 5; i++) {
                    if (propositionJoueur[i] == sequenceSecrete[i]) {
                        bienPlaces++;
                    } else if (contient(sequenceSecrete, propositionJoueur[i])) {
                        malPlaces++;
                    }
                }

                if (bienPlaces == 5) {
                    gagne = true;
                    System.out.println("Félicitations ! Vous avez deviné la séquence secrète : " + convertirEnString(sequenceSecrete));
                } else {
                    System.out.println("Chiffres bien placés : " + bienPlaces);
                    System.out.println("Chiffres mal placés : " + malPlaces);
                }

                essaisRestants--;
            }

            if (!gagne) {
                System.out.println("Vous avez épuisé vos tentatives. La séquence secrète était : " + convertirEnString(sequenceSecrete));
            }

            System.out.println("\nVoulez-vous rejouer ? (oui/non)");
            String reponse = scanner.nextLine().toLowerCase();
            rejouer = reponse.equals("oui");
        }

        System.out.println("Merci d'avoir joué au MasterMind. À bientôt !");
        scanner.close();
    }

    public static int[] genererSequenceSecrete() {
        int[] sequence = new int[5];
        for (int i = 0; i < 5; i++) {
            int chiffre;
            boolean dejaUtilise;
            do {
                chiffre = (int) (Math.random() * 9) + 1;
                dejaUtilise = false;
                for (int j = 0; j < i; j++) {
                    if (sequence[j] == chiffre) {
                        dejaUtilise = true;
                        break;
                    }
                }
            } while (dejaUtilise);
            sequence[i] = chiffre;
        }
        return sequence;
    }

    public static boolean estValide(String proposition) {
        for (int i = 0; i < proposition.length(); i++) {
            char c = proposition.charAt(i);
            if (c < '1' || c > '9') {
                return false;
            }
            for (int j = 0; j < i; j++) {
                if (proposition.charAt(j) == c) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] convertirProposition(String proposition) {
        int[] propositionJoueur = new int[5];
        for (int i = 0; i < 5; i++) {
            propositionJoueur[i] = Character.getNumericValue(proposition.charAt(i));
        }
        return propositionJoueur;
    }

    public static boolean contient(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    public static String convertirEnString(int[] array) {
        StringBuilder result = new StringBuilder();
        for (int i : array) {
            result.append(i);
        }
        return result.toString();
    }
}
