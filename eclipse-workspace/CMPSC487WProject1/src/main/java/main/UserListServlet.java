package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userList")
public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        StringBuilder jsonResponse = new StringBuilder();
        Connection connection = null;

        String userId = request.getParameter("userId");
        String date = request.getParameter("date");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/SUNLabDB", "root", "");

            StringBuilder query = new StringBuilder("SELECT u.userId, u.name, u.userType, u.createdAt, u.status, "
                    + "(SELECT al.accessTime FROM AccessRecords al WHERE al.userId = u.userId ORDER BY al.accessTime DESC LIMIT 1) AS lastAccess, "
                    + "(SELECT al.action FROM AccessRecords al WHERE al.userId = u.userId ORDER BY al.accessTime DESC LIMIT 1) AS lastAction "
                    + "FROM Users u "
                    + "LEFT JOIN AccessRecords al ON u.userId = al.userId "
                    + "WHERE 1=1");

            if (userId != null && !userId.isEmpty()) {
                query.append(" AND u.userId = ?");
            }
            if (date != null && !date.isEmpty()) {
                query.append(" AND DATE(al.accessTime) = ?");
            }
            if (startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty()) {
                query.append(" AND TIME(al.accessTime) BETWEEN ? AND ?");
            }

            PreparedStatement pstmt = connection.prepareStatement(query.toString());

            int index = 1;
            if (userId != null && !userId.isEmpty()) {
                pstmt.setInt(index++, Integer.parseInt(userId));
            }
            if (date != null && !date.isEmpty()) {
                pstmt.setString(index++, date);
            }
            if (startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty()) {
                pstmt.setString(index++, startTime);
                pstmt.setString(index++, endTime);
            }

            ResultSet rs = pstmt.executeQuery();
            jsonResponse.append("[");
            while (rs.next()) {
                jsonResponse.append("{")
                        .append("\"userId\":").append(rs.getInt("userId")).append(",")
                        .append("\"name\":\"").append(rs.getString("name")).append("\",")
                        .append("\"userType\":\"").append(rs.getString("userType")).append("\",")
                        .append("\"createdAt\":\"").append(rs.getTimestamp("createdAt")).append("\",")
                        .append("\"lastAccess\":\"").append(rs.getTimestamp("lastAccess")).append("\",")
                        .append("\"entryExit\":\"").append(rs.getString("lastAction")).append("\",")
                        .append("\"status\":\"").append(rs.getString("status")).append("\"")
                        .append("},");
            }
            if (jsonResponse.length() > 1) {
                jsonResponse.setLength(jsonResponse.length() - 1);
            }
            jsonResponse.append("]");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        out.print(jsonResponse.toString());
        out.flush();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String action = request.getParameter("action");

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/SUNLabDB", "root", "");

            String updateQuery = "UPDATE Users SET status = ? WHERE userId = ?";
            PreparedStatement pstmt = connection.prepareStatement(updateQuery);

            if ("activate".equals(action)) {
                pstmt.setString(1, "active");
            } else if ("deactivate".equals(action)) {
                pstmt.setString(1, "suspended");
            }
            pstmt.setInt(2, Integer.parseInt(userId));

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Status updated successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Failed to update status");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}