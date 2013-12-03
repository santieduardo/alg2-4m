package com.senac.algoritmos;

import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 */
public class AvaliadorExpressao {

	public static double evaluate(String expressao)
			throws InvalidOperator, InvalidToken
	{
		return evaluate(expressao, Locale.US);
	}

	public static double evaluate(String expressao, Locale locale)
			throws InvalidOperator, InvalidToken
	{
		return evaluateRPN( convertInfixToPostfix(expressao, locale), locale );
	}
	
	/**
	 * @param entrada dos operadores
	 */
	private static int priority(char op) {
		switch (op) {
			case ')':
			case '(': return 3;
			case '*':
			case '/': return 2;
			case '+':
			case '-': return 1;
			default: return -1;
		}
	}
	
	/**
	 * 
	 * @param pilha
	 * @return
	 */
	private static boolean canStackOperator(Stack<Character> pilha)
	{
		return !(!pilha.isEmpty() && pilha.peek() != '(');
	}
	
	/**
	 * 
	 * @param lhs - operando da esquerda
	 * @param rhs - operando da direita
	 * @return retorna a ordem da prioridade
	 */
	private static boolean isLowerPriority(char lhs, char rhs)
	{
		return priority(lhs) < priority(rhs);
	}
	
	/**
	 * 
	 * @param expressao inserida
	 * @return retorna a converçao da expressao de infixa para posfixa
	 * @throws InvalidOperator
	 * @throws InvalidToken 
	 */
	public static String convertInfixToPostfix(String expressao)
			throws InvalidOperator, InvalidToken
	{
		return convertInfixToPostfix(expressao, Locale.US);
	}
	
	/**
	 * 
	 * @param expressao inserida
	 * @param locale
	 * @return retorna a expressao convertida para posfixa
	 * @throws InvalidOperator
	 * @throws InvalidToken 
	 */
	public static String convertInfixToPostfix(String expressao, Locale locale)
			throws InvalidOperator, InvalidToken
	{
		Stack<Character> pilha = new Stack<Character>(); 
		String postfix = "";
		Scanner sc = new Scanner(sanitize(expressao, locale));
		sc.useLocale(locale);
		
		while (sc.hasNext()) {
			if (sc.hasNextDouble()) {
				double d = sc.nextDouble();
				postfix += d + " ";
			} else {
				String next = sc.next();
				if (isOperator(next)) {
					postfix += handleOperator(next.charAt(0), pilha);
				} else {
					throw new InvalidToken(next);
				}
			}
		}
		
		while (!pilha.isEmpty())
			postfix += pilha.pop() + " ";

		return postfix;
	}

	/**
	 * 
	 * @param op inserido
	 * @return retorna true quando o operador e valido; false quando nao e valido
	 */
	private static boolean isOperator(String op)
	{
		switch (op.charAt(0))
		{
			case '(':
			case ')':
			case '+':
			case '-':
			case '*':
			case '/': return true;
			default: return false;
		}
	}
	
	/**
	 * 
	 * @param op inserido
	 * @param pilha
	 * @return retorna expressao posfixa
	 */
	private static String handleOperator(char op, Stack<Character> pilha)
	{
		String postfix = "";
		if (op == ')') {
			while (!canStackOperator(pilha))
				postfix += pilha.pop()  + " ";
			pilha.pop();
		} else if (op == '(') {
			pilha.push(op);
		} else {
			while (!canStackOperator(pilha) && isLowerPriority(op,pilha.peek()))
				postfix += pilha.pop()  + " ";
			pilha.push(op);
		}
		return postfix;
	}
	
	/**
	 * 
	 * @param expressao inserida
	 * @return
	 */
	private static String sanitize(String expressao, Locale locale) {
		Scanner sc = new Scanner(expressao);
		sc.useLocale(locale);
		String result = "";
		String floatNumber = "[0-9]+(\\.[0-9]*)?";
		String operator    = "[\\*-/+=\\(\\)]";
		String cellCoord   = "[a-zA-Z][a-zA-z]*[0-9]+";
		String regex = floatNumber + "|" + operator + "|" + cellCoord;
		String token;
		while ( null != (token = sc.findInLine(regex)))
			result += token + " ";
		return result;
	}

	/**
	 * 
	 * @param expressao inserida
	 * @return retorn RPN
	 * @throws InvalidOperator
	 * @throws InvalidToken 
	 */
	public static double evaluateRPN(String expressao)
			throws InvalidOperator, InvalidToken
	{
		return evaluateRPN(expressao, Locale.US);
	}
	
	/**
	 * 
	 * @param expressao
	 * @param locale
	 * @return
	 * @throws InvalidOperator
	 * @throws InvalidToken 
	 */
	public static double evaluateRPN(String expressao, Locale locale)
			throws InvalidOperator, InvalidToken
	{
		Stack<Double> pilha = new Stack<Double>();
		Scanner sc = new Scanner(sanitize(expressao, locale));
		sc.useLocale(locale);
		
		while (sc.hasNext()) {
			if (sc.hasNextDouble()) {
				pilha.push(sc.nextDouble());
			} else {
				String next = sc.next();
				if (isOperator(next))
					pilha.push( executeOperation(pilha, next) );
				else
					throw new InvalidToken(next);
			}
		}
		
		return pilha.pop();
	}

	/**
	 * 
	 * @param pilha
	 * @param op
	 * @return
	 * @throws InvalidOperator
	 */
	private static Double executeOperation(Stack<Double> pilha, String op)
			
	{
		Double rhs = pilha.pop();
		Double lhs = pilha.pop();
		Double res = 0.0;
		switch (op.charAt(0)) {
			case '+': return res = lhs + rhs;
			case '-': return res = lhs - rhs;
			case '*': return res = lhs * rhs;
			case '/': return res = lhs / rhs;
		}
		
		return res;
		
	}
}
