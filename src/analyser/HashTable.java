package analyser;

import java.util.ArrayList;

public class HashTable {
	private static ArrayList<String> listaPalavrasReservadas = new ArrayList<String>();
	
	public static void addPalavraReservada(String palavra){
		listaPalavrasReservadas.add(palavra);
	}
	
	public static boolean isReservada(String palavra){
		for (int i = 0; i < listaPalavrasReservadas.size(); i++) {
			if(listaPalavrasReservadas.get(i).equals(palavra)){
				return true;
			}				
		}
		return false;
	}
}

