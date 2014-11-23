package com.nextbreakpoint.nextfractal.flux.grammar;

import org.antlr.v4.runtime.Token;

class ASTComplexOp extends ASTComplexExpression {
	private String op;
	private ASTComplexExpression exp1;
	private ASTComplexExpression exp2;

	public ASTComplexOp(Token location, String op, ASTComplexExpression exp) {
		super(location);
		this.op = op;
		this.exp1 = exp;
		this.exp2 = null;
	}

	public ASTComplexOp(Token location, String op, ASTComplexExpression exp1, ASTComplexExpression exp2) {
		super(location);
		this.op = op;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	public String getOp() {
		return op;
	}
	
	public ASTComplexExpression getExp1() {
		return exp1;
	}

	public ASTComplexExpression getExp2() {
		return exp2;
	}
}