package teste;

import java.util.Scanner;

import desenvolvimento.Alinhamento;
import desenvolvimento.AlinhamentoGlobal;
import desenvolvimento.AlinhamentoSemiglobal1;
import desenvolvimento.Dados;
import desenvolvimento.AlinhamentoLocal1;

public class Teste {
	public static void main(String[] argumentos){
		//vari�veis do alinhamento e programa
		Dados dados;
		Alinhamento alaine = null;
		Scanner entrada = new Scanner(System.in);

		//iteradores que permitem que se use o mesmo dado inserido sem precisar
		//novamente p
		boolean isUsarMesmosDados = false;
		boolean isUsarNovosDados = false;
		boolean isEsquivarPerguntas = false;

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
			System.out.println("Insira a primeira sequ�ncia: ");
			seqA = entrada.next();
			System.out.println("Insira a segunda sequ�ncia: ");
			seqB = entrada.next();

			//pega a pontua��o que ser� adotada para o match, mismatch e gap
			System.out.println("Insira a pontua��o para o match: ");
			match = entrada.nextInt();
			System.out.println("Insira a pontua��o para o mismatch: ");
			mismatch = entrada.nextInt();
			System.out.println("Insira a pontua��o para o gap: ");
			gap = entrada.nextInt();

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
					System.out.println("Alinhamento Global");
					alaine = new AlinhamentoGlobal(dados);
					break;
				case '2':
					alaine = new AlinhamentoSemiglobal1(dados);
					System.out.println("Alinhamento Semiglobal");
					break;
				case '3':
					alaine = new AlinhamentoLocal1(dados);
					System.out.println("Alinhamento Local");
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
					alaine.exibirMatriz();
					alaine.construirCaminho();
					alaine.exibirAlinhamento();
					
					System.out.println("Voc� deseja fazer um novo alinhamento? ");
					System.out.println("1. Sim");
					System.out.println("2. N�o");
					escolha = entrada.next().charAt(0);

					switch (escolha){
					case '1'://sim
						System.out.println("Voc� deseja inserir novos dados? ");
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
