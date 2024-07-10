import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataHandling {

    //file에 data 추가한다
    public void inputDataToFile(List<ToDo> toDoList) {
        try (FileWriter fileWriter = new FileWriter("src/menu.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            for (ToDo t : toDoList) {
                StringBuilder todo = new StringBuilder();
                todo.append(t.getTitle()).append(',');
                todo.append(t.getUntil());
                String line = todo.toString();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    //file에 data를 꺼낸다
    public List<ToDo> getDataFromFile() {
        List<ToDo> toDoList = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader("src/menu.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String line;
            while ( (line = bufferedReader.readLine()) != null){
                String elements[] = line.split(",");
                ToDo  toDo = new ToDo(elements[0], elements.length > 1 ? elements[1]: "" );
                toDoList.add(toDo);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return  toDoList;
    }
}
