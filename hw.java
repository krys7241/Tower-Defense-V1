package sample;







import javafx.animation.KeyFrame;



import javafx.animation.PauseTransition;



import javafx.animation.Timeline;



import javafx.application.Application;



import javafx.application.Platform;



import javafx.event.ActionEvent;



import javafx.event.EventHandler;



import javafx.geometry.Bounds;



import javafx.geometry.Insets;



import javafx.geometry.Point2D;



import javafx.scene.Group;



import javafx.scene.control.Button;



import javafx.scene.effect.Light;



import javafx.scene.image.Image;



import javafx.scene.image.ImageView;



import javafx.scene.image.PixelReader;


import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;



import javafx.scene.layout.*;



import javafx.scene.paint.Color;



import javafx.scene.shape.LineTo;



import javafx.scene.shape.MoveTo;



import javafx.scene.shape.Path;



import javafx.scene.text.Text;



import javafx.stage.Stage;



import javafx.scene.Scene;



import javafx.util.Duration;







import javax.swing.*;



import java.awt.*;


import java.net.http.WebSocket;
import java.util.*;
import java.util.Timer;


public class hw extends Application {



    private EnnemiFactory ennemiFactory = new EnnemiFactory();



    private ArrayList<String> types = new ArrayList<>();



    @Override



