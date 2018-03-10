import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public enum Course {
    DATA_STRUCTURES, ELEMENTARY_LINEAR_ALGEBRA, BEGINNING_TENNIS, ENVIRONMENTAL_ISSUES_IN_THE_CHRISTIAN_PERSPECTIVE;

    public static Course randomCourse(){
        ArrayList<Course> courses = new ArrayList<>();
        courses.addAll(Arrays.asList(values()));
        Random rand = new Random();
        return courses.get(rand.nextInt(courses.size()));
    }
}
