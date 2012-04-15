package analyser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile {
	private File outputFile;
	private BufferedWriter writer;

	/*
	 * Gera um arquivo de saída com base no caminho do arquivo de entrada
	 */	
	public void openOutputFile(String path){
		String parent;
		String filename;
		String extension;
		try {
			
			outputFile = new File(path);
			parent = outputFile.getParent();
			filename = outputFile.getName().substring(0, outputFile.getName().indexOf('.'));
			extension = outputFile.getName().substring(outputFile.getName().indexOf('.'), outputFile.getName().length());
			outputFile = new File(parent + "\\" + filename + "_out" + extension);			
			
			writer = new BufferedWriter(new FileWriter(outputFile));			
		} catch (IOException e) {
			System.out.println("OutputFile.openOutputFile: " + e.getMessage());
		}
	}
	
	public void write(String line) throws IOException{
		if (outputFile == null) 
			throw new IOException("Arquivo de saída não inicializado!");
		else
			writer.write(line);
	}
	
	public void write(char c) throws IOException{
		if (outputFile == null) 
			throw new IOException("Arquivo de saída não inicializado!");
		else
			writer.write(c);
	}

	
	public File getFile(){
		return outputFile;
	}
	
	public void closeFile(){
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Outputfile.closeFile: " + e.getMessage());
		}
	}
}
