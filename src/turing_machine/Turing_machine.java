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

    // Indice Rouge : Vérifier si un chiffre est pair ou impair
    public static String redHint(String password) {
        Random random = new Random();
        int index = random.nextInt(password.length());
        char digit = password.charAt(index);
        boolean isEven = (digit - '0') % 2 == 0;
        return "Indice Rouge : Le chiffre à la position " + (index + 1) + " est " + (isEven ? "pair." : "impair.");
    }

    // Indice Vert : Donner la somme des chiffres du mot de passe
    public static String greenHint(String password) {
        int sum = 0;
        for (char c : password.toCharArray()) {
            sum += c - '0';
        }
        return "Indice Vert : La somme des chiffres du mot de passe est " + sum + ".";
    }

    // Indice Bleu : Révéler la position d’un chiffre
    public static String blueHint(String password) {
        Random random = new Random();
        int index = random.nextInt(password.length());
        return "Indice Bleu : Le chiffre à la position " + (index + 1) + " est " + password.charAt(index) + ".";
    }

    // Fournir des indices basés sur la couleur
    public static String provideHintByColor(String password, String color) {
        switch (color.toLowerCase()) {
            case "rouge":
                return redHint(password);
            case "vert":
                return greenHint(password);
            case "bleu":
                return blueHint(password);
            default:
                return "Couleur invalide. Choisissez parmi : Rouge, Vert, Bleu.";
        }
    }

    // Méthode principale du jeu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu Turing Machine !");
        
        int passwordLength = 4;
        int maxTurns = 3;
        String password = generatePassword(passwordLength);

        System.out.println("Un mot de passe de " + passwordLength + " chiffres a été généré.");
        System.out.println("Vous avez " + maxTurns + " tours pour le deviner.");
        
        int turn = 1;

        while (turn <= maxTurns) {
            System.out.println("\nTour " + turn + "/" + maxTurns);
            System.out.println("Options :");
            System.out.println("1. Entrer une hypothèse.");
            System.out.println("2. Demander un indice (Rouge, Vert ou Bleu).");
            System.out.print("Choisissez une option : ");
            String choice = scanner.nextLine();

            if (choice.equals("2")) {
                System.out.print("Choisissez une couleur d'indice (Rouge, Vert, Bleu) : ");
                String color = scanner.nextLine();
                String hint = provideHintByColor(password, color);
                System.out.println(hint);
                continue; // Permet à l'utilisateur de continuer le tour
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

            // Fournir un retour après la tentative
            System.out.println("Mauvaise réponse. Essayez encore !");
            turn++;
        }

        if (turn > maxTurns) {
            System.out.println("Partie terminée ! Le mot de passe était : " + password);
        }

        scanner.close();
    }
}



