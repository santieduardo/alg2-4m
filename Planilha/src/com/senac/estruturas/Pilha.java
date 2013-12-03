package com.senac.estruturas;

public class Pilha<T> {
	private Object[] dados;
	private int topo;
	
	public Pilha()
	{
		this.dados = new Object[100];
		this.topo = 0;
	}
	
	public void push(T object) {
		if (!isFull()) {
			dados[topo] = object;
			topo++;
		}
	}
	
	private boolean isFull() {
		return topo == dados.length;
	}

	public T pop() {
		if (!isEmpty()) {
			topo--;
			return (T)(dados[topo]);
		}
		return null;
	}
	
	public T peek() {
		return (T)(dados[topo-1]);
	}
	
	public boolean isEmpty()
	{
		return topo == 0;
	}
}
