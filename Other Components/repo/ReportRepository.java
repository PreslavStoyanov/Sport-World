package web_project.repositories;

import web_project.repositories.models.ReportDAO;

import java.util.List;

public interface ReportRepository {
    ReportDAO createReport(String content, int user_id, int post_id, int comment_id);

    ReportDAO getReport(int id);

    List<ReportDAO> listReports(int page, int pageSize);

    void deleteReport(int id);
}
