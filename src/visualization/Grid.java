package visualization;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Grid {

    private ObservableList<ObservableList<BooleanProperty>> cells;

    void initializeCells(int n, int m)
    {
        cells = FXCollections.observableArrayList();
        for(int i = 0; i < n; i++)
        {
            ObservableList<BooleanProperty> row = FXCollections.observableArrayList();
            cells.add(i, row);
            for(int j = 0; j < m; j++)
                row.add(new SimpleBooleanProperty(false));
        }
    }
    BooleanProperty checkIfAlive(int n, int m)
    {
        return cells.get(n).get(m);
    }

    public ObservableList<ObservableList<BooleanProperty>> getCells() {
        return cells;
    }

    public void updateCell(int i, int j) {
        Boolean cellValue= checkIfAlive(i,j).getValue();
        checkIfAlive(i,j).setValue(!cellValue);
    }

    public void nextGeneration() {
        goToNextState(calculateNextState());
    }

    private void goToNextState(ObservableList<ObservableList<BooleanProperty>> nextState) {
    }

    private ObservableList<ObservableList<BooleanProperty>> calculateNextState() {
        ObservableList<ObservableList<BooleanProperty>> nextState = FXCollections.observableArrayList();
        return nextState;
    }
}
