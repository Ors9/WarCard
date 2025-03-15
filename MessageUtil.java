package Maman11Q1;

import java.awt.Font;

import javax.swing.JTextArea;

public class MessageUtil {
	// function helper to do the messages and make them look nice ;)
	public static void showStyleJOptionPaneMessage(String title, String message) {
		JTextArea textArea = new JTextArea(message);

		// Set the font
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

		// Make it non-editable, no border, and transparent to look like a label
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setBorder(null);

		// Enable line wrapping
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setColumns(30);

		System.out.println(title + ": " + message);
		// Display the message dialog
		// JOptionPane.showMessageDialog(null, textArea, title,
		// JOptionPane.INFORMATION_MESSAGE);
	}
}
