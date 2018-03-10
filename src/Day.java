import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    public static Day randomDay(){
        ArrayList<Day> days = new ArrayList<>();
        days.addAll(Arrays.asList(values()));
        Random rand = new Random();
        return days.get(rand.nextInt(days.size()));
    }
}
