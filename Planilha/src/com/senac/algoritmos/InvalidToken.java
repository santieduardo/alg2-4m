package com.senac.algoritmos;

public class InvalidToken extends Exception {
	public InvalidToken(String token) {
		super("Token invalido: " + token);
	}
}
