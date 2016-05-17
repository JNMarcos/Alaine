/**
 * 
 */
package desenvolvimento;

import java.util.Hashtable;

/**
 * @author JN
 *
 */
public class Dados {
	private int nLinhas;
	private int nColunas;
	private String sequenciaA; //seq de cima
	private String sequenciaB; //seq lateral
	
	protected Hashtable<String, Integer> sistemaPontuacao;

	public Dados(String sequenciaA, String sequenciaB, int match, int mismatch,
		int gap){
	this.setSequenciaA(sequenciaA);
	this.setSequenciaB(sequenciaB);
	this.setnLinhas(sequenciaB.length());
	this.setnColunas(sequenciaA.length());
	this.sistemaPontuacao = new Hashtable<String, Integer>();
	this.setSistemaPontuacao(match, mismatch, gap);
	}
	
	public int getnLinhas() {
		return nLinhas;
	}

	public void setnLinhas(int nLinhas) {
		this.nLinhas = nLinhas + 1; //adição do espaço vazio
		// ex. se for atg, a linha será -atg
	}

	public int getnColunas() {
		return nColunas;
	}

	public void setnColunas(int nColunas) {
		this.nColunas = nColunas + 1;//adição do espaço vazio
		// ex. se for atg, a coluna será -atg
	}

	public String getSequenciaA() {
		return sequenciaA;
	}

	public void setSequenciaA(String sequenciaA) {
		// trim() = remove espaços em branco ao fim da string
		// toUpperCase() = põe a string para maiúscula
		this.sequenciaA = sequenciaA.trim().toUpperCase();
	}

	public String getSequenciaB() {
		return sequenciaB;
	}

	public void setSequenciaB(String sequenciaB) {
		this.sequenciaB = sequenciaB.trim().toUpperCase();
	}

	public Hashtable<String, Integer> getSistemaPontuacao() {
		return sistemaPontuacao;
	}

	public void setSistemaPontuacao(int match, int mismatch, int gap) {
		this.sistemaPontuacao.put("match", match);
		this.sistemaPontuacao.put("mismatch", mismatch);
		this.sistemaPontuacao.put("gap", gap);
	}
}
