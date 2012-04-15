package analyser;

import java.util.ArrayList;

public class ListaToken {
	private ArrayList<Token> listaToken = new ArrayList<Token>();
	
	public void addToken(Token token){
		listaToken.add(token);
	}
	
	public ArrayList<Token> getLista(){
		return listaToken;
	}
}
