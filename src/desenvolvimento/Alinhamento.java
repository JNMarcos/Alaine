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
	
	//inicializa o alinhamento com informa��es importantes
	//passa as sequ�ncias, o tamanho da matriz, o vetor de caminho � instanciado
	//assim como a matriz e o sistema de pontua��o
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
		//se o s�mbolo na coluna x na seq A for igual ao s�mbolo na linha y na seq B
		//h� um MATCH, sen�o � um mismatch ("letras diferentes")
		if (this.getDados().getSequenciaA().charAt(coluna) == this.getDados().getSequenciaB().charAt(linha)){
			//o c�lculo pela vertical
			//o valor da c�lula de cima + o valor do match/mismatch
			diagonal = matriz[linha-1][coluna-1] + getDados().getSistemaPontuacao().get("match");
		} else { //s�o letras diferentes
			diagonal = matriz[linha-1][coluna-1] + getDados().getSistemaPontuacao().get("mismatch");
		}
		return diagonal;
	}
	
	public int calcularMaximo(int horizontal, int vertical, int diagonal){
		int maximo;
		//preced�ncia D>H>V
		//o igual garante a preced�ncia requerida no projeto
		if ((horizontal >= vertical) && (horizontal > diagonal)){
				maximo = horizontal;
				//adiciona de onde veio o m�ximo
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
