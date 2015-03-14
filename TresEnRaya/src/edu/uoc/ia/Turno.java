package edu.uoc.ia;

public enum Turno {

	O , X;

	public Turno contrario(){
		
		return this == O ? X : O;
	}
}
