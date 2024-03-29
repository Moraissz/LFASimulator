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

    public static String Run(String tape, ArrayList<TuringMachineState> states) {
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
        String reading;
        String actualStateS;
        String actualCharS;
        String showOnThePane;
        String firstPartOfReading;
        String untilTheEndOfReading;

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
        reading = "";
        actualStateS = "";
        showOnThePane = "";

        while (!error && !end) {
            actualChar = tapeArray.get(actualTapeIndex);
            actualStateS = "<span style=\"color:blue\" >q" + actualState + "</span>";
            actualCharS = "<span style=\"color:red\" >" + tapeArray.get(actualTapeIndex) + "</span>";
            reading = getStringRepresentation(tapeArray);

            firstPartOfReading = reading.substring(0, actualTapeIndex);
            untilTheEndOfReading = reading.substring(actualTapeIndex + 1, reading.length());
            showOnThePane = showOnThePane + firstPartOfReading + actualStateS + actualCharS + untilTheEndOfReading + "<br>";
            for (TuringMachineState s : states) {

                if (s.getThisState() == actualState) {
                    if (hasState) {
                        break;
                    }
                    hasState = true;
                    for (TuringMachineSymbol symbol : s.getList()) {
                        if (symbol.getSymbol() == actualChar) {
                            actualState = symbol.getNextState();
                            direction = symbol.getDirection();
                            symbolToWrite = symbol.getSymbolToWrite();
                            hasSymbol = true;
                            ;
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

            if (direction == 'D') {
                actualTapeIndex++;
            } else {
                actualTapeIndex--;
            }
            if (actualTapeIndex >= tapeArray.size()) {
                tapeArray.add(actualTapeIndex, '?');

            }

            hasSymbol = false;
            hasState = false;

        }
        showOnThePane = showOnThePane + reading + "<span style=\"color:blue\" >q" + actualState + "</span>" + "<br>";
        if (end) {
            JOptionPane.showMessageDialog(null, "Sentença reconhecida");
            showOnThePane = showOnThePane
                    + "------------------------------------------------"
                    + "<br>" + "SENTENCA RECONHECIDA" + "<br>"
                    + "------------------------------------------------" + "<br>";
        }
        if (error) {
            JOptionPane.showMessageDialog(null, "Sentença não reconhecida");
            showOnThePane = showOnThePane
                    + "------------------------------------------------" + "<br>" + "SENTENCA NAO RECONHECIDA"
                    + "<br>" + "------------------------------------------------" + "<br>";
        }
        return showOnThePane;

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

    public static ArrayList<Character> stringToCharArray(ArrayList<Character> charArray, String words) {
        for (int i = 0; i < words.length(); i++) {
            charArray.add(words.charAt(i));
        }
        return charArray;
    }

    public static String getStringRepresentation(ArrayList<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for (Character ch : list) {
            builder.append(ch);
        }
        return builder.toString();
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
            System.out.println("Simbolo " + actualChar + "\n");
            System.out.println("Estado " + actualState + "\n");
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
                    int i = actualChar;
                    System.out.println(i);
                    if (actualChar >= 33 && actualChar < 127) {
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
                    } else if (actualChar == ',') {
                        actualState = 12;

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
                case 12:
                    if (actualChar >=33 && actualChar < 127) {
                        actualState = 13;
                    } else {
                        error = true;
                    }
                    break;
                case 13:
                    if (actualChar == ',') {
                        actualState = 14;
                    } else {
                        error = true;
                    }
                    break;
                case 14:
                    if (actualChar == 'E' || actualChar == 'D') {
                        actualState = 15;
                    } else {
                        error = true;
                    }
                    break;
                case 15:
                    if (actualChar == '\n') {
                        actualState = 0;
                    } else {
                        error = true;
                    }

            }
            actualIndex++;
        }

        if ((actualIndex == phrase.length() && actualState == 0) && !error) {
            return true;
        } else {
            return false;
        }
    }

}
