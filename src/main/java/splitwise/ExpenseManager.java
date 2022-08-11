package splitwise;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ExpenseManager {
    private List<Expense> expenseList;
    private Map<String, User> users;
    private Map<String, Map<String, Double>> userBalanceMapping;

    public ExpenseManager() {
        this.expenseList = new ArrayList<>();
        this.users = new HashMap<>();
        this.userBalanceMapping = new HashMap<>();
    }

    public void addUser(User user){
        this.users.put(user.getUserID(), user);
        this.userBalanceMapping.put(user.getUserID(), new HashMap<>());
    }

    public void addExpense(ExpenseType expenseType, Double shareAmount, String paidBy, List<Split> splits){
        Expense expense = ExpenseService.calculateExpenses(expenseType, this.users.get(paidBy), splits, shareAmount);
        expenseList.add(expense);

        assert expense != null;
        for (Split split: expense.getSplits()){
            Map<String, Double> owedUser = userBalanceMapping.get(paidBy);
            String paidTo = split.getUser().getUserID();
            if(!owedUser.containsKey(paidTo)){
                owedUser.put(paidTo, 0.0);
            }
            owedUser.put(paidTo, owedUser.get(paidTo) + split.getAmount());

            owedUser = userBalanceMapping.get(paidTo);
            if(!owedUser.containsKey(paidBy)){
                owedUser.put(paidBy, 0.0);
            }
            owedUser.put(paidBy, owedUser.get(paidBy) - split.getAmount());

        }

    }

    public void showBalance(String user){

        for(Map.Entry<String, Double> userBalance : userBalanceMapping.get(user).entrySet()){

            if(userBalance.getValue() > 0){
                System.out.println(userBalance.getKey() + " owes " + Math.abs(userBalance.getValue()) +  " to " + user);
            }
            else if(userBalance.getValue() < 0){
                System.out.println(user + " owes " + Math.abs(userBalance.getValue()) +  " to " + userBalance.getKey());
            }
        }
    }

    public void showAllBalances(){

        for(Map.Entry<String, Map<String, Double>> user : userBalanceMapping.entrySet()){
            for (Map.Entry<String, Double> userBalance : user.getValue().entrySet()){
                if(userBalance.getValue() > 0){
                    if(userBalance.getValue() > 0){
                        System.out.println(userBalance.getKey() + " owes " + Math.abs(userBalance.getValue()) +  " to " + user.getKey());
                    }
                    else if(userBalance.getValue() < 0){
                        System.out.println(user.getKey() + " owes " + Math.abs(userBalance.getValue()) +  " to " + userBalance.getKey());
                    }
                }
            }

        }
    }
}
