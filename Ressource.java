package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Ressource implements Info {

    private int argent;
    public Ressource() {
        this.argent = 1000;
    }

    public int getargent() {
        return argent;
    }

    public void setargent(int valeur) {
        if(argent + valeur >= 0) {
            argent = argent + valeur;
        }
    }

    public void achetetour(Tour t) {
        if(t.getcost() <= argent) {
            argent = argent - t.getcost();
        }
    }

    public boolean assezriche(Tour t) {
        return t.getcost() <= argent;
    }

    public void afficheinfo(Pane p2){
        Text nbror = new Text("or: " + argent);
        Timeline ormtn = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nbror.setText("or: " + argent);
            }
        }));
        ormtn.setCycleCount(Timeline.INDEFINITE);
        ormtn.play();
        StackPane infoor = new StackPane(nbror);
        infoor.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        infoor.setMinSize(50, 20);
        infoor.setTranslateX(1300);
        infoor.setTranslateY(20);
        p2.getChildren().add(infoor);
    }
}
