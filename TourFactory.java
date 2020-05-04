package sample;

public class TourFactory {

    public static Tour cree(String type, int x, int y) {

        Tour res = getInstance(type, x, y);

        return res;

    }

    public static Tour getInstance(String type, int x, int y) {

        Tour res = null;

        switch (type){

            case "g": res = new Tourdegarde(x, y); break;

            case "m": res = new Tourdemarchand(x, y); break;

        }

        return res;

    }

}
