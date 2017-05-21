package bdd.table;

public class Prestataire {
	private String id;
	private String nom;
	private String support;

	private Prestataire() {
	}

	public static Prestataire create(final String id, final String nom, final String support) {
		final Prestataire type = new Prestataire();
		type.setId(id);
		type.setNom(nom);
		type.setSupport(support);
		return type;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public final String getNom() {
		return this.nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public final void setNom(final String nom) {
		this.nom = nom;
	}

	/**
	 * @return the support
	 */
	public String getSupport() {
		return support;
	}

	/**
	 * @param support the support to set
	 */
	public void setSupport(String support) {
		this.support = support;
	}

	@Override
	public String toString() {
		return this.nom;
	}
}
