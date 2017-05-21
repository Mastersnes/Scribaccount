package bdd.table;

import java.math.BigDecimal;

public class CoupleLP {
	private String idLivre;
	private String idPresta;
	private BigDecimal redevance;

	private CoupleLP() {
	}

	public static CoupleLP create(final String idLivre, final String idPresta, final BigDecimal redevance) {
		final CoupleLP couple = new CoupleLP();
		couple.setIdLivre(idLivre);
		couple.setIdPresta(idPresta);
		couple.setRedevance(redevance);
		return couple;
	}

	/**
	 * @return the idLivre
	 */
	public String getIdLivre() {
		return idLivre;
	}

	/**
	 * @param idLivre
	 *            the idLivre to set
	 */
	public void setIdLivre(final String idLivre) {
		this.idLivre = idLivre;
	}

	/**
	 * @return the idPresta
	 */
	public String getIdPresta() {
		return idPresta;
	}

	/**
	 * @param idPresta
	 *            the idPresta to set
	 */
	public void setIdPresta(final String idPresta) {
		this.idPresta = idPresta;
	}

	/**
	 * @return the redevance
	 */
	public BigDecimal getRedevance() {
		return redevance;
	}

	/**
	 * @param redevance
	 *            the redevance to set
	 */
	public void setRedevance(final BigDecimal redevance) {
		this.redevance = redevance;
	}
}
