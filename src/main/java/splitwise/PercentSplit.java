package splitwise;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PercentSplit extends Split{

    private Double percent;

    public PercentSplit(User user, Double percent) {
        super(user);
        this.percent = percent;
    }
}
