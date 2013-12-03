package com.senac.planilha;

import java.util.Scanner;

import com.senac.algoritmos.Calc;

public class Planilha {

	/**
	 * variaveis para os comandos
	 */
	public static int Sair = 0;
	public static int Ajuda = 1;
	public static int Adicionar = 2;
	public static int Remover = 3;
	public static int Calcular = 4;
	public static int Resultado = 5;

	/**
	 * operadores para os calculos
	 */
	public static char OpMais = '+';
	public static char OpMenos = '-';
	public static char OpMultiplicacao = '*';
	public static char OpDivisao = '/';

	private Scanner scanner = new Scanner(System.in);
	private Lista<Celula>[] lista;

	
	/**
	 * roda o sistema
	 * @param args
	 */
	public static void main(String[] args) {
		new Planilha().run();
	}

	/**
	 * auxilia o sistema
	 */
	private void run() {
		lista = new Lista[10];
		for (int i = 0; i < lista.length; i++) {
			lista[i] = new Lista<Celula>();
		}
		int cmd = -1;
		imprimirComandos();
		do {
			try {
				Scanner tmp = new Scanner(scanner.nextLine().toLowerCase());
				cmd = converterCMD(tmp.next());
				switch (cmd) {
				case 1:
					imprimirComandos();
					break;
				case 2:
					adicionarCelula(tmp.next(), tmp.next());
					break;
				case 3:
					quebrarCelula(tmp.next(), tmp.next());
					break;
				case 4:
					calcularCelulas(tmp.next(), tmp.next(), tmp.next(),
							tmp.next());
					break;
				case 5:
					displayPanilha();
					break;
				}
				tmp.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (cmd != 0);
		scanner.close();
		System.exit(0);
	}

	/**
	 * faz a conversao dos comandos
	 * @param cmd inserido pelo usuario
	 * @return retorna o comando desejado
	 * @throws Exception
	 */
	private int converterCMD(String cmd) throws Exception {
		if (cmd.equals("sair"))
			return Sair;
		if (cmd.equals("ajuda"))
			return Ajuda;
		if (cmd.equals("adicionar"))
			return Adicionar;
		if (cmd.equals("remover"))
			return Remover;
		if (cmd.equals("calcular"))
			return Calcular;
		if (cmd.equals("total"))
			return Resultado;
		throw new Exception("Comando invalido");
	}

	/**
	 * faz a conversao para os operadores
	 * @param op inserido
	 * @return retorna o operador convertido
	 * @throws Exception
	 */
	private char converterOP(int op) throws Exception {
		switch (op) {
		case 1:
			return OpMais;
		case 2:
			return OpMenos;
		case 3:
			return OpMultiplicacao;
		case 4:
			return OpDivisao;
		default:
			throw new Exception("Operador Invalido");
		}
	}

	/**
	 * imprime no console a lista de comandos disponiveis
	 */
	private void imprimirComandos() {
		System.out.println("COMANDOS:");
		System.out.println("sair - Sai do sistema");
		System.out.println("adicionar <linha> <coluna> - adicionar celula");
		System.out.println("remover <linha> <coluna> - remover celula");
		System.out.println("calcular <linha> <coluna> <linha> <coluna>");
		System.out.println("total - Faz o calculo");
	}

	/**
	 * exibe as linhas da planilha no console para visualizacao do usuario
	 * @throws Exception
	 */
	private void displayPanilha() throws Exception {
		for (int linha = 0; linha < lista.length; linha++) {
			Lista<Celula> line = lista[linha];
			if (line == null)
				continue;
			if (!line.vazio())
				continue;

			System.out.print("L -> " + linha);
			Nodo<Celula> nodo = line.getNodo();
			while (!(nodo == null)) {
				System.out.print(" | " + nodo.getObjeto());
				nodo = nodo.getProximo();
			}
			System.out.println(" --- total");
		}
	}

	/**
	 * faz a verificacao das linhas utilizadas
	 * @param lineStg inserida
	 * @throws Exception
	 */
	private boolean validarLinha(String lineStg) throws Exception {
		try {
			int linha = Integer.parseInt(lineStg);
			if (!(linha >= 0 && linha < lista.length))
				return false;

			return true;
		} catch (Exception e) {
			throw new Exception("Linha deve ser numero!");
		}
	}

	/**
	 * destroi a celula desejada do usuario
	 * @param linha inserida
	 * @param row inserida
	 * @throws Exception
	 */
	private void quebrarCelula(String linha, String row) throws Exception {
		if (!validarLinha(linha))
			throw new Exception("Esta linha " + linha + " nao existe");

		Nodo<Celula> nodo = lista[Integer.parseInt(linha)].getNodo();
		while (!(nodo == null)) {
			Celula celula = (Celula) nodo.getObjeto();
			if (celula.getNome().equals(row)) {
				nodo = null;
				break;
			}
			nodo = nodo.getProximo();
		}
	}

	/**
	 * incrementa uma nova linha desejada
	 * @param linha inserida
	 * @param row inserida
	 * @throws Exception
	 */
	private void adicionarCelula(String linha, String row) throws Exception {
		if (!validarLinha(linha))
			throw new Exception("A linha " + linha + " nao existe");

		Celula celula = criarCelula(row);

		if (getCelula(linha, row) == null)
			lista[Integer.parseInt(linha)].inserirFinal(celula);

	}

	/**
	 * criacao da nova celula
	 * @param row inserida
	 * @return
	 */
	private Celula criarCelula(String row) {
		System.out.println("Digite os dados da nova celula:");
		System.out.print("Valor: ");
		int valor = Integer.parseInt(scanner.nextLine());
		System.out.print("Formula: ");
		int formula = Integer.parseInt(scanner.nextLine());
		return new Celula(row, valor, formula);
	}

	/**
	 * captura a celula desejada pelo usuario
	 * @param line inserida
	 * @param row inserida
	 * @return retorna a celula
	 * @throws Exception
	 */
	private Celula getCelula(String line, String row) throws Exception {
		if (!validarLinha(line))
			return null;

		Nodo<Celula> nodo = lista[Integer.parseInt(line)].getNodo();
		while (!(nodo == null)) {
			Celula celula = (Celula) nodo.getObjeto();
			if (celula.getNome().equals(row))
				return celula;
			nodo = nodo.getProximo();
		}
		return null;
	}

	/**
	 * realiza o calculo entre as celulas desejadas
	 * @param row1 inserida
	 * @param line1 inserida 
	 * @param row2 inserida 
	 * @param line2 inserida
	 * @throws Exception
	 */
	private void calcularCelulas(String row1, String line1, String row2,
			String line2) throws Exception {
		Celula first = getCelula(row1, line1);
		Celula second = getCelula(row2, line2);

		String formula = first.getValor() + " "
				+ converterOP(first.getFormula()) + " " + second.getValor();
		System.out.println(formula + " = " + Calc.calcularForm(formula));
	}
}
