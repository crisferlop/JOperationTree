
public class OperatorPower implements Operator{
	private final static int priority = 3;
	private Operator a,b;
	public OperatorPower(Operator base, Operator exponent) {
		// TODO Auto-generated constructor stub
		a = base;
		b = exponent;
	}
	@Override
	public SimpleOperator resolve() {
		// TODO Auto-generated method stub
		return new SimpleOperator((int)Math.pow(a.resolve().getValue(), b.resolve().getValue()));
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}

}
