package bdd.table;

public class Livre {
	private String id;
	private String nom;

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

	@Override
	public String toString() {
		return this.nom;
	}

	public static Livre create(final String id, final String titre) {
		final Livre livre = new Livre();
		livre.setId(id);
		livre.setNom(titre);
		return livre;
	}
}
