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
			matriz[i][0] = getDados().sistemaPontuacao.get("gap")*i;
		}

		for (int j = 1; j < getDados().getnColunas(); j++){
			matriz[0][j] = getDados().sistemaPontuacao.get("gap")*j;
		}

	}

	@Override
	public void preencherMatriz() {
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
				maximo = calcularMaximo(horizontal, vertical, diagonal);

				//atribui o maior valor (máximo) a posição atual da matriz
				this.matriz[i][j] = maximo;				
			}
		}
	}
}
