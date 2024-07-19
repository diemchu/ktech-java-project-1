import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainWithDb

{
    public  static List<Todo> todos;
    public static void main(String[] args) throws IOException, SQLException {

        todos= new ArrayList<>();
        // 사용자 입력 받기용
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        DataBaseService dbService = new DataBaseService(reader);

        todos = dbService.getTodos();
        while (true) {
            // 첫화면 출력
            printFirstScreen();
            // 입력 안내
            System.out.print("Input: ");
            int selection;
            try {
                // 선택을 입력하다
                selection = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                // 숫자가 아니면 `continue`로 다시 반복의 처음으로
                System.out.println("Input must be an integer (1 ~ 5)");
                continue;
            }
            // `5`의 경우 `break` (종료)
            if (selection == 5) break;
//             그 외는 `switch - case - default`
            switch (selection) {
                case 1 -> dbService.createTodo();
//                case 2 -> dbService.editTodo();
                case 3 -> dbService.finishTodo();
//                case 4 -> service.deleteTodo();
                default -> System.out.println("Invalid selection, select from 1 to 5");
            }
        }


    }

    private static void printFirstScreen() {
        System.out.println("Welcome!!!\n");
        int  todosLeft = todos.stream()
                .filter(todo -> !todo.isCompleted())
                .mapToInt(todo -> 1)
                .sum();



        // 갯수에 따라 다른 출력 만들기
        switch (todosLeft) {
            case 0 -> System.out.println("You have no more TODOs left!!!");
            case 1 -> System.out.println("You have 1 TODO left.\n");
            default -> System.out.println("You have " + todosLeft + " TODOs left.\n");
        }

        // 각 TODO를 한번에 출력하기 위한 StringBuilder
        StringBuilder todoBuilder = new StringBuilder();
        for (int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            if (todo.isCompleted() && todo.getDueDate().isBefore(LocalDate.now())) continue;
            todoBuilder.append(i + 1).append(". ");
            todoBuilder.append(todo.getTitle());
            if (todo.isCompleted()) todoBuilder.append(" (Done)");
            todoBuilder.append('\n');
        }
        // 선택지 출력
        System.out.println(todoBuilder);
        System.out.println("1. Create TODO");
        System.out.println("2. Edit TODO");
        System.out.println("3. Finish TODO");
        System.out.println("4. Delete TODO");
        System.out.println("5. Exit");
        System.out.println();
    }
}
