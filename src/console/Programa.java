package console;

import java.util.Scanner;

import desenvolvimento.Alinhamento;
import desenvolvimento.AlinhamentoGlobal;
import desenvolvimento.AlinhamentoSemiglobal;
import desenvolvimento.Dados;
import desenvolvimento.AlinhamentoLocal;

public class Programa {
	public static void main(String[] argumentos){
		//variáveis do alinhamento e programa
		Dados dados;
		Alinhamento alaine = null;
		Scanner entrada = new Scanner(System.in);

		//iteradores que permitem que se use o mesmo dado inserido sem precisar
		//novamente ou não
		boolean isUsarMesmosDados = false;
		boolean isUsarNovosDados = false;
		boolean isEsquivarPerguntas = false;
		
		//verifica se o sistema de pontuação segue match > mismatch e mismatch > gap
		// se for incoerente, pede para inserir novamente
		boolean isCoerenteAPontuacao = true;
		
		//indica para o alinhamento semiglobal, se o maior escore será da última linha ou
		// da última coluna
		boolean isUltimaLinha = true;

		//variável que indica qual alinhamento será realizado
		//o usuário insere
		char escolha;
		char escolhaSecundaria;

		//variáveis que servirão de atributo para a classe Dados
		String seqA = null;
		String seqB = null;
		int match;
		int mismatch;
		int gap;

		//laço novosDados
		do{
			//boas-vinda
			System.out.println("\t\t\t\t__________ALAINE__________\n");
			System.out.println("\t\t\t\t Software de Alinhamentos\n\n");
			//pega as duas sequências que serão usadas no alinhamento
			
			System.out.println("Insira apenas letras.\n");
			System.out.println("Insira a primeira sequência: ");
			seqA = entrada.next();
			System.out.println("Insira a segunda sequência: ");
			seqB = entrada.next();
			
			//pega a pontuação que será adotada para o match, mismatch e gap
			do{
				System.out.println("\nInsira apenas números inteiros.\n");
				System.out.println("Insira a pontuação para o match: ");
				match = entrada.nextInt();
				System.out.println("Insira a pontuação para o mismatch: ");
				mismatch = entrada.nextInt();
				System.out.println("Insira a pontuação para o gap: ");
				gap = entrada.nextInt();
			
			// se match é menor ou igual a mismatch está incoerente o sist. de pont
			// se mismatch é menor ou igual a gap está incoerente o sist. de pont.
			if (match <= mismatch || mismatch <= gap){
				System.out.println("\n\n\n\n\n\n");
				isCoerenteAPontuacao = false;
				System.out.println("Seu sistema de pontuação está incoerente. "
						+ "Observe que o match não pode ser menor ou igual ao mismatch\n"
						+ "nem o mismatch menor ou igual ao gap.\n");
				System.out.println("Por favor, corrija seu sistema de pontuação.");
			} else{
				System.out.println("\n\n\n\n\n\n");
				isCoerenteAPontuacao = true;
			}
			
			} while(!isCoerenteAPontuacao);
			//todos os dados foram definidos, podemos instanciar um classe Dados
			dados = new Dados(seqA, seqB, match, mismatch, gap);
			
			//laço mesmosDados
			do{
				//o usuário escolhe um dos alinhamento disponíveis
				System.out.println("Escolha uma das seguintes opções: ");
				System.out.println("1. Alinhamento Global");
				System.out.println("2. Alinhamento Semiglobal");
				System.out.println("3. Alinhamento Local");
				System.out.println("4. Sair");
				escolha = entrada.next().charAt(0);

				//faz a diferenciação do algoritmo
				switch(escolha){
				case '1':
					alaine = new AlinhamentoGlobal(dados);
					break;
				case '2':
					
					System.out.println("\n\n\n\n\nQual tipo de alinhamento semiglobal você deseja? ");
					System.out.println("1. Maior escore na última linha");
					System.out.println("2. Maior escore na última coluna");
					escolhaSecundaria = entrada.next().charAt(0);
					System.out.println("\n\n\n\n\n\n");
					switch (escolhaSecundaria){
					case '1': default: //por padrão, é a última linha
						isUltimaLinha = true;
						break;
					case '2':
						isUltimaLinha = false;
						break;
					}
					
					alaine = new AlinhamentoSemiglobal(dados, isUltimaLinha);
					break;
				case '3':
					alaine = new AlinhamentoLocal(dados);
					break;
				case '4':
					isUsarMesmosDados = false;
					isUsarNovosDados = false;
					isEsquivarPerguntas = true;
					break;
				default:
					System.out.println("Nenhuma opção foi selecionada. Deseja tentar novamente?");
					System.out.println("1. Sim");
					System.out.println("2. Não");
					escolhaSecundaria = entrada.next().charAt(0);

					if (escolhaSecundaria == '1'){
						isUsarMesmosDados = true;
					} else {
						//sai dos dois laços com dois ou mais
						isUsarMesmosDados = false;
						isUsarNovosDados = false;
						isEsquivarPerguntas = true;
					}
				}

				//não é para se esquivar das perguntas se não selecionou sair
				//fez o alinhamento. quer fazer outro?
				if (isEsquivarPerguntas == false){
					alaine.inicializarMatriz();
					alaine.preencherMatriz();
					alaine.construirCaminho();
					alaine.exibirResultado();
					
					System.out.println("\n\n\n\nVocê deseja fazer um novo alinhamento? ");
					System.out.println("1. Sim");
					System.out.println("2. Não");
					escolha = entrada.next().charAt(0);

					switch (escolha){
					case '1'://sim
						System.out.println("\n\n\n\nVocê deseja inserir novos dados? ");
						System.out.println("1. Sim");
						System.out.println("2. Não");
						escolhaSecundaria = entrada.next().charAt(0);

						switch (escolhaSecundaria){
						case '1'://sim
							//permite que haja iteração, pegando novos dados e
							//descartando os antigos
							isUsarNovosDados = true;
							isUsarMesmosDados = false; //sai do laço dos mesmos dados
							break;
						case '2'://não
							isUsarMesmosDados = true;
							break;
						default:
							//pôs outro número, considera que quer sair
							//sai dos dois laços
							isUsarMesmosDados = false;
							isUsarNovosDados = false;
						}
						break;
					case '2':
					default:
						isUsarNovosDados = false;
						isUsarMesmosDados = false;
					}
				}
			} while (isUsarMesmosDados);
		} while (isUsarNovosDados);
		entrada.close();
		System.out.println("Processo encerrado.");
	}
}
