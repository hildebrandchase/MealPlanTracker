import java.util.ArrayList;

public class MealPlan {
    private String name;
    private double balance;
    private int weeks;
    private int currentWeek;
    private ArrayList<Integer> costs;

    // Constructor
    public MealPlan(String name, double balance, int weeks, int currentWeek, ArrayList<Integer> costs) {
        this.name = name;
        this.balance = balance;
        this.weeks = weeks;
        this.currentWeek = currentWeek;
        this.costs = costs;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public int getWeeks() {
        return weeks;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public ArrayList<Integer> getCosts() {
        return costs;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    public void setCosts(ArrayList<Integer> costs) {
        this.costs = costs;
    }

    // Custom Methods
    public double calculateWeeklySpend() {
        return balance / (weeks-currentWeek);
    }

    public void logPayment(double amount) {
        balance -= amount;
    }

    public double calculateDailySpend() {
        return calculateWeeklySpend() / 7;
    }
}
