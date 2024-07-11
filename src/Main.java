import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ToDoHandling toDoHandling = new ToDoHandling();
        int option= 0;

        int index;
        while (true) {
            System.out.println("Welcome!");
            toDoHandling.showListToDo();
            System.out.println("\n");
            System.out.println("1. Create TODO");
            System.out.println("2. Edit TODO");
            System.out.println("3. Finish TODO");
            System.out.println("4. Delete TODO");
            System.out.println("5. Exit");
            System.out.println("\n");
            System.out.print("Input: ");
            option = inputNumber();
            switch (option) {
                case 1:
                    toDoHandling.addTodo();
                    break;
                case 2:
                    System.out.print("edit TODO number: ");
                    index = inputNumber();
                    if (!toDoHandling.editToDo(index)) {
                        System.out.println(String.format("1-> %d까지 입력하세요",toDoHandling.getToDoList().size()+1));
                    } else {
                        System.out.println("edit suc!!!!");
                    }
                    break;
                case 3:
                    System.out.print("finish TODO number: ");
                    index = inputNumber();
                    if(!toDoHandling.finishToDo(index))  System.out.println(String.format("1-> %d까지 입력하세요",toDoHandling.getToDoList().size()));
                    else  System.out.println("finish suc!!!!");
                    break;
                case 4:
                    System.out.print("delete TODO number: ");
                    index = inputNumber();
                    if(!toDoHandling.deleteTodo(index))  System.out.println(String.format("1-> %d까지 입력하세요",toDoHandling.getToDoList().size()));
                    else  System.out.println("deleted suc!!!!");
                    break;
                case 5:
                    return;
            }
        }
    }

    public static  int inputNumber(){
        int number ;
        while (true){
            try{
                number = Integer.parseInt(new Scanner(System.in).nextLine());
                return  number;
            }catch (NumberFormatException numberFormatException){
                System.out.println("정수를 입력하세요!!!");
                System.out.print("Input: ");
            }
        }
    }
}
