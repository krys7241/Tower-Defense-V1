package sample;

public class EnnemiFactory {

    public Ennemi cree(int type) {

        Ennemi res = getInstance(type);

        return res;

    }

    public Ennemi getInstance(int type) {

        Ennemi res = null;

        switch (type){

            case 1: res = new Ennemi1(); break;

            case 2: res = new Ennemi2(); break;

            case 3: res = new Ennemi3(); break;

        }

        return res;

    }

}
