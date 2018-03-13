package kpi.training;

import java.util.Scanner;

public class Controller {

    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model  = model;
        this.view = view;
    }

    public void processUser(){
        Scanner sc = new Scanner(System.in);
        
        view.printMessage(View.INPUT_DATA);
        while (!matchHelloWorld(sc)) {
            view.printMessage(View.WRONG_INPUT_DATA);
        }
        view.printMessage(Model.getHelloWorld());
    }

    private boolean matchHelloWorld(Scanner sc) {
        String hello = getWord(sc);
        String world = getWord(sc);
        return Model.verifyHelloWorld(hello, world);
    }

    private String getWord(Scanner sc) {
        return sc.next();
    }

}
