/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2lfa;

import Model.TuringMachineState;
import Model.TuringMachineSymbol;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class TuringMachine {

    public static void createTuringMachine(String phrase) {
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
        if(!hasFinal){
            createState = new TuringMachineState(finalState);
            createState.setIsFinal(true);
            allStates.add(createState);
        }
        
       
        
         
         
         

    }

    public void Run() {

    }

}
