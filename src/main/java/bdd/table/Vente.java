package bdd.table;

import java.math.BigDecimal;

public class Vente {
	private String id;
	private BigDecimal prixLibre;

	public static Vente create(final String id, final BigDecimal prixLibre) {
		final Vente declaration = new Vente();
		declaration.setId(id);
		declaration.setPrixLibre(prixLibre);
		return declaration;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the prixLibre
	 */
	public BigDecimal getPrixLibre() {
		return prixLibre;
	}

	/**
	 * @param prixLibre
	 *            the prixLibre to set
	 */
	public void setPrixLibre(final BigDecimal prixLibre) {
		this.prixLibre = prixLibre;
	}
}
