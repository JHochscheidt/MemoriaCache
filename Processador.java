package memoriaCache;
import java.util.Random;
import java.util.Scanner;

      
public class Processador {
	
	private static Scanner s;

	public static void acessaMemoria(long endereco, boolean itsinst, MemoriaPrincipal[] memorias, boolean FIFO){
		int achouCacheInstrucoes;
		int achouCacheDados;
		int achouCacheUnificada;
		int achouRAM;
		// if cache de instrucoes
		if(itsinst){ //cache de instrucoes
			CacheInstrucoes cacheInstrucoes = (CacheInstrucoes) memorias[3];
			achouCacheInstrucoes = cacheInstrucoes.buscaBloco(endereco, memorias);
			
			if(achouCacheInstrucoes > -1){ // achou na cache de instrucoes
				System.out.println("in cache instructions");
				System.out.println("hit na cache instrucoes");
				//so dar um return aqui. 
			
			// nao achou na cache de instrucoes
			} 
			// fim if achou na cache de instrucoes 
			
			else{ // nao achou na cache de instrucoes
				System.out.println("not found in cache instructions, passing to cache de unificada");
				CacheUnificada memoriaUnificada = (CacheUnificada) memorias[1];
				achouCacheUnificada = memoriaUnificada.buscaBloco(endereco, memorias);
				
				// if cache unificada
				if(achouCacheUnificada > -1){ //achou na cache unificada
					System.out.println("achou na cache unificada");
					//chama metodo copiaBloco para copiar bloco da unificada para a cache de instrucoes
					// politica de substituicao FIFO
					if(FIFO){
						int indice;
						indice = cacheInstrucoes.menosRecente();
						memoriaUnificada.copiaBloco(memoriaUnificada, achouCacheUnificada, cacheInstrucoes, indice);
					
					// politica de substituicao aleatoria	
					}else{
						Random gerador = new Random();
						int resultado = gerador.nextInt(2);
						memoriaUnificada.copiaBloco(memoriaUnificada, achouCacheUnificada, cacheInstrucoes, resultado);
					}
				// nao achou na cache unificada	
				}else{ // nao achou na cache unificada 
					System.out.println("not found in cache unificada, passing to memoria principal");
					MemoriaPrincipal ram = (MemoriaPrincipal) memorias[0];
					achouRAM = ram.buscaBloco(endereco, memorias);
					
					//if memoria RAM
					if(achouRAM > -1){ // achou endereco na memoria RAM
						if(FIFO){
							int indiceCacheUnificada = memoriaUnificada.menosRecente();
							ram.copiaBloco(ram, achouRAM, memoriaUnificada,indiceCacheUnificada);
							int indiceCacheInstrucoes = cacheInstrucoes.menosRecente();
							memoriaUnificada.copiaBloco(memoriaUnificada, indiceCacheUnificada, cacheInstrucoes, indiceCacheInstrucoes);	
						}else{
							Random gerador = new Random();
							int geradorCacheUnificada = gerador.nextInt(4);
							ram.copiaBloco(ram, achouRAM, memoriaUnificada, geradorCacheUnificada);
							int geradorCacheInstrucoes = gerador.nextInt(2);
							memoriaUnificada.copiaBloco(memoriaUnificada, geradorCacheUnificada, cacheInstrucoes, geradorCacheInstrucoes);
						}
					}else{
						System.out.println("endereco informado nao esta na memoria principal Fudeeeeeu kkkk");
					}
				}
			}	
		}else{ // cache de dadosint achouCacheUnificada;
			CacheDados cacheDados = (CacheDados) memorias[2];
			achouCacheDados = cacheDados.buscaBloco(endereco, memorias);
			if(achouCacheDados > -1){
				System.out.println("in cache dados");				
			}else{
				System.out.println("not found in cache instructions, passing to cache de unificada");
				CacheUnificada memoriaUnificada = (CacheUnificada) memorias[1];
				achouCacheUnificada = memoriaUnificada.buscaBloco(endereco, memorias);
				if(achouCacheUnificada > -1){ //achou na cache unificada
					System.out.println("achou na cache unificada");
					//chama metodo copiaBloco para copiar bloco da unificada para a cache de instrucoes
					
					if(FIFO){
						int indice;
						indice = cacheDados.menosRecente();
						memoriaUnificada.copiaBloco(memoriaUnificada,achouCacheUnificada,cacheDados,indice);
					
					}else{
						Random gerador = new Random();
						int resultado = gerador.nextInt(2);
						memoriaUnificada.copiaBloco(memoriaUnificada, achouCacheUnificada, cacheDados,resultado);
					}					
				}else{ // nao achou na cache unificada 
					System.out.println("not found in cache unificada, passing to memoria principal");
					MemoriaPrincipal ram = (MemoriaPrincipal) memorias[0];
					achouRAM = ram.buscaBloco(endereco, memorias);
					
					if(achouRAM > -1){ // achou endereco na memoria RAM
						if(FIFO){
							int indiceCacheUnificada = memoriaUnificada.menosRecente();
							ram.copiaBloco(ram, achouRAM, memoriaUnificada,indiceCacheUnificada);
							int indiceCacheDados = cacheDados.menosRecente();
							memoriaUnificada.copiaBloco(memoriaUnificada, indiceCacheUnificada, cacheDados, indiceCacheDados);	
						}else{
							Random gerador = new Random();
							int geradorCacheUnificada = gerador.nextInt(4);
							ram.copiaBloco(ram, achouRAM, memoriaUnificada, geradorCacheUnificada);
							
							int geradorCacheDados = gerador.nextInt(2);
							memoriaUnificada.copiaBloco(memoriaUnificada, geradorCacheUnificada, cacheDados, geradorCacheDados);
						}
					}else{
						System.out.println("endereco informado nao esta na memoria principal Fudeeeeeu kkkk");
					}
				}
			}			
		}	
	}
	
