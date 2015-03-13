package edu.uoc.ia;

public class Estado {

	public Integer valor = null;
	public Boolean[][] tablero = new Boolean[3][3];

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Estado estadoClone = new Estado();

		if (valor != null) {

			estadoClone.valor = Integer.valueOf(valor);
		}

		if (tablero != null) {

			estadoClone.tablero = tablero.clone();

			for (int i = 0; i < tablero.length; i++) {

				estadoClone.tablero[i] = tablero[i].clone();
			}

		}

		return estadoClone;
	}

	@Override
	public String toString() {

		StringBuffer print = new StringBuffer();

		if (valor != null) {

			print.append("Valor función Utilidad: " + valor);
		}

		print.append("\r\n   A   B   C  \r\n");

		for (int i = 0; i < tablero.length; i++) {

			print.append(i + 1);

			for (int j = 0; j < tablero[0].length; j++) {

				if (tablero[i][j] == TresEnRaya.jugadorO_Min) {

					print.append("| O ");

				} else if (tablero[i][j] == TresEnRaya.jugadorX_Max) {

					print.append("| X ");

				} else {

					print.append("|   ");
				}

			}

			print.append("|\r\n");
		}

		print.append("\r\n");

		return print.toString();
	}

}
