package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br>
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    public static ArrayList<String> readRecipes() {
        ArrayList<String> recipes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("recipes.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                recipes.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        return recipes;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName  レシピ名
     * @param ingredients 材料名
     */
    //
    public static void addRecipe(String recipeName, String ingredients) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("recipes.txt", true))) {
            writer.println(recipeName + "," + ingredients);
        } catch (IOException e) {
            System.out.println("Error reading file: 例外のメッセージ" + e.getMessage());
        }
    }
}
