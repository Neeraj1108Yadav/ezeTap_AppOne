package com.example.mathops;

public class PerformOperations {

    private static PerformOperations instance;
    private PerformOperations(){};
    private int RESULT_ADDITION = 102;
    private int RESULT_SUBTRACTION = 103;

    /*========== @ Lazy initialization ========*/
    public static PerformOperations getInstance(){
        if (instance == null){
            instance = new PerformOperations();
        }
        return instance;
    }

    public String addNumbers(){
        int num1 = 5;
        int num2 = 4;
        int result = num1+num2;
        return String.valueOf(result);
    }

    public String subtractNumbers(){
        int num1 = 5;
        int num2 = 4;
        int result = num1-num2;
        return String.valueOf(result);
    }

}
