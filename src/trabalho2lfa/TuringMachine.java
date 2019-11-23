/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2lfa;

import Model.TuringMachineState;
import Model.TuringMachineSymbol;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class TuringMachine {

    public static ArrayList<TuringMachineState> createTuringMachine(String phrase) {
        String array[] = phrase.split("\n");
        ArrayList<TuringMachineState> allStates = new ArrayList<>();
        TuringMachineState createState = null;
        TuringMachineSymbol createSymbol;
        int lastState = -1;
        int initialState = 0;
        int finalState = 0;
        String arrayStatesFinals[] = null;
        boolean hasFinal = false;

        for (String s : array) {

            if (Character.isDigit(s.charAt(0))) {
                String arrayState[] = s.split(",");

                if (Integer.parseInt(arrayState[0]) != lastState) {
                    createState = new TuringMachineState(Integer.parseInt(arrayState[0]));
                    createSymbol = new TuringMachineSymbol(arrayState[1].charAt(0),
                            Integer.parseInt(arrayState[2]),
                            arrayState[3].charAt(0),
                            arrayState[4].charAt(0));
                    createState.addSymbol(createSymbol);
                    allStates.add(createState);
                } else {
                    createSymbol = new TuringMachineSymbol(arrayState[1].charAt(0),
                            Integer.parseInt(arrayState[2]),
                            arrayState[3].charAt(0),
                            arrayState[4].charAt(0));
                    createState.addSymbol(createSymbol);
                }
                lastState = Integer.parseInt(arrayState[0]);
            } else {
                if (s.charAt(0) == 'F') {
                    finalState = Integer.parseInt(s.substring(2));
                } else {
                    initialState = Integer.parseInt(s.substring(2));

                }
            }

        }
        for (TuringMachineState s : allStates) {
            if (s.getThisState() == finalState) {
                s.setIsFinal(true);
            }

            if (s.getThisState() == initialState) {
                s.setIsInitial(true);
            }
        }

        for (TuringMachineState s : allStates) {

            if (s.getThisState() == finalState) {
                hasFinal = true;
            }

        }
        if (!hasFinal) {
            createState = new TuringMachineState(finalState);
            createState.setIsFinal(true);
            allStates.add(createState);
        }
        return allStates;
    }
    

    public static void Run(String tape, ArrayList<TuringMachineState> states) {
       // char tapeArray[];
        ArrayList<Character> tapeArray = new ArrayList<>();
        boolean error;
        boolean end;
        char actualChar;
        int actualState;
        boolean hasState;
        boolean hasSymbol;
        char direction;
        char symbolToWrite;
        int actualTapeIndex;
        
        tapeArray = stringToCharArray(tapeArray, tape);
       
        error = false;
        end = false;
        hasState = false;
        hasSymbol = false;
        actualChar = tapeArray.get(0);
        actualState = isInitial(states);
        direction = ' ';
        symbolToWrite = ' ';
        actualTapeIndex = 0;
       
        while (!error && !end) {
            System.out.println("ESTADO " + actualState);
            for (TuringMachineState s : states) {
                actualChar = tapeArray.get(actualTapeIndex);
                if (s.getThisState() == actualState) {
                    if (hasState) {
                        break;
                    }
                    hasState = true;
                    for (TuringMachineSymbol symbol : s.getList()) {
                        if (symbol.getSymbol() == actualChar) {
                            actualState = symbol.getNextState();
                            direction =  symbol.getDirection();
                            symbolToWrite = symbol.getSymbolToWrite();
                            hasSymbol = true;
                            System.out.println("actualChar " +actualChar);
                        }

                    }
                }
            }
             if (!hasState || !hasSymbol) {
                error = true;
            }

            if (isFinal(states, actualState)) {
                end = true;
            }
            
       
            
            tapeArray.set(actualTapeIndex, symbolToWrite);
            
            if(direction == 'D'){
                actualTapeIndex++;
            }else{
                actualTapeIndex--;
            }
                 if(actualTapeIndex >= tapeArray.size()){
                tapeArray.add(actualTapeIndex, '?');
                
            }
            
            hasSymbol = false;
            hasState = false;
            System.out.println("String:" + tapeArray.toString());

           

        }
        
        if(end){
            JOptionPane.showMessageDialog(null, "Sentença reconhecida");
        }
        if(error){
            JOptionPane.showMessageDialog(null, "Sentença não reconhecida");
        }
        
        
    }

    public static int isInitial(ArrayList<TuringMachineState> states) {
        for (TuringMachineState s : states) {
            if (s.isIsInitial()) {
                return s.getThisState();
            }
        }
        return 0;
    }

    public static boolean isFinal(ArrayList<TuringMachineState> states, int actualState) {
        for (TuringMachineState s : states) {
            if (s.getThisState() == actualState) {
                if (s.isIsFinal()) {
                    return true;
                }
            }

        }
        return false;
    }
    
    public static  ArrayList<Character> stringToCharArray(ArrayList<Character> charArray,String words){
        for(int i = 0; i<words.length(); i++){
           charArray.add(words.charAt(i));
       }
       return charArray;
    }

}
