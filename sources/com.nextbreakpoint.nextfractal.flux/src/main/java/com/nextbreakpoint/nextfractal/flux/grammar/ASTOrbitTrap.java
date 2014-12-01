package com.nextbreakpoint.nextfractal.flux.grammar;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

public class ASTOrbitTrap extends ASTObjectImpl {
	private String name;
	private ASTComplex center;
	private List<ASTOrbitTrapOp> operators = new ArrayList<>(); 

	public ASTOrbitTrap(Token location, String name, ASTComplex center) {
		super(location);
		this.center = center;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public ASTComplex getCenter() {
		return center;
	}

	public void addOperator(ASTOrbitTrapOp operator) {
		operators.add(operator);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("name = ");
		builder.append(name);
		builder.append(",center = ");
		builder.append(center);
		builder.append(",operators = [");
		for (int i = 0; i < operators.size(); i++) {
			ASTOrbitTrapOp statement = operators.get(i);
			builder.append("{");
			builder.append(statement);
			builder.append("}");
			if (i < operators.size() - 1) {
				builder.append(",");
			}
		}
		builder.append("]");
		return builder.toString();
	}
}