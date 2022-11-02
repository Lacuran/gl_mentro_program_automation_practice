package utility;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class User {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String confirmation;

    public User(String firstName, String middlename, String lastName, String email, String password) {
        this.firstName = firstName;
        this.middleName = middlename;
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

    public String getName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }


}
