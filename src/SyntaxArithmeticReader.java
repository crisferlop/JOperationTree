
/**
 * 
 * @author cristianrivera
 *
 */

import controlStructure.Stack;


class SyntaxHelper{
	protected int count;
	protected Operator operator;
	public SyntaxHelper(int pcount, Operator poperator) {
		// TODO Auto-generated constructor stub
		count = pcount;
		operator = poperator;
	}
	public int getCount() {
		return count;
	}
	public Operator getOperator(){
		return operator;
	}
	
}

class BufferNum{
	int num;
	int count;
	
	public BufferNum(int pnum, int pcount) {
		num = pnum;
		count = pcount;
	}
}
public class SyntaxArithmeticReader {
	

	Operator head;
	public static final char PLUS = '+';
	public static final char EXPONENT = '^';
	public static final char ROOT = 'r';
	public static final char MINUS = '-';
	public static final char DIVIDE = '/';
	public static final char MULTIPLY = '*';
	public static final char BRACKETS_OPEN = '(';
	public static final char BRACKETS_CLOSE = ')';
	private final OperatorFabric fabric = new OperatorFabric();
	
	
	public SyntaxArithmeticReader(String expression) {
		if (expression != null && !expression.equals("")){
			char a = expression.charAt(expression.length()-1);
			if (isNumber(a) || a == ')'){
				head = readExpression(0,expression).getOperator();
			}
			else{throw new BadSintaxException("token final no valido");}
		}
		else{
			throw new BadSintaxException("Error: expresion invalida");
		}
	}
	
	private BufferNum intBuffer(int count, String expression){
		String tmp;
		char datoLeido;
		int datoSimple = 0;
		for(;count < expression.length(); count++){
			datoLeido = expression.charAt(count);
			tmp = String.format("%s",datoLeido);
			if ("0123456789".contains(tmp)){datoSimple *= 10;datoSimple += new Integer(tmp);}
			else{break;}
		}
		return new BufferNum(datoSimple, count);
	}
	
	
	private SyntaxHelper bufferNum(int count, String expression){
			BufferNum buffer = intBuffer(count,expression);
			SyntaxHelper sintaxhelper  = new SyntaxHelper(buffer.count, fabric.createSimpleOperator(buffer.num));
			return sintaxhelper;
	}

	
	/**
	 * Existe problemas con los numeros negativos
	 */
	private SyntaxHelper readExpression(int count, String expression){
		Stack<Operator> operatores = new Stack<>();
		Stack<Character> operations = new Stack<>();
		SyntaxHelper sh;
		char operation;
		BufferNum bn;
		boolean lastIsOperation = true;
		Operator oper1, oper2;
		for(; count< expression.length();){
			operation = expression.charAt(count);
			if (isValidOperation(operation)){
				if(lastIsOperation){
					if(operation == '-'){
						bn = intBuffer(count+1, expression);
						count=bn.count;
						operatores.push(fabric.createSimpleOperator(bn.num*-1));
						lastIsOperation = false;
					}
					else{
						throw new BadSintaxException("Sintaxis erronea!");
					}
				}
				else if (!operations.isEmpty() && compare(operations.peek(),operation)){
					while(!operations.isEmpty() && compare(operations.peek(),operation)){
						oper2 = operatores.pop();
						oper1 = operatores.pop();
						operatores.push(fabric.createOperator(operations.pop(), oper1, oper2));
					}
					operations.push(operation);
					count++;
					lastIsOperation = true;
				}
				else{
					operations.push(operation);
					count++;
					lastIsOperation = true;
				}
			}
			else if (operation == '('){
				sh = readExpression(count+1, expression);
				count = sh.getCount();
				operatores.push(sh.getOperator());
				lastIsOperation = false;
			}
			else if(operation == ')'){
				if (lastIsOperation){
					throw new BadSintaxException("Operador " + operations.peek() + " no tiene sentancia a la derecha, choca con \")\"" );
				}
				operatores.push(subEvaluation(operatores, operations));
				return new SyntaxHelper(count+1, operatores.pop());
			}
			else if(isNumber(operation)){
				sh = bufferNum(count, expression);
				count = sh.getCount();
				operatores.push(sh.operator);
				lastIsOperation = false;
			}
		}
		if (!operations.isEmpty()){
			operatores.push(subEvaluation(operatores, operations));
		}
		return new SyntaxHelper(count, operatores.pop());
	}
	
	private boolean isValidOperation(char operation){
		return operation == '+' | operation == '-' | operation == '*' | operation == '/' | operation == '^' | operation == 'r';
		
	}
	
	private Operator subEvaluation(Stack<Operator> operatores, Stack<Character> operations){
		if(!operations.isEmpty()){
			Operator oper1, oper2;
			while(!operations.isEmpty()){
				oper2 = operatores.pop();
				oper1 = operatores.pop();
				operatores.push(fabric.createOperator(operations.pop(), oper1, oper2));
			}
		}
		return operatores.pop();
	}
	
	private boolean isNumber(char number){
		return number == '0' | number == '1' | number == '2' | number == '3' | number == '4' | number == '5' | number == '6' | number == '7' | number == '8' | number == '9';
	}
	
	/**
	 * es mayor a que b
	 */
	private boolean compare(char oper1, char oper2){
		Operator operator1 = fabric.createOperator(oper1, null, null);
		Operator operator2 = fabric.createOperator(oper2, null, null);
		return operator1.getPriority() > operator2.getPriority(); 
	}
	public int resolve(){
		return head.resolve().getValue();
	} 

	public static void main(String[] args) {
		SyntaxArithmeticReader sr = new SyntaxArithmeticReader("(2r4)r128");
		System.out.println("Listo!");
		System.out.println(sr.resolve());
		
	}

}
