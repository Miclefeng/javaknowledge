package com.baseknowledge.annotation;

/**
 * @author miclefengzss
 */
@Table("users")
public class Filter {

    @Column("id")
    private int id;

    @Column("user_name")
    private String userName;

    @Column("email")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
