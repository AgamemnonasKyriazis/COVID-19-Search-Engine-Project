package client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import mainengine.MainEngine;

public class Client implements ActionListener{
	
	static JFileChooser fc = new JFileChooser();
	
	static JTextArea jta;
	static JScrollPane jsp;
	
	static JTextField userText;
	static JButton searchButton;
	static JButton readButton;
	static JButton writeButton;
	
	static MainEngine mainEngine;
	
	public static void main(String[] args) {
		mainEngine = new MainEngine();
		setupGUI();

	}

	private String selectFolderGUI() {
		String directory = null;
		int r;
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		do {
			r = fc.showOpenDialog(null);
		} while (r == JFileChooser.ERROR_OPTION);
		try {
			directory = fc.getSelectedFile().getCanonicalPath().toString();
			System.out.println(directory);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return directory;
	}

	private static void setupGUI() {
		JFrame frame = new JFrame();
		frame.setSize(500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("NOODLE");
		label.setBounds(220, 20, 80, 25);
		panel.add(label);
		
		userText = new JTextField();
		userText.setBounds(150, 50, 185, 25);
		panel.add(userText);
		
		searchButton = new JButton("Search");
		readButton = new JButton("Read Index");
		writeButton = new JButton("Write Index");
		
		searchButton.setBounds(205, 80, 80, 30);
		readButton.setBounds(350, 30, 100, 30);
		writeButton.setBounds(350, 70, 100, 30);
		
		searchButton.addActionListener(new Client());
		readButton.addActionListener(new Client());
		writeButton.addActionListener(new Client());
		
		panel.add(searchButton);
		panel.add(readButton);
		panel.add(writeButton);
		
		jta = new JTextArea();
		jta.setEditable(false);
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(40, 200, 400, 200);
		
		panel.add(jsp);
		
		frame.setVisible(true);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object abstractButton = e.getSource();
		if (abstractButton.equals(searchButton)) {
			String text = userText.getText();
			String ret = mainEngine.searchIndex(text);
			jta.setText(ret);
			userText.setText(null);	
		} else if (abstractButton.equals(writeButton)) {
			String indexPath;
			String dataPath;
			indexPath = selectFolderGUI();
			dataPath = selectFolderGUI();
			mainEngine.writeIndex(indexPath, dataPath);
		} else if (abstractButton.equals(readButton)) {
			String indexPath;
			indexPath = selectFolderGUI();
			mainEngine.readIndex(indexPath);
		}
	}
}