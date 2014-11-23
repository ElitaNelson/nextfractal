package com.nextbreakpoint.nextfractal.flux.grammar;

import org.antlr.v4.runtime.Token;

class ASTRealOp extends ASTRealExpression {
	private String op;
	private ASTRealExpression exp1;
	private ASTRealExpression exp2;

	public ASTRealOp(Token location, String op, ASTRealExpression exp) {
		super(location);
		this.op = op;
		this.exp1 = exp;
		this.exp2 = null;
	}

	public ASTRealOp(Token location, String op, ASTRealExpression exp1, ASTRealExpression exp2) {
		super(location);
		this.op = op;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	public String getOp() {
		return op;
	}
	
	public ASTRealExpression getExp1() {
		return exp1;
	}

	public ASTRealExpression getExp2() {
		return exp2;
	}
}