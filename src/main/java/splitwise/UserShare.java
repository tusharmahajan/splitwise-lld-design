package splitwise;


public class UserShare {
    private User user;
    private double amount;

    public UserShare(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public UserShare updateUserDetails(double shareAmount) {
        this.amount+=shareAmount;
        return new UserShare(user, amount+shareAmount);
    }
}
