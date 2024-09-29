package CW2;

public class Builder extends Loan {
    public Builder() {
        // Default constructor containing no arguments, sets default values.

        recordID = "XXXXXX";
        loanType = "Builder";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Builder(String recordID) {
        // This constructor takes a recordID and creates a record with that recordID, and sets defaults values for the other variables.

        this.recordID = recordID;
        loanType = "Builder";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Builder(String recordID, double interestRate, double amountDue, double loanTermLeft, double paymentAmount) {
        // This constructor takes inputs for all variables and sets the attributes appropriately.
        
        this.recordID = recordID;
        loanType = "Builder";
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        this.loanTermLeft = loanTermLeft;
        this.paymentAmount = paymentAmount;
    }

    public void overpay(double percentageToPay) {
        // This method allows the user to overpay on their builder loan by a percentage between 0 and 2%.

        System.out.println("The current amount due is £" + amountDue);
        double thisPayment = paymentAmount * (percentageToPay / 100);
        amountDue -= thisPayment;
        System.out.println("The new amount due is £");

        if (amountDue < 0) {
            // If the amount due is less than zero, set the amount due to zero.
            
            amountDue = 0;
            System.out.print(amountDue);
            System.out.println("The loan has been repaid.");
        }
        else {
            System.out.print(amountDue);
        }
    }
}