import java.time.LocalDate;
import java.util.Scanner;

public class Todo {
    private  String title;
    private LocalDate dueDate;
    private  boolean completed;


    public  Todo(String title, LocalDate dueDate){
        this(title,dueDate,false);
    }

    public  Todo(String title, LocalDate dueDate, boolean completed){
        this.title = title;
        this.dueDate = dueDate;
        this.completed  = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", completed=" + completed +
                '}';
    }

    public  String toCsvRow(){
        return String.format("%s,%s,%s", title, dueDate, completed);
    }

    public void complete() {
        this.completed = true;
    }

}
