package CW2;

public class Auto extends Loan {
    public Auto() {
        // Default constructor containing no arguments, sets default values.

        recordID = "XXXXXX";
        loanType = "Auto";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Auto(String recordID) {
        // This constructor takes a recordID and creates a record with that recordID, and sets defaults values for the other variables.

        this.recordID = recordID;
        loanType = "Auto";
        interestRate = 0;
        amountDue = 0;
        loanTermLeft = 0;
        paymentAmount = 0;
    }

    public Auto(String recordID, double interestRate, double amountDue, double loanTermLeft, double paymentAmount) {
        // This constructor takes inputs for all variables and sets the attributes appropriately.
        
        this.recordID = recordID;
        loanType = "Auto";
        this.interestRate = interestRate;
        this.amountDue = amountDue;
        this.loanTermLeft = loanTermLeft;
        this.paymentAmount = paymentAmount;
    }
}