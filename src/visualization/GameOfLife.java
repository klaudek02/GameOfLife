package visualization;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class GameOfLife {

    private final Grid grid;
    private Timeline timeline;
    private final ReadOnlyLongWrapper generation = new ReadOnlyLongWrapper();


    public GameOfLife(int n, int m) {
        grid = new Grid();
        buildGrid(n,m);
        setTimeline();
    }

    private void setTimeline() {
        EventHandler<ActionEvent> eventHandler = event-> next();
        KeyFrame keyFrame = new KeyFrame(new Duration(1000), eventHandler);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);

    }

    private void next() {
        grid.nextGeneration();
        generation.set(generation.get()+1);
    }

    public void updateState(States newValue) {
    }

    private void buildGrid(int n, int m)
    {
        grid.initializeCells(n,m);
    }
    public ObservableList<ObservableList<BooleanProperty>> getGrid()
    {
        return grid.getCells();
    }
    public void updateCell(int i , int j)
    {
        grid.updateCell(i, j);
    }
    public void playGame()
    {
        timeline.play();
    }
    public void pauseGame()
    {

    }
}
