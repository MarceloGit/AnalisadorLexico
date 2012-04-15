package analyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InputFile {
	private File inputFile;
	private BufferedReader reader;
	
	public void openInputFile(String path){
		try {
			inputFile = new File(path);
			reader = new BufferedReader(new FileReader(inputFile));			
		} catch (IOException e) {
			System.out.println("InputFile.openInputFile: " + e.getMessage());
		}
	}
	
	public String readLine() throws IOException{
		if (inputFile == null) 
			throw new IOException("Arquivo de entrada não inicializado!");
		else
			return reader.readLine();		
	}	
	
	public File getFile(){
		return inputFile;
	}
	
	public void closeFile(){
		try {
			reader.close();	
		} catch (IOException e) {
			System.out.println("InputFile.closeFile: " + e.getMessage());
		}
		
	}
	
}
