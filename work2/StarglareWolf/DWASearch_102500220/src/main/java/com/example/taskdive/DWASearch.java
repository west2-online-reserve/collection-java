package com.example.taskdive;

public class DWASearch {
    public static void main(String[] args) {
        try{
            TaskRunner.run(args);
        }
        catch (InvalidInputException e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch(Exception e){
            System.err.println("Unexpected Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
