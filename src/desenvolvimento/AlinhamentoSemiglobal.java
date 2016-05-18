/**
 * 
 */
package desenvolvimento;

/**
 * @author JN
 *
 */
public class AlinhamentoSemiglobal extends Alinhamento {

	private int maiorEscore;
	private int posArrayList;
	private boolean isUltimaLinha;

	public AlinhamentoSemiglobal(Dados dados, boolean isUltimaLinha) {
		super(dados);
		this.setUltimaLinha(isUltimaLinha);
		this.setMaiorEscore(0);
		this.setPosX(0);
		this.setPosY(0);
		this.setPosArrayList(0);
	}

	public int getMaiorEscore() {
		return maiorEscore;
	}

	public void setMaiorEscore(int maiorEscore) {
		this.maiorEscore = maiorEscore;
	}

	public int getPosArrayList() {
		return posArrayList;
	}

	public void setPosArrayList(int posArrayList) {
		this.posArrayList = posArrayList;
	}

	public boolean isUltimaLinha() {
		return isUltimaLinha;
	}

	public void setUltimaLinha(boolean isUltimaLinha) {
		this.isUltimaLinha = isUltimaLinha;
	}

	@Override
	public void inicializarMatriz() {
		matriz[0][0] = 0; // - com - = 0
		for (int i = 1; i < getDados().getnLinhas(); i++){
			matriz[i][0] = 0; //insere 0 na célula da matriz na primeira linha
			//põe no vetorCaminho que o valor vem da vertical
			getVetorCaminho().set(calcularPosicaoMatriz(i, 0) - 1, 'V'); 
		}

		for (int j = 1; j < getDados().getnColunas(); j++){
			matriz[0][j] = 0; //insere 0 na célula da matriz na primeira coluna
			//põe no vetorCaminho que o valor vem da horizontal
			getVetorCaminho().set(calcularPosicaoMatriz(0, j) - 1, 'H');
		}
	}

	public int calcularMaximo(int horizontal, int vertical, int diagonal, 
			int posicaoArrayList) {
		int maximo;
		//precedência D>H>V
		//o igual garante a precedência requerida no projeto
		if ((horizontal >= vertical) && (horizontal > diagonal)){
			maximo = horizontal;
			//adiciona de onde veio o máximo
			getVetorCaminho().set(posicaoArrayList, 'H');
		} else if (vertical > diagonal){
			maximo = vertical;
			getVetorCaminho().set(posicaoArrayList, 'V');
		} else{
			maximo = diagonal;
			getVetorCaminho().set(posicaoArrayList, 'D');
		}
		return maximo;
	}

	@Override
	public void preencherMatriz(){
		//valores do cálculo para cada célula
		int horizontal;
		int vertical;
		int diagonal;
		int maximo;

		//como a matriz já foi inicializada, podemos começar da lin = 1 col = 1
		for (int i = 1; i < getDados().getnLinhas(); i++){
			for (int j = 1; j < getDados().getnColunas(); j++){
				//para cada uma das células da matriz
				//chamamos os métodos de calcularHorizontal, calcularVertical
				//e calcularDiagonal passando a coordenada da célula atual 
				//como parâmetro
				horizontal = calcularHorizontal(i, j);
				vertical = calcularVertical(i, j);
				diagonal = calcularDiagonal(i, j);

				//verifica qual dos valores é o maior
				maximo = calcularMaximo(horizontal, vertical, diagonal,
						calcularPosicaoMatriz(i, j) - 1);

				//atribui o maior valor (máximo) a posição atual da matriz
				this.matriz[i][j] = maximo;			

				if (j == getDados().getnColunas() - 1 && isUltimaLinha() == false) {
					if (maximo > this.getMaiorEscore()){
						//guarda o novo máximo para fazer as novas comparações
						this.setMaiorEscore(maximo);
						this.setPosX(i);
						this.setPosY(j);
						//indica a de onde veio o escore da célula de maior escore no ArrayList
						//tem que tirar um, pois o array começa por 0, não por 1 
						this.setPosArrayList(calcularPosicaoMatriz(i, j) - 1);
					}
				} else	if (i == getDados().getnLinhas() - 1 && isUltimaLinha() == true){ 
					if (maximo > this.getMaiorEscore()){
						//guarda o novo máximo para fazer as novas comparações
						this.setMaiorEscore(maximo);
						this.setPosX(i);
						this.setPosY(j);
						//indica a de onde veio o escore da célula de maior escore no ArrayList
						//tem que tirar um, pois o array começa por 0, não por 1 
						this.setPosArrayList(calcularPosicaoMatriz(i, j) - 1);
					}
				}
			}
		}
	}

	@Override
	public void construirCaminho(){
		//indica a direção que deve seguir para encontrar o melhor alinhamento
		char direcao;
		// dá a última posição da matriz
		int posMatriz;

		//faça enquanto não chega na posição (0,0) da matriz
		while (getPosX() != 0 || getPosY() != 0){
			posMatriz = calcularPosicaoMatriz(getPosX(), getPosY());
			//subtrai um, pois o array começa por zero
			direcao = getVetorCaminho().get(posMatriz - 1);
			construirAlinhamento(direcao);
		}
	}

	@Override
	public String toString() {
		return "Alinhamento Semiglobal";
	}
}
