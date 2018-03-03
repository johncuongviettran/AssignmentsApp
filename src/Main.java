import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.WEEKS;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("\n\nHello, AssignmentsApp!\n");

        //Outputs the current LocalDateTime.
        LocalDateTime today = LocalDateTime.now();
        System.out.println("The current date and time is " + today);

        //Outputs tommorrow's date using a formatter.
        LocalDateTime tommorrow = today.plus(1, DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        String formatDateTime = tommorrow.format(formatter);
        System.out.println("The date and time one day from now is " + formatDateTime);

        //Outputs the LocalDateTime 5 weeks from now.
        LocalDateTime fiveWeeksLater = today.plus(5, WEEKS);
        String formatFiveWeeksLater = fiveWeeksLater.format(formatter);
        System.out.println("The date and time 5 weeks from now is " + formatFiveWeeksLater);

        //Outputs the LocalDateTime of the user's birthdate with the time, 12:35.
        System.out.println("Enter your birthdate in the format of yyyy mm dd: ");
        LocalDateTime birthDateTime = LocalDateTime.of(sc.nextInt(),sc.nextInt(),sc.nextInt(),12,35);
        String formatBirthDateTime = birthDateTime.format(formatter);
        System.out.println("The date of the user's birthdate with the time, 12:35, is " + formatBirthDateTime);

        //Outputs the day of the week that the user was born on.
        DayOfWeek birthDateDayOfWeek = birthDateTime.getDayOfWeek();
        System.out.println("The day of the week of the user's birthdate is " + birthDateDayOfWeek);

        //Outputs the number of days the user has been alive.
        Long daysAlive = DAYS.between(birthDateTime,today);
        System.out.println("The user has been alive for " + daysAlive + " days.");

    }
}
