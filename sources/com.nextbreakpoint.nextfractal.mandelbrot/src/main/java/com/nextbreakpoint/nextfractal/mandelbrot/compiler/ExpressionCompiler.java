package com.nextbreakpoint.nextfractal.mandelbrot.compiler;

import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTColorComponent;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTColorPalette;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTConditionCompareOp;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTConditionExpression;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTConditionLogicOp;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTConditionTrap;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTException;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTExpression;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTExpressionCompiler;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTFunction;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTNumber;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTOperator;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTParen;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTRuleCompareOpExpression;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTRuleExpression;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTRuleLogicOpExpression;
import com.nextbreakpoint.nextfractal.mandelbrot.grammar.ASTVariable;

public class ExpressionCompiler implements ASTExpressionCompiler {
	private final StringBuilder builder;
	
	public ExpressionCompiler(StringBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void compile(ASTNumber number) {
		if (number.isReal()) {
			builder.append(number.r());
		} else {
			builder.append("number(");
			builder.append(number.r());
			builder.append(",");
			builder.append(number.i());
			builder.append(")");
		}
	}

	@Override
	public void compile(ASTFunction function) {
		builder.append("func");
		builder.append(function.getName().toUpperCase().substring(0, 1));
		builder.append(function.getName().substring(1));
		builder.append("(");
		switch (function.getName()) {
			case "mod":
			case "mod2":
			case "pha":
			case "re":
			case "im":
				if (function.getArguments().length != 1) {
					throw new ASTException("Invalid number of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				break;
				
			case "sin":
			case "cos":
			case "tan":
			case "asin":
			case "acos":
			case "atan":
				if (function.getArguments().length != 1) {
					throw new ASTException("Invalid number of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				break;

			case "log":
				if (function.getArguments().length != 1) {
					throw new ASTException("Invalid number of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				if (!function.getArguments()[0].isReal()) {
					throw new ASTException("Invalid type of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				break;
				
			case "atan2":
			case "hypot":
				if (function.getArguments().length != 2) {
					throw new ASTException("Invalid number of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				if (!function.getArguments()[0].isReal()) {
					throw new ASTException("Invalid type of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				if (!function.getArguments()[1].isReal()) {
					throw new ASTException("Invalid type of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				break;
				
			case "pow":
				if (function.getArguments().length != 2) {
					throw new ASTException("Invalid number of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				if (!function.getArguments()[1].isReal()) {
					throw new ASTException("Invalid type of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				break;

			case "sqrt":
			case "exp":
				if (function.getArguments().length != 1) {
					throw new ASTException("Invalid number of arguments: " + function.getLocation().getText(), function.getLocation());
				}				
				break;
				
			default:
				throw new ASTException("Unsupported function: " + function.getLocation().getText(), function.getLocation());
		}
		ASTExpression[] arguments = function.getArguments();
		for (int i = 0; i < arguments.length; i++) {
			arguments[i].compile(this);
			if (i < arguments.length - 1) {
				builder.append(",");
			}
		}
		builder.append(")");
	}

	@Override
	public void compile(ASTOperator operator) {
		ASTExpression exp1 = operator.getExp1();
		ASTExpression exp2 = operator.getExp2();
		if (exp2 == null) {
			switch (operator.getOp()) {
				case "-":
					if (exp1.isReal()) {
						builder.append("-");
						exp1.compile(this);
					} else {
						builder.append("opNeg");
						builder.append("(");
						exp1.compile(this);
						builder.append(")");
					}
					break;
				
				case "+":
					if (exp1.isReal()) {
						builder.append("-");
						exp1.compile(this);
					} else {
						builder.append("opPos");
						builder.append("(");
						exp1.compile(this);
						builder.append(")");
					}
					break;
				
				default:
					throw new ASTException("Unsupported operator: " + operator.getLocation().getText(), operator.getLocation());
			}
		} else {
			if (exp1.isReal() && exp2.isReal()) {
				builder.append("(");
				exp1.compile(this);
				switch (operator.getOp()) {
					case "+":
						builder.append("+");
						break;
					
					case "-":
						builder.append("-");
						break;
						
					case "*":
						builder.append("*");
						break;
						
					case "/":
						builder.append("/");
						break;
						
					case "^":
						builder.append("^");
						break;
					
					default:
						throw new ASTException("Unsupported operator: " + operator.getLocation().getText(), operator.getLocation());
				}
				exp2.compile(this);
				builder.append(")");
			} else if (exp2.isReal()) {
				switch (operator.getOp()) {
					case "+":
						builder.append("opAdd");
						break;
					
					case "-":
						builder.append("opSub");
						break;
						
					case "*":
						builder.append("opMul");
						break;
						
					case "/":
						builder.append("opDiv");
						break;
					
					default:
						throw new ASTException("Unsupported operator: " + operator.getLocation().getText(), operator.getLocation());
				}
				builder.append("(");
				exp1.compile(this);
				builder.append(",");
				exp2.compile(this);
				builder.append(")");
			} else {
				switch (operator.getOp()) {
					case "+":
						builder.append("opAdd");
						break;
					
					case "-":
						builder.append("opSub");
						break;
						
					case "*":
						builder.append("opMul");
						break;
						
					default:
						throw new ASTException("Unsupported operator: " + operator.getLocation().getText(), operator.getLocation());
				}
				builder.append("(");
				exp1.compile(this);
				builder.append(",");
				exp2.compile(this);
				builder.append(")");
			}
		}
	}

	@Override
	public void compile(ASTParen paren) {
		builder.append("(");
		paren.getExp().compile(this);
		builder.append(")");
	}

	@Override
	public void compile(ASTVariable variable) {
		builder.append(variable.getName());
	}

	@Override
	public void compile(ASTConditionCompareOp compareOp) {
		ASTExpression exp1 = compareOp.getExp1();
		ASTExpression exp2 = compareOp.getExp2();
		if (exp1.isReal() && exp2.isReal()) {
			builder.append("(");
			exp1.compile(this);
			switch (compareOp.getOp()) {
				case ">":
					builder.append(">");
					break;
				
				case "<":
					builder.append("<");
					break;
					
				case ">=":
					builder.append(">=");
					break;
					
				case "<=":
					builder.append("<=");
					break;
					
				case "=":
					builder.append("==");
					break;
					
				case "<>":
					builder.append("!=");
					break;
				
				default:
					throw new ASTException("Unsupported operator: " + compareOp.getLocation().getText(), compareOp.getLocation());
			}
			exp2.compile(this);
			builder.append(")");
		}
	}

	@Override
	public void compile(ASTConditionLogicOp logicOp) {
		ASTConditionExpression exp1 = logicOp.getExp1();
		ASTConditionExpression exp2 = logicOp.getExp2();
		builder.append("(");
		exp1.compile(this);
		switch (logicOp.getOp()) {
			case "&":
				builder.append("&&");
				break;
			
			case "|":
				builder.append("||");
				break;
				
			case "^":
				builder.append("^^");
				break;
				
			default:
				throw new ASTException("Unsupported operator: " + logicOp.getLocation().getText(), logicOp.getLocation());
		}
		exp2.compile(this);
		builder.append(")");
	}

	@Override
	public void compile(ASTConditionTrap trap) {
		if (!trap.isContains()) {
			builder.append("!");
		}
		builder.append("trap");
		builder.append(trap.getName().toUpperCase().substring(0, 1));
		builder.append(trap.getName().substring(1));
		builder.append(".contains(");
		trap.getExp().compile(this);
		builder.append(")");
	}

	@Override
	public void compile(ASTRuleLogicOpExpression logicOp) {
		ASTRuleExpression exp1 = logicOp.getExp1();
		ASTRuleExpression exp2 = logicOp.getExp2();
		builder.append("(");
		exp1.compile(this);
		switch (logicOp.getOp()) {
			case "&":
				builder.append("&&");
				break;
			
			case "|":
				builder.append("||");
				break;
				
			case "^":
				builder.append("^^");
				break;
				
			default:
				throw new ASTException("Unsupported operator: " + logicOp.getLocation().getText(), logicOp.getLocation());
		}
		exp2.compile(this);
		builder.append(")");
	}

	@Override
	public void compile(ASTRuleCompareOpExpression compareOp) {
		ASTExpression exp1 = compareOp.getExp1();
		ASTExpression exp2 = compareOp.getExp2();
		if (exp1.isReal() && exp2.isReal()) {
			builder.append("(");
			exp1.compile(this);
			switch (compareOp.getOp()) {
				case ">":
					builder.append(">");
					break;
				
				case "<":
					builder.append("<");
					break;
					
				case ">=":
					builder.append(">=");
					break;
					
				case "<=":
					builder.append("<=");
					break;
					
				case "=":
					builder.append("==");
					break;
					
				case "<>":
					builder.append("!=");
					break;
				
				default:
					throw new ASTException("Unsupported operator: " + compareOp.getLocation().getText(), compareOp.getLocation());
			}
			exp2.compile(this);
			builder.append(")");
		}
	}

	@Override
	public void compile(ASTColorPalette palette) {
		builder.append("palette");
		builder.append(palette.getName().toUpperCase().substring(0, 1));
		builder.append(palette.getName().substring(1));
		builder.append(".get(");
		if (palette.getExp().isReal()) {
			palette.getExp().compile(this);
		} else {
			throw new ASTException("Expression type not valid: " + palette.getLocation().getText(), palette.getLocation());
		}
		builder.append(")");
	}

	@Override
	public void compile(ASTColorComponent component) {
		builder.append("color(");
		component.getExp1().compile(this);
		if (component.getExp2() != null) {
			builder.append(",");
			component.getExp2().compile(this);
		}
		if (component.getExp3() != null) {
			builder.append(",");
			component.getExp3().compile(this);
		}
		if (component.getExp4() != null) {
			builder.append(",");
			component.getExp4().compile(this);
		}
		builder.append(")");
	}
}