	public static void main(String[] args) {
		
		MemoriaPrincipal[] memorias = new MemoriaPrincipal[4];
		
		MemoriaPrincipal RAM = new MemoriaPrincipal(64);
		CacheUnificada CacheUnificada = new CacheUnificada(4);
		CacheDados CacheDados = new CacheDados(2);
		CacheInstrucoes CacheInstrucoes = new CacheInstrucoes(2);
				
		memorias[0] = RAM;
		memorias[1] = CacheUnificada;
		memorias[2] = CacheDados;
		memorias[3] = CacheInstrucoes;
		
		RAM.preencheRAM();
		
		CacheUnificada.preencheRAM();
		CacheDados.preencheRAM();
		CacheInstrucoes.preencheRAM();
		
		int opcao;
		
		s = new Scanner(System.in);
		do{
			System.out.println();
			System.out.println("Informe uma das opções:");
			System.out.println("1. Imprimir memoria RAM");
			System.out.println("2. Imprimir cache UNIFICADA");
			System.out.println("3. Imprimir cache DADOS");
			System.out.println("4. Imprimir cache INSTRUCOES");
			System.out.println("5. Chamar método ACESSAMEMORIA");
			opcao = s.nextInt();			
			switch(opcao){
			case 1:
				RAM.imprimeMemoria(0);
				break;
			case 2:
				CacheUnificada.imprimeMemoria(1);
				break;
			case 3:
				CacheDados.imprimeMemoria(2);
				break;
			case 4:
				CacheInstrucoes.imprimeMemoria(3);
				break;
			case 5:
				int endereco;
				boolean instrucao;
				boolean FIFO;
				System.out.println("Informe os parametros do metodo ACESSAMEMORIA");
				System.out.println("1º --> informe qual o endereço a ser buscado");
				endereco = s.nextInt();
				System.out.println("2º --> informe TRUE para cache de INSTRUCAO ou FALSE para a cache de DADOS ");
				instrucao = s.nextBoolean();
				System.out.println("3º --> informe TRUE para politica FIFO ou FALSE para politica ALEATORIA");
				FIFO = s.nextBoolean();
				acessaMemoria(endereco, instrucao, memorias, FIFO);	
			default:
				break;
			}
		}while(opcao > 0);
		
		
		
		
		
	
		
	}
}