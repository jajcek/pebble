/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Original work Copyright (c) 2009-2013 by the Twig Team
 * Modified work Copyright (c) 2013 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.mitchellbosecke.pebble.extension.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.Function;
import com.mitchellbosecke.pebble.extension.Test;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryAdd;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryAnd;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryDivide;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryEqual;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryFilter;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryGreaterThan;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryGreaterThanEquals;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryLessThan;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryLessThanEquals;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryModulus;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryMultiply;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryNotEqual;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryOr;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinarySubtract;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryTestNegative;
import com.mitchellbosecke.pebble.node.expression.binary.NodeExpressionBinaryTestPositive;
import com.mitchellbosecke.pebble.node.expression.unary.NodeExpressionUnaryMinus;
import com.mitchellbosecke.pebble.node.expression.unary.NodeExpressionUnaryNot;
import com.mitchellbosecke.pebble.node.expression.unary.NodeExpressionUnaryPlus;
import com.mitchellbosecke.pebble.operator.Associativity;
import com.mitchellbosecke.pebble.operator.BinaryOperator;
import com.mitchellbosecke.pebble.operator.BinaryOperatorImpl;
import com.mitchellbosecke.pebble.operator.UnaryOperator;
import com.mitchellbosecke.pebble.operator.UnaryOperatorImpl;
import com.mitchellbosecke.pebble.tokenParser.BlockTokenParser;
import com.mitchellbosecke.pebble.tokenParser.ExtendsTokenParser;
import com.mitchellbosecke.pebble.tokenParser.FlushTokenParser;
import com.mitchellbosecke.pebble.tokenParser.ForTokenParser;
import com.mitchellbosecke.pebble.tokenParser.IfTokenParser;
import com.mitchellbosecke.pebble.tokenParser.ImportTokenParser;
import com.mitchellbosecke.pebble.tokenParser.IncludeTokenParser;
import com.mitchellbosecke.pebble.tokenParser.MacroTokenParser;
import com.mitchellbosecke.pebble.tokenParser.ParallelTokenParser;
import com.mitchellbosecke.pebble.tokenParser.SetTokenParser;
import com.mitchellbosecke.pebble.tokenParser.TokenParser;

public class CoreExtension extends AbstractExtension {

	@Override
	public void initRuntime(PebbleEngine engine) {

	}

	@Override
	public List<TokenParser> getTokenParsers() {
		ArrayList<TokenParser> parsers = new ArrayList<>();
		parsers.add(new BlockTokenParser());
		parsers.add(new ExtendsTokenParser());
		parsers.add(new IfTokenParser());
		parsers.add(new ForTokenParser());
		parsers.add(new MacroTokenParser());
		parsers.add(new ImportTokenParser());
		parsers.add(new IncludeTokenParser());
		parsers.add(new SetTokenParser());
		parsers.add(new FlushTokenParser());
		parsers.add(new ParallelTokenParser());
		return parsers;
	}

	@Override
	public List<UnaryOperator> getUnaryOperators() {
		ArrayList<UnaryOperator> operators = new ArrayList<>();
		operators.add(new UnaryOperatorImpl("not", 50, NodeExpressionUnaryNot.class));
		operators.add(new UnaryOperatorImpl("+", 500, NodeExpressionUnaryPlus.class));
		operators.add(new UnaryOperatorImpl("-", 500, NodeExpressionUnaryMinus.class));
		return operators;
	}

	@Override
	public List<BinaryOperator> getBinaryOperators() {
		ArrayList<BinaryOperator> operators = new ArrayList<>();
		operators.add(new BinaryOperatorImpl("or", 10, NodeExpressionBinaryOr.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("and", 15, NodeExpressionBinaryAnd.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("==", 20, NodeExpressionBinaryEqual.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("equals", 20, NodeExpressionBinaryEqual.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("!=", 20, NodeExpressionBinaryNotEqual.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl(">", 20, NodeExpressionBinaryGreaterThan.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("<", 20, NodeExpressionBinaryLessThan.class, Associativity.LEFT));
		operators
				.add(new BinaryOperatorImpl(">=", 20, NodeExpressionBinaryGreaterThanEquals.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("<=", 20, NodeExpressionBinaryLessThanEquals.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("+", 30, NodeExpressionBinaryAdd.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("-", 30, NodeExpressionBinarySubtract.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("*", 60, NodeExpressionBinaryMultiply.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("/", 60, NodeExpressionBinaryDivide.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("%", 60, NodeExpressionBinaryModulus.class, Associativity.LEFT));

		/*
		 * The precedence of the "is", "is not", and "|" operators is completely
		 * irrelevant here. These operators are uniquely handled by the
		 * ExpressionParser and will ALWAYS have a higher precedence than all
		 * other operators.
		 */
		operators.add(new BinaryOperatorImpl("is", 100, NodeExpressionBinaryTestPositive.class, Associativity.LEFT));
		operators
				.add(new BinaryOperatorImpl("is not", 100, NodeExpressionBinaryTestNegative.class, Associativity.LEFT));
		operators.add(new BinaryOperatorImpl("|", 110, NodeExpressionBinaryFilter.class, Associativity.LEFT));

		return operators;
	}

	@Override
	public Map<String, Filter> getFilters() {
		Map<String, Filter> filters = new HashMap<>();
		filters.put("abbreviate", new AbbreviateFilter());
		filters.put("capitalize", new CapitalizeFilter());
		filters.put("date", new DateFilter());
		filters.put("default", new DefaultFilter());
		filters.put("lower", new LowerFilter());
		filters.put("numberformat", new NumberFormatFilter());
		filters.put("title", new TitleFilter());
		filters.put("trim", new TrimFilter());
		filters.put("upper", new UpperFilter());
		filters.put("urlencode", new UrlEncoderFilter());
		return filters;
	}

	@Override
	public Map<String, Test> getTests() {
		Map<String, Test> tests = new HashMap<>();
		tests.put("empty", new EmptyTest());
		tests.put("even", new EvenTest());
		tests.put("iterable", new IterableTest());
		tests.put("null", new NullTest());
		tests.put("odd", new OddTest());
		return tests;
	}

	@Override
	public Map<String, Function> getFunctions() {
		Map<String, Function> functions = new HashMap<>();

		/*
		 * For efficiency purposes, some core functions are individually parsed
		 * by our expression parser and compiled in their own unique way. This
		 * includes the block and parent functions.
		 */

		functions.put("max", new MaxFunction());
		functions.put("min", new MinFunction());
		return functions;
	}

	@Override
	public Map<String, Object> getGlobalVariables() {

		return null;
	}

}