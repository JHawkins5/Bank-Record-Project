package CW2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer implements CheckerPrinter {
    private String customerID;
    private double income;

    // There is no getter or setter for eligible, but instead a method checkEligibilty, which updates eligible depending on the results.

    private boolean eligible;

    // There is no getter or setter for creditRecords, but instead methods to create, update, and delete records.

    private List<Loan> creditRecords = new ArrayList<Loan>();

    public Customer() {
        // Default constructor containing no arguments, sets default values.

        customerID = "AAAXXX";
        income = 0;
    }

    public Customer(String customerID, double income) {
        // This constructor takes inputs for both variables and sets the attributes appropriately.
        // The letters in the customerID are converted to upper case using substrings.

        this.customerID = customerID.substring(0, 3).toUpperCase() + customerID.substring(3);
        this.income = income;
    }

    // Getters and setters for customerID and income.

    public String getCustomerID() {
        return customerID;
    }
    
    public void setCustomerID(String customerID) {
        this.customerID = customerID.substring(0, 3).toUpperCase() + customerID.substring(3);
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public boolean checkEligibility() {
        // This method checks if the customer is eligible to create a new record.
        // A customer is eligible if they don't currently have any records, or if they do, if the amount they owe is not greater than four times their income.
        // The method updates eligible to true/false appropriately, and then returns eligible.

        if (creditRecords.size() == 0) {
            // If creditRecords is empty, the customer is eligible to open a new record.

            eligible = true;
        }
        else {
            // If creditRecords is not empty, add up the amount they owe for each existing record.

            double dueSum = 0;

            for (Loan record : creditRecords) {
                dueSum += record.amountDue;
            }

            if (dueSum <= income * 4) {
                // If the total amount the customer owes is less than or equal to four times their income, they are eligible to open a new record.

                eligible = true;
            }
            else {
                // Else, the customer is ineligible.

                eligible = false;
            }
        }

        return eligible;
    }

    public void printCustomerDetails() {
        // This method is used to display the customer's credit records and their eligibility to register a new record.

        System.out.println("CustomerID " + customerID);
        System.out.println("\nEligible to register a new record: ");
        
        if (checkEligibility()) {
            // If the customer is eligible, print yes.

            System.out.print("YES\n");
        }
        else {
            // Else, print no.
            System.out.print("NO\n");
        }

        if (creditRecords.size() == 0) {
            // If the customer has no records, display a message.

            System.out.println("\nThis customer has no credit records.");
        }
        else {
            // Else, print each record.
            // Formatting for the table

            System.out.format("%-15s%-15s%-15s%-15s%-15s\n", "RecordID", "Loan Type", "Interest Rate", "Amount Due", "Time Left");

            for (Loan loan : creditRecords) {
                System.out.format("%-15s%-15s%-15s%-15s%-15s\n", loan.getRecordID(), loan.getLoanType(), loan.getInterestRate() + "%", "£" + XYZBank.roundTwoDecimalPlaces(loan.getAmountDue() * 1000), loan.getLoanTermLeft() + " years");
            }
        }
    }

    public void createRecord(List<Loan> records) {
        // This method is used to create a new record for a customer.
        // It uses do while loops to allow the user to try again if they enter an invalid value, without having to start over again.

        Scanner input = new Scanner(System.in);
        boolean recordAdded = false;

        do {
            // While the user has not added a record, take values for the required variables for a record.

            boolean recordIDAdded = false;
            String recordID;

            do {
                // While the user has not entered a valid recordID, ask for a recordID.

                System.out.println("Please enter a recordID. This must be six numerical digits and must be unique.");
                recordID = input.nextLine();

                if (recordID.length() == 6) {
                    // If the input recordID is six characters long, check if they are all numbers.

                    if (XYZBank.isInt(recordID)) {
                        // If the input recordID consists only of numbers, break the loop.

                        recordIDAdded = true;
                    }
                }
                else {
                    // Else, display an error message.

                    System.out.println("RecordID must be six numerical digits.");
                }
            } while (!recordIDAdded);

            boolean interestAdded = false;
            double interestRate = 0;

            do {
                // While the user has not entered a valid interest rate, ask for an interest rate.

                System.out.println("Please enter the interest rate. For example, for 5%, enter 5.");
                String interest = input.nextLine();

                if (XYZBank.isDouble(interest)) {
                    // If the input interest rate is a number, check if it is greater than zero.
                    
                    interestRate = Double.parseDouble(interest);

                    if (interestRate > 0) {
                        // If the input interest rate is greater than zero, break the loop.

                        interestAdded = true;
                    }
                    else {
                        // Else, display an error message.

                        System.out.println("Interest rate must be greater than zero.");
                    }
                }
            } while (!interestAdded);

            boolean amountDueAdded = false;
            double amountDue = 0;

            do {
                // While the user has not entered a valid amount due, ask for an amount due.

                System.out.println("Please enter the amount due in thousands of pounds. For example, for £1000, enter 1.");
                String due = input.nextLine();

                if (XYZBank.isDouble(due)) {
                    // If the input amount due is a number, check if it is greater than or equal to zero.
                    
                    amountDue = Double.parseDouble(due);

                    if (amountDue >= 0) {
                        // If the input amount due is greater than or equal to zero, break the loop.

                        amountDueAdded = true;    
                    }
                    else {
                        // Else, display an error message.

                        System.out.println("Amount due cannot be negative.");
                    }
                }
            } while (!amountDueAdded);

            boolean loanTermLeftAdded = false;
            double loanTermLeft = 0;

            do {
                // While the user has not entered a valid number of years, ask for the number of years left.

                System.out.println("Please enter the number of years left to pay back the loan.");
                String termLeft = input.nextLine();

                if (XYZBank.isDouble(termLeft)) {
                    // If the input years left is a number, check if it is greater than or equal to zero.

                    loanTermLeft = Double.parseDouble(termLeft);

                    if (loanTermLeft >= 0) {
                        // If the input number of years is greater than or equal to zero, break the loop.

                        loanTermLeftAdded = true;
                    }
                    else {
                        // Display an error message.

                        System.out.println("Number of years left cannot be negative.");
                    }
                }
            } while (!loanTermLeftAdded);

            boolean loanTypeAdded = false;

            do {
                // While the user has not entered a valid loan type, calculate the yearly payment amount and ask for a loan type.

                double paymentAmount = amountDue / loanTermLeft;
                System.out.println("Which kind of loan would you like to take out? Auto, builder, mortgage, personal, or other.");
                String choice = input.nextLine();

                if (choice.equalsIgnoreCase("auto")) {
                    // If the user enters auto, create an instance of Auto with the entered values.

                    Auto record = new Auto(recordID, interestRate, amountDue, loanTermLeft, paymentAmount);
                    creditRecords.add(record);
                    records.add(record);
                    loanTypeAdded = true;
                    recordAdded = true;
                }
                else if (choice.equalsIgnoreCase("builder")) {
                    // If the user enters builder, create an instance of Builder with the entered values.

                    Builder record = new Builder(recordID, interestRate, amountDue, loanTermLeft, paymentAmount);
                    creditRecords.add(record);
                    records.add(record);
                    loanTypeAdded = true;
                    recordAdded = true;
                }
                else if (choice.equalsIgnoreCase("mortgage")) {
                    // If the user enters mortgage, create an instance of Mortgage with the entered values.

                    Mortgage record = new Mortgage(recordID, interestRate, amountDue, loanTermLeft, paymentAmount);
                    creditRecords.add(record);
                    records.add(record);
                    loanTypeAdded = true;
                    recordAdded = true;
                }
                else if (choice.equalsIgnoreCase("personal")) {
                    // If the user enters personal, create an instance of Personal with the entered values.

                    Personal record = new Personal(recordID, interestRate, amountDue, loanTermLeft, paymentAmount);
                    creditRecords.add(record);
                    records.add(record);
                    loanTypeAdded = true;
                    recordAdded = true;
                }
                else if (choice.equalsIgnoreCase("other")) {
                    // If the user enters other, create an instance of Other with the entered values.

                    Other record = new Other(recordID, interestRate, amountDue, loanTermLeft, paymentAmount);
                    creditRecords.add(record);
                    records.add(record);
                    loanTypeAdded = true;
                    recordAdded = true;
                }
                else {
                    // Else, display an error message.

                    System.out.println("Invalid loan type.");
                }
            } while (!loanTypeAdded);
        } while (!recordAdded);
    }

    public void updateRecord(String recordID, List<Loan> records) {
        // This method is used to update customer records.
        // It asks the user whether they want to update each attribute and then calls the corresponding methods if they say yes.

        Scanner input = new Scanner(System.in);
        String choice;
        boolean validChoice = false;

        do {
            // While the user has not entered a valid choice, ask if they want to update the recordID.

            System.out.println("Would you like to update the record's ID? Yes or no.");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                // If the user enters yes, allow them to update the recordID.

                recordID = updateRecordID(recordID, records);
                validChoice = true;
            }
            else if (choice.equalsIgnoreCase("no")) {
                // If the user enters no, break the loop.

                validChoice = true;
            }
            else {
                // Else display an error message.

                System.out.println("Choice must be yes or no.");
            }
        } while (!validChoice);

        validChoice = false;

        do {
            // While the user has not entered a valid choice, ask if they want to update the interest rate.

            System.out.println("Would you like to update the interest rate? Yes or no.");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                // If the user enters yes, allow them to update the interest rate.

                updateInterestRate(recordID, records);
                validChoice = true;
            }
            else if (choice.equalsIgnoreCase("no")) {
                // If the user enters no, break the loop.

                validChoice = true;
            }
            else {
                // Else, display an error message.

                System.out.println("Choice must be yes or no.");
            }
        } while (!validChoice);

        validChoice = false;

        do {
            // While the user has not entered a valid choice, ask if they want to update the amount due.

            System.out.println("Would you like to update the amount due? Yes or no.");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                // If the user enters yes, ask if they want to add interest to the amount due.

                boolean newValidChoice = false;

                do {
                    // While the user has not entered a valid choice, ask if they want to add interest to the amount due.

                    System.out.println("Would you like to add interest to the amount due? Yes or no.");
                    choice = input.nextLine();

                    if (choice.equalsIgnoreCase("yes")) {
                        // If the user enters yes, add interest to the loan.

                        for (Loan loan : records) {
                            if (loan.getRecordID().equals(recordID)) {
                                loan.addInterest();
                            }
                        }

                        newValidChoice = true;
                    }
                    else if (choice.equalsIgnoreCase("no")) {
                        // If the user enters no, break the loop.

                        newValidChoice = true;
                    }
                    else {
                        // Else, display an error message.

                        System.out.println("Choice must be yes or no.");
                    }
                } while (!newValidChoice);

                newValidChoice = false;

                do {
                    // While the user has not entered a valid choice, ask if the customer wants to pay the next instalment.

                    System.out.println("Would the customer like to pay the next instalment of their loan? Yes or no.");
                    choice = input.nextLine();

                    if (choice.equalsIgnoreCase("yes")) {
                        // If the user enters yes, check if the loan is a Builder loan or a Mortgage.

                        for (Loan loan : records) {
                            if (loan.getRecordID().equals(recordID)) {
                                if (loan.getLoanType().equals("Builder") || loan.getLoanType().equals("Mortgage")) {
                                    // If the loan is a Builder loan or a Mortgage, ask if the customer wants to overpay on their next instalment.

                                    boolean paymentMade = false;

                                    do {
                                        // While the user has not entered a valid choice, ask if the customer wants to overpay on their next instalment.

                                        System.out.println("This record is a " + loan.getLoanType() + " loan. Would the customer like to overpay? Yes or no.");
                                        choice = input.nextLine();
        
                                        if (choice.equalsIgnoreCase("yes")) {
                                            // If the user enters yes, ask for the overpayment percentage.

                                            boolean overpaymentMade = false;
        
                                            do {
                                                // While the user has not entered a valid percentage, ask for the overpayment percentage.

                                                System.out.println("Please enter the percentage they would like to overpay by. This must be between 0 and 2.");
                                                String overpayment = input.nextLine();
        
                                                if (XYZBank.isDouble(overpayment)) {
                                                    // If the input overpayment is a number, check that it is between 0 and 2.

                                                    if (Double.parseDouble(overpayment) > 0 && Double.parseDouble(overpayment) <= 2) {
                                                        // If the input overpayment is between 0 and 2, make the overpayment.

                                                        if (loan.getLoanType().equals("Builder")) {
                                                            ((Builder) loan).overpay(Double.parseDouble(overpayment));
                                                        }
                                                        else {
                                                            ((Mortgage) loan).overpay(Double.parseDouble(overpayment));
                                                        }
        
                                                        overpaymentMade = true;
                                                        paymentMade = true;
                                                    }
                                                    else {
                                                        // Else, display an error message.

                                                        System.out.println("Overpayment amount must be between 0 and 2%.");
                                                    }
                                                }
                                            } while (!overpaymentMade);
                                        }
                                        else if (choice.equalsIgnoreCase("no")) {
                                            // If the user enters no, pay the next instalment.

                                            loan.pay();
                                            paymentMade = true;
                                        }
                                        else {
                                            // Else, display an error message.

                                            System.out.println("Choice must be yes or no.");
                                        }
                                    } while (!paymentMade);
                                }
                                else {
                                    // Else, pay the next instalment.

                                    loan.pay();
                                    newValidChoice = true;
                                }
                            }
                        }

                        if (checkEligibility()) {
                            System.out.println("Following the changes to the amount due, the customer is eligible to register a new credit record.");
                        }
                        else {
                            System.out.println("Following the changes to the amount due, the customer is not eligbile to register a new credit record.");
                        }
                    }
                    else if (choice.equalsIgnoreCase("no")) {
                        // If the user enters no, break the loop.

                        newValidChoice = true;
                    }
                    else {
                        // Else, display an error message.

                        System.out.println("Choice must be yes or no.");
                    }
                } while (!newValidChoice);

                validChoice = true;
            }
            else if (choice.equalsIgnoreCase("no")) {
                // If the user enters no, break the loop.

                validChoice = true;
            }
            else {
                // Else, display an error message.

                System.out.println("Choice must be yes or no.");
            }
        } while (!validChoice);

        validChoice = false;

        do {
            // While the user has not entered a valid choice, ask if they want to update the number of years left.

            System.out.println("Would you like to update the number of years left? Yes or no.");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                // If the user enters yes, allow them to update the number of years left.

                updateTermLeft(recordID, records);
                validChoice = true;
            }
            else if (choice.equalsIgnoreCase("no")) {
                // If the user enters no, break the loop.

                validChoice = true;
            }
            else {
                // Else, display an error message.
                System.out.println("Choice must be yes or no.");
            }
        } while (!validChoice);
    }

    public String updateRecordID(String recordID, List<Loan> records) {
        // This method allows the user to update the recordID.
        // This method is only called within the updateRecord method.
        // The new recordID is returned to allow the record to be found in the rest of the updateRecord method.

        Scanner input = new Scanner(System.in);
        boolean recordIDChanged = false;
        String newID;

        do {
            // While the user has not entered a new, valid recordID, ask for a valid recordID.

            System.out.println("Please enter the new recordID. This must be six numerical digits and must be unique.");
            newID = input.nextLine();

            if (newID.length() == 6) {
                // If the input recordID is six characters long, check if that ID is already taken.

                if (XYZBank.recordExists(newID)) {
                    // If that ID is already taken, display an error message.

                    System.out.println("There is already a record with that ID.");
                }
                else {
                    // Else, check if the input recordID is a number.

                    if (XYZBank.isInt(newID)) {
                        // If the input recordID is a number, update the recordID.

                        for (Loan loan : records) {
                            if (loan.getRecordID().equals(recordID)) {
                                loan.setRecordID(newID);
                            }
                        }

                        recordIDChanged = true;
                    }
                }
            }
            else {
                // Else, display an error message.

                System.out.println("RecordID must be six numerical digits.");
            }
        } while (!recordIDChanged);

        return newID;
    }

    public void updateInterestRate(String recordID, List<Loan> records) {
        // This method allows the user to update the interest rate.
        // This method is only called within the updateRecord method.

        Scanner input = new Scanner(System.in);
        boolean interestChanged = false;

        do {
            // While the user has not entered a valid interest rate, ask for a new interest rate.

            System.out.println("Please enter the new interest rate. For example, for 5%, enter 5.");
            String interest = input.nextLine();

            if (XYZBank.isDouble(interest)) {
                // If the input interest rate is a number, check it is greater than zero.

                if (Double.parseDouble(interest) > 0) {
                    // If the input interest rate is greater than zero, update the interest rate.

                    for (Loan loan : records) {
                        if (loan.getRecordID().equals(recordID)) {
                            loan.setInterestRate(Double.parseDouble(interest));
                        }
                    }
    
                    interestChanged = true;
                }
                else {
                    // Else, display an error message.

                    System.out.println("Interest rate must be greater than zero.");
                }
            }
        } while (!interestChanged);
    }

    public void updateTermLeft(String recordID, List<Loan> records) {
        // This method is used to update the number of years left.
        // This method is only called within the updateRecord method.

        Scanner input = new Scanner(System.in);
        boolean termLeftChanged = false;

        do {
            // While the user has not entered a valid number of years, ask for a number of years.

            System.out.println("Please enter the new loan term left in years.");
            String termLeft = input.nextLine();

            if (XYZBank.isDouble(termLeft)) {
                // If the input years left is a number, check if it is greater than or equal to zero.

                if (Double.parseDouble(termLeft) >= 0) {
                    // If the input years left is greater than or equal to zero, update the loan term left.

                    for (Loan loan : records) {
                        if (loan.getRecordID().equals(recordID)) {
                            loan.setLoanTermLeft(Double.parseDouble(termLeft));
                        }
                    }
    
                    termLeftChanged = true;    
                }
            }
        } while (!termLeftChanged);
    }

    public void deleteRecord(String recordID, List<Loan> records) {
        // This method is used to delete methods from a customer's credit records list.
        // It takes the recordID of the record to delete and iterates through the list to find the corresponding record.
        // That record is then removed from the list.

        for (Loan loan : creditRecords) {
            if (loan.getRecordID().equals(recordID)) {
                creditRecords.remove(loan); 
                records.remove(loan); 
                break;              
            }
        }
    }
}