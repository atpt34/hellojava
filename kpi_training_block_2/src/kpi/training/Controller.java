package kpi.training;

import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

import kpi.training.Model.Result;

public class Controller {
    
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;        
    }
    
    private final class ResultMapHolder {
        private final Map<Model.Result, Response> mapResults;
        ResultMapHolder() {
            mapResults = new EnumMap<>(Model.Result.class);
            mapResults.put(Model.Result.HIT, new HitResponse());
            mapResults.put(Model.Result.MORE, new MoreResponse());
            mapResults.put(Model.Result.LESS, new LessResponse());
        }
    }

    public void processUser(){
        Scanner sc = new Scanner(System.in);
        Map<Result, Response> mapResults = new ResultMapHolder().mapResults;
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
    private final class HitResponse extends Response {
        @Override
        public void printMessage() {
            view.printMessage(View.HIT_VALUE);
        }
        @Override
        public boolean stop() {
            return true;
        }       
    }
    private final class MoreResponse extends Response {
        @Override
        public void printMessage() {
            view.printMessage(View.MORE_VALUE);
        }        
    }
    private final class LessResponse extends Response {
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

        while (!model.checkValueInRange(value)) {
            view.printMessage(View.WRONG_RANGE_DATA
                    + View.INPUT_INT_DATA);
            value = inputIntValueWithScanner(sc);
        }
        return value;
    }

}
