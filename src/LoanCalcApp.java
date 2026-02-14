import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//Updated 02/14/26
public class LoanCalcApp extends Application{
    public void start(Stage stage){
        //inputs and input labels
        Label rateLabel = new Label("Annual Interest Rate:");
        TextField annualInterestRate = new TextField();

        Label yearsLabel = new Label("Number of years:");
        TextField numYears = new TextField();

        Label amountLabel = new Label("Amount:");
        TextField loanAmount = new TextField();


        //Compute button
        Button multiplyButton = new Button("Compute");

        //Result labels
        Label resultLabel2 = new Label("Monthly Payments:");
        Label resultMonthly = new Label();
        Label resultLabel1 = new Label("Total Payment:");
        Label totalResult = new Label();
        Label errorLabel = new Label("");

        //result label textfields
        TextField result1Field = new TextField();
        result1Field.setEditable(false);
        TextField result2Field = new TextField();
        result2Field.setEditable(false);

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
               result1Field.setText(String.format("$%.2f", monthlyPayment));
               result2Field.setText(String.format("$%.2f", totalPayment));


           } catch (NumberFormatException ex) {
               //invalid input result
               errorLabel.setText("Please enter valid numbers");
           }
        });
        //GridPane to add prompt text outside of textfield
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(rateLabel, 0, 0);
        grid.add(annualInterestRate, 1, 0);
        grid.add(yearsLabel, 0, 1);
        grid.add(numYears, 1, 1);
        grid.add(amountLabel, 0, 2);
        grid.add(loanAmount, 1, 2);
        grid.add(resultLabel2, 0, 3);
        grid.add(result1Field, 1, 3);
        grid.add(resultLabel1, 0, 4);
        grid.add(result2Field, 1, 4);
        grid.add(errorLabel, 0, 6);




        //Layout and scene
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(grid, multiplyButton);

        Scene scene = new Scene(layout, 350, 270);
        scene.getStylesheets().add("style.css");
        stage.setTitle("LoanCalculator");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args){
        launch();
    }
}
