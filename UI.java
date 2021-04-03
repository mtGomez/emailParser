import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class UI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Parser emailParser = new Parser();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setIgnoreRepaint(true);
		frame.setBounds(100, 100, 700, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TextArea inputTextArea = new TextArea();
		inputTextArea.setBounds(10, 38, 250, 358);
		
		JLabel lblInput = new JLabel("INPUT");
		lblInput.setBounds(10, 5, 67, 30);
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblOutput = new JLabel("OUTPUT");
		lblOutput.setBounds(423, 5, 75, 30);
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNemails = new JLabel("Nº EMAILS");
		lblNemails.setBounds(293, 37, 102, 16);
		lblNemails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JTextPane panelNemails = new JTextPane();
		panelNemails.setBounds(293, 65, 94, 20);
		
		JTextPane panelOutput = new JTextPane();
		panelOutput.setBounds(424, 38, 250, 357);
		
		JButton btnGetEmails = new JButton("Get emails");
		btnGetEmails.setBounds(293, 96, 93, 23);
		btnGetEmails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text[] = inputTextArea.getText().split("\\r?\\n");
			    ArrayList<String> arrList = new ArrayList<>(Arrays.asList(text)) ;
			    try {
					emailParser.extractEmail(arrList, panelOutput, panelNemails);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGetEmails.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JButton btnCopyToClipboard = new JButton("Copy to clipboard");
		btnCopyToClipboard.setBounds(537, 10, 137, 23);
		btnCopyToClipboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StringSelection stringSelection = new StringSelection(emailParser.getOutput());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		btnCopyToClipboard.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblInput);
		frame.getContentPane().add(lblOutput);
		frame.getContentPane().add(btnCopyToClipboard);
		frame.getContentPane().add(inputTextArea);
		frame.getContentPane().add(lblNemails);
		frame.getContentPane().add(panelNemails);
		frame.getContentPane().add(btnGetEmails);
		frame.getContentPane().add(panelOutput);
	}
}
