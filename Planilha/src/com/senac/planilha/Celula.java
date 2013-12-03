package com.senac.planilha;

public class Celula {

	private String nome;
	private int valor;
	private int formula;

	/**
	 * 
	 * @param nome da celula
	 * @param valor da celula
	 * @param formula
	 */
	public Celula(String nome, int valor, int formula) {
		this.nome = nome;
		this.valor = valor;
		this.formula = formula;
	}

	/**
	 * 
	 * @return nome da celula
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome para a celula
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return valor da celula
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * 
	 * @param valor para a celula
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	/**
	 * 
	 * @return formula da celula
	 */
	public int getFormula() {
		return formula;
	}

	/**
	 * 
	 * @param formula para a celula
	 */
	public void setFormula(int formula) {
		this.formula = formula;
	}
}
