package teste;

import java.util.Scanner;

import desenvolvimento.Alinhamento;
import desenvolvimento.AlinhamentoGlobal;
import desenvolvimento.AlinhamentoSemiglobal1;
import desenvolvimento.Dados;
import desenvolvimento.AlinhamentoLocal1;

public class Teste {
	public static void main(String[] argumentos){
		//variáveis do alinhamento e programa
		Dados dados;
		Alinhamento alaine = null;
		Scanner entrada = new Scanner(System.in);

		//iteradores que permitem que se use o mesmo dado inserido sem precisar
		//novamente p
		boolean isUsarMesmosDados = false;
		boolean isUsarNovosDados = false;
		boolean isEsquivarPerguntas = false;

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
			System.out.println("Insira a primeira sequência: ");
			seqA = entrada.next();
			System.out.println("Insira a segunda sequência: ");
			seqB = entrada.next();

			//pega a pontuação que será adotada para o match, mismatch e gap
			System.out.println("Insira a pontuação para o match: ");
			match = entrada.nextInt();
			System.out.println("Insira a pontuação para o mismatch: ");
			mismatch = entrada.nextInt();
			System.out.println("Insira a pontuação para o gap: ");
			gap = entrada.nextInt();

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
					alaine.exibirMatriz();
					alaine.construirCaminho();
					alaine.exibirAlinhamento();
					
					System.out.println("Você deseja fazer um novo alinhamento? ");
					System.out.println("1. Sim");
					System.out.println("2. Não");
					escolha = entrada.next().charAt(0);

					switch (escolha){
					case '1'://sim
						System.out.println("Você deseja inserir novos dados? ");
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
