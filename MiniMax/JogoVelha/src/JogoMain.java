
public class JogoMain {
	
	public static void main(String[] args) {
	
		Tabuleiro tab = new Tabuleiro(3);
		Jogo jogo = new Jogo(tab);
		JogoGUI jogoGui = new JogoGUI(tab, jogo);
	}
}