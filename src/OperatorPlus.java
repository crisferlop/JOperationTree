/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author usuario dell
 */
public class OperatorPlus implements Operator{
    private Operator a,b;
    private final static int priority = 1;
    public OperatorPlus(Operator first, Operator second) {
        this.a = first;
        this.b = second;
    }
    
    @Override
    public SimpleOperator resolve() {
        return new SimpleOperator(a.resolve().getValue() + b.resolve().getValue());
    }

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return a.toString() + " + " + b.toString();
	}
    
}
