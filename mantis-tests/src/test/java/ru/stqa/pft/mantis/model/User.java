package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class User {
    @Id
    @Column(name = "id")
    private  int  id  = Integer.MAX_VALUE;
    @Column(name = "password")
    public String password;
    @Column(name = "username")
    public String username;
    @Column(name = "email")
    private String email;




    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    public User withPassword (String password){
        this.password = password;
        return this;
    }



    public User withId(int id) {
        this.id = id;
        return this;
    }


    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }
    public  int getId() {
        return id;
    }
    public String getUsername() { return username;}
    public String getEmail() { return email; }





    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Username='" + username + '\'' +
                '}';
    }


}
