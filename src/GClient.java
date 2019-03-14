import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class GClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
		Socket s=new Socket("localhost",10000);//istanzio il socket per connessione con server (gioco)
		Scanner tastiera=new Scanner(System.in);//scanner per la lettura da tastiera
		char[] cbuf=new char[22];//buffer per acquisizione caratteri (la funzione read() legge solo 1 carattere per volta)
		int quanti_letti;
		String cosa_riceve,cosa_inviare;
		InputStreamReader isr = new InputStreamReader(s.getInputStream());//flusso in ingresso
		PrintWriter output=new PrintWriter(new BufferedOutputStream(s.getOutputStream()), true);//flusso in output (true=flushable)
		quanti_letti=isr.read(cbuf,0,22);//lettura bytes (caratteri) in ingresso SINTASSI: read(buffer,primo byte,lunghezza buffer)
		cosa_riceve= new String(cbuf);//stringa che indica cosa è stato inviato al server
		System.out.println("Ricevuto ["+cosa_riceve.trim()+"]");
		do
		{
		cosa_inviare=tastiera.nextLine(); //lettura da tastiera
		System.out.println("Hai scritto ["+cosa_inviare+"]");//e.g. Hai scritto [SCA]
		output.println(cosa_inviare);//stringa inviata al server tramite outputstream (printwriter)
		cbuf=new char[22];
		quanti_letti=isr.read(cbuf,0,22);//come sopra
		cosa_riceve= new String(cbuf);
		System.out.println("Ricevuto ["+cosa_riceve.trim()+"]");
		}while(!cosa_inviare.equals("DIS"));//continua finchè il client non manda il comando DIS che termina la connessione col server
		
		cbuf=new char[22];
		quanti_letti=isr.read(cbuf,0,22);//read conta il numero di caratteri di un buffer
		cosa_riceve= new String(cbuf);
		System.out.println("Ricevuto ["+cosa_riceve.trim()+"]");
		s.close();
		}
		catch(Exception e)
		{
		throw new RuntimeException(e);
		}
		}

	}


