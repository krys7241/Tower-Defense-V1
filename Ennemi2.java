package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Ennemi2 extends Ennemi {

    public Ennemi2() {
        super("ennemi résistant", 200, 50, 50, 30, "ennemi2.png");
    }

    public void afficheinfo(Pane p) {
        Text t = new Text(nom + "\n" + "point de vie: " + vie +"/200" + "\n" + "attaque: " + attaque);
        Timeline vieennemi = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                t.setText(nom + "\n" + "point de vie: " + vie +"/200" + "\n" + "attaque: " + attaque);
            }
        }));
        vieennemi.setCycleCount(Timeline.INDEFINITE);
        vieennemi.play();
        StackPane infoennemi = new StackPane(t);
        infoennemi.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        infoennemi.setMaxSize(110, 50);
        imageview.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                infoennemi.setTranslateX(getposition().getX()+(image.getWidth()/2));
                infoennemi.setTranslateY(getposition().getY()-(image.getHeight()/2));
                p.getChildren().add(infoennemi);
            }
        });

        imageview.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                p.getChildren().remove(infoennemi);
            }
        });
        Timeline debug = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (ennemivivant()){
                    if (p.getChildren().contains(infoennemi)){
                        p.getChildren().remove(infoennemi);
                    }
                }
            }
        }));
        debug.setCycleCount(1);
        debug.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!ennemivivant()){
                    debug.play();
                }
            }
        });
        debug.play();
    }
}
