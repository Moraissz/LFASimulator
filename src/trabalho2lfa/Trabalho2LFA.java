/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2lfa;

import View.MainView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        MainView mainView = new MainView();
        mainView.setVisible(true);
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
                    } else if(actualChar == '\n'){
                        actualState = 0;
                        
                    } 
                    
                    else {
                        error = true;
                    }
                    break;
                case 6:
                    if (actualChar == '=') {
                        actualState = 7;
                    }else {
                        error = true;
                    }
                    break;
                case 7:
                    if (Character.isDigit(actualChar)) {
                        actualState = 8;
                    }else {
                        error = true;
                    }
                    break;
                case 8:
                    if (Character.isDigit(actualChar)) {
                        actualState = 8;
                    }else if(actualChar == ',') {
                        actualState = 7;
                    }else if(actualChar == '\n') {
                        actualState = 0;
                    }
                    else{
                        error = true;
                    }
                    break;
                case 9:
                    if (actualChar == '=') {
                        actualState = 10;
                    } else{
                        error = true;
                    }
                    break;
                case 10:
                    if (Character.isDigit(actualChar)) {
                        actualState = 11;
                    } else{
                        error = true;
                    }
                    break;
                case 11:
                    if (Character.isDigit(actualChar)) {
                        actualState = 11;
                    } else if(actualChar == '\n'){
                        actualState = 0;
                    } else{
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

}
