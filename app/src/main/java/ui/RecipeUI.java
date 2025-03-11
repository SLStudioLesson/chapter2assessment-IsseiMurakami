package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            System.out.println();
            System.out.println("Main Menu:");
            System.out.println("1: Display Recipes");
            System.out.println("2: Add New Recipe");
            System.out.println("3: Search Recipe");
            System.out.println("4: Exit Application");
            System.out.print("Please choose an option: ");

            try {
                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 一覧表示機能
                        displayRecipes();
                        break;
                    case "2":
                        // 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        ArrayList<String> recipes = fileHandler.readRecipes();

        System.out.println();
        System.out.print("Recipes: ");

        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
            return;
        }

        for (String recipeLine : recipes) {
            String[] parts = recipeLine.split(",", 2);
            String recipeName = parts[0].trim();
            String mainIngredients = (parts.length > 1) ? parts[1].trim() : "";

            System.out.println("-----------------------------------------");
            System.out.println("Recipe Name: " + recipeName);
            System.out.println("Main Ingredients: " + mainIngredients);
        }
        System.out.println("------------------------------------------");

    }

    /**
     * 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter recipe name: ");
        String recipeName = reader.readLine();

        System.out.print("Enter main ingredients (comma separated): ");
        String ingredients = reader.readLine();

        fileHandler.addRecipe(recipeName, ingredients);

        System.out.println("Recipe added successfully");
    }
}