package bdd.table;

import java.math.BigDecimal;

public class Achat {
	private String id;
	private String nom;
	private String idTypeAchat;
	private String idLivre;
	private int quantite;
	private BigDecimal prix;

	private Achat() {
	}

	/**
	 * @param nom
	 * @param idTypeAchat
	 * @param quantite
	 * @param prix
	 */
	public static Achat create(final String id, final String nom, final String idLivre, final String idTypeAchat,
			final int quantite, final BigDecimal prix) {
		final Achat achat = new Achat();
		achat.id = id;
		achat.nom = nom;
		achat.idLivre = idLivre;
		achat.idTypeAchat = idTypeAchat;
		achat.quantite = quantite;
		achat.prix = prix;
		return achat;
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
	 * @return the idTypeAchat
	 */
	public String getIdTypeAchat() {
		return idTypeAchat;
	}

	/**
	 * @param idTypeAchat
	 *            the idTypeAchat to set
	 */
	public void setIdTypeAchat(final String idTypeAchat) {
		this.idTypeAchat = idTypeAchat;
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
}
