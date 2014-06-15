
public class OperatorRoot implements Operator {
	private final static int priority = 3;
	Operator a,b;
	public OperatorRoot(Operator rootIndex, Operator base) {
		b = rootIndex;
		a = base;
	}
	@Override
	public SimpleOperator resolve() {
		// TODO Auto-generated method stub
		int solution = a.resolve().getValue();
		if (solution > 0){
			double a = (double)1/(double)b.resolve().getValue();

			return new SimpleOperator((int)Math.pow(solution, a));
		}
		throw new ArithmeticException("Expresion matematica invalida: Base de raiz menor a cero");
		
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}


}
