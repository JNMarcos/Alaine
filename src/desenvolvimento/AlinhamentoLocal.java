package desenvolvimento;

/**
 * @author JN
 *
 */
public class AlinhamentoLocal extends Alinhamento{
	
	//posX e posY ser�o mais usados aqui, pois ser�o chamados a cada itera��o da matriz
	//para guardar a posi��o da c�lula da matriz que tem o maior escore, por
	//onde come�ar� o alinhamento, j� que � local

	//salva o maior escore para compara��o
	private int maiorEscore;

	//pos. do ArrayList que tem a dire��o de onde ir do maior escore
	private int posArrayList;
	public AlinhamentoLocal(Dados dados) {
		super(dados);
		//seta dados padr�es, maiorEscore = 0, e posi��o de inicio = 0 e pos. de fim = 0
		//pos. array = 0
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

	@Override
	public void inicializarMatriz() {
		matriz[0][0] = 0; // - com - = 0
		for (int i = 1; i < getDados().getnLinhas(); i++){
			matriz[i][0] = 0; // insere zero nas c�lulas da primeira linha
			// seta que o valor da c�lula veio da vertical
			getVetorCaminho().set(calcularPosicaoMatriz(i, 0) - 1, 'V');
		}

		for (int j = 1; j < getDados().getnColunas(); j++){
			matriz[0][j] = 0; // insere zero nas c�lulas da primeira coluna
			// seta que o valor da c�lula veio da horizontal
			getVetorCaminho().set(calcularPosicaoMatriz(0, j) - 1, 'H');
		}
	}

	public int calcularMaximo(int horizontal, int vertical, int diagonal, 
			int posicaoArrayList) {
		int maximo;
		//preced�ncia D>H>V
		//o igual garante a preced�ncia requerida no projeto
		if ((horizontal >= vertical) && (horizontal > diagonal)){
			maximo = horizontal;
			//adiciona de onde veio o m�ximo
			getVetorCaminho().set(posicaoArrayList, 'H');
		} else if (vertical > diagonal){
			maximo = vertical;
			getVetorCaminho().set(posicaoArrayList, 'V');
		} else{
			maximo = diagonal;
			getVetorCaminho().set(posicaoArrayList, 'D');
		}
		
		//se maximo < 0, pelo alinh. local, devemos zerar a c�lula da matriz
		if (maximo < 0){
			maximo = 0;
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

				//ver se � o maior escore da matriz at� ent�o
				if (maximo > getMaiorEscore()){//� o maior escore da matriz
					//guarda o novo m�ximo para fazer as novas compara��es
					this.setMaiorEscore(maximo);
					this.setPosX(i);
					this.setPosY(j);
					//indica a de onde veio o escore da c�lula de maior escore no ArrayList
					//tem que tirar um, pois o array come�a por 0, n�o por 1 
					this.setPosArrayList(calcularPosicaoMatriz(i, j) - 1);
				}
			}
		}
	}

	@Override
	public void construirCaminho() {
		//indica a dire��o que deve seguir para encontrar o melhor alinhamento
				char direcao;
				//a posX e posY � obtida pela c�lula com maior escore
				// no m�todo preencherMatriz()
				//eposArrayList tb � assim obtido
				//fa�a enquanto n�o encontra um zero
				while (matriz[getPosX()][getPosY()] != 0){
					//subtrai um, pois o array come�a por zero
					direcao = getVetorCaminho().get(this.getPosArrayList());
					construirAlinhamento(direcao);
				}
			}
	@Override
	public String toString() {
		return "Alinhamento Local";
	}
}
