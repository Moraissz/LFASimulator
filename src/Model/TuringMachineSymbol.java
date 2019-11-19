/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author lucas
 */
public class TuringMachineSymbol {
    private char symbol;
    private int nextState;
    private char symbolToWrite;
    private char direction;

    public TuringMachineSymbol(char symbol, int nextState, char symbolToWrite, char direction) {
        this.symbol = symbol;
        this.nextState = nextState;
        this.symbolToWrite = symbolToWrite;
        this.direction = direction;
    }        
    

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public char getSymbolToWrite() {
        return symbolToWrite;
    }

    public void setSymbolToWrite(char symbolToWrite) {
        this.symbolToWrite = symbolToWrite;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    

}
