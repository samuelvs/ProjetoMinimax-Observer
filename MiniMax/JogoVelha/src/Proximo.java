public class Proximo {
	int[][] tabuleiro;
	int utilidade;

	public Proximo(int[][] tab){
		int tam = tab.length;
		tabuleiro = new int[tam][tam];
		for ( int i = 0 ; i < tam ; i++) {
			for ( int j = 0 ; j < tam ; j++) {
				tabuleiro[i][j] = tab[i][j];
			}
		}
	}
	
}