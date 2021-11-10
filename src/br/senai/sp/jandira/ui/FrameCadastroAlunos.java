package br.senai.sp.jandira.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import br.senai.sp.jandira.model.Aluno;
import br.senai.sp.jandira.model.Periodo;
import br.senai.sp.jandira.repository.AlunoRepository;

public class FrameCadastroAlunos extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatricula;
	private JTextField txtNome;
	private JLabel lblPeriodo;
	
	//CRIAR UM CONTADOR PARA CONTROLAR A POSIÇÃO
	private int posicao;
	
	public FrameCadastroAlunos() {
		setTitle("Cadastro de Alunos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matr\u00EDcula: ");
		lblMatricula.setBounds(10, 32, 69, 14);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(76, 29, 157, 20);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome: \r\n");
		lblNome.setBounds(10, 75, 69, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(76, 72, 157, 20);
		contentPane.add(txtNome);
		
		lblPeriodo = new JLabel("Per\u00EDodo");
		lblPeriodo.setBounds(10, 114, 69, 14);
		contentPane.add(lblPeriodo);
		
		JComboBox comboPeriodo = new JComboBox();
		
		//CRIAR O DEFAULTCOMOBOX, QUE É UM VETOR
		//QUE CARREGA OS DADOS QUE SERÃO EXIBIDOS NO COMBO
		DefaultComboBoxModel<String> comboModelPeriodo= new DefaultComboBoxModel<String>();
		
		//Carregar o comboModel com as descrições dos Períodos
		for (Periodo periodo : Periodo.values()) {
			comboModelPeriodo.addElement(periodo.getDescricao());
		}
		
		//comboPeriodo.setModel(new DefaultComboBoxModel(Periodo.values()));
		comboPeriodo.setModel(comboModelPeriodo);
		comboPeriodo.setBounds(76, 110, 99, 22);
		contentPane.add(comboPeriodo);
		
		JButton btnSalvar = new JButton("Salvar Aluno");
		btnSalvar.setBounds(28, 170, 205, 59);
		contentPane.add(btnSalvar);
		
		JLabel lblNewLabel_1 = new JLabel("Lista de Alunos: ");
		lblNewLabel_1.setBounds(285, 29, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 63, 147, 166);
		contentPane.add(scrollPane);
		
		JList listAluno = new JList();
		scrollPane.setViewportView(listAluno);
		
		//CRIAR O MODEL PARA DEFINIR A LISTA DOS ALUNOS
		DefaultListModel<String> modelAlunos = new DefaultListModel<String>();
		
		//DEFINIR O MODELALUNO COMO SENDO UM MODEL DO NOSSO LIST
		listAluno.setModel(modelAlunos);
		
		//CRIAR UMA TURMA QUE É UM REPOSITÓRIO DE ALUNOS
		AlunoRepository turma = new AlunoRepository(3);
		
		//COLOCANDO FUNÇÃO NO BOTÃO (LISTENER)
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
		//CRIANDO UM ALUNO
				Aluno aluno = new Aluno();
				
				aluno.setNome(txtNome.getText());
				aluno.setMatricula(txtMatricula.getText());
				
				turma.gravar(aluno, 0);
				
				posicao++;
				
				modelAlunos.addElement(aluno.getNome());
				
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
			}
		});
	}
}
