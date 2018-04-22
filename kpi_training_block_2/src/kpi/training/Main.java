package kpi.training;

public class Main {

    public static void main(String[] args) {
	    // Initialization
        Model model = Model.of(0, 100);
        View view = new View();
        Controller controller = new Controller(model, view);
        // Run
        controller.processUser();

    }


}
