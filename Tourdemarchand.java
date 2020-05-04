package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class Tourdemarchand extends Tour {

    private int upgradecost;


    public Tourdemarchand(int x, int y) {

        super(x, y, "Tour de marchand", 50, 1, 300, 5, "tour4.png");

        this.upgradecost = 300;

    }

    public void upgrade(Ressource r, Timeline tps, Text t, Pane p2) {
        imageview.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent mouseEvent) {

                if (r.getargent() >= upgradecost) {

                    r.setargent(-upgradecost);

                    niveau = niveau + 1;

                    attaque = attaque + 20;

                    image = new Image("tour5.png");

                    imageview = new ImageView(image);

                    vitat = vitat - 1;

                    tps.setDelay(Duration.millis((vitat * 1000) - 500));

                    p2.getChildren().remove(imageview);

                    imageview.relocate(position.getX() - (image.getWidth()/2), position.getY() -(image.getHeight()/2));

                    p2.getChildren().add(imageview);

                    afficheinfo(p2);

                    t.setText("Tour améliorée");

                    Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {

                        @Override

                        public void handle(ActionEvent actionEvent) {

                            t.setText("");

                        }

                    }));

                    erreurgo.setCycleCount(1);

                    erreurgo.play();

                    upgrade2(t);

                }

                else {

                    t.setText("Pas assez d'or pour ameliore la tour");

                    Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {

                        @Override

                        public void handle(ActionEvent actionEvent) {

                            t.setText("");

                        }

                    }));

                    erreurgo.setCycleCount(1);

                    erreurgo.play();

                }

            }

        });

    }

    public void upgrade2(Text t) {

        imageview.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent mouseEvent) {

                t.setText("Tour deja améliorée au max");

                Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {

                    @Override

                    public void handle(ActionEvent actionEvent) {

                        t.setText("");

                    }

                }));

                erreurgo.setCycleCount(1);

                erreurgo.play();

            }

        });

    }

    public void afficheinfo(Pane p) {

        Text t = new Text(nom + " de niveau " + niveau + "\n" + "génére " + attaque + " or" + " toutes les " + vitat + " secondes" + "\n" + "cout d'amelioration: " + upgradecost);

        Timeline vietour = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<javafx.event.ActionEvent>() {

            @Override

            public void handle(ActionEvent actionEvent) {

                if(niveau < 2) {

                    t.setText(nom + " de niveau " + niveau + "\n" + "génére " + attaque + " or" + " toutes les " + vitat + " secondes" + "\n" + "cout d'amelioration: " + upgradecost);

                }

                else {

                    t.setText(nom + " de niveau " + niveau + "\n" + "génére " + attaque + " or" + " toutes les " + vitat + " secondes");

                }

            }

        }));

        vietour.setCycleCount(Timeline.INDEFINITE);

        vietour.play();

        StackPane infotour = new StackPane(t);

        infotour.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        infotour.setMaxSize(110, 50);

        infotour.setTranslateX(position.getX()+(image.getWidth()/2));

        infotour.setTranslateY(position.getY()-(image.getHeight()/2));

        imageview.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent mouseEvent) {

                p.getChildren().add(infotour);

            }

        });



        imageview.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent mouseEvent) {

                p.getChildren().remove(infotour);

            }

        });

    }

    public void go(ArrayList<Ennemi> listenoir, Pane p2, Ressource r, Text erreur) {
        Timeline revenu = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                r.setargent(attaque);
            }
        }));
        revenu.setCycleCount(1);
        revenu.setDelay(Duration.millis((vitat*1000)-500));
        revenu.play();
        revenu.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                revenu.play();
            }
        });
        upgrade(r, revenu, erreur, p2);
    }
}
