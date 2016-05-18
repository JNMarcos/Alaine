/**
 * 
 */
package desenvolvimento;

/**
 * @author JN
 *
 */
public class AlinhamentoGlobal extends Alinhamento {

	public AlinhamentoGlobal(Dados dados) {
		super(dados);
	}

	@Override
	public void inicializarMatriz() {
		matriz[0][0] = 0;
		for (int i = 1; i < getDados().getnLinhas(); i++){
			matriz[i][0] = i * getDados().getSistemaPontuacao().get("gap");
			//tira uma posi��o pois no ArraYlist come�a a se contar por zero
			// n�o como pelo c�lculo
			getVetorCaminho().set(calcularPosicaoMatriz(i, 0) - 1, 'V');
		}

		for (int j = 1; j < getDados().getnColunas(); j++){
			matriz[0][j] = j * getDados().getSistemaPontuacao().get("gap");
			//tira uma posi��o pois no ArraYlist come�a a se contar por zero
			// n�o como pelo c�lculo
			getVetorCaminho().set(calcularPosicaoMatriz(0, j) - 1, 'H');
		}

	}

	@Override
	public int calcularMaximo(int horizontal, int vertical, int diagonal,  
			int posicaoArrayList) {
		int maximo;
		//preced�ncia D>H>V
		//o igual garante a preced�ncia requerida no projeto
		if ((horizontal >= vertical) && (horizontal > diagonal)){
			maximo = horizontal;
			//adiciona de onde veio o m�ximo
			getVetorCaminho().set(posicaoArrayList,'H');
		} else if (vertical > diagonal){
			maximo = vertical;
			getVetorCaminho().set(posicaoArrayList,'V');
		} else{
			maximo = diagonal;
			getVetorCaminho().set(posicaoArrayList,'D');
		}
		return maximo;
	}

	@Override
	public void preencherMatriz(){
		//valores do c�lculo para cada c�lula
		int horizontal;
		int vertical;
		int diagonal;
		int maximo;

		//como a matriz j� foi inicializada, podemos come�ar da lin = 1 col = 1
		for (int i = 1; i < getDados().getnLinhas(); i++){
			for (int j = 1; j < getDados().getnColunas(); j++){
				//para cada uma das c�lulas da matriz
				//chamamos os m�todos de calcularHorizontal, calcularVertical
				//e calcularDiagonal passando a coordenada da c�lula atual 
				//como par�metro
				horizontal = calcularHorizontal(i, j);
				vertical = calcularVertical(i, j);
				diagonal = calcularDiagonal(i, j);

				//verifica qual dos valores � o maior
				maximo = calcularMaximo(horizontal, vertical, diagonal,
						calcularPosicaoMatriz(i, j) - 1);

				//atribui o maior valor (m�ximo) a posi��o atual da matriz
				this.matriz[i][j] = maximo;				
			}
		}
	}

	@Override
	public void construirCaminho() {
		//indica a dire��o que deve seguir para encontrar o melhor alinhamento
		char direcao;
		// d� a �ltima posi��o da matriz
		int posMatriz;
		super.setPosX(this.getDados().getnLinhas() - 1); //obt�m a �ltima linha da matriz
		super.setPosY(this.getDados().getnColunas() - 1); //obt�m a �ltima coluna da matriz

		//fa�a enquanto n�o chega na posi��o (0,0) da matriz
		while (getPosX() != 0 || getPosY() != 0){
			posMatriz = calcularPosicaoMatriz(getPosX(), getPosY());
			//subtrai um, pois o array come�a por zero
			direcao = getVetorCaminho().get(posMatriz - 1);
			construirAlinhamento(direcao);
		}
	}

	@Override
	public String toString() {
		return "Alinhamento Global";
	}
}

