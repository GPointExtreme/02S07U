package org.uebung.wettserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerLogic {
	
	private static HashMap<String, ArrayList<String>> map = new HashMap<>();
	private SpielZustand spiel = SpielZustand.vor_dem_spiel;

	public SpielZustand getSpiel() {
		return spiel;
	}

	public String put(String name, String tipp) {
		if(spiel == SpielZustand.vor_dem_spiel) {
			if(map.containsKey(tipp)) {
				ArrayList<String> a = map.get(tipp);
				a.add(name);
				map.put(tipp, a);
			}
			else {
				ArrayList<String> a = new ArrayList<>();
				a.add(name);
				map.put(tipp, a);
			}
			return "Wette eingereicht";
		}
		else {
			return "Es werden keine Wetten mehr angenommen";
		}
	}
	
	public void start() {
		spiel = spiel.im_spiel;
	}
	
	public String ask(String tipp) {
		String name = "";
		if(map.containsKey(tipp)) {
			ArrayList<String> a = map.get(tipp);
			for (String string : a) {
				name += string + "\n";
			}
		}
		else {
			name += "Wette nicht vorhanden";
		}
		return name;
	}
	
	public void stop() {
		spiel = spiel.nach_dem_spiel;
	}
	
	public void shutdown() {
		FussballTippServer fts = new FussballTippServer();
		ArrayList<ProcessClient> list = fts.getList();
		for (ProcessClient processClient : list) {
			try {
				processClient.getClient().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
