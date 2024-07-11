
public class ToDo implements Comparable<ToDo> {
    private  String title;
    private String until;

    public ToDo(String title, String until) {
        this.title = title;
        this.until = until;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    @Override
    public String toString() {
        return  String.format("title = %s , util = %s",title,until);
    }

    @Override
    public int compareTo(ToDo toDo) {
       return this.until.compareTo(toDo.getUntil());
    }
}
