import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ToDoHandling {
    private List<ToDo> toDoList;
    private BufferedReader bufferedReader;
    private Scanner scanner;
    private  DataHandling dataHandling;


    //  Create TODO function
    public void addTodo() {
        ToDo todo = inputToDo();
        toDoList.add(todo);
        dataHandling.inputDataToFile(toDoList);
        System.out.println("saved!!!");
        System.out.println();
    }

    // Delete TODO function
    public boolean deleteTodo(int index){
        if(index > toDoList.size()) return  false;
        index = index -1;
        toDoList.remove(index);
        dataHandling.inputDataToFile(toDoList);
        return  true;
    }

    // Finish TODO function
    public boolean finishToDo(int index){
        if(index > toDoList.size()) return  false;
        index = index -1;
        StringBuilder s = new StringBuilder();
        s.append(toDoList.get(index).getTitle()).append("(Done)");
        ToDo toDo = new ToDo(s.toString(), toDoList.get(index).getUntil());
        toDoList.set(index, toDo);
        dataHandling.inputDataToFile(toDoList);
        return  true;
    }

    // Edit TODO function
    public boolean editToDo(int index) {
        if (index > toDoList.size()) return false;
        index = index -1;
        ToDo toDo = inputToDo();
        toDoList.set(index, toDo);
        dataHandling.inputDataToFile(toDoList);
        System.out.println("edit suc!!!!!!");
        return true;
    }


    //
    public ToDo inputToDo() {
        String title;
        String date;
        while (true) {
            try {
                System.out.print("Title : ");
                title = scanner.nextLine();
                System.out.print("Until(yyyy-mm-dd) : ");
                date = scanner.nextLine();
                ToDo toDo = new ToDo(title, LocalDate.parse(date));
                return toDo;
            } catch (DateTimeException dateTimeException) {
                System.out.println("날짜를 잘 못입력했습니다");
            }
        }
    }

    public void showListToDo(){
        if(toDoList.isEmpty()){
            System.out.println("할 일 없습니다");
            return;
        }
        int todoCount = 0;
        Collections.sort(toDoList);
        for (int i = 0; i < toDoList.size() ; i++) {
            if(!toDoList.get(i).getTitle().contains("(Done)")) {
                todoCount++;
            }
        }
        System.out.println(String.format("You have %d TODOs left.",todoCount));
        for (int i = 0; i < toDoList.size() ; i++) {
            System.out.println(String.format("%d. %s",(i+1),toDoList.get(i).getTitle()));
        }
    }


    public ToDoHandling() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        scanner = new Scanner(System.in);
        dataHandling = new DataHandling();
        toDoList = dataHandling.getDataFromFile();
    }

    public List<ToDo> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<ToDo> toDoList) {
        this.toDoList = toDoList;
    }


}
