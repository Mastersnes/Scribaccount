package bdd.table;

import java.util.HashMap;
import java.util.Map;

public class VenteGroup {
	private String idLivre;
	private String idType;
	private Map<String, Vente> listVente = new HashMap<>();

	/**
	 * @return the idType
	 */
	public final String getIdPresta() {
		return this.idType;
	}

	/**
	 * @param idType
	 *            the idType to set
	 */
	public final void setIdType(final String idType) {
		this.idType = idType;
	}

	/**
	 * @return the idLivre
	 */
	public String getIdLivre() {
		return this.idLivre;
	}

	/**
	 * @param idLivre
	 *            the idLivre to set
	 */
	public void setIdLivre(final String idLivre) {
		this.idLivre = idLivre;
	}

	/**
	 * @return the listVente
	 */
	public Map<String, Vente> getListVente() {
		return listVente;
	}

	/**
	 * @param listVente
	 *            the listVente to set
	 */
	public void setListVente(final Map<String, Vente> listVente) {
		this.listVente = listVente;
	}

	public static VenteGroup create(final String idLivre, final String idPresta) {
		final VenteGroup group = new VenteGroup();
		group.setIdLivre(idLivre);
		group.setIdType(idPresta);
		return group;
	}
}
