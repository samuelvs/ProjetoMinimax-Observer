import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class JogoGUI implements Observer{
	
	private Scanner ler = new Scanner(System.in);
	private Observable tabuleiro;
	private Jogo jogo;
	private int modo;
	private boolean vencedor;
	
	public int getModo() {
		return modo;
	}

	public void setModo(int modo) {
		this.modo = modo;
	}
	
	public JogoGUI(Observable tabuleiro, Jogo jogo) {
		this.tabuleiro = tabuleiro;
		this.jogo = jogo;
		this.vencedor = false;
		tabuleiro.addObserver(this);
		mostrarMenu();
	}
	
	public void mostrarMenu() {
		System.out.println();
		System.out.println("\t\t Jogo da Velha");
		System.out.println("\n");
		System.out.println("\tEscolha uma das opções abaixo:");
		System.out.println();
		System.out.println("\t1 -> Jogador 1 vs Jogador 2");
		System.out.println("\t2 -> Jogador 1 vs Computador");
		System.out.println("\t3 -> Computador vs Computador");
		System.out.println("\t4 -> Instruções");
		this.modo = ler.nextInt();
		if(modo == 1) adicionarJogador(1);
		if(modo == 2) adicionarJogador(2);
		if(modo == 3) adicionarJogador(3);
		if(modo == 4) imprimirInstrucoes();
	}
	
	public void adicionarJogador(int modo) {
		ler.nextLine();
		if(modo == 1) {
			System.out.println("\tJogador 1 deseja X ou O ?");
			String esp = caractereValido();
			jogo.adicionarJogadores(new Jogador((esp.equalsIgnoreCase("x") ? 1 : -1)));
			jogo.adicionarJogadores(new Jogador((esp.equalsIgnoreCase("x") ? -1 : 1)));
			telaDeJogadas();
		}
		else if(modo == 2) {
			jogo.adicionarJogadores(new Jogador());
			jogo.adicionarJogadores(new Minimax(3, -1));
			telaDeJogadas();
		}
		else if(modo == 3) {
			jogo.adicionarJogadores(new Minimax(3, -1));
			jogo.adicionarJogadores(new Minimax(3, 1));
			telaDeJogadas();
		}
	}
	
	public void telaDeJogadas() {
		int x, y, i = 0;
		int jogador = 0;
		do {
			int l, c;
			System.out.println("\n\t\tRodada : " + (i+1));
			
			if(i%2==0) jogador = 0;
			else jogador = 1;
			
			System.out.print("\n\tVez do jogador: " + (jogador+1));
			
			if(this.modo == 1) {
				System.out.println("\n\tInforme a linha [0]-[2]");
				l = verificarEscolha();
				
				System.out.println("\tInforme a coluna [0]-[2]");
				c = verificarEscolha();
				
				jogo.fazerJogadaJogadores(l,c,jogador);
			} else if(this.modo == 2) {
				if(jogador == 0) {
					System.out.println("\n\tInforme a linha [0]-[2]");
					l = verificarEscolha();
					
					System.out.println("\tInforme a coluna [0]-[2]");
					c =  verificarEscolha();
					
					jogo.fazerJogada(l,c,jogador);	
				} else {
					jogo.setTabuleiro();
				}
				
			} else if(modo == 3) {
				if(jogador == 0) {
					jogo.jogadasTabuleiroComputadores();	
				} else {
					jogo.jogadasTabuleiroComputadores();
				}
			}
			
			i++;
		} while (!vencedor && i < 9) ;

		mensagemVencedor();
		fimDePartida();
	}
	
	public int verificarEscolha() {
		int escolha;
		do {
			escolha = ler.nextInt();
			if(escolha >= 0 && escolha <= 2) return escolha;
			else System.out.println("Numero invalido, tente novamente!");
		}while(true);
	}
	
	public void mensagemVencedor() {
		if(jogo.getVencedor() != " ") {
			if(jogo.getVencedor().equalsIgnoreCase("x")) System.out.println("\n\tX vencedor");
			else System.out.println("\n\tO vencedor");
		}
		else System.out.println("\n\tEmpate");; 
	}
	
	public void fimDePartida() {
		System.out.println("\n\t\t1 - Continuar jogando.");
		System.out.println("\t\t2 - Voltar ao menu.");
		if(ler.nextInt() == 1) {
			jogo.novaPartida();
			this.vencedor = false;
			telaDeJogadas();
		} else {
			jogo.reiniciarJogo();
			this.vencedor = false;
			mostrarMenu();
		}
	}
	
	public String caractereValido() {
		String esp;
		do {
			esp = ler.nextLine();
			if(!(esp.equalsIgnoreCase("x") || esp.equalsIgnoreCase("o"))) {
					System.out.println("\n\tCaractere Invalido, escolha novamente");
			}
		}while (!(esp.equalsIgnoreCase("x") || esp.equalsIgnoreCase("o")));
		
		return esp.toUpperCase().toString();
	}

	public void imprimirInstrucoes(){
		System.out.println();
		System.out.println("\t    0   1   2");
		for(int i = 0; i < 3; i++) {
			System.out.print("\t  " + i);
			for(int j = 0; j < 3; j++) {
				System.out.print("   ");
				if(j < 2) System.out.print("|");
			}
			if(i < 2) {
				System.out.println();
				System.out.println("\t   ------------");
			}
		}
		System.out.println("\n\nAperte qualquer tecla para voltar ao menu.");
		ler.nextLine();
		ler.nextLine();
		mostrarMenu();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Tabuleiro t = (Tabuleiro) o;
		if(jogo.verificarVencedor(t.getTabuleiro())) vencedor = true;
		t.imprimir();
	}

}
