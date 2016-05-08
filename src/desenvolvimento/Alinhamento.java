/**
 * 
 */
package desenvolvimento;

import java.util.ArrayList;

/**
 * @author JN
 *
 */
public abstract class Alinhamento {
	private Dados dados;
	protected int[][] matriz;
	
	//com o vetorCaminho pode facilmente encontrar o caminho a ser seguido na matriz
	// se 0 = horizontal, se 1 = diagonal, se 2 = vertical
	protected ArrayList<Character> vetorCaminho;
	
	//inicializa o alinhamento com informações importantes
	//passa as sequências, o tamanho da matriz, o vetor de caminho é instanciado
	//assim como a matriz e o sistema de pontuação
	public Alinhamento (Dados dados){
		this.setDados(dados);
		this.vetorCaminho = new ArrayList<Character>();
		this.matriz = new int[this.getDados().getnLinhas()][this.getDados().getnColunas()];
		
	}
	
	public int[][] getMatriz() {
		return matriz;
	}
	
	public ArrayList<Character> getVetorCaminho() {
		return vetorCaminho;
	}
	
	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
	}
	
	public abstract void inicializarMatriz();
	public abstract void preencherMatriz();;
	
	public int calcularHorizontal(int linha, int coluna){
		int horizontal;
		horizontal = matriz[linha][coluna-1] + getDados().getSistemaPontuacao().get("gap");
		return horizontal;
	}

	public int calcularVertical(int linha, int coluna){
		int vertical;
		vertical = matriz[linha-1][coluna] + getDados().getSistemaPontuacao().get("gap");
		return vertical;
	}
	
	public int calcularDiagonal(int linha, int coluna){
		int diagonal;
		//se o símbolo na coluna x na seq A for igual ao símbolo na linha y na seq B
		//há um MATCH, senão é um mismatch ("letras diferentes")
		if (this.getDados().getSequenciaA().charAt(coluna) == this.getDados().getSequenciaB().charAt(linha)){
			//o cálculo pela vertical
			//o valor da célula de cima + o valor do match/mismatch
			diagonal = matriz[linha-1][coluna-1] + getDados().getSistemaPontuacao().get("match");
		} else { //são letras diferentes
			diagonal = matriz[linha-1][coluna-1] + getDados().getSistemaPontuacao().get("mismatch");
		}
		return diagonal;
	}
	
	public int calcularMaximo(int horizontal, int vertical, int diagonal){
		int maximo;
		//precedência D>H>V
		//o igual garante a precedência requerida no projeto
		if ((horizontal >= vertical) && (horizontal > diagonal)){
				maximo = horizontal;
				//adiciona de onde veio o máximo
				getVetorCaminho().add('H');
		} else if (vertical > diagonal){
				maximo = vertical;
				getVetorCaminho().add('V');
		} else{
			maximo = diagonal;
			getVetorCaminho().add('D');
		}
		return maximo;
	}
}
