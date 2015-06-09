package com.nextbreakpoint.nextfractal.mandelbrot.interpreter;

import static com.nextbreakpoint.nextfractal.mandelbrot.core.Expression.opNeg;

import java.util.Map;

import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompiledExpression;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerVariable;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.ExpressionContext;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Number;

public class InterpreterOperatorNeg implements CompiledExpression {
	private CompiledExpression exp;
	private int index;
	
	public InterpreterOperatorNeg(ExpressionContext context, CompiledExpression exp) {
		this.index = context.newNumberIndex();
		this.exp = exp;
	}

	@Override
	public double evaluateReal(InterpreterContext context, Map<String, CompilerVariable> scope) {
		return -exp.evaluateReal(context, scope);
	}

	@Override
	public Number evaluate(InterpreterContext context, Map<String, CompilerVariable> scope) {
		return opNeg(context.getNumber(index), exp.evaluate(context, scope));
	}

	@Override
	public boolean isReal() {
		return exp.isReal();
	}
}
