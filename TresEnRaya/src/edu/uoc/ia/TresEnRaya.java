package edu.uoc.ia;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class TresEnRaya {

	public static Turno jugadorX_Max = Turno.X;
	public static Turno jugadorO_Min = Turno.O;

	private Estado estadoActual = null;
	private Turno turno = null;

	private Integer valorMinimax(Turno turno, Estado estado) {

		Integer valorFuncionUtilidad = funcionUtilidad(estado);

		if (valorFuncionUtilidad == null) {

			LinkedList<Estado> posiblesJugadas = generaPosiblesJugadas(turno,
					estado);
			Iterator<Estado> itPosiblesJugadas = posiblesJugadas.iterator();

			while (itPosiblesJugadas.hasNext()) {

				Estado jugada = itPosiblesJugadas.next();
				jugada.valor = valorMinimax(turno.contrario(), jugada);
			}

			Estado mejorEstado = seleccionaMinOMax(turno, posiblesJugadas);

			if (estadoActual.equals(estado)) {

				System.out.println("Juega X\r\n" + mejorEstado);
				estadoActual = mejorEstado;
			}

			estado.valor = mejorEstado.valor;
			return estado.valor;
		}

		return valorFuncionUtilidad;
	}

	private Estado seleccionaMinOMax(Turno turno, LinkedList<Estado> estados) {

		Collections.shuffle(estados);

		estados.sort(new Comparator<Estado>() {

			@Override
			public int compare(Estado o1, Estado o2) {
				return o1.valor.compareTo(o2.valor);
			}

		});

		return turno == jugadorO_Min ? estados.getFirst() : estados.getLast();
	}

	private LinkedList<Estado> generaPosiblesJugadas(Turno turno,
			Estado estado) {

		LinkedList<Estado> jugadas = new LinkedList<Estado>();

		for (int i = 0; i < estado.tablero.length; i++) {

			for (int j = 0; j < estado.tablero[0].length; j++) {

				if (estado.tablero[i][j] == null) {

					Estado nuevoEstado;
					try {

						nuevoEstado = (Estado) estado.clone();
						nuevoEstado.tablero[i][j] = turno;
						jugadas.add(nuevoEstado);

					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return jugadas;
	}

	private Integer funcionUtilidad(Estado estado) {

		if (haGanado(jugadorO_Min, estado.tablero)) {

			return -1;
		}

		if (haGanado(jugadorX_Max, estado.tablero)) {

			return 1;
		}

		if (tableroLleno(estado.tablero)) {

			return 0;
		}

		return null;
	}

	private boolean haGanado(Turno jugador, Turno[][] tablero) {

		Turno[][] tableroGirado = (Turno[][])MatricesUtils.girarMatriz(tablero);

		return MatricesUtils.tieneMismoValor(jugador, tablero[0])
				|| MatricesUtils.tieneMismoValor(jugador, tablero[1])
				|| MatricesUtils.tieneMismoValor(jugador, tablero[2])
				|| MatricesUtils.tieneMismoValor(jugador, tableroGirado[0])
				|| MatricesUtils.tieneMismoValor(jugador, tableroGirado[1])
				|| MatricesUtils.tieneMismoValor(jugador, tableroGirado[2])
				|| MatricesUtils.tieneMismoValor(jugador,
						MatricesUtils.extraerDiagonal(tablero))
				|| MatricesUtils.tieneMismoValor(jugador,
						MatricesUtils.extraerDiagonal(tableroGirado));
	}

	private boolean tableroLleno(Turno[][] tablero) {

		return MatricesUtils.contieneAlgunNulo(tablero);
	}

	private boolean inputJugadorX(Estado estado) {

		Scanner in = new Scanner(System.in);

		String jugada = in.next();

		if (jugada.matches("[1-3][ABCabc]")) {

			Integer fila = Integer.parseInt(jugada.substring(0, 1)) - 1;
			String columnaLetra = jugada.substring(1);
			Integer columna = "aA".contains(columnaLetra) ? 0 : "bB"
					.contains(columnaLetra) ? 1 : 2;

			if (estado.tablero[fila][columna] == null) {

				estado.tablero[fila][columna] = jugadorO_Min;
				estado.valor = null;
				System.out.println("Juega O\r\n" + estado);
				return true;
			}

			System.out.println("La posición ya está ocupada.");
			return false;
		}

		System.out.println("La posición no es correcta. [1-3][A-C]");

		return false;
	}

	public void jugarPartida() {

		System.out.println("¿Quiere colocar la pieza central? S/N");
		Scanner in = new Scanner(System.in);

		turno = in.next().matches("[Ss]") ? Turno.O : Turno.X;

		Estado estado = new Estado();
		estado.tablero[1][1] = turno;
		estadoActual = estado;

		System.out.println("Inicio Partida\r\n" + estado);

		Integer valorFuncionUtilidad = null;

		while ((valorFuncionUtilidad = funcionUtilidad(estadoActual)) == null) {

			turno = turno.contrario();

			if (turno == jugadorX_Max) {

				valorMinimax(turno, estadoActual);

			} else {

				while (!inputJugadorX(estadoActual)) {

				}
			}
		}

		switch (valorFuncionUtilidad) {

		case 0:
			System.out.println("Final partida. Resultado: empate");
			break;
		case 1:
			System.out.println("Final partida. Resultado: gana X");
			break;
		case -1:
			System.out.println("Final partida. Resultado: gana O");
			break;

		}

		System.out.println(estadoActual.toString());
	}

	public static void main(String[] args) {

		TresEnRaya tresEnRaya = new TresEnRaya();
		tresEnRaya.jugarPartida();
	}
}
