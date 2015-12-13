package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Download Manager");

        VBox rootPane = new VBox();

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.TOP_CENTER);

        Button buttonAdd = new Button("Add");
        Button buttonStop = new Button("Stop");
        HBox.setHgrow(buttonAdd, Priority.ALWAYS);
        HBox.setHgrow(buttonStop, Priority.ALWAYS);
        buttonAdd.setMaxWidth(100);
        buttonStop.setMaxWidth(100);

        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                GridPane gridPane = new GridPane();

                gridPane.setVgap(10);
                gridPane.setHgap(5);
                gridPane.setPadding(new Insets(10));

                Label lbl1 = new Label("_Name:");
                Label lbl2 = new Label("_URL:");
                Label lbl3 = new Label("_Depth:");

                TextField field1 = new TextField();
                TextField field2 = new TextField();
                TextField field3 = new TextField();

                lbl1.setLabelFor(field1);
                lbl1.setMnemonicParsing(true);
                lbl2.setLabelFor(field2);
                lbl2.setMnemonicParsing(true);
                lbl3.setLabelFor(field3);
                lbl3.setMnemonicParsing(true);

                gridPane.add(lbl1, 0, 0);
                gridPane.add(field1, 2, 0);
                gridPane.add(lbl2, 0, 1);
                gridPane.add(field2, 2, 1);
                gridPane.add(lbl3, 0, 2);
                gridPane.add(field3, 2, 2);

                GridPane.setHalignment(lbl1, HPos.RIGHT);
                GridPane.setHalignment(lbl2, HPos.RIGHT);
                GridPane.setHalignment(lbl3, HPos.RIGHT);

                Scene scene = new Scene(gridPane);

                stage.setTitle("Add Download");
                stage.setScene(scene);
                stage.show();
            }
        });

        buttonBox.getChildren().addAll(buttonAdd, buttonStop);
        buttonBox.setPrefWidth(600);
        buttonBox.setFillHeight(true);

        rootPane.getChildren().add(buttonBox);

        TableView table = new TableView();
        table.setEditable(false);

        TableColumn nameCol = new TableColumn("Name");
        TableColumn URLCol = new TableColumn("URL");
        TableColumn progressCol = new TableColumn("Progress");

        table.getColumns().addAll(nameCol, URLCol, progressCol);

        rootPane.getChildren().add(table);

        Scene mainScene = new Scene(rootPane);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
