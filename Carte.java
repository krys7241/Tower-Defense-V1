package sample;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


import java.awt.geom.Point2D;
import java.util.ArrayList;



public class Carte {

    private Image mapim;
    private ImageView map;
    private Image baseim;
    private ImageView base;
    private Image zoneim;
    private ImageView zone;
    private double basex;
    private double basey;

    public Carte(String maploc, String zoneloc, double basex, double basey) {
        mapim = new Image(maploc);
        map = new ImageView(mapim);
        zoneim = new Image(zoneloc);
        zone = new ImageView(zoneim);
        this.basex = basex;
        this.basey = basey;
    }

    public Image getmapim(){
        return mapim;
    }

    public ImageView getmap(){
        return map;
    }

    public Image getzoneim(){
        return zoneim;
    }

    public ImageView getzone(){
        return zone;
    }

    public double getbasex(){
        return basex;
    }

    public double getbasey(){
        return basey;
    }

}
