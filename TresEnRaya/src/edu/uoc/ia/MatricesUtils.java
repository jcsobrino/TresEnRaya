package edu.uoc.ia;

import java.lang.reflect.Array;

public class MatricesUtils {

	private MatricesUtils() {

	}

	public static boolean contieneAlgunNulo(Object[][] matriz) {

		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < matriz[0].length; j++) {

				if (matriz[i][j] == null) {

					return false;
				}
			}
		}

		return true;
	}

	public static Object[] extraerDiagonal(Object[][] matriz) {

		if (matriz.length != matriz[0].length) {

			throw new IllegalArgumentException("La matriz no es cuadrada");
		}

		Object[] diagonal = new Object[matriz.length];

		for (int i = 0; i < diagonal.length; i++) {

			diagonal[i] = matriz[i][i];
		}

		return diagonal;
	}

	public static boolean tieneMismoValor(Object valor, Object[] array) {

		for (int i = 0; i < array.length; i++) {

			if (array == null || array[i] != valor) {
				return false;
			}
		}

		return true;
	}

	public static <E> E[][] girarMatriz(E[][] matriz) {

		E[][] nuevaMatriz = (E[][]) Array.newInstance(matriz.getClass().getComponentType().getComponentType(), matriz.length, matriz[0].length);
		
		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < matriz[0].length; j++) {

				nuevaMatriz[j][matriz.length - i - 1] = matriz[i][j];
			}
		}

		return nuevaMatriz;
	}
}
