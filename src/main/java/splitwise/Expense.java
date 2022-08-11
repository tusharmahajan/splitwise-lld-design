package splitwise;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Expense {

    private final String expenseID;
    private final ExpenseMeta expenseMeta;
    private final User paidBy;
    private final List<Split> splits;
    private final Double amount;

    public Expense(ExpenseMeta expenseMeta, User paidBy, List<Split> splits, Double amount) {
        this.amount = amount;
        this.expenseID = UUID.randomUUID().toString();
        this.expenseMeta = expenseMeta;
        this.paidBy = paidBy;
        this.splits = splits;
    }
}
