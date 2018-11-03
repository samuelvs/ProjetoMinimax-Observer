import java.util.ArrayList;

public class Jogo{
		
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	private String vencedor;
	private Tabuleiro tabuleiro;
	private Minimax minimax = new Minimax(3 , -1);
	
	private int x = 1;
	private int o = -1;
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro() {
		int[][] novoTab = minimax.fazerMinimax(tabuleiro.getTabuleiro());
		tabuleiro.setTabuleiro(novoTab);
	}

	public Jogo(Tabuleiro tab) {
		this.tabuleiro = tab;
		this.vencedor = " ";
	}
	
	public void adicionarJogadores(Jogador jogador) {
		jogadores.add(jogador);
	}

	public void fazerJogada(int l, int c, int jogador) {
		int esp = jogadores.get(jogador).getEspecialidade();
		tabuleiro.fazerJogada(l, c, esp);
	}
	
	public void fazerJogadaJogadores(int l, int c, int jogador) {
		int esp = jogadores.get(jogador).getEspecialidade();
		tabuleiro.fazerJogadaJogadores(l, c, esp);
	}
	
	public void jogadasTabuleiroComputadores(int jogador) {
		int[][] tabAlterado;
		tabAlterado = tabuleiro.getTabuleiro();
		if(jogador == 1) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					tabAlterado[i][j] = (tabAlterado[i][j]*-1);
				}
			}
			
			tabAlterado = minimax.fazerMinimax(tabAlterado);
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					tabAlterado[i][j] = (tabAlterado[i][j]*-1);
				}
			}
		}else if(jogador == 0) {
			tabAlterado = minimax.fazerMinimax(tabAlterado);
		}
		
		tabuleiro.setTabuleiro(tabAlterado);
	}

	public String getVencedor() {
		return vencedor;
	}


	public void setVencedor(String vencedor) {
		this.vencedor = vencedor;
	}
	

	public boolean verificarVencedor(int[][] tab) {
		return vencedorLinha(tab) || vencedorColuna(tab) || vencedorDiag1(tab) || vencedorDiag2(tab);
	}

	private boolean vencedorLinha ( int[][] tab ) {
		for ( int i = 0 ; i < 3 ; i++) {
			if(tab[0][i] == x && tab[1][i] == x && tab[2][i] == x) {
				this.vencedor = "X";
				return true;
			}
			else if(tab[0][i] == o && tab[1][i] == o && tab[2][i] == o) {
				this.vencedor = "O";
				return true;
			}
		}
		return false;
	}

	private boolean vencedorColuna ( int[][] tab ){
		for ( int i = 0 ; i < 3 ; i++) {
			if(tab[i][0] == x && tab[i][1] == x && tab[i][2] == x) {
				this.vencedor = "X";
				return true;
			}
			else if(tab[i][0] == o && tab[i][1] == o && tab[i][2] == o) {
				this.vencedor = "O";
				return true;
			}
		}
		return false;
	}

	private boolean vencedorDiag1(int[][] tab ){
		if((tab[0][0] == x && tab[1][1] == x && tab[2][2] == x) ||
			(tab[0][2] == x && tab[1][1] == x && tab[2][0] == x)) {
			this.vencedor = "X";
			return true ;
		}
		else return false;
	}

	private boolean vencedorDiag2 ( int[][] tab ) {
		if((tab[0][0] == o && tab[1][1] == o && tab[2][2] == o) ||
				(tab[0][2] == o && tab[1][1] == o && tab[2][0] == o)) {
			this.vencedor = "O";
			return true ;
		}
			else return false;
	}
	
	public void reiniciarJogo() {
		novaPartida();
		jogadores.clear();
	}

	public void novaPartida() {
		tabuleiro.reiniciar();
		this.setVencedor(" ");
	}

	
}
