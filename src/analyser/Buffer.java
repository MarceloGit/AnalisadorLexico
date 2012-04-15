package analyser;

public class Buffer {
	private char[] caracteres = null;
	private int index = -1;
	
	public Buffer(char[] caracteres){
		this.caracteres = caracteres;
		index = 0;
	}
	
	public Buffer(String line){
		caracteres = line.toCharArray();
		index = 0;
	}
	
	public char readCharacter(){
		return caracteres[index++];		
	}
	
	public char readCharacterAt(int i){
		return caracteres[i];
	}
	
	public char lookAhead(){
		return caracteres[index + 1];		
	}
	
	public int length(){
		return caracteres.length;
	}	
	
	public boolean isEnd(){
		return caracteres.length == index;
	}

}
