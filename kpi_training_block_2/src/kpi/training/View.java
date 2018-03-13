package kpi.training;

public class View {
    // Text's constants
    public static final String INPUT_INT_DATA = "Input INT value = ";
    
    public static final String INPUT_DOUBLE_DATA = "Input DOUBLE value = ";
    public static final String INPUT_STRING_DATA = "Input STRING value = ";
    public static final String WRONG_INPUT_DATA =
                                        "Wrong input! Repeat please! ";
    public static final String OUR_INT = "INT value = ";
    public static final String OUR_DOUBLE = "DOUBLE value = ";
    public static final String OUR_STRING = "STRING value = ";
    
    public static final String WRONG_RANGE_DATA = "Wrong range!";
    public static final String RANGE_DATA = "Range is ";
    public static final String LESS_VALUE = "Value is lesser!";
    public static final String MORE_VALUE = "Value is bigger!";
    public static final String HIT_VALUE = "Congrats! Value is guessed!";
    public static final String NUMBER_OF_MISSES = "You missed ";
    

    public void printMessage(String message){
        System.out.println(message);
    }

}
