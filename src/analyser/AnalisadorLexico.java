package analyser;

import java.util.regex.Pattern;

public class AnalisadorLexico {
	
	private Estado 	estado;
	private String 	palavra; //palavra que é montada pelo analisador
	private char 	ch;  	 //caractere lido pelo analisador
	private Buffer 	buffer;
	private boolean isComentarioFlag1 = false; //   --->   "{"
	/*
	 * A flag "isComentarioFlag2" deve ser "resetada" antes do início da leitura de cada linha.
	 */
	private boolean isComentarioFlag2 = false; //   --->   "//"
	private ListaToken listaToken;
	private HashTable hashTable;
	
	//FINAL
	private final char UNDERLINE = '_';
	private final char ABRECOMENTARIO = '{';
	private final char FECHACOMENTARIO = '}';
	private final char BARRA = '/';
	
	public AnalisadorLexico(){
		estado 		= Estado.INICIAL;
		listaToken 	= new ListaToken();
		hashTable 	= new HashTable();
	}
	
	public void ValidaToken(Buffer buffer) throws Exception{
		this.buffer = buffer;
		isComentarioFlag2 = false; //Ver comentário na declaração
		
		while(!buffer.isEnd()){
			ch = buffer.readCharacter();
			
			switch (estado) {
			
				case INICIAL:					
					if(!isComentarioFlag1){
						//trata os caracteres
					}else{
						while((isComentarioFlag1 || isComentarioFlag2) && (!buffer.isEnd())){
							ch = buffer.readCharacter();
							estado = getEstadoByCaractere(ch);
						}
					}
					break;
					
				case LETRA:
					break;
					
				case NUMERO:
					break;
					
				case WHITESPACE:
					ValidarPalavra(palavra);
					estado = Estado.INICIAL;
					break;
					
				//Operadores relacionais
				case LOWER_THAN:		// '<'
					break;
	
				case LOWER_OR_EQUALS:	// '<='
					break;
					
				case EQUALS:			// '=='
					break;
					
				case NOT_EQUALS:		// '!=' 
					break;
				
				case GREATER_THAN:		// '>'
					break;
					
				case GREATER_OR_EQUALS: // '>='			
			
				default:
					break;
			}			
		}
	}
	
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	public Estado getEstado(){
		return estado;
	}
	
	public Estado getEstadoByCaractere(char ch){
		if(Character.isLetter(ch) || ch == UNDERLINE){
			palavra += ch;
			return Estado.LETRA;
		}
		
		if(Character.isDigit(ch)){
			palavra += ch;
			return Estado.NUMERO;
		}
		
		if(isCaracterComentario(ch)){
			isComentarioFlag1 = true;
			return Estado.INICIAL;
		}
		
		if(ch == FECHACOMENTARIO){
			isComentarioFlag1 = false;
			return Estado.INICIAL;
		}
		
		return Estado.INICIAL;
	}
	
	
	private void ValidarPalavra(String palavra) throws Exception{
		Token token = null;
		
		if(hashTable.isReservada(palavra)){
			token = new Token("PR", palavra);
		}
		
		if(palavra.matches("[0-9]{"+palavra.length()+"}")){
			token = new Token("num", palavra);
		}
		
		
		//Por fim, adiciona o token na lista
		if(token != null){
			listaToken.addToken(token);
		}	
	}
	
	private boolean isCaracterComentario(char ch){
		if(ch == BARRA){
			if(buffer.lookAhead() == BARRA){
				return true;
			}							
		}
		
		if(ch == ABRECOMENTARIO){
			return true;			
		}
		
		return false;		
	}
	
	public enum Estado {
		INICIAL, 
		LETRA,
		NUMERO,
		WHITESPACE,
		LOWER_THAN, LOWER_OR_EQUALS, EQUALS, NOT_EQUALS, GREATER_THAN, GREATER_OR_EQUALS;
	}

}
