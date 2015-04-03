package com.nextbreakpoint.nextfractal.mandelbrot.grammar;

import org.antlr.v4.runtime.Token;

public class ASTFunction extends ASTExpression {
	private String name;
	private ASTExpression[] arguments;

	public ASTFunction(Token location, String name, ASTExpression[] arguments) {
		super(location);
		this.name = name;
		this.arguments = arguments;
	}

	public ASTFunction(Token location, String name, ASTExpression argument) {
		this(location, name, new ASTExpression[] { argument });
	}

	public String getName() {
		return name;
	}

	public ASTExpression[] getArguments() {
		return arguments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append("(");
		for (int i = 0; i < arguments.length; i++) {
			builder.append(arguments[i]);
			if (i < arguments.length - 1) {
				builder.append(",");
			}
		}
		builder.append(")");
		return builder.toString();
	}

	@Override
	public void compile(ASTExpressionCompiler compiler) {
		compiler.compile(this);
	}

	@Override
	public boolean isReal() {
		if (name.equals("mod") || name.equals("mod2") || name.equals("pha") || name.equals("log") || name.equals("exp") || name.equals("atan2") || name.equals("hypot") || name.equals("sqrt") || name.equals("re") || name.equals("im")) {
			return true;
		}
		for (ASTExpression argument : arguments) {
			if (!argument.isReal()) {
				return false;
			}
		}
		return true;
	}
}