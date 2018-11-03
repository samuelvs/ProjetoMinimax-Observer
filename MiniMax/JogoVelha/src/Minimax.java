import java.util.ArrayList;
import java.util.Collections;

public class Minimax extends Jogador {

	static ArrayList<Proximo> proximos = new ArrayList<Proximo>();
	int tam, maxProf;

	public Minimax(int tam, int profundidadeMax) {
		this.tam = tam;
		if( profundidadeMax > 0 ) this.maxProf = profundidadeMax;
		else this.maxProf = Integer.MAX_VALUE;
	}

	public int[][] fazerMinimax(int [][] tab ){
		proximos.clear();
		int v = valorMaximo(tab, true, 1) ;
		for (Proximo s: proximos) {
			if(s.utilidade == v) return s.tabuleiro;
		}
		return tab;
	}

	public int valorMaximo ( int[][] tab , boolean prim , int prof ) {
		if( prof++ > maxProf || testeFim(tab)) return utilidade( tab );
		
		int v = Integer.MIN_VALUE;
		for (Proximo s : fazerProximos(tab, 1)) {
			v = Math . max(v, valorMinimo(s.tabuleiro, prof));
			s.utilidade = v;
			if ( prim ) proximos.add(s);
		}
		return v ;
	}
	
	public int valorMinimo(int [][] tab , int prof) {
		if( prof++ > maxProf || testeFim(tab)) return utilidade(tab);
		int v = Integer.MAX_VALUE;
		for ( Proximo s : fazerProximos(tab, -1)) {
			v = Math.min(v, valorMaximo( s.tabuleiro, false, prof));
			s.utilidade = v;
		}
		return v ;
	}
	
	public ArrayList<Proximo> fazerProximos(int [][] tab , int v){
		ArrayList<Proximo> suc = new ArrayList<Proximo>();
		for ( int i = 0 ; i < tam ; i++){
			for ( int j = 0 ; j < tam ; j++) {
				if(tab[i][j] == 0){
					tab[i][j] = v;
					suc.add(new Proximo(tab));
					tab[i][j] = 0 ;
				}
			}
		}
		return suc ;
	}

	public boolean testeFim(int[][] tab ){
		return (verificarVencedor(tab, 1) || verificarVencedor(tab, -1) || semEspaco(tab));
	}

	public int utilidade( int[][] tab ){
		if(verificarVencedor(tab , 1)) return 1 ;
		else if(verificarVencedor(tab , -1)) return -1;
		else return 0 ;
	}

	public boolean verificarVencedor(int[][] tab , int v ) {
		for ( int i = 0 ; i < tam ; i++) {
			if(vencedorLinha(tab, i, v) || vencedorColuna( tab, i, v)) return true;
			if(vencedorDiag1(tab , v ) || vencedorDiag2( tab , v )) return true ;
		}
		return false;
	}

	private boolean vencedorLinha ( int [ ] [ ] tab , int l , int v ) {
		for ( int i = 0 ; i < tam ; i++) if(tab[l][i] != v ) return false;
		return true;
	}

	private boolean vencedorColuna ( int [ ] [ ] tab , int c , int v ){
		for ( int i = 0 ; i < tam ; i++) {
			if(tab[i][c] != v ) return false;
		}
		return true ;
	}

	private boolean vencedorDiag1(int[][] tab , int v){
		for ( int i = 0 ; i < tam ; i++) {
			if (tab[i][i] != v ) return false;
		}
		return true ;
	}

	private boolean vencedorDiag2 ( int[][] tab , int v ) {
		for ( int i = 0 ; i < tam ; i++) {
			if(tab[(tam-1)-i][i] != v ) return false;
		}
		return true ;
	}
	
	public boolean semEspaco(int[][] tab){
		for( int l = 0 ; l< tam ; l++) {
	
			for( int c = 0 ; c < tam ; c++) {
				if( tab [ l ] [ c ] == 0 ) return false;
			}
		}
		return true;
	}
}