package sample;



import javafx.animation.KeyFrame;

import javafx.animation.PathTransition;

import javafx.animation.Timeline;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.geometry.Point2D;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.*;

import javafx.scene.paint.Color;

import javafx.scene.shape.LineTo;

import javafx.scene.shape.MoveTo;

import javafx.scene.shape.Path;

import javafx.scene.text.Text;

import javafx.util.Duration;



import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Tourdegarde extends Tour {

    private int upgradecost;

    private int range;

    public Tourdegarde(int x, int y) {

        super( x, y,"Tour de garde",20, 1, 200, 2,"tour.png");

        this.range = 200;

        this.upgradecost = 200;

    }

    public int getupgradecost() {

        return upgradecost;

    }


    public int getrange() {

        return range;

    }

    public void upgrade(Ressource r, Timeline tps, Text t, Pane p2) {

        imageview.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent mouseEvent) {

                if (r.getargent() >= upgradecost) {

                    r.setargent(-upgradecost);

                    niveau = niveau + 1;

                    attaque = attaque + 10;

                    range = range + 100;

                    upgradecost = upgradecost + 200;

                    image = new Image("tour2.png");

                    imageview = new ImageView(image);

                    if (vitat > 0.5) {

                        vitat = vitat - 0.5;

                        tps.setDelay(Duration.millis((vitat * 1000) - 500));

                    }

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

                    upgrade2(r, tps, t, p2);

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



    public void upgrade2(Ressource r, Timeline tps, Text t, Pane p2) {

        imageview.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override

            public void handle(MouseEvent mouseEvent) {

                if (r.getargent() >= upgradecost) {

                    r.setargent(-upgradecost);

                    niveau = niveau + 1;

                    attaque = attaque + 10;

                    range = range + 100;

                    upgradecost = upgradecost + 200;

                    image = new Image("tour3.png");

                    imageview = new ImageView(image);

                    if (vitat > 0.5) {

                        vitat = vitat - 0.5;

                        tps.setDelay(Duration.millis((vitat * 1000) - 500));

                    }

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

                    upgrade3(t);

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



    public void upgrade3(Text t) {

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



    public void attaqueennimi(Ennemi e, Pane p) {

        Image sort = new Image("sort.png");

        ImageView tir = new ImageView(sort);

        tir.setX(this.getposition().getX());

        tir.setY(this.getposition().getY());

        p.getChildren().add(tir);

        Path ennemicentre = new Path();

        MoveTo initial = new MoveTo(this.getposition().getX(), this.getposition().getY());

        LineTo ligne1 = new LineTo(e.getposition().getX(), e.getposition().getY());

        ennemicentre.getElements().addAll(initial, ligne1);

        PathTransition lancetir = new PathTransition(Duration.millis(350),ennemicentre,tir);

        lancetir.play();

        lancetir.setOnFinished(new EventHandler<javafx.event.ActionEvent>() {

            @Override

            public void handle(javafx.event.ActionEvent actionEvent) {

                e.setvie(-attaque);

                p.getChildren().remove(tir);

            }

        });

    }



    public void afficheinfo(Pane p) {

        Text t = new Text(nom + " de niveau " + niveau + "\n" + "attaque de " + attaque + " toutes les " + vitat + " secondes" + "\n" +"portée: " + range + "\n" + "cout d'amelioration: " + upgradecost);

        Timeline vietour = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<javafx.event.ActionEvent>() {

            @Override

            public void handle(ActionEvent actionEvent) {

                if(niveau < 3) {

                    t.setText(nom + " de niveau " + niveau + "\n" + "attaque de " + attaque + " toutes les " + vitat + " secondes" + "\n" + "portée: " + range + "\n" + "cout d'amelioration: " + upgradecost);

                }

                else {

                    t.setText(nom + " de niveau " + niveau + "\n" + "attaque de " + attaque + " toutes les " + vitat + " secondes" + "\n" + "portée: " + range);

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

    public void go (ArrayList<Ennemi> listenoir, Pane p2, Ressource r, Text erreur){

        Timeline ennemivstour = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<Ennemi> ennemiaporte = new ArrayList<Ennemi>();
                for (Ennemi e : listenoir) {
                    if (Math.pow((e.getposition().getX() - position.getX()), 2) + Math.pow((e.getposition().getY() - position.getY()), 2) <= Math.pow(range, 2)) {
                        ennemiaporte.add(e);
                    }
                }
                if (!ennemiaporte.isEmpty()) {
                    Random heureuxgagnant = new Random();
                    int legagnant = heureuxgagnant.nextInt(ennemiaporte.size());
                    Ennemi victime = ennemiaporte.get(legagnant);
                    attaqueennimi(victime, p2);
                    ennemiaporte.clear();
                }
            }
        }));
        ennemivstour.setCycleCount(1);
        ennemivstour.setDelay(Duration.millis((getvitat()*1000)-500));
        ennemivstour.play();
        ennemivstour.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ennemivstour.play();
            }
        });
        upgrade(r, ennemivstour, erreur, p2);
    }

}
