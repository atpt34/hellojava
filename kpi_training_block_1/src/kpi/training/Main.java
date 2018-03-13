package kpi.training;

import kpi.training.businesslogic.Model;

public class Main {

    public static void main(String[] args) {
        // Initialization
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        
        // Run
        controller.processUser();
    }

}
