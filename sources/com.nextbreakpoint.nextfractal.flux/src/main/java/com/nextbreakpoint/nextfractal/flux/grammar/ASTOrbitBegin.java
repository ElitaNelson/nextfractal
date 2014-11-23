package com.nextbreakpoint.nextfractal.flux.grammar;

import java.util.List;

import org.antlr.v4.runtime.Token;

class ASTOrbitBegin extends ASTObject {
	private List<ASTStatement> statements; 

	public ASTOrbitBegin(Token location) {
		super(location);
	}

	public List<ASTStatement> getStatements() {
		return statements;
	}
}