package main;

import java.security.Timestamp;

public class User {
	private int userId;
    private String name;
    private Timestamp createdAt;
    private String status;
    private String userType;

    public User(int userId, String name, Timestamp createdAt, String status, String userType) {
        this.userId = userId;
        this.name = name;
        this.createdAt = createdAt;
        this.status = status;
        this.userType = userType;
    }

    public User(int int1, String string, java.sql.Timestamp timestamp, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
