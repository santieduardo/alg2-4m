package com.senac.algoritmos;

import java.util.Scanner;

import com.senac.estruturas.PilhaCheia;
import com.senac.estruturas.PilhaOperador;
import com.senac.estruturas.PilhaOperando;
import com.senac.estruturas.PilhaVazia;

public class AvaliadorRPN {
	public static double avalia(String expressao) throws PilhaCheia,
			PilhaVazia, InvalidOperator {
		PilhaOperando p = new PilhaOperando(50);

		Scanner sc = new Scanner(expressao);

		while (sc.hasNext()) {
			if (sc.hasNextInt()) {
				p.push(sc.nextInt());
			} else {
				String op = sc.next();
				double rhs = p.pop();
				double lhs = p.pop();
				p.push(ExecOperador(op.charAt(0), lhs, rhs));
			}
		}

		return p.pop();
	}

	public static void InversorPosFixo(String expressao) throws PilhaCheia,
			PilhaVazia, InvalidOperator {
		Scanner sc = new Scanner(expressao);

		PilhaOperador p = new PilhaOperador(50);
		String saida = "";

		while (sc.hasNext()) {

			if (sc.hasNextInt()) {
				saida += " " + sc.next();
			}

			else {
				String operadorLido = sc.next();

				if (FechaParentes(operadorLido)) {
					do {
						saida += " " + p.pop();

					} while (!AbreParenteses(p.peek()));
					p.pop();
				}

				else {
					if (p.isEmpty()) {

						p.push(operadorLido);

					} else if (Prioridade(operadorLido) > Prioridade(p.peek()))
						p.push(operadorLido);

					else {
						while (!p.isEmpty()
								&& Prioridade(operadorLido) < Prioridade(p
										.peek()) && !AbreParenteses(p.peek())) {
							if (AbreParenteses(p.peek()))
								p.pop();
							else
								saida += " " + p.pop();
						}
						p.push(operadorLido);
					}
				}
			}
		}

		while (!p.isEmpty()) {
			if (AbreParenteses(p.peek()))
				p.pop();
			else
				saida += " " + p.pop();
		}

		System.out.println("Notação Polonesa Reversa: " + saida);
		System.out.println(AvaliadorRPN.avalia(saida));
	}

	public static boolean AbreParenteses(String op) {
		return op.equals("(");
	}

	public static boolean FechaParentes(String op) {
		return op.equals(")");

	}

	public static int Prioridade(String operador) throws InvalidOperator {
		int precedencia = 0;
		switch (operador.charAt(0)) {
		case '(':
			precedencia = 3;
			break;

		case '*':
			precedencia = 2;
			break;

		case '/':
			precedencia = 2;
			break;

		case '+':
			precedencia = 1;
			break;

		case '-':
			precedencia = 1;
			break;

		default:
			throw new InvalidOperator(operador.charAt(0));
		}

		return precedencia;
	}

	private static double ExecOperador(char op, double lhs, double rhs)
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