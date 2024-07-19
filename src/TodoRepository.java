import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private final List<Todo> todos;
    private final String fileName;

    public TodoRepository() {
        this("src/todo.csv");
    }

    public TodoRepository(String fileName) {
        this.fileName = fileName;
        this.todos = new ArrayList<>();
        try {
            this.readFromFile();
        }catch (FileNotFoundException fileNotFoundException){
            System.out.println("file not found , if there are new todo's  it will be created");
        }catch (IOException ioException){
            System.err.println("Error while reading file");
            throw  new RuntimeException(ioException);
        }
    }

    private void readFromFile() throws IOException {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String line ;
            while ((line = bufferedReader.readLine()) != null){
                String[] values = line.split(",");
                todos.add(new Todo(
                        values[0], LocalDate.parse(values[1]),Boolean.parseBoolean(values[2])
                ));
            }
        }
    }
    public void writeToFile () throws  IOException{
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (Todo todo : todos) {
                writer.write(todo.toCsvRow());
                writer.newLine();
            }
        }
    }
    public List<Todo> getTodos(){
        return  this.todos;
    }
}
