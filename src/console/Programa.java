package console;

import java.util.Scanner;

import desenvolvimento.Alinhamento;
import desenvolvimento.AlinhamentoGlobal;
import desenvolvimento.AlinhamentoSemiglobal;
import desenvolvimento.Dados;
import desenvolvimento.AlinhamentoLocal;

public class Programa {
	public static void main(String[] argumentos){
		//vari�veis do alinhamento e programa
		Dados dados;
		Alinhamento alaine = null;
		Scanner entrada = new Scanner(System.in);

		//iteradores que permitem que se use o mesmo dado inserido sem precisar
		//novamente ou n�o
		boolean isUsarMesmosDados = false;
		boolean isUsarNovosDados = false;
		boolean isEsquivarPerguntas = false;
		
		//verifica se o sistema de pontua��o segue match > mismatch e mismatch > gap
		// se for incoerente, pede para inserir novamente
		boolean isCoerenteAPontuacao = true;
		
		//indica para o alinhamento semiglobal, se o maior escore ser� da �ltima linha ou
		// da �ltima coluna
		boolean isUltimaLinha = true;

		//vari�vel que indica qual alinhamento ser� realizado
		//o usu�rio insere
		char escolha;
		char escolhaSecundaria;

		//vari�veis que servir�o de atributo para a classe Dados
		String seqA = null;
		String seqB = null;
		int match;
		int mismatch;
		int gap;

		//la�o novosDados
		do{
			//boas-vinda
			System.out.println("\t\t\t\t__________ALAINE__________\n");
			System.out.println("\t\t\t\t Software de Alinhamentos\n\n");
			//pega as duas sequ�ncias que ser�o usadas no alinhamento
			
			System.out.println("Insira apenas letras.\n");
			System.out.println("Insira a primeira sequ�ncia: ");
			seqA = entrada.next();
			System.out.println("Insira a segunda sequ�ncia: ");
			seqB = entrada.next();
			
			//pega a pontua��o que ser� adotada para o match, mismatch e gap
			do{
				System.out.println("\nInsira apenas n�meros inteiros.\n");
				System.out.println("Insira a pontua��o para o match: ");
				match = entrada.nextInt();
				System.out.println("Insira a pontua��o para o mismatch: ");
				mismatch = entrada.nextInt();
				System.out.println("Insira a pontua��o para o gap: ");
				gap = entrada.nextInt();
			
			// se match � menor ou igual a mismatch est� incoerente o sist. de pont
			// se mismatch � menor ou igual a gap est� incoerente o sist. de pont.
			if (match <= mismatch || mismatch <= gap){
				System.out.println("\n\n\n\n\n\n");
				isCoerenteAPontuacao = false;
				System.out.println("Seu sistema de pontua��o est� incoerente. "
						+ "Observe que o match n�o pode ser menor ou igual ao mismatch\n"
						+ "nem o mismatch menor ou igual ao gap.\n");
				System.out.println("Por favor, corrija seu sistema de pontua��o.");
			} else{
				System.out.println("\n\n\n\n\n\n");
				isCoerenteAPontuacao = true;
			}
			
			} while(!isCoerenteAPontuacao);
			//todos os dados foram definidos, podemos instanciar um classe Dados
			dados = new Dados(seqA, seqB, match, mismatch, gap);
			
			//la�o mesmosDados
			do{
				//o usu�rio escolhe um dos alinhamento dispon�veis
				System.out.println("Escolha uma das seguintes op��es: ");
				System.out.println("1. Alinhamento Global");
				System.out.println("2. Alinhamento Semiglobal");
				System.out.println("3. Alinhamento Local");
				System.out.println("4. Sair");
				escolha = entrada.next().charAt(0);

				//faz a diferencia��o do algoritmo
				switch(escolha){
				case '1':
					alaine = new AlinhamentoGlobal(dados);
					break;
				case '2':
					
					System.out.println("\n\n\n\n\nQual tipo de alinhamento semiglobal voc� deseja? ");
					System.out.println("1. Maior escore na �ltima linha");
					System.out.println("2. Maior escore na �ltima coluna");
					escolhaSecundaria = entrada.next().charAt(0);
					System.out.println("\n\n\n\n\n\n");
					switch (escolhaSecundaria){
					case '1': default: //por padr�o, � a �ltima linha
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
					System.out.println("Nenhuma op��o foi selecionada. Deseja tentar novamente?");
					System.out.println("1. Sim");
					System.out.println("2. N�o");
					escolhaSecundaria = entrada.next().charAt(0);

					if (escolhaSecundaria == '1'){
						isUsarMesmosDados = true;
					} else {
						//sai dos dois la�os com dois ou mais
						isUsarMesmosDados = false;
						isUsarNovosDados = false;
						isEsquivarPerguntas = true;
					}
				}

				//n�o � para se esquivar das perguntas se n�o selecionou sair
				//fez o alinhamento. quer fazer outro?
				if (isEsquivarPerguntas == false){
					alaine.inicializarMatriz();
					alaine.preencherMatriz();
					alaine.construirCaminho();
					alaine.exibirResultado();
					
					System.out.println("\n\n\n\nVoc� deseja fazer um novo alinhamento? ");
					System.out.println("1. Sim");
					System.out.println("2. N�o");
					escolha = entrada.next().charAt(0);

					switch (escolha){
					case '1'://sim
						System.out.println("\n\n\n\nVoc� deseja inserir novos dados? ");
						System.out.println("1. Sim");
						System.out.println("2. N�o");
						escolhaSecundaria = entrada.next().charAt(0);

						switch (escolhaSecundaria){
						case '1'://sim
							//permite que haja itera��o, pegando novos dados e
							//descartando os antigos
							isUsarNovosDados = true;
							isUsarMesmosDados = false; //sai do la�o dos mesmos dados
							break;
						case '2'://n�o
							isUsarMesmosDados = true;
							break;
						default:
							//p�s outro n�mero, considera que quer sair
							//sai dos dois la�os
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
