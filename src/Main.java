import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoHandling toDoHandling = new ToDoHandling();
        int option;
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
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    toDoHandling.addTodo();
                    break;
                case 2:
                    System.out.print("edit TODO number: ");
                    index = Integer.parseInt(scanner.nextLine());
                    if (!toDoHandling.editToDo(index)) {
                        System.out.println("해당 번호 입력하세요");
                    } else {
                        System.out.println("edit suc!!!!");
                    }
                    break;
                case 3:
                    System.out.print("finish TODO number: ");
                    index = Integer.parseInt(scanner.nextLine());
                    if(!toDoHandling.finishToDo(index)) System.out.println("해당 번호 입력하세요");
                    else  System.out.println("finish suc!!!!");
                    break;
                case 4:
                    System.out.print("delete TODO number: ");
                    index = Integer.parseInt(scanner.nextLine());
                    if(!toDoHandling.deleteTodo(index)) System.out.println("해당 번호 입력하세요");
                    else  System.out.println("deleted suc!!!!");
                    break;
                case 5:
                    return;

            }
        }
    }
}
