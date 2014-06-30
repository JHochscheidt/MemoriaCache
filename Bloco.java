package memoriaCache;
 

public class Bloco {
	public long [] endereco;
	public long hora;
	
	public void setHora(){
		this.hora = System.currentTimeMillis();		
	}	
		// bloco nao pode receber tamanha maior que 16
	public Bloco(int tamanho){
		endereco = new long[tamanho];
		this.setHora();
	}
	
	public void preencheEnderecos(){
		for(long valor = 0; valor < this.endereco.length; valor++){
			this.endereco[(int) valor] = valor;
			//System.out.println("valor do endereco" + this.endereco[(int)valor]);
		}
	}
	
	public void imprimeEnderecos(int i){
		for(int indice = 0; indice < this.endereco.length; indice++){
			System.out.print(i + "[" + indice + "]=");
			System.out.print(this.endereco[indice] + " ");
			
		}
	}
	
	public int procuraEndereco(long endereco){
		/*	devolve -1 quando nao encontra o endereco.
		 *  caso encontrar o valor devolvido sera o indice do bloco 
		 */
		int retorno = -1;
		for(int indice = 0; indice < this.endereco.length; indice++){
			if(endereco == this.endereco[indice]) return indice;
		}
		return retorno;	
	}
	
}