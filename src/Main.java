import java.io.*;
import java.util.*;

public class Main {
	static List<Aluno> alunos = new ArrayList<Aluno>();
	
	public static Boolean cadastraAluno(String nomeAluno, String nomeMateria, String situacao) {
		
		List<String> materiasAprovadas = new ArrayList<>();
		List<String> materiasReprovadas = new ArrayList<>();
		
		for(int i=0;i<alunos.size();i++) {//fazendo a busca pelo aluno na lista
			if(alunos.get(i).getNome().equals(nomeAluno)) { // se o aluno já existir na lista
				
				if(alunos.get(i).getListaMateriasAprovadas() != null)
					materiasAprovadas = alunos.get(i).getListaMateriasAprovadas();
				
				if(alunos.get(i).getListaMateriasReprovadas() != null)
					materiasReprovadas = alunos.get(i).getListaMateriasReprovadas();
				
				if(situacao.equals("aprovado")) {
					materiasAprovadas.add(nomeMateria);
					alunos.get(i).setListaMateriasAprovadas(materiasAprovadas); // sistema atualiza a lista de matérias inserindo a matéria nova
				}
				else if(situacao.equals("reprovado")) {
					materiasReprovadas.add(nomeMateria);
					alunos.get(i).setListaMateriasReprovadas(materiasReprovadas);
				}
				return true;
			}
		}
		
		//se o aluno não estiver na lista ele o insere:
		
		Aluno novoAluno = new Aluno();
		novoAluno.setNome(nomeAluno);
		if(situacao.equals("aprovado")) {
			materiasAprovadas.add(nomeMateria);
			novoAluno.setListaMateriasAprovadas(materiasAprovadas); // sistema atualiza a lista de matérias inserindo a matéria nova
		}
		else {
			materiasReprovadas.add(nomeMateria);
			novoAluno.setListaMateriasReprovadas(materiasReprovadas);
		}
		
		alunos.add(novoAluno); // inserindo o aluno na lista;
		
		return true;
	}
	
	public static void printSituacaoAluno() {
		for(int i=0; i<alunos.size(); i++) {
			
			if(alunos.get(i).getListaMateriasAprovadas() == null)
				System.out.print(alunos.get(i).getNome()+"! 0 aprovado(s) (");
			else
				System.out.print(alunos.get(i).getNome()+"! "+ alunos.get(i).getListaMateriasAprovadas().size() +"aprovado(s) (");
			
			if(alunos.get(i).getListaMateriasAprovadas() != null) {
				for(int j=0; j<alunos.get(i).getListaMateriasAprovadas().size(); j++) { //imprimindo as matérias aprovadas
					if(j == alunos.get(i).getListaMateriasAprovadas().size()-1)
						System.out.print(alunos.get(i).getListaMateriasAprovadas().get(j));
					else
						System.out.print(alunos.get(i).getListaMateriasAprovadas().get(j)+", ");
				}				
			}
			else {
				System.out.print("vazio");
			}
			
			if(alunos.get(i).getListaMateriasReprovadas() == null)
				System.out.print(")! 0 reprovado(s) (");
			else
				System.out.print(")!"+alunos.get(i).getListaMateriasReprovadas().size()+" reprovado(s) (");
			
			if(alunos.get(i).getListaMateriasReprovadas() != null) {
				for(int j=0; j<alunos.get(i).getListaMateriasReprovadas().size(); j++) { //imprimindo as matérias reprovadas
					if(j == alunos.get(i).getListaMateriasReprovadas().size()-1)
						System.out.print(alunos.get(i).getListaMateriasReprovadas().get(j));
					else
						System.out.print(alunos.get(i).getListaMateriasReprovadas().get(j)+", ");
						
				}				
			}
			else {
				System.out.print("vazio");
			}
			System.out.println(")!");
		}
		
	}

	public static void populandoListAlunos() {
		
		cadastraAluno("Samuel", "Planejamento", "reprovado");
		cadastraAluno("Samuel", "Libras", "aprovado");
		cadastraAluno("Samuel", "Banco de dados", "aprovado");
		cadastraAluno("Samuel", "Grafos", "reprovado");
		
		cadastraAluno("Silvio Santos", "Origami de Aviaozinho", "aprovado");
		cadastraAluno("Silvio Santos", "Jequiti", "aprovado");
		
		cadastraAluno("Latino", "Composicao Autoral", "reprovado");
		cadastraAluno("Latino", "Canto e Afinacao", "reprovado");
	}

	public static void cadastrandoViaArquivoTexto(String caminho) { // parte do codigo obtido em: http://www.guj.com.br/t/abrir-e-ler-arquivos-no-java/64324/3
		try{
	         BufferedReader br = new BufferedReader(new FileReader(caminho));
	         while(br.ready()){
	            String linha = br.readLine(); 
	            String[] parametrosDeCadastroDeAluno = new String[3];
	            parametrosDeCadastroDeAluno = linha.split(";"); //quebrando a string em 3 partes NOME - MATERIA - SITUACAO
	            
	            //System.out.println(parametrosDeCadastroDeAluno[0] + " materia "+ parametrosDeCadastroDeAluno[1] + " status "+ parametrosDeCadastroDeAluno[2]);
	            
	            cadastraAluno(parametrosDeCadastroDeAluno[0], parametrosDeCadastroDeAluno[1], parametrosDeCadastroDeAluno[2]);
	         }
	         br.close();
	      }catch(IOException ioe){
	         ioe.printStackTrace();
	      }
	}
	
	public static void main(String[] args){
		cadastrandoViaArquivoTexto("c:/arquivo.txt"); // passar o caminho para o método
		populandoListAlunos(); //método para inserir alguns alunos via código
		printSituacaoAluno(); // método para imprimir com a formatação correta
		
	}
}
