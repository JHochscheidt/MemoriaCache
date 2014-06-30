package memoriaCache;

public class MemoriaPrincipal{
	
	public Bloco[] BlocoAssociativo;
	
	public MemoriaPrincipal(int tamanho){
		BlocoAssociativo = new Bloco[tamanho];
		for(int indiceBloco = 0; indiceBloco < this.BlocoAssociativo.length; indiceBloco++){
			BlocoAssociativo[indiceBloco] = new Bloco(16);
		}
	}
	
	public void preencheRAM(){
		for(int indiceBloco = 0; indiceBloco < this.BlocoAssociativo.length; indiceBloco++){
			for(long indEndereco = 0; indEndereco < this.BlocoAssociativo[indiceBloco].endereco.length; indEndereco++){
				if(this instanceof CacheDados || this instanceof CacheInstrucoes || this instanceof CacheUnificada)
					this.BlocoAssociativo[indiceBloco].endereco[(int) indEndereco] = indiceBloco;
				else{
					this.BlocoAssociativo[indiceBloco].endereco[(int) indEndereco] = (indiceBloco * this.BlocoAssociativo[indiceBloco].endereco.length) + indEndereco;
				}
				 
				
			}
		}
	}
	
	public void imprimeMemoria(int i){
		System.out.println("valores dos enderecos da memoria principal");
		for(int indice = 0; indice < this.BlocoAssociativo.length ; indice++){
			System.out.println("\n bloco[" + indice + "]");
			this.BlocoAssociativo[indice].imprimeEnderecos(i);
		}// fim for impressao dos enderecos da RAM
	}//fim metodo imprimeRAM
	
	public int buscaBloco(long endereco, MemoriaPrincipal[] memorias){
		int achou = -1; // achou sera o valor do indice do bloco onde se encontra o endereco
		int achouEndereco;
		for(int indice = 0; indice < this.BlocoAssociativo.length; indice++){
			achouEndereco = this.BlocoAssociativo[indice].procuraEndereco(endereco);
			//teste com a cache unificada
			//achou na cache unificada
			if(achouEndereco > -1){
				System.out.println("hit");
				achou = indice;
				// chamar aqui o metodo copiaBloco com o this
				break;
			}
		}	
		if(achou == -1)System.out.println("miss");
		
		return achou;
	}
	
	public void copiaBloco(MemoriaPrincipal origem, int indiceBlocoOrigem, MemoriaPrincipal destino,int indiceBlocoDestino){
		
		//System.out.println("imprimindo os valores que vao ser trocados....................");
		//System.out.println("imprimindo os valores que vao ser trocados....................");
		//System.out.println("imprimindo os valores que vao ser trocados....................");
		System.out.println("imprimindo os valores que vao ser trocados....................");
		System.out.println("imprimindo os valores que vao ser trocados....................");
		
		destino.imprimeMemoria(10);
		
		for(int indice = 0; indice < this.BlocoAssociativo.length; indice++){
			destino.BlocoAssociativo[indiceBlocoDestino].endereco = this.BlocoAssociativo[indiceBlocoOrigem].endereco;
		}
		destino.BlocoAssociativo[indiceBlocoDestino].setHora();
	}
	
	public int menosRecente(){
		long menosRecente = this.BlocoAssociativo[0].hora;
		int menorIndice = 0;
		for(int indice = 0; indice < this.BlocoAssociativo.length; indice++){
			if(this.BlocoAssociativo[indice].hora < menosRecente){
				menosRecente = this.BlocoAssociativo[indice].hora;
				menorIndice = indice;
			}
		}
		return menorIndice;
	}
	
}