package model.bean;

import java.math.BigDecimal;

public class Stock {
	private String idLivre;
	private String nom;
	private String mois;
	private int quantite;
	private BigDecimal prix;

	private Stock() {
	}

	public static Stock create(final String idLivre, final String nom, final String mois, final int quantite,
			final BigDecimal prix) {
		final Stock stock = new Stock();
		stock.idLivre = idLivre;
		stock.nom = nom;
		stock.setMois(mois);
		stock.quantite = quantite;
		stock.prix = prix;
		return stock;
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(final String nom) {
		this.nom = nom;
	}

	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite
	 *            the quantite to set
	 */
	public void setQuantite(final int quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the prix
	 */
	public BigDecimal getPrix() {
		return prix;
	}

	/**
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(final BigDecimal prix) {
		this.prix = prix;
	}

	/**
	 * @return the mois
	 */
	public String getMois() {
		return mois;
	}

	/**
	 * @param mois
	 *            the mois to set
	 */
	public void setMois(final String mois) {
		this.mois = mois;
	}

}
