package web_project.core.models;

public record Report(String content, User user, Post post, Comment comment) {

    @Override
    public String toString() {
        return "Report{" +
                "content='" + content + '\'' +
                ", user=" + user +
                ", post=" + post +
                ", comment=" + comment +
                '}';
    }
}
