package splitwise;

import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    public static Expense calculateExpenses(ExpenseType type, User paidBy, List<Split> splits, Double amount){

        switch (type){
            case EQUAL:
                Double shareAmount =  Math.round(amount*100/splits.size()) /100.0;
                for(Split split: splits){
                    split.setAmount(shareAmount);
                }
                splits.get(0).setAmount(shareAmount + ( amount- (shareAmount* splits.size()) ));
                return new Expense(null, paidBy, splits, amount);
            case EXACT:
                return new Expense(null, paidBy, splits, amount);
            case PERCENT:
                for(Split split: splits){
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount*percentSplit.getPercent())/100.0);
                }
                return new Expense(null, paidBy, splits, amount);
        }
        return null;
    }
}
