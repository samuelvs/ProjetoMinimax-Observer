import java.util.Observable;

public class Tabuleiro extends Observable{

	static private String[] opcoesDeJogada = {"o"," ","x"};

	static int[][] tabuleiro;

	int tam;

	public Tabuleiro( int tam ) {
		this.tam = tam;
		tabuleiro = new int[tam][tam];
		//reiniciar();
	}
	
	public int[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(int[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
		setChanged();
		notifyObservers();
	}

	public void reiniciar() {
		for(int i = 0; i < tam; i++) {
			for(int j = 0; j < tam; j++) {
				tabuleiro[i][j] = 0;
			}
		}
	}
	
	public void fazerJogada(int l, int c, int esp) {
		if (tabuleiro[l][c] == 0) {
			tabuleiro[l][c] = -1;
		}
		else System.out.println("Posicao ja ocupada , perdeu a vez!");
		setChanged();
		notifyObservers();
	}
	
	public void fazerJogadaJogadores(int l, int c, int esp) {
		if (tabuleiro[l][c] == 0) {
			tabuleiro[l][c] = esp;
		}
		else System.out.println("Posicao ja ocupada , perdeu a vez!");
		setChanged();
		notifyObservers();
	}
	
	public void imprimir(){
		System.out.println();
		for(int i = 0; i < 3; i++) {
			System.out.print("\t\t");
			for(int j = 0; j < 3; j++) {
				System.out.print(" " + opcoesDeJogada[tabuleiro[i][j]+1] + " ");
				if(j < 2) System.out.print("|");
			}
			if(i < 2) {
				System.out.println();
				System.out.println("\t\t------------");
			}
		}
	}

}