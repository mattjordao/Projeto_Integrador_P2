package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;



public class telaEntrada extends JFrame {

	private JPanel contentPane;
	private JTextField tid;
	private JTextField tnome;
	private JTextField tcpf;
	private JTextField tplaca;
	private JTextField ttipo;
	private JTable tdados;
	private JTextField tpesquisa;	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaEntrada frame = new telaEntrada();
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
	
	public telaEntrada() {
		setTitle("Sistema de Estacionamento Simplificado");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 665);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel.setBounds(12, 73, 76, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.BOLD, 22));
		lblCpf.setBounds(29, 104, 58, 26);
		contentPane.add(lblCpf);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Arial", Font.BOLD, 22));
		lblPlaca.setBounds(18, 132, 63, 26);
		contentPane.add(lblPlaca);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Arial", Font.BOLD, 22));
		lblTipo.setBounds(29, 161, 76, 26);
		contentPane.add(lblTipo);
		
		tnome = new JTextField();
		tnome.setBounds(95, 79, 202, 20);
		contentPane.add(tnome);
		tnome.setColumns(10);
		
		tcpf = new JTextField();
		tcpf.setBounds(95, 110, 202, 20);
		contentPane.add(tcpf);
		tcpf.setColumns(10);
		
		tplaca = new JTextField();
		tplaca.setBounds(95, 138, 202, 20);
		contentPane.add(tplaca);
		tplaca.setColumns(10);
		
		JButton btnNewButton = new JButton("Entrada");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				if (tnome.getText().equals("") || tcpf.getText().equals("") || tplaca.getText().equals("") || 
						ttipo.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Preencha os campos!");
				}else {
				
				
				try {
					Connection con = Main.getConnection();
					String sql = "insert into clientes(nome, cpf, placa, tipo) values (?, ?, ?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tnome.getText());
					stmt.setString(2, tcpf.getText());
					stmt.setString(3, tplaca.getText());
					stmt.setString(4, ttipo.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Entrada Realizada!");
					
					tnome.setText("");
					tcpf.setText("");
					tplaca.setText("");
					ttipo.setText("");
					tid.setText("");
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 22));
		btnNewButton.setBounds(76, 229, 202, 47);
		contentPane.add(btnNewButton);
		
		ttipo = new JTextField();
		ttipo.setColumns(10);
		ttipo.setBounds(95, 167, 202, 20);
		contentPane.add(ttipo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 338, 614, 188);
		contentPane.add(scrollPane);
		
		tdados = new JTable();
		tdados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Ticket", "Nome", "CPF", "Placa", "Tipo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tdados);
		
		JButton btnNewButton_1 = new JButton("Gerar Relat\u00F3rio");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Main.getConnection();					
					String sql = "select * from clientes";	
					
					PreparedStatement stmt = con.prepareStatement(sql);		
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel tabela = (DefaultTableModel) tdados.getModel();
					
					tabela.setNumRows(0);
					
					while (rs.next()) {			

						tabela.addRow(new Object[]{rs.getString("ID"), rs.getString("Nome"), rs.getString("CPF"), rs.getString("Placa"), rs.getString("Tipo")});
						
					}						

					rs.close();
					con.close();					

					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 30));
		btnNewButton_1.setBounds(194, 537, 262, 70);
		contentPane.add(btnNewButton_1);
		
		JButton btnSaida = new JButton("Saida");
		btnSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tid.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "Insira os dados!");

				}else {
				
				try {
					
					Connection con = Main.getConnection();
					
					String sql = "delete from clientes where id =?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tid.getText());
					
					tnome.setText("");
					tcpf.setText("");
					tplaca.setText("");
					ttipo.setText("");
					tid.setText("");
					
					stmt.execute();
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Saida Realizada!");

					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		btnSaida.setForeground(Color.BLACK);
		btnSaida.setFont(new Font("Arial", Font.BOLD, 22));
		btnSaida.setBounds(381, 229, 202, 47);
		contentPane.add(btnSaida);
		
		JLabel lblId = new JLabel("Ticket:");
		lblId.setFont(new Font("Arial", Font.BOLD, 28));
		lblId.setBounds(369, 66, 103, 35);
		contentPane.add(lblId);
		
		tpesquisa = new JTextField();
		tpesquisa.setColumns(10);
		tpesquisa.setBounds(479, 76, 94, 26);
		contentPane.add(tpesquisa);
		
		JButton btnAbrir = new JButton("Abrir Dados");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
								
				try {
					Connection con = Main.getConnection();
					
					String sql = "select * from clientes where id =?";

					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tpesquisa.getText());					

					ResultSet rs = stmt.executeQuery();					

					while (rs.next()) {					

						tid.setText(rs.getString("ID"));
						tnome.setText(rs.getString("Nome"));
						tcpf.setText(rs.getString("cpf"));						
						tplaca.setText(rs.getString("placa"));
						ttipo.setText(rs.getString("tipo"));
											

					}							

					rs.close();
					con.close();
					
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnAbrir.setFont(new Font("Arial", Font.BOLD, 24));
		btnAbrir.setBounds(386, 126, 187, 35);
		contentPane.add(btnAbrir);
		
		JLabel lblVaga = new JLabel("Ticket:");
		lblVaga.setFont(new Font("Arial", Font.BOLD, 22));
		lblVaga.setBounds(58, 30, 76, 26);
		contentPane.add(lblVaga);
		
		tid = new JTextField();
		tid.setEditable(false);
		tid.setColumns(10);
		tid.setBounds(144, 36, 103, 20);
		contentPane.add(tid);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(321, 7, 303, 294);
		contentPane.add(panel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBackground(SystemColor.activeCaption);
		panel_1_1.setBounds(8, 7, 303, 294);
		contentPane.add(panel_1_1);
	}
}

