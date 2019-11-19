/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2lfa;

import Model.State;
import Model.Symbol;
import View.MainView;
import View.TuringMachineView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class Trabalho2LFA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //MainView mainView = new MainView();
        //mainView.setVisible(true);
        TuringMachineView  turingMachineView = new TuringMachineView();
        turingMachineView.setVisible(true);
    }

    public static boolean isValid(String phrase) {
        char actualChar;
        boolean error;
        int actualIndex;
        int actualState;

        actualState = 0;
        actualIndex = 0;
        error = false;

        while (actualIndex < phrase.length() && !error) {
            actualChar = phrase.charAt(actualIndex);

            switch (actualState) {
                case 0:
                    if (actualChar == 'F') {
                        actualState = 6;
                    } else if (actualChar == 'I') {
                        actualState = 9;
                    } else if (Character.isDigit(actualChar)) {
                        actualState = 1;
                    } else {
                        error = true;
                    }
                    break;
                case 1:
                    if (Character.isDigit(actualChar)) {
                        actualState = 1;
                    } else if (actualChar == ',') {
                        actualState = 2;
                    } else {
                        error = true;
                    }
                    //Falta q12, não entendi
                    break;
                case 2:
                    if (Character.isLetter(actualChar)) {
                        actualState = 3;
                    } else {
                        error = true;
                    }
                    break;
                case 3:
                    if (actualChar == ',') {
                        actualState = 4;
                    } else {
                        error = true;
                    }
                    break;
                case 4:
                    if (Character.isDigit(actualChar)) {
                        actualState = 5;
                    } else {
                        error = true;
                    }
                    break;
                case 5:
                    if (Character.isDigit(actualChar)) {
                        actualState = 5;
                    } else if (actualChar == '\n') {
                        actualState = 0;

                    } else {
                        error = true;
                    }
                    break;
                case 6:
                    if (actualChar == '=') {
                        actualState = 7;
                    } else {
                        error = true;
                    }
                    break;
                case 7:
                    if (Character.isDigit(actualChar)) {
                        actualState = 8;
                    } else {
                        error = true;
                    }
                    break;
                case 8:
                    if (Character.isDigit(actualChar)) {
                        actualState = 8;
                    } else if (actualChar == ',') {
                        actualState = 7;
                    } else if (actualChar == '\n') {
                        actualState = 0;
                    } else {
                        error = true;
                    }
                    break;
                case 9:
                    if (actualChar == '=') {
                        actualState = 10;
                    } else {
                        error = true;
                    }
                    break;
                case 10:
                    if (Character.isDigit(actualChar)) {
                        actualState = 11;
                    } else {
                        error = true;
                    }
                    break;
                case 11:
                    if (Character.isDigit(actualChar)) {
                        actualState = 11;
                    } else if (actualChar == '\n') {
                        actualState = 0;
                    } else {
                        error = true;
                    }
                    break;

            }
            actualIndex++;
        }

        if ((actualIndex == phrase.length() && actualState == 0) && !error) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<State> createAutomato(String phrase) {
        String array[] = phrase.split("\n");
        ArrayList<State> allStates = new ArrayList<>();
        State createState = null;
        Symbol createSymbol;
        int lastState = -1;
        int initialState = 0;
        String arrayStatesFinals[] = null;

        for (String s : array) {

            if (Character.isDigit(s.charAt(0))) {
                String arrayState[] = s.split(",");

                if (Integer.parseInt(arrayState[0]) != lastState) {
                    createState = new State(Integer.parseInt(arrayState[0]));
                    createSymbol = new Symbol(arrayState[1].charAt(0), Integer.parseInt(arrayState[2]));
                    createState.addSymbol(createSymbol);
                    allStates.add(createState);
                } else {
                    createSymbol = new Symbol(arrayState[1].charAt(0), Integer.parseInt(arrayState[2]));
                    createState.addSymbol(createSymbol);
                }

                lastState = Integer.parseInt(arrayState[0]);

            } else {
                if (s.charAt(0) == 'F') {
                    String justValues = s.replace("F=", "");
                    arrayStatesFinals = justValues.split(",");
                } else {
                    initialState = Character.getNumericValue(s.charAt(2));

                }
            }
        }

        for (State s : allStates) {
            for (int i = 0; i < arrayStatesFinals.length; i++) {
                if (s.getThisState() == Integer.parseInt(arrayStatesFinals[i])) {
                    s.setIsFinal(true);
                }
            }

            if (s.getThisState() == initialState) {
                s.setIsInitial(true);
            }
        }
        
        return allStates;

    }

    public static String isValidPhrase(String phrase, ArrayList<State> states) {

        int actualState = 0;
        int actualIndex;
        char actualChar;
        boolean hasState;
        boolean hasSymbol;
        boolean isFinal;
        boolean error;
        String reading;
        String actualStateS;
        String actualCharS;
        String showOnThePane;
        String firstPartOfReading;
        String untilTheEndOfReading;
        
        reading = phrase;
        error = false;
        hasState = false;
        hasSymbol = false;
        actualIndex = 0;
        actualStateS = "";
        actualState = Trabalho2LFA.isInitial(states);
        showOnThePane = "";
        System.out.println(reading.length());
        
        while (actualIndex < phrase.length() && !error) {
            actualChar = phrase.charAt(actualIndex);
            actualStateS = "<span style=\"color:blue\" >q" + actualState + "</span>";
            actualCharS = "<span style=\"color:red\" >" + reading.charAt(actualIndex) + "</span>";
  
                firstPartOfReading = reading.substring(0, actualIndex);

                
            untilTheEndOfReading = reading.substring(actualIndex+1, reading.length());
            showOnThePane = showOnThePane + firstPartOfReading + actualStateS + actualCharS + untilTheEndOfReading + "<br>";

            for (State s : states) {
                if (s.getThisState() == actualState) {
                    if (hasState) {
                        break;
                    }
                    hasState = true;
                    for (Symbol symbol : s.getList()) {
                        if (symbol.getSymbol() == actualChar) {
                            actualState = symbol.getNextState();
                            hasSymbol = true;
                        }

                    }
                }
            }
            if (!hasState || !hasSymbol) {
                error = true;
            }
            actualIndex++;
            hasSymbol = false;
            hasState = false;
        }

        isFinal = isFinal(states, actualState);
        showOnThePane = showOnThePane + reading +"<span style=\"color:blue\" >q" + actualState + "</span>"+ "<br>";
        
        if (error || !isFinal) {
            JOptionPane.showMessageDialog(null, "Sentença não reconhecida");
            showOnThePane = showOnThePane + 
                    "------------------------------------------------" + "<br>" +"SENTENCA NAO RECONHECIDA" + 
                    "<br>" + "------------------------------------------------" + "<br>";
        }

        if (isFinal) {
            JOptionPane.showMessageDialog(null, "Sentença reconhecida");
            showOnThePane = showOnThePane 
                    + "------------------------------------------------" +
                    "<br>" +"SENTENCA RECONHECIDA" + "<br>" 
                    + "------------------------------------------------" + "<br>";
        }

        return showOnThePane;
    }

    public static int isInitial(ArrayList<State> states) {
        for (State s : states) {
            if (s.isIsInitial()) {
                return s.getThisState();
            }
        }
        return 0;
    }

    public static boolean isFinal(ArrayList<State> states, int actualState) {
        for (State s : states) {
            if (s.getThisState() == actualState) {
                if (s.isIsFinal()) {
                    return true;
                }
            }

        }
        return false;
    }
    
   public static String AutomatoToGrammar(ArrayList<State> allStates){ 
        String symbols = null;
        String states = null;
        String retorno = "";
        ArrayList<String> grammar = new ArrayList<>();
        String sentence = "";
       
       for(State s: allStates){
           if(states == null){
               states = StateToChar(s, states);
           }else{
               if(!states.contains(String.valueOf(s.getThisState()))){
                   states = states + ", " + StateToChar(s, states);
               }
           }
           for(Symbol symbol: s.getList()){               
               sentence = StateToChar(s, states) + "->";
               if(symbols == null){
                   symbols = String.valueOf(symbol.getSymbol());
               } else{    
                   if(!symbols.contains(String.valueOf(symbol.getSymbol()))){
                       symbols = symbols + ", " +String.valueOf(symbol.getSymbol());
                   }                   
               }
               sentence = sentence + symbol.getSymbol() + StateToChar(GetStateBySymbol(allStates, symbol.getNextState()), states); 
               grammar.add(sentence); 
           }
           if(s.isIsFinal()){
               sentence = StateToChar(s, states) + "->?";
               grammar.add(sentence); 
           }           
       }
       for(String s: grammar){
           retorno = retorno + s + "\n"; 
       }
       retorno = "G = (VN, VT, P, S)\n" + "VN = (" + states + ")\nVT = (" + symbols + ")\nP = \n" + retorno;           
       
       return(retorno);       
   }
   
   public static String StateToChar(State state, String states){
       
       if (state.isIsInitial()){
           return "S";
       }
       
       switch (state.getThisState()){
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            case 9:
                return "I";
            case 10:
                return "J";
            case 11:
                return "K";
            case 12:
                return "L";
            case 13:
                return "M";
            case 14:
                return "N";
            case 15:
                return "O";      
            case 16:
                return "P";
            case 17:
                return "Q";
            case 18:
                return "R";
            case 19:
                return "T";
            case 20:
                return "U";
            case 21:
                return "V";
            case 22:
                return "W";
            case 23:
                return "X";
            case 24:
                return "Y";
            case 25:
                return "Z";
            case 0:
                return "Z";
       }
        return null;      
   }
   
   public static State GetStateBySymbol (ArrayList<State> allStates, int nextStateSymbol){
       for(State s: allStates){
           if(s.getThisState() == nextStateSymbol){
               return s;
           }
       }
       return null;
   }
   
   public static boolean isDeterministic (ArrayList<State> allStates){
       String[] symbols = getAllSymbols(allStates).split(", "); 
       String stateSymbols = null;

       for (State s : allStates){
           stateSymbols = "";
           for (Symbol symbol : s.getList()){
               stateSymbols = stateSymbols + symbol.getSymbol();
           }
           for(int i = 0; i <symbols.length; i++){
               if(!stateSymbols.contains(symbols[i])){
                   return false;
               }
           }
       }     
       return true;       
   } 
   
   public static String getAllSymbols (ArrayList<State> allStates){ 
        String symbols = null;
        for (State s : allStates){
            for (Symbol symbol : s.getList()){
               if(symbols == null){
                   symbols = String.valueOf(symbol.getSymbol());
               } else{    
                   if(!symbols.contains(String.valueOf(symbol.getSymbol()))){
                       symbols = symbols + ", " +String.valueOf(symbol.getSymbol());
                   }                   
               }
           }
        }       
        return symbols;
   }

}
