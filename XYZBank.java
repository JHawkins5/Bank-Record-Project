package CW2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Instances of Scanner are not closed in this program (across this class and the Customer class) as closing them resulted in the program crashing due to multiple being open at the same time.

public class XYZBank {
    public static void main(String[] args) {
        menu();
    }

    public static List<Customer> customers = new ArrayList<Customer>();
    public static List<Loan> records = new ArrayList<Loan>();

    public static void menu() {
        // The menu method displays a list of options for the user and asks them to choose one.
        // Valid inputs: 1 - 5.
        // If an invalid input is entered a message is displayed and the menu is presented again.

        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the bank. Please choose an option by entering the corresponding number:");
        System.out.println("1 - Register a new customer.");
        System.out.println("2 - Update information about an existing customer.");
        System.out.println("3 - View information about an existing customer.");
        System.out.println("4 - View information about all existing customers");
        System.out.println("5 - Quit.");
        String choice = input.nextLine();

        if (isInt(choice)) {
            switch (choice) {
                case "1": // Register a new customer.
                    registerNewCustomer();
                    break;
                case "2": // Update information about an existing customer.
                    System.out.println("Please enter the customerID of the customer you would like to update.");
                    String customerID = input.nextLine();

                    if (validCustomerID(customerID)) {
                        // If the input customerID is a valid customerID, check if a customer exists with that ID.

                        customerID = customerID.substring(0, 3).toUpperCase() + customerID.substring(3);
                        
                        if (customerExists(customerID)) {
                            // If there is a customer with the input customerID, allow the user to update the customer's information.

                            updateCustomerInfo(customerID);
                        }
                        else {
                            // Else, display an error message.

                            System.out.println("There is no existing customer with the ID " + customerID + ".");
                        }
                    }

                    break;
                case "3": // View information about an existing customer.
                    System.out.println("Please enter the customerID of the customer you would like to view.");
                    customerID = input.nextLine();

                    if (validCustomerID(customerID)) {
                        // If the input customerID is a valid customerID, check if a customer exists with that ID.

                        customerID = customerID.substring(0, 3).toUpperCase() + customerID.substring(3);

                        if (customerExists(customerID)) {
                            // If there is a customer with the input customerID, allow the user to view the customer's information.

                            viewCustomerInfo(customerID);
                        }
                        else {
                            // Else, display an error message.

                            System.out.println("There is no existing customer with the ID " + customerID + ".");
                        }
                    }

                    break;
                case "4": // View information about all existing customers.
                    viewCustomerInfo();
                    break;
                case "5": // Halts the program.
                    input.close();
                    return;
                default: // If the user enters anything that isn't 1 - 5, display an error message.
                    System.out.println("Invalid choice.");
                    break;
            }
        }


        // Call the menu method again to allow the user to do something else.

        menu();
    }

    public static void registerNewCustomer() {
        // This method allows the user to register a new customer.
        // It asks the user to enter a valid, unique customerID, and a valid income.
        // Valid customerID: see validCustomerID method.
        // Valid income: any number, where the number represents the customer's income in thousands of pounds.

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a customerID. This must be three letters followed by three numerical digits, and must be unique.");
        String customerID = input.nextLine();

        if (customerExists(customerID)) {
            // If there is a customer with the input customerID, display an error message.

            System.out.println("This customerID is already taken.");
        }
        else {
            // Else, check whether the input customerID is a valid customerID.

            if (validCustomerID(customerID)) {
                // If the input customerID is valid, ask the user to enter the customer's income.

                System.out.println("Please enter the customer's annual income in thousands of pounds. For example, for £1000, enter 1.");
                String income = input.nextLine();

                if (isDouble(income)) {
                    // If the input income is a number, create an instance of Customer with the customerID and income entered by the user, and add the customer to the list customers.

                    customers.add(new Customer(customerID, Double.parseDouble(income)));
                }
            }
        }
    }

