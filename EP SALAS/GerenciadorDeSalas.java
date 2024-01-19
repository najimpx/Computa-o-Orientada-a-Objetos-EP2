import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;


public class GerenciadorDeSalas {
	
    private File arqS = new File("salas.txt");
    private File arqR = new File("reservas.txt");

	public void adicionaSalaChamada(String nome, int capacidadeMaxima,String descricao) {

		List<Sala>novasala = listaDeSalas();
		
		Sala adiciona = new Sala(nome," ",descricao,capacidadeMaxima);
		
		novasala.add(adiciona);
	}
	public void removeSalaChamada(String nomeDaSala) {
		ArrayList<Sala>completa = getSalas();
	    Iterator<Sala> itr = completa.iterator();
		Sala salinha;

	    while (itr.hasNext()) {
	        salinha = itr.next();
	        if(salinha.getNome().equals(nomeDaSala)) {
	        	itr.remove();
	        	
	    	    try {
	    	    	arqS.delete();
	    	    	writeToFile(completa);
	    	    }
	    		catch(IOException e) {
	    			System.console().writer().println("Não foi possível ler int do arquivo: " + e);
	    		}
	        }
	     }
	}

	public List<Sala> listaDeSalas(){
        ArrayList<Sala> novaDala = new ArrayList<>();

        if(arqS.exists()) {
        	novaDala = getSalas();
        }
		return novaDala;
	}
	
	public void adicionaSala(Sala novaSala) {
        
        ArrayList<Sala> novasala = new ArrayList<>();
        if(arqS.exists()) {
        	novasala = getSalas();
        }
        novasala.add(novaSala);

        try {
        	arqS.delete();
        	writeToFile(novasala);
            ArrayList<Sala> novaDala = new ArrayList<>();

        	novaDala = getSalas();
			for(int d = 0; d<novaDala.size();d++) {
				System.console().writer().println("\nNome da Sala "+d+": "+novaDala.get(d).getNome());
				System.console().writer().println("Local da Sala: "+novaDala.get(d).getLocal());	
				System.console().writer().println("Capacidade maxima da Sala: "+novaDala.get(d).getCapacidade());	
				System.console().writer().println("Obervacoes sobre a Sala: \n   "+novaDala.get(d).getLocal());	
			}
        }
		catch(IOException e) {
			System.console().writer().println("Não foi possível ler int do arquivo: " + e);
		}
        
	}
	
	private static void writeToFile(ArrayList<Sala> salas) throws FileNotFoundException, IOException{
	    String path = "salas.txt";
	    FileOutputStream fos = new FileOutputStream(path, true);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(salas);
	    oos.close();
	}
	
	public static ArrayList<Sala> getSalas(){
	    try{
	        FileInputStream fis = new FileInputStream("salas.txt");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        ArrayList<Sala> students1 = (ArrayList<Sala>) ois.readObject();
	        ois.close();
	        return students1;
	    } catch( ClassNotFoundException | IOException ex){
	    	System.console().writer().println(ex.getMessage());
	        return null;
	    }
	}
	
	

    public Object ReadObjectFromFile() {
    	try {
     
            FileInputStream fileIn = new FileInputStream("guarda.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
     
            Object obj = objectIn.readObject();
     
                System.out.println("The Object has been read from the file");
                objectIn.close();
                return obj;
     
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
         }
	
    }
	public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) {
		
		//procura sala pelo nome
		List<Sala> procuraSala = new ArrayList<>();
        if(arqS.exists()) {
        	procuraSala = listaDeSalas();
        }
        Sala temp;
    	Reserva chamada = new Reserva();
    	boolean achouSala = false;
    	
		for(int d = 0; d<procuraSala.size();d++) {
			if(procuraSala.get(d).getNome().equals(nomeDaSala)) {
				temp = procuraSala.get(d);
				
				Collection<Reserva> entra = new ArrayList<>();
				if(arqR.exists()) {
					entra = getReserva();
				}
				
		    	chamada.setSala(temp);
		    	chamada.setInicio(dataInicial);
		    	chamada.setFim(dataFinal);
		    	
		    	entra.add(chamada);
			    try {
			    	arqR.delete();
			    	writeReservaToFile(entra);
			    }
				catch(IOException e) {
					System.console().writer().println("Não foi possível ler int do arquivo: " + e);
				}
			    achouSala = true;
			}
		}
		if(achouSala == false) {
			System.console().writer().println("Não foi possível encontrar uma sala com esse nome, reserva falhou");
		}
		return chamada;
	}
	
	public static Collection<Reserva> getReserva(){
	    try{
	        FileInputStream fis = new FileInputStream("reservas.txt");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        Collection<Reserva> students1 = (Collection<Reserva>) ois.readObject();
	        ois.close();
	        return students1;
	    } catch( ClassNotFoundException | IOException ex){
	    	System.console().writer().println(ex.getMessage());
	        return null;
	    }
	}
	
	public Collection<Reserva> listaDeReservas(){
        Collection<Reserva> lReserva = new ArrayList<>();

        if(arqR.exists()) {
        	lReserva = getReserva();
        }
		return lReserva;
	}
	
	public void cancelaReserva(Reserva cancelada) {
	   	Collection<Reserva> entra =  getReserva();
	   	Collection<Reserva> alt = new ArrayList<>();
	   	
	    Iterator<Reserva> itr = entra.iterator();
		Reserva salinha;

	    while (itr.hasNext()) {
	        salinha = itr.next();

	        String nome1 = salinha.getSala().getNome();
	        String nome2 = cancelada.getSala().getNome();
	        
	        if(nome1.equals(nome2)) {
    	    	try {
    	            if(arqR.exists()) {
    	            	alt = getReserva();
    	            }
    	    		arqR.delete();
    	        	itr.remove();
        	   		writeReservaToFile(entra);
    	    	}
	    		catch(IOException e) {
	    			System.console().writer().println("Não foi possível ler int do arquivo: " + e);
	    		}
	        }
	     }
    	
	}
	
	private static void writeReservaToFile(Collection<Reserva> entra) throws FileNotFoundException, IOException{
	    String path = "reservas.txt";
	    File arqR = new File("reservas.txt");
	    if(arqR.exists() == false) {
	    	arqR.createNewFile();
	    }
	    FileOutputStream fos = new FileOutputStream(path, true);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(entra);
	    oos.close();
	}
	
	public Collection<Reserva> reservasParaSala(String nomeSala){
	    try{
	        FileInputStream fis = new FileInputStream("reservas.txt");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        Collection<Reserva> reservaTotal = (Collection<Reserva>) ois.readObject();
	        ois.close();
	        return reservaTotal;
	    } catch( ClassNotFoundException | IOException ex){
	    	System.console().writer().println(ex.getMessage());
	        return null;
	    }
	}
	 public void imprimeReservasDaSala(String nomeSala) {
	    Collection<Reserva> entra =  reservasParaSala(nomeSala);
	    Iterator<Reserva> itr = entra.iterator();
		Reserva reservinha;
		boolean achou = false;
		
	    while (itr.hasNext()) {
	    	reservinha = itr.next();
	    	if(reservinha.getSala().getNome().equals(nomeSala)) {
    			System.console().writer().println("Nome da Sala: " + reservinha.getSala().getNome());
    			System.console().writer().println("Data de inicio da Reserva: " + reservinha.getInicio());
    			System.console().writer().println("Data de final da Reserva: " + reservinha.getFim());
    			achou = true;
		    }
	    }
	    if(achou == false) {
			System.console().writer().println("Nao achamos uma RESERVA para uma SALA com esse nome\n");
	    }
	 }
}
