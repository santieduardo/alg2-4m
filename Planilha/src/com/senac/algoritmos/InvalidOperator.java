package com.senac.algoritmos;

public class InvalidOperator extends Exception {
	public InvalidOperator(char operador)
	{
		super(String.format("Invalid operator - %c",operador));
	}
}
