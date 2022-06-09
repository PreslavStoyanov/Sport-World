package web_project.core.models;

import java.sql.Timestamp;
import java.util.List;

public record Post(int id, String title, String content, int votes, int views, Timestamp creationDate, String category,
                   List<String> tags, User user) {

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", votes=" + votes +
                ", views=" + views +
                ", creationDate=" + creationDate +
                ", category='" + category + '\'' +
                ", tags=" + tags +
                ", user=" + user +
                '}';
    }
}
