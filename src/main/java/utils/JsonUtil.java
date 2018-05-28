package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.Date;

import frame.MainFrame;

public class JsonUtil {
	final SimpleDateFormat sf = new SimpleDateFormat("MM-yyyy");

    private static final String RACINE = "/..";
	private static final String FILE_PATH = "/save.json";
	private static final String ARCHIVE_PATH = "/old/";
	private static JsonUtil instance;

	private JsonUtil() {
	}

	public static JsonUtil getInstance() {
		if (instance == null) {
			instance = new JsonUtil();
		}
		return instance;
	}

	public String load() {
		String json = "";
		BufferedReader in = null;
		try {
			final File data = new File(getApplicationRootDirectory(), FILE_PATH);
			System.out.println("Chemin de chargement : " + data.getAbsolutePath());
			in = new BufferedReader(new FileReader(data));
			String line;
			while ((line = in.readLine()) != null) {
				json += line;
			}
		} catch (final Exception e) {
			System.out.println("Erreur lors du chargement du serveur : " + e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final IOException e) {
					System.out.println("Impossible de fermer le fichier de sauvegarde");
				}
			}
		}
		return json;
	}

	public String getApplicationRootDirectory() {
		final CodeSource codeSource = MainFrame.class.getProtectionDomain().getCodeSource();
		final File jarFile = new File(codeSource.getLocation().getPath());
		return jarFile.getAbsolutePath() + RACINE;
	}

	public void secureSave(final String json) {
		final String date = sf.format(new Date());

		final File archiveDir = new File(getApplicationRootDirectory(), ARCHIVE_PATH);
		if (!archiveDir.exists()) {
			archiveDir.mkdir();
			archiveDir.setReadable(true, false);
			archiveDir.setExecutable(true, false);
			archiveDir.setWritable(true, false);
		}
		final File archiveDateDir = new File(getApplicationRootDirectory(), ARCHIVE_PATH + date);
		if (!archiveDateDir.exists()) {
			archiveDateDir.mkdir();
			archiveDir.setReadable(true, false);
			archiveDir.setExecutable(true, false);
			archiveDir.setWritable(true, false);
			save(json, ARCHIVE_PATH + date + FILE_PATH);
		}
	}

	public void save(final String json) {
		save(json, FILE_PATH);
	}

	public void save(final String json, final String where) {
		BufferedWriter out = null;
		try {
			final File data;
			data = new File(getApplicationRootDirectory(), where);
			System.out.println("Chemin de sauvegarde : " + data.getAbsolutePath());
			if (!data.exists()) {
				data.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(data));
			out.append(json);
			out.flush();
		} catch (final Exception e) {
			System.out.println("Erreur lors de la sauvegarde du serveur : " + e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (final IOException e) {
					System.out.println("Impossible de fermer le fichier de sauvegarde");
				}
			}
		}
	}
}
