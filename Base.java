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

public class Base implements Info{

    private int vie;

    private int vieMax;

    private int revenu;

    private Image image;

    private ImageView imageview;



    public Base(int vieMax, int revenu, String locim) {

        this.vieMax = vieMax;

        this.revenu = revenu;

        vie=vieMax;

        image = new Image(locim);

        imageview = new ImageView(image);

    }



    public int getvie() {

        return vie;

    }



    public int getvieMax() {

        return vieMax;

    }



    public int getrevenu() {

        return revenu;

    }



    public void setvie(int valeur) {

        if(vie + valeur <= 0) {

            vie = 0;

        }

        else {

            vie = vie + valeur;

        }

    }



    public boolean basedetruite() {

        return vie == 0;

    }



    public void gainargent(Ressource r) {

        r.setargent(revenu);

    }
