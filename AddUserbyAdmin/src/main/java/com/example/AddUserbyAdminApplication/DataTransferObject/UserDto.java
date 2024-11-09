package com.example.AddUserbyAdminApplication.DataTransferObject;

public class UserDto {
    private String username;
    private String password;
    private String fullname;

    public UserDto(String username, String password, String fullname) {
        super();
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;}

    public String getPassword() {
        return password;}

    public String getFullname() {
        return fullname;}

    @Override
    public String toString() {
        return "UserDto [username=" + username +
                ", password=" + password +
                ", fullname=" + fullname + "]";
    }
}