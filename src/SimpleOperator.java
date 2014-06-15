/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario dell
 */
public class SimpleOperator implements Operator{
    private int dato;
    private final static int priority = 0;
    @Override
    public SimpleOperator resolve() {
        return this;
    }
    public SimpleOperator(int pdato){
        dato = pdato;
    }
    public int getValue(){return dato;}
    public void setValue(int pdato){dato = pdato;}
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}
    
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(dato);
	}
}
