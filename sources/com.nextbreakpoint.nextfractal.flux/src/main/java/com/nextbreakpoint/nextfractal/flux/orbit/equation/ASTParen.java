package com.nextbreakpoint.nextfractal.flux.orbit.equation;

import org.antlr.v4.runtime.Token;

class ASTParen extends ASTExpression {
	private ASTExpression expression;
	
	public ASTParen(Token location, ASTExpression expression) {
		super(location);
		this.expression = expression;
	}
}