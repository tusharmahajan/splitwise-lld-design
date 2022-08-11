package splitwise;

import lombok.Getter;

@Getter
public class User {
    private final String userID;
    private final String name;
    private final String email;
    private final String contactNumber;

    public User(String userID, String name, String email, String contactNumber) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }
}
