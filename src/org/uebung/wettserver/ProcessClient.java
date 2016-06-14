package org.uebung.wettserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ProcessClient implements Runnable{
	
	private ServerLogic logic = new ServerLogic();
	private Socket client;

	public ProcessClient(Socket client) {
		super();
		this.client = client;
	}

	public Socket getClient() {
		return client;
	}

	@Override
	public void run() {
		try (
			InputStreamReader isr = new InputStreamReader(client.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			OutputStreamWriter osw = new OutputStreamWriter(client.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);
			) {
			String line;
			while((line = br.readLine()) != null) {
				String[] array = line.split(" ");
				
				if(array[0].equals("put")) {
					pw.println(logic.put(array[1], array[2]));
				}
				else if(array[0].equals("ask")) {
					pw.print(logic.ask(array[1]));
				}
				else if(array[0].equals("start")) {
					logic.start();
					pw.println("Spiel wurde gstartet");
				}
				else if(array[0].equals("stop")) {
					logic.stop();
					pw.println("Spiel wurde beendet");
				}
				else if(array[0].equals("shutdown")) {
					logic.shutdown();
				}
				else {
					pw.println("Falsche Eingabe");
				}
				pw.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
