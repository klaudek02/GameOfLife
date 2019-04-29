package visualization;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GameOfLifeController implements Initializable {
    @FXML
    private AnchorPane menuPane;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private ChoiceBox<States> stateChoiceBox;

    @FXML
    private TextField nSizeField;

    @FXML
    private TextField mSizeField;

    @FXML
    private Button updateButton;

    @FXML
    private GridPane visualizationPane;

    private GameOfLife gameOfLife;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameOfLife = new GameOfLife(60,40);
        stateChoiceBox.setItems(FXCollections.observableArrayList(States.values()));
        stateChoiceBox.getSelectionModel()
                .selectedItemProperty()
                .addListener( (ObservableValue<? extends States> observable, States oldValue, States newValue) -> {
                    if(newValue != null)
                    {
                        gameOfLife.updateState(newValue);
                        resizeVisualization();
                    }
                });
    }


    public void resizeVisualization()
    {
        ObservableList<ObservableList<BooleanProperty>> grid = gameOfLife.getGrid();
        System.out.println("resize");
        visualizationPane.getChildren().clear();
        while(visualizationPane.getRowConstraints().size() > 0){
            visualizationPane.getRowConstraints().remove(0);
        }
        while(visualizationPane.getColumnConstraints().size() > 0){
            visualizationPane.getColumnConstraints().remove(0);
        }
        // ?/visualizationGridPane.resize(20,20);
        double sizee = 0;
        double width =  100./ 40;
        double height = 100./ 60;
        for (int i = 0; i < 40; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setHgrow(Priority.ALWAYS);
            colConst.setPercentWidth(width);
            visualizationPane.getColumnConstraints().add(colConst);
        }
        for(int i = 0; i <60; i++){
            RowConstraints rowConst = new RowConstraints();
            rowConst.setVgrow(Priority.ALWAYS);
            rowConst.setPercentHeight(height);
            visualizationPane.getRowConstraints().add(rowConst);
        }

        for(int i = 0; i < 60; i++)
            for(int j = 0; j <40; j++){
                StackPane square = new StackPane();
                square.setStyle("-fx-background-color: white; -fx-border-color: black");
                final int ii = i;
                final int jj = j;
                square.setOnMouseClicked(e-> {
                    gameOfLife.updateCell(ii, jj);
                });
                grid.get(i).get(j).addListener((e) -> {
                    square.setStyle("-fx-background-color: yellow; -fx-border-color: black");
                });

                visualizationPane.add(square,j,i);
            }
    }
}
