package kpi.training;

import java.util.Scanner;

public class Controller {
    
    
    private Model model;
    private View view;

    // Constants

    // REGEX

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    //Work method
    public void processUser(){
        Scanner sc = new Scanner(System.in);
        playGame(sc);
    }

    private void playGame(Scanner sc) {
        model.initRandom();
        boolean end = false;
        while (!end) {
            int value = inputIntValueWithScannerAndRange(sc);
            switch(model.processValue(value)) {
            case HIT:
                view.printMessage(View.HIT_VALUE);
                end = true;
                break;
            case LESS:
                view.printMessage(View.LESS_VALUE);
                break;
            case MORE:
                view.printMessage(View.MORE_VALUE);
                break;
            default:
            }
        }
        outputStatistic(sc);
    }

    private void outputStatistic(Scanner sc) {
        view.printMessage(View.NUMBER_OF_MISSES + View.SPACE_STRING + model.getMissCount());
    }

    public int inputIntValueWithScanner(Scanner sc) {
        view.printMessage(View.INPUT_INT_DATA);
        while( ! sc.hasNextInt()) {
            view.printMessage(View.WRONG_INPUT_DATA + View.INPUT_INT_DATA);
            sc.next();
        }
        return sc.nextInt();
    }

    public int inputIntValueWithScannerAndRange(Scanner sc) {
        view.printMessage(View.RANGE_DATA +
                model.getRangeMin() + View.CHAR_COMMA + model.getRangeMax());
        int value = inputIntValueWithScanner(sc);

        while (!checkValueInRange(value)) {
            view.printMessage(View.WRONG_RANGE_DATA
                    + View.INPUT_INT_DATA);
            value = inputIntValueWithScanner(sc);
        }
        return value;
    }

    private boolean checkValueInRange(int val) {
        return model.checkValueInRange(val);
    }

}
