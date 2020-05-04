package sample;

import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public abstract class Tour implements Info {

    protected int cost;

    protected int niveau;

    protected int attaque;

    protected String nom;

    protected Image image;

    protected ImageView imageview;

    protected Point2D position;

    protected double vitat;

    public Tour(int x, int y, String nom, int attaque, int niveau, int cost, double vitat, String emplacementimage) {

        this.nom = nom;

        this.attaque = attaque;

        this.niveau = niveau;

        this.cost = cost;

        this.vitat = vitat;

        image = new Image(emplacementimage);

        imageview = new ImageView(image);

        position = new Point2D(x,y);

    }

    public String getnom() {

        return nom;

    }



    public int getcost() {

        return cost;

    }



    public int getniveau(){

        return niveau;

    }



    public int getattaque() {

        return attaque;

    }


    public Image getimage() {

        return image;

    }



    public ImageView getimageview() {

        return imageview;

    }



    public Point2D getposition() {

        return position;

    }



    public double getvitat() {

        return vitat;

    }

    public abstract void upgrade(Ressource r, Timeline tps, Text t, Pane p2);

    public abstract void afficheinfo(Pane p);

    public abstract void go(ArrayList<Ennemi> listenoir, Pane p2, Ressource r, Text erreur);
}
