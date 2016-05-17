package desenvolvimento;

/**
 * @author JN
 *
 */
public class AlinhamentoLocal extends Alinhamento{
	
	//posX e posY serão mais usados aqui, pois serão chamados a cada iteração da matriz
	//para guardar a posição da célula da matriz que tem o maior escore, por
	//onde começará o alinhamento, já que é local

	//salva o maior escore para comparação
	private int maiorEscore;

	//pos. do ArrayList que tem a direção de onde ir do maior escore
	private int posArrayList;
	public AlinhamentoLocal(Dados dados) {
		super(dados);
		//seta dados padrões, maiorEscore = 0, e posição de inicio = 0 e pos. de fim = 0
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
			matriz[i][0] = 0; // insere zero nas células da primeira linha
			// seta que o valor da célula veio da vertical
			getVetorCaminho().set(calcularPosicaoMatriz(i, 0) - 1, 'V');
		}

		for (int j = 1; j < getDados().getnColunas(); j++){
			matriz[0][j] = 0; // insere zero nas células da primeira coluna
			// seta que o valor da célula veio da horizontal
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
		
		//se maximo < 0, pelo alinh. local, devemos zerar a célula da matriz
		if (maximo < 0){
			maximo = 0;
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

				//ver se é o maior escore da matriz até então
				if (maximo > getMaiorEscore()){//é o maior escore da matriz
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

	@Override
	public void construirCaminho() {
		//indica a direção que deve seguir para encontrar o melhor alinhamento
				char direcao;
				//a posX e posY é obtida pela célula com maior escore
				// no método preencherMatriz()
				//eposArrayList tb é assim obtido
				//faça enquanto não encontra um zero
				while (matriz[getPosX()][getPosY()] != 0){
					//subtrai um, pois o array começa por zero
					direcao = getVetorCaminho().get(this.getPosArrayList());
					construirAlinhamento(direcao);
				}
			}
	@Override
	public String toString() {
		return "Alinhamento Local";
	}
}
