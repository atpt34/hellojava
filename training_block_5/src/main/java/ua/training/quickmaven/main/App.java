package ua.training.quickmaven.main;



import ua.training.quickmaven.controller.Controller;
import ua.training.quickmaven.model.UserBook;
import ua.training.quickmaven.view.View;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       new Controller(new UserBook(), new View()).processUser();
    }
}
