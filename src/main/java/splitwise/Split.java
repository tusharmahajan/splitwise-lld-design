package splitwise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Split {

    Double amount;
    private User user;

    public Split(User user) {
        this.user = user;
    }
}
