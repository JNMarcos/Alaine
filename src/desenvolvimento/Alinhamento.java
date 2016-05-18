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
	// se horizontal = 'H', se diagonal = 'D', se vertical = 'V'
	protected ArrayList<Character> vetorCaminho;

	//sequências após o alinhamento
	private String seqAAlinhamento;
	private String seqBAlinhamento;
	
	//vetorAlinhamento = vetor em String  que deve seguir para obter melhor alinhamento
	private String vetorAlinhamento;

	//posição atual da célula que está vendo
	private int posX;
	private int posY;

	//inicializa o alinhamento com informações importantes
	//passa as sequências, o tamanho da matriz, o vetor de caminho é instanciado
	//assim como a matriz e o sistema de pontuação
	public Alinhamento (Dados dados){
		this.setDados(dados);
		//atribui inicialmente vazio às strings
		this.setSeqAAlinhamento("");
		this.setSeqBAlinhamento("");
		this.setVetorAlinhamento("");
		this.inicializarArrayList();
		this.matriz = new int[this.getDados().getnLinhas()][this.getDados().getnColunas()];

	}

	public ArrayList<Character> getVetorCaminho() {
		return this.vetorCaminho;
	}

	public String getSeqAAlinhamento() {
		return seqAAlinhamento;
	}

	public void setSeqAAlinhamento(String seqAAlinhamento) {
		this.seqAAlinhamento = seqAAlinhamento;
	}

	public String getSeqBAlinhamento() {
		return seqBAlinhamento;
	}

	public void setSeqBAlinhamento(String seqBAlinhamento) {
		this.seqBAlinhamento = seqBAlinhamento;
	}

	public String getVetorAlinhamento() {
		return vetorAlinhamento;
	}

	public void setVetorAlinhamento(String vetorAlinhamento) {
		this.vetorAlinhamento = vetorAlinhamento;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
	}

	public void inicializarArrayList(){
		int linha = this.getDados().getnLinhas();
		int coluna = this.getDados().getnColunas();
		this.vetorCaminho = new ArrayList<Character>(linha*coluna);
		for (int k = 0; k < linha; k++){
			for (int l = 0; l < coluna; l++){
				vetorCaminho.add('D');// por padrão vai para a diagonal
			}
		}
	}
	public abstract void inicializarMatriz();
	public abstract void preencherMatriz();

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
		// se remove um da linha e coluna, porque as sequencias são salvas sem o '-'
		if (this.getDados().getSequenciaA().charAt(coluna-1) == this.getDados().getSequenciaB().charAt(linha-1)){
			//o cálculo pela vertical
			//o valor da célula de cima + o valor do match/mismatch
			diagonal = matriz[linha-1][coluna-1] + getDados().getSistemaPontuacao().get("match");
		} else { //são letras diferentes
			diagonal = matriz[linha-1][coluna-1] + getDados().getSistemaPontuacao().get("mismatch");
		}
		return diagonal;
	}

	//calcula o máximo valor para uma célula da matriz. Faz uma comparação dos
	// valores obtidos na horizontal, vertical e diagonal
	public abstract int calcularMaximo(int horizontal, int vertical, int diagonal, int posicao);

	//calcula a posição da matriz, o número da célula, primeira, segunda...
	public int calcularPosicaoMatriz(int i, int j){
		return (i+1)*(j+1) + (i)*(this.getDados().getnColunas() - (j+1));
	}

	public abstract void construirCaminho();

	//constrói a sequência durante o alinhamento
	public String construirString(String sequencia, char adicionar){
		return adicionar + sequencia;
	}

	public void construirAlinhamento(char direcao){
		//caracteres que se adicionarão a seqAAlinhamento e seqBAlinhamento
		char caractereSeqA = ' ';
		char caractereSeqB = ' ';
		this.setVetorAlinhamento(getVetorAlinhamento() + " " + direcao);
		if (direcao == 'D'){//é para prosseguir o alinhamento indo para a diagonal
			caractereSeqA = this.getDados().getSequenciaA().charAt(this.getPosY() - 1);
			caractereSeqB = this.getDados().getSequenciaB().charAt(this.getPosX() - 1);
			this.setPosX(this.getPosX() - 1);//sobe uma linha
			this.setPosY(this.getPosY() - 1); //volta uma coluna
		} else if (direcao == 'H'){ // é para prosseguir o alinh. pela horizontal
			caractereSeqA = this.getDados().getSequenciaA().charAt(this.getPosY() - 1);
			caractereSeqB = '-';
			this.setPosY(this.getPosY() - 1); //volta uma coluna
		} else if (direcao == 'V'){
			caractereSeqA = '-';
			caractereSeqB = this.getDados().getSequenciaB().charAt(this.getPosX() - 1);
			this.setPosX(this.getPosX() - 1);//sobe uma linha
		}

		//faz para todos, construi a string
		setSeqAAlinhamento(construirString(this.getSeqAAlinhamento(), caractereSeqA));
		setSeqBAlinhamento(construirString(this.getSeqBAlinhamento(), caractereSeqB));
	}

	public void exibirMatriz(){
		System.out.println();
		for (int i = 0; i < getDados().getnColunas(); i++){
			//escreve as letras de cima da matriz
			if (i == 0) {
				System.out.print( "    -    ");
			} else {
				System.out.print(getDados().getSequenciaA().charAt(i - 1) + "    ");
			}
		}
		System.out.println();

		for (int i = 0; i < this.getDados().getnLinhas(); i++){
			//escreve as letras de lado da matriz
			if (i == 0) {
				System.out.print("-    ");
			} else {
				System.out.print(getDados().getSequenciaB().charAt(i - 1) + "    ");
			}
			for (int j = 0; j < this.getDados().getnColunas(); j++){
				System.out.print(matriz[i][j] + " | ");
			}
			System.out.println();
		}
	}
	
	public void exibirResultado(){
		System.out.println("\n\n\n\n\n\n");
		System.out.println(this.toString());
		System.out.println("sequência A: " + getDados().getSequenciaA());
		System.out.println("sequência B: " + getDados().getSequenciaB());
		System.out.println();
		System.out.println("match: " + getDados().getSistemaPontuacao().get("match")
				+ "\tmismatch: " + getDados().getSistemaPontuacao().get("mismatch")
				+ "\t     gap: " + getDados().getSistemaPontuacao().get("gap"));
		System.out.println();
		System.out.println("Matriz de " + this.toString());
		exibirMatriz();
		System.out.println();
		System.out.println("tamanho da matriz: "+ getDados().getnLinhas() + " x " + 
				getDados().getnColunas());
		System.out.println();
		System.out.println("vetor alinhamento: " + getVetorAlinhamento());
		System.out.println();
		System.out.println("sequência A após o " + this.toString() + ": " + getSeqAAlinhamento());
		System.out.println("sequência B após o " +  this.toString() + ": " + getSeqBAlinhamento());
		System.out.println();
	}
}
