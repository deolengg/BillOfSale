package billofsale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author karan
 */
public class EmployeeLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        //StackPane root = new StackPane(sendButton,clearButton,firstName,lastName,fName,lName);
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        root.getChildren().addAll();

        Scene scene = new Scene(root, 500, 350);

        Text scenetitle = new Text("Employee Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        root.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        root.add(userName, 0, 1);

        TextField userTextField = new TextField();
        root.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        root.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        root.add(pwBox, 1, 2);

        Button btn = new Button("Login");
        HBox hbBtn = new HBox(10);

        Button Rbtn = new Button("Reset");
        HBox RhbBtn = new HBox(10);

        Button Ebtn = new Button("Exit");
        HBox EXbBtn = new HBox(10);

        final Label message = new Label("");
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        RhbBtn.setAlignment(Pos.BOTTOM_LEFT);
        EXbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        hbBtn.getChildren().addAll(btn, Rbtn, message);
        root.add(hbBtn, 1, 4);
        root.add(Ebtn, 2, 4);

        final Text actiontarget = new Text();
        root.add(actiontarget, 1, 6);

        primaryStage.setTitle("Employee Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        Rbtn.setOnAction((javafx.event.ActionEvent event)
                -> {
            userTextField.clear();
            pwBox.clear();
            message.setText("");

        });

        Ebtn.setOnAction((javafx.event.ActionEvent event)
                -> {

            System.out.println("Exit");
            System.exit(1);
        });

        btn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    //mysql
                    //Class.forName(com.mysql.jdbc.Driver);
                    System.out.println("Driver Found");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@dilbert.humber.ca:1521:grok", "n01168570", "oracle");
                    System.out.println("Connection Done");
                    PreparedStatement st = con.prepareStatement("select count(*) as total from employeelog where id = ? and pwd= ?");
                    String username = userTextField.getText();
                    String password = pwBox.getText();
                    st.setString(1, username);
                    st.setString(2, password);

                    ResultSet RS = st.executeQuery();
                    if (RS.next()) {
                        if (RS.getInt("total") != 1) {

                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Fail !");

                            alert.setContentText("User Doesn't Exists in Database");

                            alert.showAndWait();

                        } else {

                            System.out.println("User Doesn't Exits");

                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Login Success");

                            alert.setContentText("Welcome " + username);

                            alert.showAndWait();
                            System.out.println("User Exits");

                        }
                    }

                    con.close();
                    System.out.println("Connection Closed");

                } catch (SQLException | ClassNotFoundException obj) {
                    System.err.print("Either Drive OR Class Not Found " + obj);
                }
            }

        });

    }
}
