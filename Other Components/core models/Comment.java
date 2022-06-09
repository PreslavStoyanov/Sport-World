package web_project.core.models;

import java.sql.Timestamp;

public record Comment(int id, String content, int votes, int views, Timestamp creationDate, User user, Post post) {

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", votes=" + votes +
                ", views=" + views +
                ", creationDate=" + creationDate +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
