import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataBaseTasks {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private  static  PreparedStatement preparedStatement;
    private static List<Todo> todos;
    final String url = "jdbc:sqlite:C:\\KTCBackEnd\\ktc-database\\db.sqlite";


    DataBaseTasks() {
        todos = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Todo> getData() throws SQLException {
//        Statement: Được sử dụng cho các câu lệnh SQL đơn giản mà không có tham số.
        statement = connection.createStatement();
        // Thực hiện truy vấn SQL
        resultSet = statement.executeQuery("SELECT * FROM todo;");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String content = resultSet.getString("content");
            String due_date = resultSet.getString("due_date");
            int completed = resultSet.getInt("completed");
            todos.add(new Todo(content, LocalDate.parse(due_date), Boolean.parseBoolean(String.valueOf(completed)), id));
        }
        statement.close();
        resultSet.close();
        return todos;
    }

    public  void  deleteData(int id)throws  SQLException{
        String query = "UPDATE  todo SET completed = 'true' WHERE id = ?;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }


    public void insertDb(Todo todo) throws SQLException {
        String query ="INSERT INTO todo (content, due_date, completed) VALUES(?,?,?);";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,todo.getTitle());
        preparedStatement.setString(2,todo.getDueDate().toString());
        preparedStatement.setString(3,String.valueOf(todo.isCompleted()));
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }




}
