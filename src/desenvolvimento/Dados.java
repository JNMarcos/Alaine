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
	private String sequenciaA; //matriz de cima
	private String sequenciaB; //matriz lateral
	
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
		this.nLinhas = nLinhas;
	}

	public int getnColunas() {
		return nColunas;
	}

	public void setnColunas(int nColunas) {
		this.nColunas = nColunas;
	}

	public String getSequenciaA() {
		return sequenciaA;
	}

	public void setSequenciaA(String sequenciaA) {
		this.sequenciaA = sequenciaA;
	}

	public String getSequenciaB() {
		return sequenciaB;
	}

	public void setSequenciaB(String sequenciaB) {
		this.sequenciaB = sequenciaB;
	}

	public Hashtable<String, Integer> getSistemaPontuacao() {
		return sistemaPontuacao;
	}

	public void setSistemaPontuacao(int match, int mismatch, int gap) {
		if (match != 0 && mismatch != 0 && gap != 0){
		this.sistemaPontuacao.put("match", match);
		this.sistemaPontuacao.put("mismatch", mismatch);
		this.sistemaPontuacao.put("gap", gap);
		}
	}
}
