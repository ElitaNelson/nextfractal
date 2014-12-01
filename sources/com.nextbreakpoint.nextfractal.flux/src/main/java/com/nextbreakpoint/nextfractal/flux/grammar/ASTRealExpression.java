package com.nextbreakpoint.nextfractal.flux.grammar;

import org.antlr.v4.runtime.Token;

public abstract class ASTRealExpression extends ASTComplexExpression implements ASTExpression {
	public ASTRealExpression(Token location) {
		super(location);
	}

	public boolean isReal() {
		return true;
	}
}