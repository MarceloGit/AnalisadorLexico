package analyser;

public class AnalisadorLexico {
	
	private Estado 	estado;
	private String 	palavra; //palavra que é montada pelo analisador
	private char 	ch;  	 //caractere lido pelo analisador
	
	//FINAL
	private final char UNDERSCORE = '_';
	
	public AnalisadorLexico(){
		estado = Estado.INICIAL;
	}
	
	public void ValidaToken(Buffer buffer) throws Exception{
		
		while(!buffer.isEnd()){
			ch = buffer.readCharacter();
			
			switch (estado) {
			
				case INICIAL:
					
					break;
					
				//Operadores relacionais
				case LOWER_THAN:
					break;
	
				case LOWER_OR_EQUALS:
					break;
					
				case EQUALS:			
					break;
					
				case NOT_EQUALS:
					break;
				
				case GREATER_THAN:
					break;
					
				case GREATER_OR_EQUALS:		
			
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
		if(Character.isLetter(ch) || ch == UNDERSCORE){
			palavra += ch;
			return Estado.IDENTIFICADOR;
		}			
		
		return Estado.INICIAL;
	}
	
	public enum Estado {
		INICIAL, 
		IDENTIFICADOR,
		LOWER_THAN, LOWER_OR_EQUALS, EQUALS, NOT_EQUALS, GREATER_THAN, GREATER_OR_EQUALS;
	}

}
