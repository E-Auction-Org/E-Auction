package edu.eci.eauction.service.model;

import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private String id;
    @Unique
    @NonNull
    private String userName;
    @NonNull
    private String password;
    private ArrayList<Float> rating = new ArrayList<>();
    @NonNull
    private String mail;

    public User(String id, String userName, @NonNull String password, String mail) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return id.equals(user1.id) && userName.equals(user1.userName) && password.equals(user1.password);
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public void setMail(@NonNull String mail) {
        this.mail = mail;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
