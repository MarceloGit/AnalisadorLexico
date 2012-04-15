package analyser;

import java.util.ArrayList;

public class HashTable {
	ArrayList<String> listaPalavrasReservadas;
	
	public HashTable(){
		listaPalavrasReservadas = new ArrayList<String>();
		
		//Palavras reservadas do pascal simplificado
		listaPalavrasReservadas.add("and");
		listaPalavrasReservadas.add("array");
		listaPalavrasReservadas.add("begin");
		listaPalavrasReservadas.add("case");
		listaPalavrasReservadas.add("const");
		listaPalavrasReservadas.add("div");
		listaPalavrasReservadas.add("do");
		listaPalavrasReservadas.add("downto");
		listaPalavrasReservadas.add("else");
		listaPalavrasReservadas.add("end");
		listaPalavrasReservadas.add("file");
		listaPalavrasReservadas.add("for");
		listaPalavrasReservadas.add("function");
		listaPalavrasReservadas.add("goto");
		listaPalavrasReservadas.add("if");
		listaPalavrasReservadas.add("label");
		listaPalavrasReservadas.add("model");
		listaPalavrasReservadas.add("nil");
		listaPalavrasReservadas.add("not");
		listaPalavrasReservadas.add("of");
		listaPalavrasReservadas.add("or");
		listaPalavrasReservadas.add("packed");
		listaPalavrasReservadas.add("program");
		listaPalavrasReservadas.add("record");
		listaPalavrasReservadas.add("repeat");
		listaPalavrasReservadas.add("set");
		listaPalavrasReservadas.add("then");
		listaPalavrasReservadas.add("to");
		listaPalavrasReservadas.add("type");
		listaPalavrasReservadas.add("until");
		listaPalavrasReservadas.add("var");
		listaPalavrasReservadas.add("while");
		listaPalavrasReservadas.add("with");
	}
	
	public void addPalavraReservada(String palavra){
		listaPalavrasReservadas.add(palavra);
	}
	
	public boolean isReservada(String palavra){
		for (int i = 0; i < listaPalavrasReservadas.size(); i++) {
			if(listaPalavrasReservadas.get(i).equals(palavra)){
				return true;
			}				
		}
		return false;
	}
}

