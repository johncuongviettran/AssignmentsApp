import java.time.LocalDateTime;

public class Assignment implements Comparable<Assignment>{
    private LocalDateTime dateTime;
    private Category category;
    private Course course;
    private Integer priority;

    public Assignment(LocalDateTime dateTime, Category category, Course course, Integer priority) {
        this.dateTime = dateTime;
        this.category = category;
        this.course = course;
        this.priority = priority;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Category getCategory() {
        return category;
    }

    public Course getCourse() {
        return course;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Assignment{dateTime = " + dateTime + ", category = " + category + ", course = " + course + ", priority = " + priority + "}";
    }
    public Assignment(Assignment copyTemplate) {
        this.dateTime = copyTemplate.dateTime;
        this.category = copyTemplate.category;
        this.course = copyTemplate.course;
        this.priority = copyTemplate.priority;
    }

    @Override
    public boolean equals(Object obj) {
        Assignment other = (Assignment) obj;
        if (this.dateTime == other.dateTime & this.category == other.category & this.course == other.course & this.priority == other.priority){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int compareTo(Assignment comparedAssignment) {
        Integer returnInt = null;
        if (this.dateTime.isBefore(comparedAssignment.dateTime)){
            returnInt = -1;
        }
        else if (this.dateTime.equals(comparedAssignment.dateTime)){
            returnInt = 0;
        }
        else if (this.dateTime.isAfter(comparedAssignment.dateTime)){
            returnInt = 1;
        }
        return returnInt;
    }
    public String toStringDateTimeCourseCategoryPriority (){
        return this.dateTime + " " + this.course + " " + this.category + " " + this.priority;
    }
}
