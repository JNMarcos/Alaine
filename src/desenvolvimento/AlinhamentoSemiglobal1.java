/**
 * 
 */
package desenvolvimento;

/**
 * @author JN
 *
 */
public class AlinhamentoSemiglobal1 extends Alinhamento {

	public AlinhamentoSemiglobal1(Dados dados) {
		super(dados);
	}

	@Override
	public void inicializarMatriz() {
		matriz[0][0] = 0;
		for (int i = 1; i < getDados().getnLinhas(); i++){
			matriz[i][0] = 0;
			getVetorCaminho().set(calcularPosicaoMatriz(i, 0) - 1, 'V');
		}

		for (int j = 1; j < getDados().getnColunas(); j++){
			matriz[0][j] = 0;
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
		System.out.println(getVetorCaminho().get(posicaoArrayList));
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
			}
		}
	}

	@Override
	public void construirCaminho(){
		//indica a direção que deve seguir para encontrar o melhor alinhamento
		char direcao;
		// dá a última posição da matriz
		int posMatriz;
		super.setPosX(this.getDados().getnLinhas() - 1); //obtém a última linha da matriz
		super.setPosY(this.getDados().getnColunas() - 1); //obtém a última coluna da matriz

		//faça enquanto não chega na posição (0,0) da matriz
		while (getPosX() != 0 || getPosY() != 0){
			posMatriz = calcularPosicaoMatriz(getPosX(), getPosY());
			//subtrai um, pois o array começa por zero
			direcao = getVetorCaminho().get(posMatriz - 1);
			construirAlinhamento(direcao);
		}
	}
}
