package viewer;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import analyser.AnalisadorLexico;
import analyser.Buffer;
import analyser.HashTable;
import analyser.InputFile;
import analyser.OutputFile;


public class Main extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblArquivoSelecionado;
	private JPanel panelNorth;
	private JPanel panelSouth;
	private JScrollPane scrollPane;
	private JTextArea txtArquivoEntrada;
	private InputFile inputFile = new InputFile();
	private OutputFile outputFile = new OutputFile();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Analisador Léxico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		
		JLabel lblSelecioneUmArquivo = new JLabel("Selecione um arquivo:");
		panelNorth.add(lblSelecioneUmArquivo);
		
		JButton button = new JButton("...");
		panelNorth.add(button);
		
		panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		
		lblArquivoSelecionado = new JLabel("Arquivo selecionado:");
		panelSouth.add(lblArquivoSelecionado);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		txtArquivoEntrada = new JTextArea();
		scrollPane.setViewportView(txtArquivoEntrada);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();				
				int returnVal = fileChooser.showOpenDialog(null);
				if(returnVal != JFileChooser.CANCEL_OPTION){
					inputFile.openInputFile(fileChooser.getSelectedFile().getPath());
					outputFile.openOutputFile(inputFile.getFile().getPath());
					
					txtArquivoEntrada.setText("");
					int lineCount = 0;
					
					try {

						String line = "";						
						Buffer buffer;
						AnalisadorLexico analisador = new AnalisadorLexico();

						
						while((line = inputFile.readLine()) != null){							
							line += "\r\n";
							lineCount++;
							buffer = new Buffer(line);
							
							gravaArquivoDeSaida(lineCount, buffer);
							txtArquivoEntrada.append(line + "\n");	
							
							//Realiza a análise léxica linha por linha
							analisador.ValidaToken(buffer);
						}
						
						lblArquivoSelecionado.setText("Arquivo selecionado: "+inputFile.getFile().getPath()+". Número de linhas: ");
						
					} catch (Exception ex) {
						System.err.print("Erro na linha " + lineCount + ": " + ex.getMessage());
					} finally{
						inputFile.closeFile();
						outputFile.closeFile();
					}
				}
				
			}

			private void gravaArquivoDeSaida(int lineCount, Buffer buffer) throws IOException {				
				//escrevendo caracter por caracter
				outputFile.write(String.valueOf(lineCount)+". ");
				for (int i = 0; i < buffer.length(); i++) {
					outputFile.write(buffer.readCharacter());										
				}
			}
		});
	}
	
	private void iniciarPalavrasReservadas(){
		//Palavras reservadas do pascal simplificado
		HashTable.addPalavraReservada("and");
		HashTable.addPalavraReservada("array");
		HashTable.addPalavraReservada("begin");
		HashTable.addPalavraReservada("case");
		HashTable.addPalavraReservada("const");
		HashTable.addPalavraReservada("div");
		HashTable.addPalavraReservada("do");
		HashTable.addPalavraReservada("downto");
		HashTable.addPalavraReservada("else");
		HashTable.addPalavraReservada("end");
		HashTable.addPalavraReservada("file");
		HashTable.addPalavraReservada("for");
		HashTable.addPalavraReservada("function");
		HashTable.addPalavraReservada("goto");
		HashTable.addPalavraReservada("if");
		HashTable.addPalavraReservada("label");
		HashTable.addPalavraReservada("model");
		HashTable.addPalavraReservada("nil");
		HashTable.addPalavraReservada("not");
		HashTable.addPalavraReservada("of");
		HashTable.addPalavraReservada("or");
		HashTable.addPalavraReservada("packed");
		HashTable.addPalavraReservada("program");
		HashTable.addPalavraReservada("record");
		HashTable.addPalavraReservada("repeat");
		HashTable.addPalavraReservada("set");
		HashTable.addPalavraReservada("then");
		HashTable.addPalavraReservada("to");
		HashTable.addPalavraReservada("type");
		HashTable.addPalavraReservada("until");
		HashTable.addPalavraReservada("var");
		HashTable.addPalavraReservada("while");
		HashTable.addPalavraReservada("with");
	}
}
