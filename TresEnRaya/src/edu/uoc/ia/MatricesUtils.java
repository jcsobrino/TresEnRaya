package edu.uoc.ia;

public class MatricesUtils {

	private MatricesUtils() {

	}

	public static boolean contieneAlgunNulo(Boolean[][] matriz) {

		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < matriz[0].length; j++) {

				if (matriz[i][j] == null) {

					return false;
				}
			}
		}

		return true;
	}

	public static Boolean[] extraerDiagonal(Boolean[][] matriz) {

		if (matriz.length != matriz[0].length) {

			throw new IllegalArgumentException("La matriz no es cuadrada");
		}

		Boolean[] diagonal = new Boolean[matriz.length];

		for (int i = 0; i < diagonal.length; i++) {

			diagonal[i] = matriz[i][i];
		}

		return diagonal;
	}

	public static boolean tieneMismoValor(Boolean valor, Boolean[] array) {

		for (int i = 0; i < array.length; i++) {

			if (array == null || array[i] != valor) {
				return false;
			}
		}

		return true;
	}

	public static Boolean[][] girarMatriz(Boolean[][] matriz) {

		Boolean[][] nuevaMatriz = new Boolean[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < matriz[0].length; j++) {

				nuevaMatriz[j][matriz.length - i - 1] = matriz[i][j];
			}
		}

		return nuevaMatriz;
	}
}
