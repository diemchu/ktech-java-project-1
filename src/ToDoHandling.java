import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ToDoHandling {
    private List<ToDo> toDoList;
    private BufferedReader bufferedReader;
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
        System.out.println();
        return true;
    }


    //
    public ToDo inputToDo() {
        String title;
        String date;
        while (true) {
            try {
                LocalDate localDateNow = LocalDate.now();
                StringBuilder stringBuilder = new StringBuilder();
                System.out.print("Title : ");
                stringBuilder.append(bufferedReader.readLine());
                title = stringBuilder.toString();
                stringBuilder.setLength(0);
                System.out.print("Until(yyyy-mm-dd) : ");
                stringBuilder.append(bufferedReader.readLine());
                date = stringBuilder.toString();

                // 날짜를 입력하지 않는 경우
                if(date.equals("")){
                    ToDo toDo = new ToDo(title, "");
                    return  toDo;
                }


                // 현재 날짜보다 비교한다
                // 작으면 다시 입력시킨다
                LocalDate localDate = LocalDate.parse(date);
                if(localDate.compareTo(localDateNow) < 0){
                    System.out.println("현재 날짜보다 같거나 커야 합니다!!");
                }else {
                    ToDo toDo = new ToDo(title,localDate.toString());
                    return toDo;
                }
            } catch (DateTimeException | IOException dateTimeException) {
                System.out.println("날짜를 잘 못입력했습니다");
            }
        }
    }


    // print totoList function
    public void showListToDo(){
        if(toDoList.isEmpty()){
            System.out.println("You have no more TODOs left!!!");
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
        dataHandling = new DataHandling();
        toDoList = dataHandling.getDataFromFile();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public List<ToDo> getToDoList() {
        return toDoList;
    }

}
