import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java. util. Collection;
import java.util.Iterator;


public class MarcadorDeReuniao {

	private Collection<participante> participa = new ArrayList<participante>();

	
	public void marcarReuniaoEntre(LocalDate dataInicial,LocalDate dataFinal,Collection<String> listaDeParticipantes) {
		
		File arquivo = new File( "participantes.txt" );
		boolean existe = arquivo.exists();
		try{
			//cria novo arquivo caso nao exista
			if(existe == false) {
				arquivo.createNewFile();
			}
		}
		catch(IOException e) {
			System.console().writer().println("Não foi possível ler int do arquivo: " + e);
		}
		
		// escreve no arquivo a lista de participantes e a cada vez chamada refaz a lista
		try {

			FileWriter writer = new FileWriter(arquivo);

			for(Iterator<String> iterator = listaDeParticipantes.iterator(); iterator.hasNext();) {
				
				String temp = (String) iterator.next(); 
				
				writer.write(temp+"\n");
			}
			writer.close();
		}
		catch(IOException e) {
			System.err.println("Não foi possível ler int do arquivo: " + e);
		}

		try {
			FileReader fr = new FileReader( arquivo );
			BufferedReader br = new BufferedReader( fr );
			//enquanto houver mais linhas
			while( br.ready() ){
				String linha = br.readLine();
				System.console().writer().println(linha+"\n");
			}
			br.close();
			fr.close();
		}
		catch(IOException e) {
			System.err.println("Não foi possível ler int do arquivo: " + e);
		}
		
		File datas = new File( "datas.txt" );
		boolean existeD = datas.exists();

		try{
			//cria novo arquivo caso nao exista
			if(existeD == false) {
				datas.createNewFile();
			}
		}
		catch(IOException e) {
			System.err.println("Não foi possível ler int do arquivo: " + e);
		}
		try {

			FileWriter writerD = new FileWriter(datas);
				
				
			writerD.write(dataInicial.toString()+"\n");
			writerD.write(dataFinal.toString()+"\n");

			writerD.close();
		}
		catch(IOException e) {
			System.err.println("Não foi possível ler int do arquivo: " + e);
		}
		
	}
	
	public void indicaDisponibilidadeDe(String participante,LocalDateTime inicio,LocalDateTime fim) {
		participante individuo = new participante();
		individuo.nome = participante;
		individuo.inicio = inicio;
		individuo.fim = fim;
		
		participa.add(individuo);

		//pega cada email
		try (BufferedReader br = new BufferedReader(new FileReader("participantes.txt"))) {
		    String line;
		    boolean achaParticipante = false;
		    while ((line = br.readLine()) != null) {
				if(participante.equals(line)) {
					achaParticipante = true;
				}
		    }
		    if(achaParticipante == false) {
				System.console().writer().println("email nao encontrado na lista");
				return;
		    }
		}
		catch(IOException e){
			System.err.println("Não foi possível ler int do arquivo: " + e);
		}

		
		File janelas = new File( "janelas.txt" );
		boolean existeJ = janelas.exists();

		try{
			//cria novo arquivo caso nao exista
			if(existeJ == false) {
				janelas.createNewFile();
			}
		}
		catch(IOException e) {
			System.err.println("Não foi possível ler int do arquivo: " + e);
		}
		try {

			FileWriter writerD = new FileWriter(janelas,true);
			writerD.write(individuo.nome.toString()+"\n");
			writerD.write(individuo.inicio.toString()+"\n");
			writerD.write(individuo.fim.toString()+"\n");

			writerD.close();
		}
		catch(IOException e) {
			System.err.println("Não foi possível ler int do arquivo: " + e);
		}
	}
	
	
	public void mostraSobreposicao() {
		try (BufferedReader br = new BufferedReader(new FileReader("janelas.txt"))) {
		    String line;
		    LocalDateTime tempIni = null;
		    LocalDateTime tempIniDef = null;

		    LocalDateTime tempFim = null;
		    LocalDateTime tempFimDef = null;

		    
		    int k = 0;
		    while ((line = br.readLine()) != null) {
		    	if(k==1) {
		    		tempIniDef = LocalDateTime.parse(line);
		    	}
		    	if(k==2) {
		    		tempFimDef = LocalDateTime.parse(line);
		    	}
		    	switch(k % 3) {
		    		case 0:
		    			break;
		    		case 1:
		    			tempIni = LocalDateTime.parse(line);
		    			if(tempIni.isAfter(tempIniDef) && (tempIni.isBefore(tempFimDef) || k < 2)) {
		    				tempIniDef = tempIni;
		    			}
		    			break;
		    		case 2:
		    			tempFim = LocalDateTime.parse(line);
		    			if(tempFim.isBefore(tempFimDef) && tempFim.isAfter(tempIniDef)) {
		    				tempFimDef = tempFim;
		    			}
		    			break;
		    	}
		    	k++;
		    }
			System.console().writer().println(tempIniDef+"  "+tempFimDef);

		}
		catch(IOException e) {
			System.err.println("Não foi possível ler int do arquivo: " + e);
		}
	}
	public Collection<participante> funcionario(){
		return participa;
	}
}
