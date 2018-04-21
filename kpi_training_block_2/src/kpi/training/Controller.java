package kpi.training;

import java.util.EnumMap;
import java.util.Scanner;

public class Controller {
    
    private Model model;
    private View view;
    
    private EnumMap<Model.Result, Response> mapResults;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        mapResults = new EnumMap<>(Model.Result.class);
        mapResults.put(Model.Result.HIT, new Controller.HitResponse());
        mapResults.put(Model.Result.MORE, new MoreResponse());
        mapResults.put(Model.Result.LESS, new LessResponse());
    }

    public void processUser(){
        Scanner sc = new Scanner(System.in);            
        Response response;
        do {
            int value = inputIntValueWithScannerAndRange(sc);
            response = mapResults.get(model.processValue(value));
            response.printMessage();
        } while(!response.stop());
        outputStatistic();
    }
    
    private abstract class Response {
        abstract void printMessage();
        boolean stop() { return false; }
    }
    private class HitResponse extends Response {
        @Override
        public void printMessage() {
            view.printMessage(View.HIT_VALUE);
        }
        @Override
        public boolean stop() {
            return true;
        }       
    }
    private class MoreResponse extends Response {
        @Override
        public void printMessage() {
            view.printMessage(View.MORE_VALUE);
        }        
    }
    private class LessResponse extends Response {
        @Override
        public void printMessage() {
            view.printMessage(View.LESS_VALUE);
        }        
    }

    private void outputStatistic() {
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
