package CW2;

public class Other extends Loan {
    public Other() {
        // Default constructor containing no arguments, sets default values.

        recordID = "XXXXXX";
        loanType = "Other";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Other(String recordID) {
        // This constructor takes a recordID and creates a record with that recordID, and sets defaults values for the other variables.

        this.recordID = recordID;
        loanType = "Other";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Other(String recordID, double interestRate, double amountDue, double loanTermLeft, double paymentAmount) {
        // This constructor takes inputs for all variables and sets the attributes appropriately.
        
        this.recordID = recordID;
        loanType = "Other";
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        this.loanTermLeft = loanTermLeft;
        this.paymentAmount = paymentAmount;
    }
}