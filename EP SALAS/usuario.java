import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class usuario {
	
	private static void aperte() {
		System.console().writer().println("\nAperte qualquer letra para continuar");
		System.console().readLine();
	}
	private static boolean checaArq(File arqD) {
		if(arqD.exists() != true) {
			System.console().writer().println("\nNao foi definida as datas permitidas par reuniao pelo ORGANIZADOR, favor informar o ORGANIZADOR\n");

			aperte();
			return false;
		}
		return true;
	}
	public static void main(String args[]) {
		
		boolean acaba = false;
		while(acaba == false) {
			System.console().writer().println("Digite o numero para acessar\n");
	
			
			System.console().writer().println("1 ------------ VOCÊ É O ORGAZINADOR (informar Datas de inicio e final possiveis?\n");
			System.console().writer().println("2 ------------ VOCÊ É UM PARTICIPANTE (informar horarios disponiveis e reservas de salas)?\n");
			System.console().writer().println("3 ------------ VOCÊ QUER VER DATAS SUGERIDAS?\n");
			System.console().writer().println("4 ------------ VOCÊ QUER INSERIR UMA SALA?\n");
			System.console().writer().println("5 ------------ VOCÊ QUER VER A LISTA DE SALAS?\n");
			System.console().writer().println("6 ------------ VOCÊ QUER REMOVER UMA SALA?\n");
			System.console().writer().println("7 ------------ VOCÊ QUER CRIAR UMA RESERVA DE SALA?\n");
			System.console().writer().println("8 ------------ VOCÊ DESEJA VISUALIZAR AS RESERVAS DE UMA SALA?\n");
			System.console().writer().println("9 ------------ VOCÊ DESEJA CANCELAR UMA RESERVA?\n");
			System.console().writer().println("10------------ VOCÊ QUER SAIR?\n");
	
			int option = Integer.parseInt(System.console().readLine());
			 
			MarcadorDeReuniao marca = new MarcadorDeReuniao();
			GerenciadorDeSalas gerencia = new GerenciadorDeSalas();
	
			switch(option) {
				case 1:
					
					File arqP = new File("participantes.txt");
					//checa se a lista de participantes e datas da reunao foram definidas e se quer redefinilas
					if(arqP.exists()) {
						System.console().writer().println("VOCE TEM CERTEZA DE QUE QUER REDEFINIR OS PARTICIPANTES E DATAS\n");
						System.console().writer().println("AS DATAS, PARTICIPANTES, RESERVAS, SALAS, E DATAS RECOMENDADAS PREVIAS SERAO APAGADOS\n");
						System.console().writer().println("confirme com s/n \n");
						String confirma = System.console().readLine();
						if(confirma.equals("s") == false && confirma.equals("S") == false) {
							break;	
						}
					}
					
					System.console().writer().println("\nHá quantos participantes\n");
					//passa o proximo string console para int np
					int np = Integer.parseInt(System.console().readLine());
					if(np<=0) {
						System.console().writer().println("\nNumero de participantes invalido\n");
						aperte();
						break;
					}
					System.console().writer().println("\nEscreva os emails de cada participante\n");
					
					Collection<String> listaDeParticipantes = new ArrayList<String>();
		
					//cria collection listaDeParticipantes
					for(int i =0; i < np; i++) {
						String temp = System.console().readLine();
						listaDeParticipantes.add(temp);
					}
					
					System.console().writer().println("Escreva a data de inicio para marcar a reuniao\n");
					System.console().writer().println("dia/Mes/ano   com as barras");
	
		
				    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
				    
					String userInput = System.console().readLine();
				    
				    LocalDate dateIni = LocalDate.parse(userInput, dateFormat);
				    
					System.console().writer().println("Escreva a data final para marcar a reuniao\n");
					System.console().writer().println("dia/Mes/ano   com as barras");
				     
					userInput = System.console().readLine();
		
					LocalDate dateFin = LocalDate.parse(userInput, dateFormat);
					
					marca.marcarReuniaoEntre(dateIni,dateFin,listaDeParticipantes);
					
					File arqdelJ = new File("janelas.txt");
					arqdelJ.delete();
					File arqdelS = new File("salas.txt");
					arqdelS.delete();
					File arqdelR = new File("reservas.txt");
					arqdelR.delete();
					
					aperte();
					
					break;
				case 2:		    
					System.console().writer().println("VOCÊ É UM PARTICIPANTE? s/n\n");
					
					String resp2 = System.console().readLine();
					
					LocalDate OrgIni = null;
					LocalDate OrgFim = null;
					
					//usuario envia data e hora de disponibilidade
					if(resp2.equals("s")||resp2.equals("S")) {
						
						File arqD = new File("datas.txt");
						if(checaArq(arqD) == false) {
							break;
						}
						
						int i = 1;
						int ano,mes,dia,hora,minutos;
						String participante;
						do {
							System.console().writer().println("Escreva seu email\n");
							participante = System.console().readLine();
							
							System.console().writer().println("A data da reuniao deve ser entre\n  ");
							try (BufferedReader br = new BufferedReader(new FileReader("datas.txt"))) {
							    String line;
							    line = br.readLine();
							    OrgIni = LocalDate.parse(line);
								System.console().writer().print(OrgIni+"    ");
							    System.console().writer().println("\n");
							    line = br.readLine();
							    OrgFim = LocalDate.parse(line);
								System.console().writer().print(OrgFim+"    ");
							    System.console().writer().println("\n");
							}
							catch(IOException e){
								System.err.println("Não foi possível ler int do arquivo: " + e);
							}
							
							System.console().writer().println("Insira as datas e horario de INICIO em que você pode participar da reunião\n");
							System.console().writer().println("Insira o ANO de INICIO que você está disponivel para reuniao");
							ano = Integer.parseInt(System.console().readLine());
							System.console().writer().println("Insira o MES de INICIO que você está disponivel para reuniao");
							mes = Integer.parseInt(System.console().readLine());
							System.console().writer().println("Insira o DIA de INICIO que você está disponivel para reuniao");
							dia = Integer.parseInt(System.console().readLine());
							System.console().writer().println("Insira o HORA de INICIO que você está disponivel para reuniao");
							hora = Integer.parseInt(System.console().readLine());
							System.console().writer().println("Insira o MINUTOS de INICIO que você está disponivel para reuniao");
							minutos = Integer.parseInt(System.console().readLine());
							System.console().writer().println("VOCE CONFIRMA OS DADOS? s/n");
							System.console().writer().println(participante+"   data de inicio de disponibilidade: "+dia+"/"+mes+"/"+ano+" "+hora+":"+minutos);
							
							String resp3 = System.console().readLine();
							if(resp3.equals("s")||resp3.equals("S")) {
								i=0;
							}
							else{
								System.console().writer().println("Tente normalmente");
							}
						}while(i==1);
						
			            LocalDateTime inicio = LocalDateTime.of(ano, mes, dia, hora, minutos);
			            //verifica se a data inicio e valida
			            
			            if(inicio.isBefore(OrgIni.atStartOfDay()) == true) {
							System.console().writer().println("Data invalida");
							return;
			            }
			            
			            i = 1;
						do {
							System.console().writer().println("Insira as datas e horario de FIM em que você pode participar da reunião\n");
							System.console().writer().println("Insira o ANO de FIM que você está disponivel para reuniao");
							ano = Integer.parseInt(System.console().readLine());
							System.console().writer().println("Insira o MES de FIM que você está disponivel para reuniao");
							mes = Integer.parseInt(System.console().readLine());
							System.console().writer().println("Insira o DIA de FIM que você está disponivel para reuniao");
							dia = Integer.parseInt(System.console().readLine());
							System.console().writer().println("Insira o HORA de FIM que você está disponivel para reuniao");
							hora = Integer.parseInt(System.console().readLine());
							System.console().writer().println("Insira o MINUTOS de FIM que você está disponivel para reuniao");
							minutos = Integer.parseInt(System.console().readLine());
							System.console().writer().println("VOCE CONFIRMA OS DADOS? s/n");
							System.console().writer().println("data final de disponibilidade: "+dia+"/"+mes+"/"+ano+" "+hora+":"+minutos);
							
							String resp3 = System.console().readLine();
							if(resp3.equals("s")||resp3.equals("S")) {
								i=0;
							}
							else{
								System.console().writer().println("Tente normalmente");
							}
						}while(i==1);
						
			            LocalDateTime fim = LocalDateTime.of(ano, mes, dia, hora, minutos);
			            
			            //checa se janela de tempo é possivel
			            if(fim.isBefore(inicio) || fim.isEqual(inicio) || fim.isAfter(OrgFim.atStartOfDay())) {
							System.console().writer().println("erro, janela de tempo entre inicio e fim impossivel");
							return;
			            }
			            marca.indicaDisponibilidadeDe(participante,inicio,fim);
					}
					aperte();

					break;
				case 3:
					File arqJ = new File("janelas.txt");
					if(checaArq(arqJ) == false) {
						break;
					}
					
					marca.mostraSobreposicao();
					aperte();
					
					break;
				case 4:
							        
					System.console().writer().println("Insira Nome da Sala");
					String nome = System.console().readLine();
					System.console().writer().println("Insira o endereço da Sala");
					String local = System.console().readLine();
					System.console().writer().println("Escreva uma descrição para sua Sala");
					String obs = System.console().readLine();
					System.console().writer().println("Insira a capacidade da Sala");
					int capacidade= Integer.parseInt(System.console().readLine());
			        Sala reserva = new Sala(nome,local,obs,capacidade);
			        gerencia.adicionaSala(reserva);
			        
					aperte();

					break;
				case 5:
					
			        List<Sala> leSala = new ArrayList<>();
	
					leSala = gerencia.listaDeSalas();
					System.console().writer().println("\nNumero de salas: "+leSala.size()+"\n");

					for(int d = 0; d<leSala.size();d++) {
						System.console().writer().println("Nome da Sala "+d+": "+leSala.get(d).getNome());
						System.console().writer().println("Local da Sala: "+leSala.get(d).getLocal());	
						System.console().writer().println("Capacidade maxima da Sala: "+leSala.get(d).getCapacidade());	
						System.console().writer().println("Obervacoes sobre a Sala: \n   "+leSala.get(d).getLocal());	
					}
					aperte();

					
					break;
				case 6:
					File arqS = new File("salas.txt");
					if(checaArq(arqS) == false) {
						break;
					}
					
					System.console().writer().println("Insira o nome da Sala a ser deletada");
					String nomeDaSala = System.console().readLine();
					gerencia.removeSalaChamada(nomeDaSala);
					aperte();
					break;
				case 7:
					System.console().writer().println("Insira o nome da Sala a ser Reservada");
					String nomeDaReserva = System.console().readLine();
					int h = 1;
					int g = 1;
					int anoRF,mesRF, diaRF, horaRF, minutosRF;
					int anoRI,mesRI, diaRI, horaRI, minutosRI;

					System.console().writer().println("A data da reuniao deve ser entre\n   ");
					try (BufferedReader br = new BufferedReader(new FileReader("datas.txt"))) {
					    String line;
					    line = br.readLine();
					    OrgIni = LocalDate.parse(line);
						System.console().writer().print(OrgIni+"    ");
					    System.console().writer().println("\n");
					    line = br.readLine();
					    OrgFim = LocalDate.parse(line);
						System.console().writer().print(OrgFim+"    ");
					    System.console().writer().println("\n");
					}
					catch(IOException e){
						System.err.println("Não foi possível ler int do arquivo: " + e);
					}
					
					do{							
						System.console().writer().println("Insira as datas e horario de INICIO em que a sala pode participar da reunião\n");
						System.console().writer().println("Insira o ANO de INICIO que a sala está disponivel para reuniao");
						anoRI = Integer.parseInt(System.console().readLine());
						System.console().writer().println("Insira o MES de INICIO que a sala está disponivel para reuniao");
						mesRI = Integer.parseInt(System.console().readLine());
						System.console().writer().println("Insira o DIA de INICIO que a sala está disponivel para reuniao");
						diaRI = Integer.parseInt(System.console().readLine());
						System.console().writer().println("Insira a HORA de INICIO que a sala está disponivel para reuniao");
						horaRI = Integer.parseInt(System.console().readLine());
						System.console().writer().println("Insira o MINUTOS de INICIO que a sala está disponivel para reuniao");
						minutosRI = Integer.parseInt(System.console().readLine());
						System.console().writer().println("VOCE CONFIRMA OS DADOS? s/n");
						System.console().writer().println( "data de inicio de disponibilidade: "+diaRI+"/"+mesRI+"/"+anoRI+" "+horaRI+":"+minutosRI);
					
						String resp3 = System.console().readLine();
						if(resp3.equals("s")||resp3.equals("S")) {
							g = 0;
						}
						else{
							System.console().writer().println("Tente normalmente");
						}
					//repete até aceitar
					}while(g==1);
					// insere a data e hora no console
					do {
						System.console().writer().println("Insira as datas e horario de FIM em que a sala pode participar da reunião\n");
						System.console().writer().println("Insira o ANO de FIM que a sala está disponivel para reuniao");
						anoRF = Integer.parseInt(System.console().readLine());
						System.console().writer().println("Insira o MES de FIM que a sala está disponivel para reuniao");
						mesRF = Integer.parseInt(System.console().readLine());
						System.console().writer().println("Insira o DIA de FIM que a sala está disponivel para reuniao");
						diaRF = Integer.parseInt(System.console().readLine());
						System.console().writer().println("Insira a HORA de FIM que a sala está disponivel para reuniao");
						horaRF = Integer.parseInt(System.console().readLine());
						System.console().writer().println("Insira o MINUTOS de FIM que a sala está disponivel para reuniao");
						minutosRF = Integer.parseInt(System.console().readLine());
						System.console().writer().println("VOCE CONFIRMA OS DADOS? s/n");
						System.console().writer().println("data final de disponibilidade: "+diaRF+"/"+mesRF+"/"+anoRF+" "+horaRF+":"+minutosRF);
						
						String resp3 = System.console().readLine();
						if(resp3.equals("s")||resp3.equals("S")) {
							h=0;
						}
						else{
							System.console().writer().println("Tente normalmente");
						}
					}while(h==1);
				
					LocalDateTime inicio = LocalDateTime.of(anoRI, mesRI, diaRI, horaRI, minutosRI);
					
		            LocalDateTime fim = LocalDateTime.of(anoRF, mesRF, diaRF, horaRF, minutosRF);

		            
		            if(inicio.isAfter(fim) || fim.isBefore(inicio)) {
		            	break;
		            }
					gerencia.reservaSalaChamada(nomeDaReserva,inicio,fim);
					
					aperte();
					break;
				case 8:
					File arqR = new File("reservas.txt");
					if(checaArq(arqR) == false) {
						break;
					}
					
					System.console().writer().println("Entre o nome da sala desejada");
					String nomeCheca = System.console().readLine();
					gerencia.imprimeReservasDaSala(nomeCheca);
					
					aperte();
					break;
				case 9:
					File arqR1 = new File("reservas.txt");
					if(checaArq(arqR1) == false) {
						break;
					}
					

					System.console().writer().println("Entre o nome da sala a ter sua reserva ser removida");
					String nomeSai = System.console().readLine();
					
					Collection<Reserva> procuraSala = new ArrayList<>();

			        if(arqR1.exists()) {
			        	procuraSala = gerencia.listaDeReservas();
			        }
			    	boolean achouSala = false;
			    	
			    	for (Iterator<Reserva> it = procuraSala.iterator(); it.hasNext();) {
			    		Reserva chamado = it.next();

			    		if(chamado.getSala().getNome().equals(nomeSai)) {
						    gerencia.cancelaReserva(chamado);
						    achouSala = true;
			    		}
			    	}
					if(achouSala == false) {
						System.console().writer().println("Não encontramos uma sala reservada com esse nome\n");
					}
					
					aperte();
					break;
				case 10:
					System.console().writer().println("\nOK!\n");
					return;
					
				default:
					System.console().writer().println("ERRO\n");
					return;
			}
		}
	}
}

