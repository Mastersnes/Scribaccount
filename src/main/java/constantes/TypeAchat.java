package constantes;

public enum TypeAchat {
	LOGICIEL("0", Phrases.LOGICIEL), LICENCE("2", Phrases.COPYRIGHT), MATERIEL("3", Phrases.MATERIEL), ACHAT_STOCK("4",
			Phrases.ACHAT_STOCK), PERTE_STOCK("5", Phrases.PERTE_STOCK), EXCEPTIONNELLE("6", Phrases.EXCEPTIONELLE);

	private String id;
	private String nom;

	private TypeAchat(final String id, final String nom) {
		this.id = id;
		this.nom = nom;
	}

	public String getId() {
		return id;
	}

	public static TypeAchat findById(final String idTypeAchat) {
		for (final TypeAchat type : values()) {
			if (type.id.equals(idTypeAchat)) {
				return type;
			}
		}
		return null;
	}

	public static TypeAchat findByName(final String nameSearched) {
		for (final TypeAchat type : values()) {
			if (type.nom.equals(nameSearched)) {
				return type;
			}
		}
		return null;
	}

	public static TypeAchat find(final String newValue) {
		TypeAchat search = findById(newValue);
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
