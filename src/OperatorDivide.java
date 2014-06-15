/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario dell
 */
public class OperatorDivide implements Operator{
        private Operator a,b;
        private final static int priority = 2;
        
        
        public OperatorDivide(Operator first, Operator second) {
        this.a = first;
        this.b = second;
    }
        
        
    @Override
    public SimpleOperator resolve() {
    	int solution = b.resolve().getValue();
    	if (solution != 0){
    		return new SimpleOperator(a.resolve().getValue() / b.resolve().getValue());
    	}
        throw new ArithmeticException("Expresion matematica invalida: division por cero");
    }
    
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}
}
