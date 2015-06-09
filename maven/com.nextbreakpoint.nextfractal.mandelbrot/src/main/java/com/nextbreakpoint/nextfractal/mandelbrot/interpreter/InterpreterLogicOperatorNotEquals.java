package com.nextbreakpoint.nextfractal.mandelbrot.interpreter;

import java.util.Map;

import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompiledCondition;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompiledExpression;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerVariable;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.ExpressionContext;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.InterpreterContext;

public class InterpreterLogicOperatorNotEquals implements CompiledCondition {
	private CompiledExpression[] operands;
	
	public InterpreterLogicOperatorNotEquals(ExpressionContext context, CompiledExpression[] operands) {
		this.operands = operands;
	}

	@Override
	public boolean evaluate(InterpreterContext context, Map<String, CompilerVariable> scope) {
		return operands[0].evaluateReal(context, scope) != operands[1].evaluateReal(context, scope);
	}
}
