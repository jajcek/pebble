/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Copyright (c) 2012 Mitchell Bosecke.
 * 
 * This work is licensed under the Creative Commons Attribution-ShareAlike 3.0 
 * Unported License. To view a copy of this license, visit 
 * http://creativecommons.org/licenses/by-sa/3.0/
 ******************************************************************************/
package com.mitchellbosecke.pebble.node.expression;

import com.mitchellbosecke.pebble.compiler.Compiler;
import com.mitchellbosecke.pebble.node.NodeExpression;

public class NodeExpressionName extends NodeExpression {

	protected final String name;

	public NodeExpressionName(int lineNumber, String name) {
		super(lineNumber);
		this.name = name;
	}

	@Override
	public void compile(Compiler compiler) {
		compiler.raw("context.get(").string(name).raw(")");
	}

	public String getName(){
		return name;
	}
}
