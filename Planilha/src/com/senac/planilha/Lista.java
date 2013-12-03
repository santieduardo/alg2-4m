package com.senac.planilha;

public class Lista<E> {

	private Nodo<E> head;
	private Nodo<E> tail;

	/**
	 * 
	 * @return retorna o head da lista
	 */
	public E getHead() {
		return head.getObjeto();
	}

	/**
	 * 
	 * @return retorna o tail da lista
	 */
	public E getTail() {
		return tail.getObjeto();
	}

	/**
	 * insere um novo tail na lista
	 * @param objeto inserido
	 */
	public void inserirFinal(E objeto) {
		Nodo<E> n = new Nodo<E>(objeto);
		if (vazio()) {
			head = tail = n;
		} else {
			tail.setProximo((Nodo<E>) n);
			n.setAnterior((Nodo<E>) tail);
			tail = n;

		}
	}

	/**
	 * insere um novo head na lista
	 * @param objeto inserido
	 */
	public void inserirInicio(E objeto) {
		Nodo<E> n = new Nodo<E>(objeto);
		if (vazio())
			head = tail = n;
		else {
			n.setProximo((Nodo<E>) head);
			head.setAnterior((Nodo<E>) n);
			head = n;
		}
	}

	/**
	 * deleta o head da lista
	 * @return objetoExcluido
	 */
	public E deletarInicio() {
		if (vazio())
			;
		E objetoExcluido = head.getObjeto();

		if (head == tail)
			head = tail = null;
		else
			head = (Nodo<E>) head.getProximo();

		return objetoExcluido;
	}

	/**
	 * deleta o tail da lista
	 * @return objetoExcluido
	 */
	public E deletarFinal() {
		if (vazio())
			;
		E objetoExcluido = tail.getObjeto();

		if (head == tail)
			head = tail = null;
		else
			tail = (Nodo<E>) tail.getAnterior();

		return objetoExcluido;
	}

	/**
	 * 
	 * @return true se o head e vazio
	 */
	public boolean vazio() {
		return head == null;
	}

	/**
	 * 
	 * @return retorna o head
	 */
	public Nodo<E> getNodo() {
		return head;
	}
}
