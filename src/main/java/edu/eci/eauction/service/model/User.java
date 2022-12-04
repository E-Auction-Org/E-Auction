package edu.eci.eauction.service.model;

import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Unique
    @NonNull
    private String userName;
    @NonNull
    private String password;

    public User(String id, String userName, @NonNull String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return id.equals(user1.id) && userName.equals(user1.userName) && password.equals(user1.password);
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
