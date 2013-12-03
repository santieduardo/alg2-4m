package com.senac.algoritmos;

public class PilhaOperador {

	private char[] dados;
	private int topo;

	public PilhaOperador(int tam) {
		dados = new char[tam];
		topo = 0;
	}

	public char peek() throws PilhaVazia {
		if (isEmpty())
			throw new PilhaVazia();

		return dados[topo - 1];
	}

	public void push(char valor) throws PilhaCheia {
		if (isFull())
			throw new PilhaCheia();

		dados[topo] = valor;
		topo++;
	}

	public char pop() throws PilhaVazia {
		if (isEmpty())
			throw new PilhaVazia();

		topo--;
		return dados[topo];
	}

	public boolean isFull() {
		return topo == dados.length;
	}

	public boolean isEmpty() {
		return topo == 0;
	}
}