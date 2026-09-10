# Meal Plan Tracker

A Java desktop application that helps students manage a meal plan balance throughout the semester. Create a plan, view a weekly and daily spending allowance, and deduct breakfast, lunch, or dinner purchases using preset costs.

Built with **Java and Swing**, this project demonstrates object-oriented programming, event-driven interfaces, input validation, and basic budgeting calculations.

> **Status:** Work in progress. Creating a plan, calculating spending allowances, and logging preset meal payments are implemented. Saving plans, editing account details, and submitting custom payments are not yet available.

## Features

- **Custom meal plans:** Enter a plan name, starting balance, total weeks, current week, and preset meal costs.
- **Budget overview:** View the remaining balance and calculated weekly and daily spending allowances.
- **Quick payment logging:** Deduct a preset breakfast, lunch, or dinner cost with one click.
- **Updated calculations:** Recalculate spending allowances after each logged meal.
- **Basic input checks:** Display warnings for empty fields and nonnumeric entries.

## Getting Started

### Requirements

- **JDK 21 or newer**, with `java` and `javac` available on your PATH.
- A desktop environment capable of displaying Java Swing windows.
- Git to clone the repository, or download and extract it using GitHub's **Code → Download ZIP** option.

Java 21+ is required because the application uses `ArrayList.getFirst()`. No external libraries, database, Maven, or Gradle setup is required.

### Run from the command line

Clone the repository and enter its directory:

```bash
git clone https://github.com/hildebrandchase/MealPlanTracker.git
cd MealPlanTracker
```

Compile the source files and launch the application:

```bash
javac -d out src/Main.java src/MealPlan.java src/PaymentOption.java
java -cp out Main
```

### Run in IntelliJ IDEA

1. Open the project folder in IntelliJ IDEA.
2. Set the **Project SDK** to JDK 21 or newer.
3. Ensure `src` is marked as a **Sources Root**.
4. Open `src/Main.java` and run its `main` method.

## Using the App

1. Select **Create New Plan** from the main menu.
2. Enter the plan name, balance, total weeks of school, current week, and breakfast, lunch, and dinner costs.
3. Select **Continue** to view the balance and spending allowances.
4. Select **Log Payment**, then choose **Log Breakfast**, **Log Lunch**, or **Log Dinner**.
5. The selected cost is deducted, and the app returns to the updated plan overview.

Use whole numbers for both week fields and all three preset meal costs. The balance can include decimal places. Enter monetary values without a dollar sign or commas, and keep the current week below the total number of weeks.

**Plans are held only in memory.** Closing the application loses the plan, and returning to the main menu provides no way to reopen it.

## Budget Calculations

The application uses these formulas:

```text
Remaining weeks = Total weeks − Current week
Weekly allowance = Remaining balance ÷ Remaining weeks
Daily allowance = Weekly allowance ÷ 7
Updated balance = Previous balance − Logged meal cost
```

For example, a **$1,400** balance with **16 total weeks** and a **current week of 2** produces an allowance of **$100 per week**, or approximately **$14.29 per day**.

The calculation uses the entered week values directly. It does not automatically advance the current week or account for school breaks.

## Project Structure

| File | Purpose |
| --- | --- |
| `src/Main.java` | Application entry point, Swing screens, input checks, and button actions. |
| `src/MealPlan.java` | Meal plan data, balance updates, and spending calculations. |
| `src/PaymentOption.java` | Empty placeholder class for future payment options. |
| `src/META-INF/MANIFEST.MF` | Manifest identifying `Main` as the application entry point. |
| `MealPlanTracker.iml` | IntelliJ IDEA module configuration. |

## Current Limitations

- **Saved Plan**, **Edit Payment Options**, and **Edit Account Info** are placeholder buttons with no implemented action.
- The **Custom Payment** field is visible but has no submission behavior.
- Payments update the balance without retaining transaction history.
- Input validation is incomplete: decimal values in integer-only fields can cause parsing errors, and invalid ranges are not rejected.
- A current week equal to the total weeks causes division by zero; a larger value produces a negative allowance.
- Payments can reduce the balance below zero.

## Possible Improvements

- Save and reload meal plans between sessions.
- Implement account editing and configurable payment options.
- Support custom payments and decimal preset meal costs.
- Add transaction history and payment correction or undo.
- Strengthen validation for week ranges, costs, and balances.
- Use calendar dates to update the remaining time automatically.

## Author

**Chase Hildebrand** — [GitHub](https://github.com/hildebrandchase)
