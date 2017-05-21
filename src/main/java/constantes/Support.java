package constantes;

public enum Support {
	LIVRE("0", Phrases.LIVRE), EBOOK("1", Phrases.EBOOK);

	private String id;
	private String nom;

	private Support(final String id, final String nom) {
		this.id = id;
		this.nom = nom;
	}

	public String getId() {
		return id;
	}

	public static Support findById(final String idSupport) {
		for (final Support type : values()) {
			if (type.id.equals(idSupport)) {
				return type;
			}
		}
		return null;
	}

	public static Support findByName(final String nameSearched) {
		for (final Support type : values()) {
			if (type.nom.equals(nameSearched)) {
				return type;
			}
		}
		return null;
	}

	public static Support find(final String newValue) {
		Support search = findById(newValue);
		if (search == null) {
			search = findByName(newValue);
		}
		return search;
	}

	@Override
	public String toString() {
		return nom;
	}
}
