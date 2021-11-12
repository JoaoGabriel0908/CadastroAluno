package br.senai.sp.jandira.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
		setBounds(100, 100, 500, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matr\u00EDcula: ");
		lblMatricula.setBounds(10, 32, 69, 14);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(76, 29, 157, 31);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome: \r\n");
		lblNome.setBounds(10, 75, 69, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(76, 72, 157, 31);
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
		comboPeriodo.setBounds(76, 110, 157, 22);
		contentPane.add(comboPeriodo);
		
		JButton btnSalvar = new JButton("Salvar Aluno");
		btnSalvar.setBounds(28, 143, 205, 37);
		contentPane.add(btnSalvar);
		
		JLabel lblNewLabel_1 = new JLabel("Lista de Alunos: ");
		lblNewLabel_1.setBounds(285, 29, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 63, 147, 167);
		contentPane.add(scrollPane);
		
		JList listAluno = new JList();
		
		scrollPane.setViewportView(listAluno);
		
		//CRIAR O MODEL PARA DEFINIR A LISTA DOS ALUNOS
		DefaultListModel<String> modelAlunos = new DefaultListModel<String>();
		
		//DEFINIR O MODELALUNO COMO SENDO UM MODEL DO NOSSO LIST
		listAluno.setModel(modelAlunos);
		
		JButton btnExibir = new JButton("Exibir alunos");
		btnExibir.setBounds(28, 191, 205, 37);
		contentPane.add(btnExibir);
		
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
				//DEFINIR O HORÁRIO QUE O ALUNO ESTUDA
				aluno.setPeriodo(definirPeriodo(comboPeriodo.getSelectedIndex()));
				
				turma.gravar(aluno, posicao);
				
				posicao++;
				
				//ADICIONAR O NOME DO ALUNO AO MODEL DA LISTA
				modelAlunos.addElement(aluno.getNome());
				
				if (posicao == turma.getTamanho()) {
					btnSalvar.setEnabled(false);
					JOptionPane.showMessageDialog(null, "A Turma ja esta cheia!", "OPSS!!!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnExibir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (Aluno aluno : turma.listarTodos()) {
					System.out.println(aluno.getMatricula());
					System.out.println(aluno.getNome());
					System.out.println(aluno.getPeriodo().getDescricao());
					System.out.println("-------------------");
				}
			}
		});
		listAluno.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				Aluno aluno = turma.listarAluno(listAluno.getSelectedIndex());
				txtMatricula.setText(aluno.getMatricula());
				txtNome.setText(aluno.getNome());
				
				comboPeriodo.setSelectedIndex(aluno.getPeriodo().ordinal());
				
			}
		});	
	}
	//MÉTODO QUE IRÁ DEFINIR O PERIODO DO ALUNO
	//TODO MÉTODO TEM QUE TER UM VERBO PARA ELE FAZER UMA AÇÃO
	private Periodo definirPeriodo(int periodoSelecionado) {
		
		if (periodoSelecionado == 0) {
			return Periodo.MANHA;
		}
		else if (periodoSelecionado == 1) {
			return Periodo.TARDE;
		}
		else if (periodoSelecionado == 2) {
			return Periodo.NOITE;
		}
		else if (periodoSelecionado == 3) {
			return Periodo.SABADO;
		}
		else {
			return Periodo.ONLINE;
		}
		
	}
}
