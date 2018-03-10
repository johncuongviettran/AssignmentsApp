import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public enum Category {
    HOMEWORK, QUIZ, TEST, PRESENTATION, FINAL_EXAM;

    public static Category randomCategory(){
        ArrayList<Category> categories = new ArrayList<>();
        categories.addAll(Arrays.asList(values()));
        Random rand = new Random();
        return categories.get(rand.nextInt(categories.size()));
    }
}
