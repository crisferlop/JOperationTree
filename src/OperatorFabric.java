/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author usuario dell
 */
public class OperatorFabric {
    public Operator createOperator(char operation, Operator left, Operator right){
        Operator operator = null;
        switch (operation) {
		case SyntaxArithmeticReader.PLUS:
			operator = new OperatorPlus(left,right);
			break;
		case SyntaxArithmeticReader.MINUS:
			operator = new OperatorMinus(left,right);
			break;
		case SyntaxArithmeticReader.MULTIPLY:
			operator = new OperatorMultiply(left,right);
			break;
		case SyntaxArithmeticReader.DIVIDE:
			operator = new OperatorDivide(left,right);
			break;
		case SyntaxArithmeticReader.EXPONENT:
			operator = new OperatorPower(left, right);
			break;
		case SyntaxArithmeticReader.ROOT:
			operator = new OperatorRoot(left, right);
			break;
		default:
			break;
		}
        if (operator == null){
        	throw  new BadSintaxException("Error: no se conoce el caracter \"" + operation + "\"");
        }
        return operator;
        
    }
    public Operator createSimpleOperator(int pdato){
        return new SimpleOperator(pdato);
    }
}
