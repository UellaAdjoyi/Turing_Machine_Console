/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package turing_machine;

import java.util.Scanner;
import java.util.Random;

public class Turing_machine {

    // Générer un mot de passe aléatoire
    public static String generatePassword(int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            password.append(random.nextInt(9) + 1); // Chiffres de 1 à 9
        }
        return password.toString();
    }

    // Fournir des indices basés sur l'hypothèse
    public static String provideHint(String password, String guess) {
        int correctPosition = 0;
        int correctDigits = 0;
        boolean[] guessedFlags = new boolean[password.length()];
        boolean[] passwordFlags = new boolean[password.length()];

        // Vérifier les chiffres à la bonne position
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == guess.charAt(i)) {
                correctPosition++;
                guessedFlags[i] = true;
                passwordFlags[i] = true;
            }
        }

        // Vérifier les chiffres corrects mais mal positionnés
        for (int i = 0; i < guess.length(); i++) {
            if (!guessedFlags[i]) {
                for (int j = 0; j < password.length(); j++) {
                    if (!passwordFlags[j] && guess.charAt(i) == password.charAt(j)) {
                        correctDigits++;
                        passwordFlags[j] = true;
                        break;
                    }
                }
            }
        }

        return correctPosition + " chiffres corrects à la bonne position, " + correctDigits + " autres chiffres corrects.";
    }

    // Fournir un indice aléatoire
    public static String provideRandomHint(String password) {
        Random random = new Random();
        int index = random.nextInt(password.length());
        return "Le chiffre à la position " + (index + 1) + " est : " + password.charAt(index);
    }

    // Méthode principale du jeu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu Turing Machine !");
        
        int passwordLength = 4;
        int maxTurns = 2;
        String password = generatePassword(passwordLength);

        System.out.println("Un mot de passe de " + passwordLength + " chiffres a été généré.");
        System.out.println("Vous avez " + maxTurns + " tours pour le deviner.");
        
        int turn = 1;
        boolean usedHintThisTurn = false;

        while (turn <= maxTurns) {
            System.out.println("\nTour " + turn + "/" + maxTurns);
            System.out.println("Options :");
            System.out.println("1. Entrer une hypothèse.");
            System.out.println("2. Demander un indice (une seule fois par tour).");
            System.out.print("Choisissez une option : ");
            String choice = scanner.nextLine();

            if (choice.equals("2")) {
                if (usedHintThisTurn) {
                    System.out.println("Vous avez déjà demandé un indice pour ce tour.");
                } else {
                    System.out.println("Indice : " + provideRandomHint(password));
                    usedHintThisTurn = true;
                }
                continue; // L'utilisateur peut encore entrer une hypothèse après avoir demandé un indice
            } else if (!choice.equals("1")) {
                System.out.println("Option invalide. Essayez encore.");
                continue;
            }

            System.out.print("Entrez votre hypothèse (4 chiffres) : ");
            String guess = scanner.nextLine();

            // Valider la longueur de l'hypothèse
            if (guess.length() != passwordLength || !guess.matches("\\d+")) {
                System.out.println("Veuillez entrer une combinaison valide de " + passwordLength + " chiffres.");
                continue;
            }

            // Vérifier si le mot de passe est correct
            if (guess.equals(password)) {
                System.out.println("Félicitations ! Vous avez trouvé le mot de passe : " + password + " 🎉");
                break;
            }

            // Fournir un indice après la tentative
            String hint = provideHint(password, guess);
            System.out.println("Indice : " + hint);

            turn++;
            usedHintThisTurn = false; // Réinitialiser pour le tour suivant
        }

        if (turn > maxTurns) {
            System.out.println("Partie terminée ! Le mot de passe était : " + password);
        }

        scanner.close();
    }
}


