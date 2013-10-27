package com.senac;

import java.util.Scanner;

import com.senac.algoritmos.AvaliadorRPN;

import static java.lang.System.*;

public class Planilha {
	public static void main(String[] args) throws Exception {

		Scanner entradaTeclado = new Scanner(System.in);
		String expressao = "";

		out.println("Insira uma expressão ou digite a palavra 'fim' para finalizar com programa");
		
		while (entradaTeclado.hasNext()) {
			expressao = entradaTeclado.nextLine();
			if (expressao.equals("fim")){
				out.println("Programa finalizado !");
				System.exit(0);
			}
			else{
				AvaliadorRPN.InversorPosFixo(expressao);
			}
		}

	}
}
