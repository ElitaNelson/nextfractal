/*
 * NextFractal 1.0.5
 * https://github.com/nextbreakpoint/nextfractal
 *
 * Copyright 2015 Andrea Medeghini
 *
 * This file is part of NextFractal.
 *
 * NextFractal is an application for creating fractals and other graphics artifacts.
 *
 * NextFractal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NextFractal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NextFractal.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.nextbreakpoint.nextfractal.mandelbrot.compiler.javascript;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerError;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerErrorStrategy;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerReport;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerVariable;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.ExpressionContext;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerError.ErrorType;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerReport.Type;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTBuilder;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTColor;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTException;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTFractal;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTOrbit;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTOrbitBegin;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTOrbitEnd;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTOrbitLoop;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTOrbitTrap;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTOrbitTrapOp;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTPalette;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTPaletteElement;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTRule;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTStatement;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.MandelbrotLexer;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.MandelbrotParser;

public class JavaScriptSourceCompiler {
	private static final Logger logger = Logger.getLogger(JavaScriptSourceCompiler.class.getName());
	private final String packageName;
	private final String className;
	
	public JavaScriptSourceCompiler(String packageName, String className) {
		this.packageName = packageName;
		this.className = className;
	}
	
	public CompilerReport generateSource(String source) throws IOException {
		List<CompilerError> errors = new ArrayList<>();
		ASTFractal ast = parse(source, errors);
		if (errors.size() == 0) {
			ExpressionContext orbitContext = new ExpressionContext();
			String orbitSource = buildOrbit(orbitContext, ast, errors);
			ExpressionContext colorContext = new ExpressionContext();
			String colorSource = buildColor(colorContext, ast, errors);
			if (logger.isLoggable(Level.FINE)) {
				logger.fine(orbitSource);
				logger.fine(colorSource);
			}
			return new CompilerReport(ast, Type.JAVASCRIPT, orbitSource, colorSource, orbitContext, colorContext, errors);
		}
		return new CompilerReport(ast, Type.JAVASCRIPT, "", "", null, null, errors);
	}
	
	private ASTFractal parse(String source, List<CompilerError> errors) throws IOException {
		try {
			ANTLRInputStream is = new ANTLRInputStream(new StringReader(source));
			MandelbrotLexer lexer = new MandelbrotLexer(is);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			MandelbrotParser parser = new MandelbrotParser(tokens);
			parser.setErrorHandler(new CompilerErrorStrategy(errors));
			ParseTree fractalTree = parser.fractal();
            if (fractalTree != null) {
            	ASTBuilder builder = parser.getBuilder();
            	ASTFractal fractal = builder.getFractal();
            	return fractal;
            }
		} catch (ASTException e) {
			CompilerError error = new CompilerError(CompilerError.ErrorType.M_COMPILER, e.getLocation().getLine(), e.getLocation().getCharPositionInLine(), e.getLocation().getStartIndex(), e.getLocation().getStopIndex() - e.getLocation().getStartIndex(), e.getMessage());
			logger.log(Level.FINE, error.toString(), e);
			errors.add(error);
		} catch (Exception e) {
			CompilerError error = new CompilerError(CompilerError.ErrorType.M_COMPILER, 0L, 0L, 0L, 0L, e.getMessage());
			logger.log(Level.FINE, error.toString(), e);
			errors.add(error);
		}
		return null;
	}

	private String buildOrbit(ExpressionContext context, ASTFractal fractal, List<CompilerError> errors) {
		try {
			StringBuilder builder = new StringBuilder();
			Map<String, CompilerVariable> variables = new HashMap<>();
			compileOrbit(context, builder, variables, fractal);
			return builder.toString();
		} catch (ASTException e) {
			CompilerError error = new CompilerError(CompilerError.ErrorType.M_COMPILER, e.getLocation().getLine(), e.getLocation().getCharPositionInLine(), e.getLocation().getStartIndex(), e.getLocation().getStopIndex() - e.getLocation().getStartIndex(), e.getMessage());
			logger.log(Level.FINE, error.toString(), e);
			errors.add(error);
		}
		return "";
	}
	
	private String buildColor(ExpressionContext context, ASTFractal fractal, List<CompilerError> errors) {
		try {
			StringBuilder builder = new StringBuilder();
			Map<String, CompilerVariable> variables = new HashMap<>();
			compileColor(context, builder, variables, fractal);
			return builder.toString();
		} catch (ASTException e) {
			CompilerError error = new CompilerError(CompilerError.ErrorType.M_COMPILER, e.getLocation().getLine(), e.getLocation().getCharPositionInLine(), e.getLocation().getStartIndex(), e.getLocation().getStopIndex() - e.getLocation().getStartIndex(), e.getMessage());
			logger.log(Level.FINE, error.toString(), e);
			errors.add(error);
		}
		return "";
	}
	
	private String compileOrbit(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> variables, ASTFractal fractal) {
		buildOrbit(context, builder, variables, fractal);
		return builder.toString();
	}

	private String compileColor(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> variables, ASTFractal fractal) {
		buildColor(context, builder, variables, fractal);
		return builder.toString();
	}

	private void buildOrbit(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> scope, ASTFractal fractal) {
		if (fractal != null) {
			for (CompilerVariable var : fractal.getOrbitVariables()) {
				scope.put(var.getName(),  var);
			}
			for (CompilerVariable var : fractal.getStateVariables()) {
				scope.put(var.getName(),  var);
			}
			compile(context, builder, scope, fractal.getStateVariables(), fractal.getOrbit());
		}
	}

	private void buildColor(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> scope, ASTFractal fractal) {
		if (fractal != null) {
			for (CompilerVariable var : fractal.getColorVariables()) {
				scope.put(var.getName(),  var);
			}
			for (CompilerVariable var : fractal.getStateVariables()) {
				scope.put(var.getName(),  var);
			}
			compile(context, builder, scope, fractal.getStateVariables(), fractal.getColor());
		}
	}

	private void compile(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> scope, Collection<CompilerVariable> stateVariables, ASTOrbit orbit) {
		builder.append("function render() {\n");
		if (orbit != null) {
			builder.append("var n = 0;\n");
			builder.append("var x = orbit.getX();\n");
			builder.append("var w = orbit.getW();\n");
//			for (CompilerVariable var : stateVariables) {
//				builder.append("orbit.addVariable(");
//				builder.append(var.getName());
//				builder.append(");\n");
//			}
			for (ASTOrbitTrap trap : orbit.getTraps()) {
				builder.append("orbit.addTrap(trap");
				builder.append(trap.getName().toUpperCase().substring(0, 1));
				builder.append(trap.getName().substring(1));
				builder.append(");\n");
			}
		}
		for (CompilerVariable var : scope.values()) {
			scope.put(var.getName(), var);
			if (var.isCreate()) {
				if (var.isReal()) {
					builder.append("var ");
					builder.append(var.getName());
					builder.append(" = 0.0;\n");
				} else {
					builder.append("var ");
					builder.append(var.getName());
					builder.append(" = orbit.getNumber(");
					builder.append(context.newNumberIndex());
					builder.append(").set(0.0,0.0);\n");
				}
			}
		}
		if (orbit != null) {
			for (ASTOrbitTrap trap : orbit.getTraps()) {
				compile(context, builder, scope, trap);
			}
		}
		if (orbit != null) {
			compile(context, builder, scope, orbit.getBegin(), stateVariables);
			Map<String, CompilerVariable> vars = new HashMap<String, CompilerVariable>(scope);
			compile(context, builder, vars, orbit.getLoop(), stateVariables);
			compile(context, builder, scope, orbit.getEnd(), stateVariables);
		}
		int i = 0;
		for (CompilerVariable var : stateVariables) {
			builder.append("orbit.setVariable(");
			builder.append(i++);
			builder.append(",");
			builder.append(var.getName());
			builder.append(");\n");
		}
		builder.append("}\n");
		builder.append("render();\n");
	}

	private void compile(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> scope, Collection<CompilerVariable> stateVariables, ASTColor color) {
		for (CompilerVariable var : stateVariables) {
			scope.put(var.getName(), var);
		}
		if (color != null) {
			for (ASTPalette palette : color.getPalettes()) {
				compile(context, builder, scope, palette);
			}
		}
		builder.append("function render() {\n");
		int i = 0;
		for (CompilerVariable var : stateVariables) {
			if (var.isReal()) {
				builder.append("var ");
				builder.append(var.getName());
				builder.append(" = color.getRealVariable(");
				builder.append(i++);
				builder.append(");\n");
			} else {
				builder.append("var ");
				builder.append(var.getName());
				builder.append(" = color.getVariable(");
				builder.append(i++);
				builder.append(");\n");
			}
		}
		i = 0;
		for (CompilerVariable var : scope.values()) {
			if (!stateVariables.contains(var)) {
				if (var.isReal()) {
					builder.append("var ");
					builder.append(var.getName());
					builder.append(" = 0;\n");
				} else {
					builder.append("var ");
					builder.append(var.getName());
					builder.append(" = color.getNumber(");
					builder.append(i++);
					builder.append(");\n");
				}
			}
		}
		if (color != null) {
			builder.append("color.setColor(color.color(");
			builder.append(color.getArgb().getComponents()[0]);
			builder.append(",");
			builder.append(color.getArgb().getComponents()[1]);
			builder.append(",");
			builder.append(color.getArgb().getComponents()[2]);
			builder.append(",");
			builder.append(color.getArgb().getComponents()[3]);
			builder.append("));\n");
			if (color.getInit() != null) {
				for (ASTStatement statement : color.getInit().getStatements()) {
					compile(context, builder, scope, statement);
				}
			}
			for (ASTRule rule : color.getRules()) {
				compile(context, builder, scope, rule);
			}
		}
		builder.append("}\n");
		builder.append("render();\n");
	}

	private void compile(ExpressionContext context, StringBuilder builder,	Map<String, CompilerVariable> variables, ASTRule rule) {
		builder.append("if (");
		rule.getRuleExp().compile(new JavaScriptExpressionCompiler(context, variables, builder));
		builder.append(") {\n");
		builder.append("color.addColor(");
		builder.append(rule.getOpacity());
		builder.append(",");
		rule.getColorExp().compile(new JavaScriptExpressionCompiler(context, variables, builder));
		builder.append(");\n}\n");
	}

	private void compile(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> variables, ASTPalette palette) {
		builder.append("var palette");
		builder.append(palette.getName().toUpperCase().substring(0, 1));
		builder.append(palette.getName().substring(1));
		builder.append(" = color.palette()");
		for (ASTPaletteElement element : palette.getElements()) {
			builder.append(".add(");
			compile(context, builder, variables, element);
			builder.append(")");
		}
		builder.append(".build();\n");
	}

	private void compile(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> variables, ASTPaletteElement element) {
		builder.append("color.element(");
		builder.append(createArray(element.getBeginColor().getComponents()));
		builder.append(",");
		builder.append(createArray(element.getEndColor().getComponents()));
		builder.append(",");
		builder.append(element.getSteps());
		builder.append(",(start, end, step) -> { return ");
		if (element.getExp() != null) {
			if (element.getExp().isReal()) {
				element.getExp().compile(new JavaScriptExpressionCompiler(context, variables, builder));
			} else {
				throw new ASTException("Expression type not valid: " + element.getLocation().getText(), element.getLocation());
			}
		} else {
			builder.append("step / (end - start)");
		}
		builder.append(";})");
	}

	private void compile(ExpressionContext context, StringBuilder builder,	Map<String, CompilerVariable> variables, ASTOrbitTrap trap) {
		builder.append("var trap");
		builder.append(trap.getName().toUpperCase().substring(0, 1));
		builder.append(trap.getName().substring(1));
		builder.append(" = orbit.trap(exp.number(");
		builder.append(trap.getCenter());
		builder.append("))");
		for (ASTOrbitTrapOp operator : trap.getOperators()) {
			builder.append(".");
			switch (operator.getOp()) {
				case "MOVETO":
					builder.append("moveTo");
					break;

				case "MOVETOREL":
					builder.append("moveToRel");
					break;

				case "LINETO":
					builder.append("lineTo");
					break;

				case "LINETOREL":
					builder.append("lineToRel");
					break;

				case "ARCTO":
					builder.append("arcTo");
					break;

				case "ARCTOREL":
					builder.append("arcToRel");
					break;

				case "QUADTO":
					builder.append("quadTo");
					break;

				case "QUADTOREL":
					builder.append("quadToRel");
					break;

				case "CURVETO":
					builder.append("curveTo");
					break;

				case "CURVETOREL":
					builder.append("curveToRel");
					break;

				default:
					break;
			}
			builder.append("(");
			if (operator.getC1().isReal()) {
				builder.append("exp.number(");
				operator.getC1().compile(new JavaScriptExpressionCompiler(context, variables, builder));
				builder.append(")");
			} else {
				operator.getC1().compile(new JavaScriptExpressionCompiler(context, variables, builder));
			}
			if (operator.getC2() != null) {
				builder.append(",");
				if (operator.getC2().isReal()) {
					builder.append("exp.number(");
					operator.getC2().compile(new JavaScriptExpressionCompiler(context, variables, builder));
					builder.append(")");
				} else {
					operator.getC2().compile(new JavaScriptExpressionCompiler(context, variables, builder));
				}
			}
			if (operator.getC3() != null) {
				builder.append(",");
				if (operator.getC3().isReal()) {
					builder.append("exp.number(");
					operator.getC3().compile(new JavaScriptExpressionCompiler(context, variables, builder));
					builder.append(")");
				} else {
					operator.getC3().compile(new JavaScriptExpressionCompiler(context, variables, builder));
				}
			}
			builder.append(")");
		}
		builder.append(";\n");
	}

	private void compile(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> variables, ASTOrbitBegin begin, Collection<CompilerVariable> stateVariables) {
		if (begin != null) {
			for (ASTStatement statement : begin.getStatements()) {
				compile(context, builder, variables, statement);
			}
		}
	}

	private void compile(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> variables, ASTOrbitEnd end, Collection<CompilerVariable> stateVariables) {
		if (end != null) {
			for (ASTStatement statement : end.getStatements()) {
				compile(context, builder, variables, statement);
			}
		}
	}

	private void compile(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> variables, ASTOrbitLoop loop, Collection<CompilerVariable> stateVariables) {
		if (loop != null) {
			builder.append("n = ");
			builder.append(loop.getBegin());
			builder.append(";\n");
			builder.append("if (orbit.getStates() != null) {\n");
			builder.append("orbit.getStates().add(exp.numberArray([");
			int i = 0;
			for (CompilerVariable var : stateVariables) {
				if (i > 0) {
					builder.append(", ");
				}
				builder.append("exp.number(");
				builder.append(var.getName());
				builder.append(")");
				i += 1;
			}
			builder.append("]));\n");
			builder.append("}\n");
			builder.append("for (var i = ");
			builder.append(loop.getBegin());
			builder.append(" + 1; i <= ");
			builder.append(loop.getEnd());
			builder.append("; i++) {\n");
			for (ASTStatement statement : loop.getStatements()) {
				compile(context, builder, variables, statement);
			}
			builder.append("if (");
			loop.getExpression().compile(new JavaScriptExpressionCompiler(context, variables, builder));
			builder.append(") { n = i; break; }\n");
			builder.append("if (orbit.getStates() != null) {\n");
			builder.append("orbit.getStates().add(exp.numberArray([");
			i = 0;
			for (CompilerVariable var : stateVariables) {
				if (i > 0) {
					builder.append(", ");
				}
				builder.append("exp.number(");
				builder.append(var.getName());
				builder.append(")");
				i += 1;
			}
			builder.append("]));\n");
			builder.append("}\n");
			builder.append("}\n");
			builder.append("if (orbit.getStates() != null) {\n");
			builder.append("orbit.getStates().add(exp.numberArray([");
			i = 0;
			for (CompilerVariable var : stateVariables) {
				if (i > 0) {
					builder.append(", ");
				}
				builder.append("exp.number(");
				builder.append(var.getName());
				builder.append(")");
				i += 1;
			}
			builder.append("]));\n");
			builder.append("}\n");
		}
	}

	private void compile(ExpressionContext context, StringBuilder builder, Map<String, CompilerVariable> variables, ASTStatement statement) {
		if (statement != null) {
			statement.compile(new JavaScriptExpressionCompiler(context, variables, builder));
		}		
	}
	
	private String createArray(float[] components) {
		return "[" + components[0] + "," + components[1] + "," + components[2] + "," + components[3] + "]";
	}
}	