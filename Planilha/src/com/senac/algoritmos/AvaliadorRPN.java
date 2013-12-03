package com.senac.algoritmos;

import java.util.Scanner;

public class AvaliadorRPN {
	public static double avaliarExp(String expressao) throws PilhaCheia,
			PilhaVazia, InvalidOperator {
		PilhaOperando pilha = new PilhaOperando(50);

		Scanner scanner = new Scanner(expressao);

		while (scanner.hasNext()) {
			if (scanner.hasNextInt()) {
				pilha.push(scanner.nextInt());
			} else {
				String op = scanner.next();
				double rhs = pilha.pop();
				double lhs = pilha.pop();
				pilha.push(executarOp(op.charAt(0), lhs, rhs));
			}
		}

		return pilha.pop();
	}

	private static double executarOp(char op, double lhs, double rhs)
			throws InvalidOperator {
		switch (op) {
		case '+':
			return lhs + rhs;
		case '-':
			return lhs - rhs;
		case '*':
			return lhs * rhs;
		case '/':
			return lhs / rhs;
		default:
			throw new InvalidOperator(op);
		}
	}
}