    public void start(Stage stage) throws Exception {



        Image ImgC1 = new Image("Carte1.jpg");



        ImageView boutonCarte1 = new ImageView(ImgC1);



        boutonCarte1.setFitHeight(100);



        boutonCarte1.setFitWidth(100);



        boutonCarte1.setTranslateX(-100);



        boutonCarte1.setTranslateY(130);





        Text txt = new Text("Bienvenu");



        txt.setTranslateX(100);





        Image y = new Image("fond.png");



        ImageView back = new ImageView(y);





        Pane p = new Pane(back);



        StackPane sp = new StackPane(p, boutonCarte1, txt);



        sp.setMinSize(1376, 736);



        Scene s = new Scene(sp);



        stage.setResizable(false);



        stage.setScene(s);



        stage.setTitle("Tower defense");



        stage.show();



        boutonCarte1.setOnMouseClicked((new EventHandler<MouseEvent>() {



            @Override



            public void handle(MouseEvent event) {



                types.add("Ennemi1");



                types.add("Ennemi2");



                types.add("Ennemi3");



                sp.getChildren().removeAll(p, boutonCarte1, txt);



                Carte carte = new Carte("map.png","zone.png", 105, 0);



                Text erreur = new Text("");



                erreur.setTranslateY(-320);



                erreur.setTranslateX(-50);

                Base b = new Base(1000,20, "base.png");

                Pane p2 = new Pane(b.getimageview());

                p2.setMinSize(1376, 736);

                b.getimageview().relocate(carte.getbasex(), carte.getbasey());

                Pane p3 = new Pane(carte.getzone());

                p3.setMinSize(1376, 736);

                p3.setVisible(false);

                sp.getChildren().addAll(carte.getmap(),p2, p3, erreur);

                Ressource r = new Ressource();

                ArrayList<Point2D> lestours = new ArrayList<Point2D>();

                ArrayList<Ennemi> listenoir = new ArrayList<Ennemi>();

                Path chemin = createPath();


                Timeline finjeu = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        if (b.basedetruite()) {



                            Text defaite = new Text("GAME OVER");



                            StackPane endgame = new StackPane(defaite);



                            endgame.setMinSize(1376, 736);



                            Scene fin = new Scene(endgame);



                            stage.setScene(fin);



                            stage.show();





                        }



                    }



                }));



                finjeu.setCycleCount(1);



                finjeu.play();



                finjeu.setOnFinished(new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        if (!b.basedetruite()) {



                            finjeu.setCycleCount(1);



                            finjeu.play();



                        }



                    }



                });





                Timeline victoir = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        if (Ennemi.compte == 50) {



                            Text win = new Text("VICTOIR");



                            StackPane happyend = new StackPane(win);



                            happyend.setMinSize(1376, 736);



                            Scene fin = new Scene(happyend);



                            stage.setScene(fin);



                            stage.show();





                        }



                    }



                }));



                victoir.setCycleCount(1);



                victoir.play();



                victoir.setOnFinished(new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        if (!b.basedetruite()) {



                            victoir.setCycleCount(1);



                            victoir.play();



                        }



                    }



                });

                b.afficheinfo(p2);

                r.afficheinfo(p2);

                    Timeline revenuor = new Timeline(new KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>() {



                        @Override



                        public void handle(ActionEvent actionEvent) {



                            b.gainargent(r);



                        }



                }));



                revenuor.setCycleCount(Timeline.INDEFINITE);



                revenuor.play();


                Image tdgicon = new Image("icon1.png");

                ImageView tdgiconview = new ImageView(tdgicon);

                StackPane tourdegardeactive = new StackPane(tdgiconview);

                tourdegardeactive.setMinSize(50, 50);

                tourdegardeactive.setTranslateX(30);

                tourdegardeactive.setTranslateY(670);

                p2.getChildren().add(tourdegardeactive);

                tourdegardeactive.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

                Image tdmicon = new Image("icon2.png");

                ImageView tdmiconview = new ImageView(tdmicon);

                StackPane tourdemarchandactive = new StackPane(tdmiconview);

                tourdemarchandactive.setMinSize(50, 50);

                tourdemarchandactive.setTranslateX(100);

                tourdemarchandactive.setTranslateY(670);

                p2.getChildren().add(tourdemarchandactive);

                tourdemarchandactive.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

                ArrayList<String> touractive = new ArrayList<String>();

                tourdegardeactive.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        tourdegardeactive.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                        tourdemarchandactive.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                        touractive.clear();
                        touractive.add("g");
                    }
                });

                tourdemarchandactive.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        tourdemarchandactive.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                        tourdegardeactive.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                        touractive.clear();
                        touractive.add("m");
                    }
                });



                Timeline spawn1 = new Timeline(new KeyFrame(Duration.millis(10000), new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        createenemmi(chemin, b, p2, listenoir, r, types);



                    }



                }));





                Timeline spawn2 = new Timeline(new KeyFrame(Duration.millis(7000), new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        createenemmi(chemin, b, p2, listenoir, r, types);



                    }



                }));





                Timeline spawn3 = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        createenemmi(chemin, b, p2, listenoir, r, types);



                    }



                }));





                Timeline spawn4 = new Timeline(new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        createenemmi(chemin, b, p2, listenoir, r, types);



                    }



                }));





                Timeline spawn5 = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        createenemmi(chemin, b, p2, listenoir, r, types);



                    }



                }));





                spawn1.setCycleCount(5);



                spawn1.play();



                spawn1.setOnFinished(new EventHandler<ActionEvent>() {



                    @Override



                    public void handle(ActionEvent actionEvent) {



                        spawn2.setCycleCount(5);



                        spawn2.play();



                        spawn2.setOnFinished(new EventHandler<ActionEvent>() {



                            @Override



                            public void handle(ActionEvent actionEvent) {



                                spawn4.setCycleCount(3);



                                spawn4.play();



                                spawn4.setOnFinished(new EventHandler<ActionEvent>() {



                                    @Override



                                    public void handle(ActionEvent actionEvent) {



                                        spawn3.setCycleCount(3);



                                        spawn3.play();



                                        spawn3.setOnFinished(new EventHandler<ActionEvent>() {



                                            @Override



                                            public void handle(ActionEvent actionEvent) {



                                                spawn4.setCycleCount(3);



                                                spawn4.play();



                                                spawn4.setOnFinished(new EventHandler<ActionEvent>() {



                                                    @Override



                                                    public void handle(ActionEvent actionEvent) {



                                                        spawn2.setCycleCount(2);



                                                        spawn2.play();



                                                        spawn2.setOnFinished(new EventHandler<ActionEvent>() {



                                                            @Override



                                                            public void handle(ActionEvent actionEvent) {



                                                                spawn4.setCycleCount(5);



                                                                spawn4.play();



                                                                spawn4.setOnFinished(new EventHandler<ActionEvent>() {



                                                                    @Override



                                                                    public void handle(ActionEvent actionEvent) {



                                                                        spawn3.setCycleCount(2);



                                                                        spawn3.play();



                                                                        spawn3.setOnFinished(new EventHandler<ActionEvent>() {



                                                                            @Override



                                                                            public void handle(ActionEvent actionEvent) {



                                                                                spawn5.setCycleCount(3);



                                                                                spawn5.play();



                                                                                spawn5.setOnFinished(new EventHandler<ActionEvent>() {



                                                                                    @Override



                                                                                    public void handle(ActionEvent actionEvent) {



                                                                                        spawn2.setCycleCount(1);



                                                                                        spawn2.play();



                                                                                        spawn2.setOnFinished(new EventHandler<ActionEvent>() {



                                                                                            @Override



                                                                                            public void handle(ActionEvent actionEvent) {



                                                                                                spawn4.setCycleCount(5);



                                                                                                spawn4.play();



                                                                                                spawn4.setOnFinished(new EventHandler<ActionEvent>() {



                                                                                                    @Override



                                                                                                    public void handle(ActionEvent actionEvent) {



                                                                                                        spawn3.setCycleCount(2);



                                                                                                        spawn3.play();



                                                                                                        spawn3.setOnFinished(new EventHandler<ActionEvent>() {



                                                                                                            @Override



                                                                                                            public void handle(ActionEvent actionEvent) {



                                                                                                                spawn4.setCycleCount(4);



                                                                                                                spawn4.play();



                                                                                                                spawn4.setOnFinished(new EventHandler<ActionEvent>() {



                                                                                                                    @Override



                                                                                                                    public void handle(ActionEvent actionEvent) {



                                                                                                                        spawn3.setCycleCount(1);



                                                                                                                        spawn3.play();



                                                                                                                        spawn3.setOnFinished(new EventHandler<ActionEvent>() {



                                                                                                                            @Override



                                                                                                                            public void handle(ActionEvent actionEvent) {



                                                                                                                                spawn5.setCycleCount(6);



                                                                                                                                spawn5.play();



                                                                                                                                spawn5.setOnFinished(new EventHandler<ActionEvent>() {



                                                                                                                                    @Override



                                                                                                                                    public void handle(ActionEvent actionEvent) {



                                                                                                                                    }



                                                                                                                                });



                                                                                                                            }



                                                                                                                        });



                                                                                                                    }



                                                                                                                });



                                                                                                            }



                                                                                                        });



                                                                                                    }



                                                                                                });



                                                                                            }



                                                                                        });



                                                                                    }



                                                                                });



                                                                            }



                                                                        });



                                                                    }



                                                                });



                                                            }



                                                        });



                                                    }



                                                });



                                            }



                                        });



                                    }



                                });



                            }



                        });



                    }



                });





                p2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        int x = ((int) mouseEvent.getX());
                        int y = ((int) mouseEvent.getY());
                        if (!touractive.isEmpty()) {
                                Tour actTour = TourFactory.cree(touractive.get(0), x, y);
                                double h = actTour.getimage().getHeight();
                                double l = actTour.getimage().getWidth();
                                PixelReader pixel = carte.getzoneim().getPixelReader();
                                try {
                                    if (!(x>=30 && x<=80 && y>=670 && y<=720) && !(x>=100 && x<=150 && y>=670 && y<=720)) {
                                        if ((x - l / 2) > 0 && (x + l / 2) < 1376 && (y - h / 2) > 0 && (y + h / 2) < 736) {
                                            if (!pixel.getColor((int) (x - l / 2), (int) (y + h / 2)).equals(Color.BLACK)) {
                                                if (!pixel.getColor((int) (x + l / 2), (int) (y + h / 2)).equals(Color.BLACK)) {
                                                    if (!pixel.getColor((int) (x - l / 2), (int) (y - h / 2)).equals(Color.BLACK)) {
                                                        if (!pixel.getColor((int) (x + l / 2), (int) (y + h / 2)).equals(Color.BLACK)) {
                                                            if (!lestours.contains((new Point2D((int) (x - l / 2), (int) (y - h / 2)))) && !lestours.contains(new Point2D((int) (x - l / 2), (int) (y + h / 2))) && !lestours.contains(new Point2D((int) (x + l / 2), (int) (y - h / 2))) && !lestours.contains(new Point2D((int) (x + l / 2), (int) (y + h / 2)))) {
                                                                if (r.assezriche(actTour)) {
                                                                    for (int i = 0; i < l; i++) {
                                                                        for (int j = 0; j < h; j++) {
                                                                            Point2D point = new Point2D(x - l / 2 + i, y - h / 2 + j);
                                                                            lestours.add(point);
                                                                        }
                                                                    }
                                                                    r.achetetour(actTour);
                                                                    actTour.getimageview().relocate(x - l / 2, y - h / 2);
                                                                    p2.getChildren().add(actTour.getimageview());
                                                                    actTour.afficheinfo(p2);
                                                                    actTour.go(listenoir, p2, r, erreur);
                                                                } else {
                                                                    erreur.setText("Vous n'avez pas assez d'or");
                                                                    Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                                                                        @Override
                                                                        public void handle(ActionEvent actionEvent) {
                                                                            erreur.setText("");
                                                                        }
                                                                    }));
                                                                    erreurgo.setCycleCount(1);
                                                                    erreurgo.play();
                                                                }
                                                            } else if (!lestours.contains(new Point2D(x, y))) {
                                                                erreur.setText("Vous ne pouez pas construire de tour a cet endroit");
                                                                Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                                                                    @Override
                                                                    public void handle(ActionEvent actionEvent) {
                                                                        erreur.setText("");
                                                                    }
                                                                }));
                                                                erreurgo.setCycleCount(1);
                                                                erreurgo.play();
                                                            }
                                                        } else {
                                                            erreur.setText("Vous ne pouez pas construire de tour a cet endroit");
                                                            Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                                                                @Override
                                                                public void handle(ActionEvent actionEvent) {
                                                                    erreur.setText("");
                                                                }
                                                            }));
                                                            erreurgo.setCycleCount(1);
                                                            erreurgo.play();
                                                        }
                                                    } else {
                                                        erreur.setText("Vous ne pouez pas construire de tour a cet endroit");
                                                        Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                                                            @Override
                                                            public void handle(ActionEvent actionEvent) {
                                                                erreur.setText("");
                                                            }
                                                        }));
                                                        erreurgo.setCycleCount(1);
                                                        erreurgo.play();
                                                    }
                                                } else {
                                                    erreur.setText("Vous ne pouez pas construire de tour a cet endroit");
                                                    Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                                                        @Override
                                                        public void handle(ActionEvent actionEvent) {
                                                            erreur.setText("");
                                                        }
                                                    }));
                                                    erreurgo.setCycleCount(1);
                                                    erreurgo.play();
                                                }
                                            } else {
                                                erreur.setText("Vous ne pouez pas construire de tour a cet endroit");
                                                Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        erreur.setText("");
                                                    }
                                                }));
                                                erreurgo.setCycleCount(1);
                                                erreurgo.play();
                                            }
                                        } else {
                                            erreur.setText("Vous ne pouez pas construire de tour a cet endroit");
                                            Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent actionEvent) {
                                                    erreur.setText("");
                                                }
                                            }));
                                            erreurgo.setCycleCount(1);
                                            erreurgo.play();
                                        }
                                    }
                                } catch (IndexOutOfBoundsException e) {
                                    erreur.setText("Vous ne pouez pas construire de tour a cet endroit");
                                    Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            erreur.setText("");
                                        }
                                    }));
                                    erreurgo.setCycleCount(1);
                                    erreurgo.play();
                                }
                            }
                        else{
                            erreur.setText("Sélectioner une tour");
                            Timeline erreurgo = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    erreur.setText("");
                                }
                            }));
                            erreurgo.setCycleCount(1);
                            erreurgo.play();
                        }
                    }
                });
            }

            public Path createPath() {



                Path chemin = new Path();



                MoveTo spawn = new MoveTo(1163, 700);



                LineTo ligne1 = new LineTo(1163, 162);



                LineTo ligne2 = new LineTo(813, 162);



                LineTo ligne3 = new LineTo(813, 569);



                LineTo ligne4 = new LineTo(270, 569);



                LineTo ligne5 = new LineTo(270, 215);



                chemin.getElements().addAll(spawn, ligne1, ligne2, ligne3, ligne4, ligne5);



                return chemin;



            }







            public void createenemmi(Path chemin, Base b, Pane p, ArrayList<Ennemi> list, Ressource r, ArrayList<String> types) {



                Random random = new Random();



                int nb = random.nextInt(types.size())+1;



                Ennemi e = ennemiFactory.cree(nb);



                e.afficheinfo(p);



                list.add(e);



                e.deplacement(e, chemin, b, p, list, r);



            }



        }));

    }



    public static void main(String[] args) {







        launch(args);



    }



}
