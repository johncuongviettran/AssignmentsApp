public class EnumTest {
    private Day day;
    private Course course;
    private Category category;

    public EnumTest(Day day) {
        this.day = day;
    }

    public EnumTest(Course course) {
        this.course = course;
    }

    public EnumTest(Category category) {
        this.category = category;
    }
    public void stateDay() {
        System.out.println("The day enumerated type value is " + day);
    }
    public void stateCourse(){
        System.out.println("The course enumerated type value is " + course);
    }
    public void stateCategory(){
        System.out.println("The category enumerated type value is " + category);
    }
}
