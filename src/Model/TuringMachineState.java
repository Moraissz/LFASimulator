/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class TuringMachineState {
        private int thisState;
    private ArrayList<TuringMachineSymbol> list; 
    private boolean isFinal;
      private boolean isInitial;
    
    public TuringMachineState(int thisState){
        this.thisState = thisState;
        list = new ArrayList<>();
        this.isFinal = false;
        this.isInitial = false;
    }
    
    public void addSymbol(TuringMachineSymbol s){
        list.add(s);
    }

    public int getThisState() {
        return thisState;
    }

    public void setThisState(int thisState) {
        this.thisState = thisState;
    }

    public ArrayList<TuringMachineSymbol> getList() {
        return list;
    }

    public void setList(ArrayList<TuringMachineSymbol> list) {
        this.list = list;
    }

    public boolean isIsFinal() {
        return isFinal;
    }

    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isIsInitial() {
        return isInitial;
    }

    public void setIsInitial(boolean isInitial) {
        this.isInitial = isInitial;
    }
    
    
    
    
}
