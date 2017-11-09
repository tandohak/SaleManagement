package kr.or.dgit.SaleManagement.jdbc_setting;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class jdbcSetting extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jdbcSetting frame = new jdbcSetting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public jdbcSetting() {
		setTitle("Database Setting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnInit = new JButton(new BtnAction("초기화"));
		contentPane.add(btnInit);
		
		JButton btnExport = new JButton(new BtnAction("백업"));
		contentPane.add(btnExport);
		
		JButton btnImport = new JButton(new BtnAction("복원"));
		contentPane.add(btnImport);
	}

}
