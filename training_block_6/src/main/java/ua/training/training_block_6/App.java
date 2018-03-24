package ua.training.training_block_6;

import ua.training.training_block_6.controller.Controller;
import ua.training.training_block_6.model.UserBookSampleFiller;
import ua.training.training_block_6.model.UserBook;
import ua.training.training_block_6.view.View;

/**
 * 
 * @author atpt34
 *
 */
public class App 
{
    public static void main( String[] args )    {
       UserBook userBook = new UserBook();
       new UserBookSampleFiller(userBook).fill();
       new Controller(userBook, new View()).processUser();
    }
}
