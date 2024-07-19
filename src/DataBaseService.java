import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DataBaseService {
    private List<Todo> todos;
    DataBaseTasks tasksDataBase;
    BufferedReader reader;

    DataBaseService(BufferedReader reader) throws SQLException {
        todos = new ArrayList<>();
        tasksDataBase = new DataBaseTasks();
        todos = tasksDataBase.getData();
        this.sortTodos();
        this.reader = reader;
    }

    public void createTodo() throws IOException, SQLException {
        System.out.print("Title: ");
        String title = reader.readLine();
        LocalDate dueDate = autoRetryUntil(false).orElseThrow();
        Todo task = new Todo(title, dueDate);
        todos.add(new Todo(title, dueDate));
        tasksDataBase.insertDb(task);
        System.out.println("Saved!!!");
    }

    public void finishTodo() throws IOException, SQLException {
        int id = autoRetryIndex("Finish Todo Number: ");
        if (!(id < todos.size())) {
            System.out.println("No TODO by that number");
            System.out.println();
            return;
        }
        todos.remove(id);
        tasksDataBase.deleteData(id);
    }


    public List<Todo> getTodos() {
        return todos;
    }

    public void sortTodos() {
        todos.sort(Comparator.comparing(Todo::getDueDate));
    }


    private int autoRetryIndex(String prompt) throws IOException {
        int id;
        while (true) {
            try {
                System.out.print(prompt);
                id = Integer.parseInt(reader.readLine()) - 1;
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Invalid input , input a integer");
            }
        }
        return id;
    }


    public Optional<LocalDate> autoRetryUntil(boolean allowEmpty) throws IOException {
        while (true) {
            System.out.print("Until: ");
            try {
                String date = reader.readLine();
                if (!date.isEmpty()) {
                    return Optional.of(LocalDate.parse(date));
                } else if (allowEmpty) return Optional.empty();
                System.out.println("No input , try again");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format , try again!!!!");

            }

        }
    }

}
