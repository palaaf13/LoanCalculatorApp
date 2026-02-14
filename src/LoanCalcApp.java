import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//Will update later on
public class LoanCalcApp extends Application{
    public void start(Stage stage){
        //inputs
        TextField annualInterestRate = new TextField();
        annualInterestRate.setPromptText("Annual Interest Rate:  ");

        TextField numYears = new TextField();
        numYears.setPromptText("Number of Years");

        TextField loanAmount = new TextField();
        loanAmount.setPromptText("Loan Amount ");

        //Compute button
        Button multiplyButton = new Button("Compute");

        //Result labels
        Label resultLabel1 = new Label();
        Label resultLabel2 = new Label();

        //Exception handling(invalid input eg(letters, symbols))
        multiplyButton.setOnAction(e -> {
           try {
               float annualRate = Float.parseFloat(annualInterestRate.getText());
               float years = Float.parseFloat(numYears.getText());
               float amount = Float.parseFloat(loanAmount.getText());

               //monthly rate(r) and total months calculation(n)
               float r = annualRate/12/100;
               float n = years*12;

               //Calculations
               double monthlyPayment = amount * ((r * Math.pow(1 + r, n))/((Math.pow(1+r, n)-1)));
               double totalPayment = n * monthlyPayment;

               //Formatting to 2 decimals
               resultLabel2.setText((String.format("Monthly Payments: $%.2f", monthlyPayment)));
               resultLabel1.setText((String.format("Total Payment: $%.2f", totalPayment)));
           } catch (NumberFormatException ex) {
               //invalid input result
               resultLabel1.setText("Please enter valid numbers");
           }
        });

        //Layout and scene
        VBox layout = new VBox(10, annualInterestRate, numYears, loanAmount, multiplyButton, resultLabel2, resultLabel1);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 300);
        stage.setTitle("LoanCalculator");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
