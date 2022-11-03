package utility;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Getter @Setter
public class User {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String confirmation;

    public User(String firstName, String middleName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmation = password;
    }

    public static User createUserFrom(String url) {
        try {
            FileReader fileReader = new FileReader("src/test/java/utility/" + url);
            JsonReader reader = new JsonReader(fileReader);
            return new Gson().fromJson(reader, User.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
