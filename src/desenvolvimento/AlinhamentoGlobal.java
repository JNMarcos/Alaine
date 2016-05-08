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
				maximo = calcularMaximo(horizontal, vertical, diagonal);

				//atribui o maior valor (m�ximo) a posi��o atual da matriz
				this.matriz[i][j] = maximo;				
			}
		}
	}
}
