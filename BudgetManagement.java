import java.util.*;

class BudgetManagement{
    private Stack<String> expenseStack;
    private Stack<Double> amountStack;
    
    public BudgetManagement() {
        expenseStack = new Stack<>();
        amountStack = new Stack<>();
    }
    
    public void addExpense(String category, double amount) {
        String entry = category + ": $" + amount;
        expenseStack.push(entry);
        amountStack.push(amount);
        System.out.println("Added expense: " + entry);
    }
    
    public void undoLastExpense() {
        if (!expenseStack.isEmpty()) {
            System.out.println("Undo successful: " + expenseStack.pop());
            amountStack.pop();
        } else {
            System.out.println("No expenses to undo.");
        }
    }
    
    public void viewLastExpense() {
        if (!expenseStack.isEmpty()) {
            System.out.println("Last Expense: " + expenseStack.peek());
        } else {
            System.out.println("No expenses recorded.");
        }
    }
    
    public void displayAllExpenses() {
        if (expenseStack.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("All Expenses:");
        double total = 0;
        for (int i = 0; i < expenseStack.size(); i++) {
            System.out.println(expenseStack.get(i));
            total += amountStack.get(i);
        }
        System.out.println("Total Expenses: $" + total);
    }
    
    public void clearAllExpenses() {
        expenseStack.clear();
        amountStack.clear();
        System.out.println("All expenses have been cleared.");
    }
    
    public int countExpenses() {
        return expenseStack.size();
    }
    
    public void findExpense(String category) {
        boolean found = false;
        for (String expense : expenseStack) {
            if (expense.startsWith(category)) {
                System.out.println(expense);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found under category: " + category);
        }
    }
    
}
