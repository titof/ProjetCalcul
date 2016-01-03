

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TestForm {
	static EG1 parser = null;
	
	public static void main(String[] args) {
		JFrame guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Example GUI");
        guiFrame.setSize(300,600);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        //The first JPanel contains a label and text field for entering the expression
        final JPanel inputPanel = new JPanel();
        JLabel inputLbl = new JLabel("Enter your Expression: ");
        final JTextField inputText = new JTextField("");
        inputText.setColumns(20);
        
        inputPanel.add(inputLbl);
        inputPanel.add(inputText);
        
		// The bottom Panel contains a text box for showing results of a parse
        final JTextArea outputText = new JTextArea();
        outputText.setColumns(20);
        outputText.setRows(10);
        
        JPanel mainPanel = new JPanel();
        mainPanel.add(inputPanel);
        mainPanel.add(outputText);
        
        
        //Christophe
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem file = new JMenuItem("Sélectionner un fichier");
        
        menu.add(file);
        bar.add(menu);
        guiFrame.setJMenuBar(bar);
        
        final JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        
        file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	   
            	int returnVal = fileChooser.showOpenDialog(guiFrame);

            	if (returnVal == JFileChooser.APPROVE_OPTION) {
            		File selectedFile = fileChooser.getSelectedFile();
            		
            		try {
						InputStream f = new FileInputStream(selectedFile);
						
						InputStreamReader fr = new InputStreamReader(f);
						
						BufferedReader br = new BufferedReader(fr);
						String chaine="";
						String ligne;
						while ((ligne=br.readLine())!=null){
							String sentence = ligne;
							inputText.setText(sentence);
			                // Put parens around sentence so that parser knows scope
			                sentence = "(" + sentence + ")";
			                InputStream is = new ByteArrayInputStream(sentence.getBytes());
			                if(parser == null) parser = new EG1(is);
			                else EG1.ReInit(is);
			                try
			                {
			                  switch (EG1.start())
			                  {
			                    case 0 :
			                    	outputText.setText("expression parsed ok.");
			                    break;
			                    default :
			                    break;
			                  }
			                }
			                catch (Exception e)
			                {
			                  outputText.setText("error in expression.\n"+
			                		  				e.getMessage());
			                }
			                catch (Error e)
			                {
			                 outputText.setText("error in expression.\n"+
			    		  						   e.getMessage());
			                }
			                finally
			                {
			                  
			                }
							chaine+=ligne+"\n";
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
            	}
            }       
        });
        
     
        // Textfield Action Listener callback - executed when user hits "return"
        inputText.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
                String sentence = inputText.getText();
                // Put parens around sentence so that parser knows scope
                sentence = "(" + sentence + ")";
                InputStream is = new ByteArrayInputStream(sentence.getBytes());
                if(parser == null) parser = new EG1(is);
                else EG1.ReInit(is);
                try
                {
                  switch (EG1.start())
                  {
                    case 0 :
                    	outputText.setText("expression parsed ok.");
                    break;
                    default :
                    break;
                  }
                }
                catch (Exception e)
                {
                  outputText.setText("error in expression.\n"+
                		  				e.getMessage());
                }
                catch (Error e)
                {
                 outputText.setText("error in expression.\n"+
    		  						   e.getMessage());
                }
                finally
                {
                  
                }
        	}
        });

        guiFrame.add(mainPanel, BorderLayout.NORTH);
        guiFrame.add(outputText, BorderLayout.CENTER);
        // Layout all component panels
        guiFrame.pack();
        
        //make sure the JFrame is visible
        guiFrame.setVisible(true);
	}

}
