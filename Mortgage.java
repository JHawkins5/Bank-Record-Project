package CW2;

public class Mortgage extends Loan {
    protected double paymentAmount;

    public Mortgage() {
        // Default constructor containing no arguments, sets default values.

        recordID = "XXXXXX";
        loanType = "Mortgage";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Mortgage(String recordID) {
        // This constructor takes a recordID and creates a record with that recordID, and sets defaults values for the other variables.

        this.recordID = recordID;
        loanType = "Mortgage";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Mortgage(String recordID, double interestRate, double amountDue, double loanTermLeft, double paymentAmount) {
        // This constructor takes inputs for all variables and sets the attributes appropriately.
        
        this.recordID = recordID;
        loanType = "Mortgage";
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        this.loanTermLeft = loanTermLeft;
        this.paymentAmount = paymentAmount;
    }

    public void overpay(double percentageToPay) {
        // This method allows the user to overpay on their mortgage by a percentage between 0 and 2%.

        System.out.println("The current amount due is £" + amountDue);

        double thisPayment = paymentAmount * (percentageToPay / 100);
        amountDue -= thisPayment;
        System.out.println("The new amount due is £");

        if (amountDue < 0) {
            // If the amount due is less than zero, set the amount due to zero and tell the user the loan has been repaid.

            amountDue = 0;
            System.out.print(amountDue);
            System.out.println("The loan has been repaid.");
        }
        else {
            // Else, display the new amount due.
            
            System.out.print(amountDue);
        }
    }
}