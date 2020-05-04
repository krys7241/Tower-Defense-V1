package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;

public abstract class Ennemi implements Info {

    protected String nom;
    protected int vie;
    protected int attaque;
    protected int valeur;
    protected int vitesse;
    protected Image image;
    protected ImageView imageview;

    public Ennemi(String nom, int vie, int attaque, int vitesse, int valeur, String localisationimage) {

        this.nom = nom;
        this.vie = vie;
        this.attaque = attaque;
        this.vitesse = vitesse;
        this.valeur = valeur;
        image = new Image(localisationimage);
        imageview = new ImageView(image);
    }

    public static int compte = 0;

    public int getvie() {
        return vie;
    }

    public int getattaque() {
        return attaque;
    }

    public String getnom() {
        return nom;
    }

    public int getvaleur() {
        return valeur;
    }

    public int getvitesse() {
        return vitesse;
    }

    public Image getimage() {
        return image;
    }

    public ImageView getimageview() {
        return imageview;
    }

    public void setvie(int valeur) {
        if(vie + valeur > 0) {
            vie = vie + valeur;
        }
        else {
            vie = 0;
        }
    }

    public boolean ennemivivant() {
        return vie == 0;
    }

    public void attaquebase(Base b) {
        b.setvie(-attaque);
    }

    public void gainmort(Ressource r) {
        r.setargent(valeur);
    }

    public void deplacement(Ennemi e, Path p, Base b, Pane pane, ArrayList<Ennemi> list,Ressource r) {
        imageview.setX(1163);
        imageview.setY(700);
        pane.getChildren().add(imageview);
        PathTransition marche = new PathTransition(Duration.millis(vitesse*1000), p, imageview);
        marche.play();
        marche.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                attaquebase(b);
                pane.getChildren().remove(imageview);
                list.remove(e);
                compte = compte + 1;
            }
        });
        Timeline vivant = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(e.ennemivivant()) {
                    marche.stop();
                    pane.getChildren().remove(imageview);
                    list.remove(e);
                    e.gainmort(r);
                    compte = compte + 1;
                }
            }
        }));
        vivant.setCycleCount(1);
        vivant.play();
        vivant.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!e.ennemivivant()) {
                    vivant.setCycleCount(1);
                    vivant.play();
                }

            }
        });
    }

    public abstract Point2D getposition();

    public abstract void afficheinfo(Pane p);
}
