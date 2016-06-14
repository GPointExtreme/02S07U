package org.uebung.wettserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FussballTippServer {
	
	private static ArrayList<ProcessClient> list = new ArrayList<>();

	public ArrayList<ProcessClient> getList() {
		return list;
	}

	public static void main(String[] args) {
		try (
			ServerSocket server = new ServerSocket(1111);
			) {
			while(true) {
				Socket client = server.accept();
				ProcessClient pc = new ProcessClient(client);
				FussballTippServer fts = new FussballTippServer();
				fts.getList().add(pc);
				Thread t = new Thread(pc);
				t.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
