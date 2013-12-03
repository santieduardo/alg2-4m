package com.senac.algoritmos;

import java.util.Scanner;

public class Calc {

	PilhaOperador pilha;
	String saida;

	
	/**
	 * 
	 * @param formula inserida
	 * @return retorna calculo ja checado
	 * @throws Exception
	 */
	public static double calcularForm(String formula) throws Exception {
		return new Calc().parse(formula);
	}

	/**
	 * 
	 * @param expressao inserida
	 * @return retorna a saida da expressao
	 * @throws Exception
	 */
	private double parse(String expressao) throws Exception {
		Scanner scanner = new Scanner(expressao);
		pilha = new PilhaOperador(30);
		saida = "";
		while (scanner.hasNext()) {
			if (scanner.hasNextInt()) {
				saida += scanner.nextInt() + " ";
			} else {
				char ch = scanner.next().charAt(0);
				switch (ch) {
				case '+':
				case '-':
					operador(ch, 1);
					break;
				case '*':
				case '/':
					operador(ch, 2);
					break;
				case '(':
					pilha.push(ch);
					break;
				case ')':
					isFechaParentese();
					break;
				}
			}
		}
		while (!pilha.isEmpty()) {
			saida += pilha.pop() + " ";
		}

		return AvaliadorRPN.avaliarExp(saida);
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void isFechaParentese() throws Exception {
		while (!pilha.isEmpty()) {
			char ch = pilha.pop();
			if (ch == '(')
				break;
			saida += ch + " ";
		}
	}

	/**
	 * 
	 * @param op
	 * @param prioridade
	 * @throws Exception
	 */
	private void operador(char op, int prioridade) throws Exception {
		while (!pilha.isEmpty()) {
			int prioridadeTmp = 2;
			char ch = pilha.pop();

			if (ch == '(') {
				pilha.push(ch);
				break;
			}

			if (ch == '+' || ch == '-') {
				prioridadeTmp = 1;
			}

			if (prioridadeTmp < prioridade) {
				pilha.push(ch);
				break;
			}

			saida += ch + " ";
		}
		pilha.push(op);
	}
}