    public static void updateCustomerInfo(String customerID) {
        // This method allows the user to update an existing customer's information.
        // It does not check whether the customer exists as this check was already done in the menu method.
        // It asks the user whether they want to update the customerID, and if they do, they are allowed to do so within the same constraints as the above method.
        // It then asks the user whether they want to update the income, with the same constraints as the above method.
        // Finally, it asks if the user would like to create or delete a record for a customer.

        Scanner input = new Scanner(System.in);
        boolean validChoice = false;
        String choice;

        do {
            // While the user has not entered a valid choice, ask if they want to update the customer's ID.

            System.out.println("Would you like to update the customer's ID? Yes or no.");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                // If the user enters yes, allow the user to update the customerID.

                customerID = updateCustomerID(customerID);
                validChoice = true;
            }
            else if (choice.equalsIgnoreCase("no")) {
                // If the user enters no,break the loop.

                validChoice = true;
            }
            else {
                // Else, display an error message.

                System.out.println("Choice must be yes or no.");
            }
        } while (!validChoice);
        
        validChoice = false;
        
        do {
            // While the user has not entered a valid choice, ask if they want to update the customer's income.

            System.out.println("Would you like to update the customer's income? Yes or no.");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                // If the user enters yes, allow the user to update the customer's income.

                updateCustomerIncome(customerID);
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
            // While the user has not entered a valid choice, ask if they want to create a new record for the customer.

            System.out.println("Would you like to create a new record for this customer? Yes or no.");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                // If the user enters yes, check if the customer is eligible for a new record.

                for (Customer customer : customers) {
                    if (customer.getCustomerID().equals(customerID)) {
                        if (customer.checkEligibility()) {
                            customer.createRecord(records);
                        }
                        else {
                            System.out.println("This customer is ineligible for a new record.");
                        }
                    }
                }

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
            // While the user has not entered a valid choice, ask if they want to update a record for the customer.

            System.out.println("Would you like to update a record for this customer? Yes or no.");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                // If the user enters yes, ask the user for the ID of the record they would like to update.

                boolean validRecordID = false;

                do {
                    // While the user has not entered a valid recordID, ask for the recordID.

                    System.out.println("Please enter the recordID of the record you would like to update. It must consist of six numerical digits.");
                    String recordID = input.nextLine();

                    if (isInt(recordID)) {
                        // If the input recordID is a number, check if it is six characters long.

                        if (recordID.length() == 6) {
                            // If the input recordID is six characters long, check if the record exists.

                            for (Customer customer : customers) {
                                if (customer.getCustomerID().equals(customerID)) {
                                    if (recordExists(recordID)) {
                                        // If the record exists, allow the user to update the record.

                                        customer.updateRecord(recordID, records);
                                        validRecordID = true;
                                        validChoice = true;
                                    }
                                    else {
                                        // Else, display an error message.

                                        System.out.println("This customer does not have a record with that ID.");
                                    }

                                    break;
                                }
                            }
                        }
                        else {
                            // Else, display an error message.

                            System.out.println("RecordID must be six numerical digits.");
                        }
                    }
                    else {
                        // Else, display an error message.

                        System.out.println("RecordID must be six numerical digits.");
                    }
                } while (!validRecordID);
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
            // While the user has not entered a valid choice, ask if they want to delete a record for the customer.
            
            System.out.println("Would you like to delete a record for this customer? Yes or no.");
            choice = input.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                // If the user enters yes, ask the user for the ID of the record they would like to delete.

                boolean validRecordID = false;

                do {
                    // While the user has not entered a valid recordID, ask for the recordID.

                    System.out.println("Please enter the recordID of the record you would like to delete. It must be consist of six numerical digits.");
                    String recordID = input.nextLine();

                    if (isInt(recordID)) {
                        // If the input recordID is a number, check if it is six characters long.

                        if (recordID.length() == 6) {
                            // If the input recordID is six characters long, check if the record exists.

                            for (Customer customer : customers) {
                                if (customer.getCustomerID().equals(customerID)) {
                                    if (recordExists(recordID)) {
                                        // If the record exists, delete the record.

                                        customer.deleteRecord(recordID, records);
                                        validRecordID = true;
                                        validChoice = true;
                                    }
                                    else {
                                        // Else, display an error message.

                                        System.out.println("This customer does not have a record with that ID.");
                                    }

                                    break;
                                }
                            }
                        }
                        else {
                            // Else, display an error message.

                            System.out.println("RecordID must be six numerical digits.");
                        }
                    }
                    else {
                        // Else, display an error message.
                        
                        System.out.println("RecordID must be six numerical digits.");
                    }
                } while (!validRecordID);
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

    public static String updateCustomerID(String customerID) {
        // This method allows the user to update the customerID of a customer.
        // It uses a do while loop to allow the multiple attempts to enter a new ID.

        Scanner input = new Scanner(System.in);
        boolean customerIDChanged = false;
        String newID;

        do {
            // While the user has not updated the customer's ID, ask for a new ID.

            System.out.println("Please enter the new customerID. This must be three letters followed by three numerical digits, and must be unique.");
            newID = input.nextLine();

            if (customerExists(newID)) {
                // If there is a customer with the input customerID, display an error message.

                System.out.println("This customerID is already taken.");
            }
            else {
                // Else, check if the input customerID is a valid customerID.

                if (validCustomerID(newID)) {
                    // If the input customerID is valid, find the customer with the original customerID and update it to the new one.

                    customerID = customerID.substring(0, 3).toUpperCase() + customerID.substring(3);

                    for (Customer customer : customers) {
                        if (customer.getCustomerID().equals(customerID)) {
                            customer.setCustomerID(newID);
                            customerIDChanged = true;
                            break;
                        }
                    }
                 }
            }
        } while (!customerIDChanged);

        return newID;
    }

    public static void updateCustomerIncome(String customerID) {
        // This method allows the user to update a customer's income.
        // It uses a do while loop to allow the user multiple attempts to enter a valid income.

        Scanner input = new Scanner(System.in);
        boolean incomeChanged = false;

        do {
            // While the user has not updated the customer's income, ask for a new income.

            System.out.println("Please enter the new income in thousands of pounds. For example, for £1000, enter 1.");
            String newIncome = input.nextLine();

            if (isDouble(newIncome)) {
                // If the input income is a number, find the customer and update the customer's income.

                for (Customer customer : customers) {
                    if (customer.getCustomerID().equals(customerID)) {
                        customer.setIncome(Double.parseDouble(newIncome));
                        incomeChanged = true;
                        break;
                    }
                }
            }
        } while (!incomeChanged);
    }

    public static void viewCustomerInfo() {
        // This method displays every customer's information and records.

        for (Customer customer : customers) {
            customer.printCustomerDetails();
            System.out.println();
        }
    }

    public static void viewCustomerInfo(String customerID) {
        // This method displays a given customer's information and records.

        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                customer.printCustomerDetails();
            }
        }
    }

    public static boolean validCustomerID(String customerID) {
        // This method checks if the input customerID is valid.
        // Valid customerID: a six character string consisting of three letters followed by three numerical digits.
        // If the input customerID is valid, the method returns true.
        // If the input customerID is invalid, the method displays an error message and returns false.

        if (customerID.length() == 6) {
            // If the input customerID is six characters long, check if it matches the format requirements.

            if (areLetters(customerID.substring(0, 3)) && isInt(customerID.substring(3))) {
                // If the first three characters are letters and the last three are numerical digits, return true.
    
                return true;
            }
        }
        
        System.out.println("Invalid customerID. Must be three letters followed by three numerical digits.");
        return false;
    }

    public static boolean customerExists(String customerID) {
        // This method checks if there exists a customer with the input customerID.
        // It iterates through the list customers and compares each customer's customerID with the input customerID.
        // If a customer is found with a matching customerID, the method returns true.
        // If not, it returns false.

        customerID = customerID.substring(0, 3).toUpperCase() + customerID.substring(3);
        
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isInt(String s) {
        // This method checks if the input String can be converted to an Integer.
        // If it can, the method returns true.
        // If it cannot, the exception is caught, an error message is displayed, and the method returns false.

        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input must be an integer.");
            return false;
        }
    }

    public static boolean isDouble(String s) {
        // This method is the same as the above isInt method, however it checks whether the String can be converted to a Double.
        // This is used where an a decimal number would be an acceptable input.

        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input must be a number.");
            return false;
        }
    }

    public static boolean areLetters(String s) {
        // This method checks if the input String consists only of letters.
        // This is used when checking the input customerIDs.
        // It returns true if the String only contains letters, and false if not.

        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean recordExists(String recordID) {
        // This method iterates through the records and returns true if a record is found with the given recordID.

        for (Loan loan : records) {
            if (loan.getRecordID().equals(recordID)) {
                return true;
            }
        }

        return false;
    }

    public static String roundTwoDecimalPlaces(double num) {
        // This method is used to round decimals to 2 places and is used while printing the customer details.
        // It returns a String which consists of the rounded number.

        return String.format("%.2f", num);
    }
}