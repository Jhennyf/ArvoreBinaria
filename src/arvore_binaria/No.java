package arvore_binaria;

import java.util.ArrayList;
import java.util.Scanner;

public class No {
	int valor;
	No noEsquerda, noDireita;

	public No(int valor) {
		this.valor = valor;
	}

	public static No raiz; // apontador raiz

	public static void inserir(int valor) {
		inserir(raiz, valor); // recebe o valor
	}

	public static void inserir(No node, int valor) {
		if (node == null) { // verifica se nó é igual a null
			System.out.println("Raiz " + valor);
			raiz = new No(valor);
		} else {
			if (valor < node.valor) { // verifica se o valor que está passando é maior ou menor que nó
				if (node.noEsquerda != null) { // se o valor for menor será inserido o valor á esquerda
					inserir(node.noEsquerda, valor);
				} else {
					System.out.println("inserindo " + valor + " a esquerda de " + node.valor);
					node.noEsquerda = new No(valor); // cria novo elemento
				}
			} else {
				if (node.noDireita != null) { // se for diferente de null já existe um elemento nessa posição
					inserir(node.noDireita, valor); // então volta o codigo ate encontrar null
				} else {
					System.out.println("Inserindo " + valor + " a direita de " + node.valor);
					node.noDireita = new No(valor); // cria novo elemento
				}
			}

		}
	}

	public static No contem(int dado, No nodo) {
		while (true) {
			if (nodo != null) { // verifica se o nó é nulo
				if (dado < nodo.valor) { // verifica se o dado é menor
					nodo = nodo.noEsquerda; // faz uma procura recursiva se o nó está à esquerda ou à direita.
					continue;
				}
				if (dado > nodo.valor) {
					nodo = nodo.noDireita;
					continue;
				}
				return nodo;
			}
			return null;
		}
	}

	public static No minimo(No raiz) {
		// Se a raiz for nula ou não houver um filho à esquerda, a raiz é o valor
		// mínimo.
		if (raiz == null || raiz.noEsquerda == null) {
			return raiz;
		}
		return minimo(raiz.noEsquerda); // procura filo à esquerda
	}

	public static No maximo(No raiz) {
		// Se a raiz for nula ou não houver um filho à direita, a raiz é o valor mínimo.
		if (raiz == null || raiz.noDireita == null) {
			return raiz;
		}
		return maximo(raiz.noDireita); // procura filho à direita
	}

	// Este método imprime os valores dos nós da árvore em ordem decrescente
	// (maiores valores primeiro).
	public static void maiores(No node) {
		if (node != null) {
			maiores(node.noDireita);
			System.out.print(node.valor + "| ");
			maiores(node.noEsquerda);

		}
	}

	// Este método imprime os valores dos nós da árvore em ordem crescente (menores
	// valores primeiro).
	public static void menores(No node) {
		if (node != null) {
			menores(node.noEsquerda);
			System.out.print(node.valor + "| ");
			menores(node.noDireita);
		}
	}

	// Este método calcula a altura máxima da árvore a partir de um nó.
	// Ele verifica a altura máxima entre o filho esquerdo e o filho direito,
	// adiciona 1 para incluir o nó atual e retorna a altura máxima.
	public static int maxSoma(No node) {
		if (node == null || (node.noEsquerda == null && node.noDireita == null)) {
			return 0;
		} else {
			if (maxSoma(node.noEsquerda) > maxSoma(node.noDireita)) {
				return (1 + maxSoma(node.noEsquerda));
			} else
				return (1 + maxSoma(node.noDireita));
		}
	}

	// Este método calcula o número total de nós na árvore a partir de um nó.
	public static int numNo(No node) {
		if (node == null) {
			return 0;
		} else
			return (1 + numNo(node.noEsquerda) + numNo(node.noDireita));
	}

	// Este método calcula o número total de folhas.
	public static int numFolhas(No node) {
		if (node == null) {
			return 0;
		} else if (node.noEsquerda == null && node.noDireita == null) {
			return 1;
		} else
			return numFolhas(node.noEsquerda) + numFolhas(node.noDireita);
	}

	// Este método verifica se a árvore é balanceada, comparando a diferença de
	// alturas
	public static boolean balanceada(No node) {
		No raiz;
		for (raiz = node; raiz != null; raiz = raiz.noEsquerda) {
			if (maxSoma(raiz.noDireita) - maxSoma(raiz.noEsquerda) == 2) {
				raiz = raiz.noDireita;
				return false;
			}
		}
		for (raiz = node; raiz != null; raiz = raiz.noDireita) {
			if (maxSoma(raiz.noDireita) - maxSoma(raiz.noEsquerda) == 2) {
				raiz = raiz.noEsquerda;
				return false;
			}
		}

		return true;
	}

	// Este método realiza um percurso em largura.
	public static String percursoEmLargura(No node) {
		StringBuilder percurso = new StringBuilder();

		if (node == null) {
			return "";
		}
		ArrayList<No> fila = new ArrayList<>();
		fila.add(node);

		while (!fila.isEmpty()) {
			No atual = fila.remove(0);

			percurso.append(atual.valor).append("| ");

			if (atual.noEsquerda != null) {
				fila.add(atual.noEsquerda);
			}
			if (atual.noDireita != null) {
				fila.add(atual.noDireita);
			}
		}

		return percurso.toString().trim();
	}

	public static void main(String[] args) {

		int continuar;

		Scanner ler = new Scanner(System.in);
		System.out.println("***Arvore Binária***");
		System.out.println("Número correspondente:\n" + "1 - Para inserir elementos na árvore binária\n"
				+ "2 - Verifica se contém um dado\n" + "3 - Retorna o menor valor\n" + "4 - Retorna o maior valor\n"
				+ "5 - Menores elementos na árvore\n" + "6 - Maiores elementos na árvore\n"
				+ "7 - Maior somátorio de um caminho\n" + "8 - retorna número de nós\n"
				+ "9 - Retorna número de nós que são folhas" + "10 - Verifica se a árvore é balanceada\n"
				+ "11 - Mostra o percurso em largura da árvore\n");
		do {
			int posicao;
			System.out.println("Digite o número: ");
			posicao = ler.nextInt();

			switch (posicao) {
			case 1: {
				inserir(10);
				inserir(4);
				inserir(15);
				inserir(5);
				inserir(2);
				inserir(13);
				break;
			}
			case 2: {
				int dado = 15;
				No resultado = contem(dado, raiz);
				if (resultado != null) {
					System.out.println("O valor do Dado " + dado + " está na árvore.");
				} else {
					System.out.println("O valor do Dado " + dado + " não está contido na árvore.");
				}
				break;
			}
			case 3: {
				System.out.println();
				No numMinimo = minimo(raiz);
				System.out.println("minímo da árvore: " + numMinimo.valor);
				break;
			}
			case 4: {
				System.out.println();
				No numMaximo = maximo(raiz);
				System.out.println("máximo da árvore: " + numMaximo.valor);
				break;
			}
			case 5: {
				System.out.println();
				System.out.print("menores valores da árvore: ");
				menores(raiz);
				break;
			}
			case 6: {
				System.out.println();
				System.out.print("maiores valores da árvore: ");
				maiores(raiz);
				break;
			}
			case 7: {
				System.out.println();
				int altura = maxSoma(raiz);
				System.out.println("Altura da árvore: " + altura);
				break;
			}
			case 8: {
				int totalNos = numNo(raiz);
				System.out.println("Total de nós na árvore: " + totalNos);
				break;
			}
			case 9: {
				int totalFolhas = numFolhas(raiz);
				System.out.println("Total de Folhas na árvore: " + totalFolhas);
				break;
			}
			case 10: {
				boolean estaBalanceada = balanceada(raiz);

				if (estaBalanceada) {
					System.out.println("A árvore está balanceada.");
				} else {
					System.out.println("A árvore não está balanceada.");
				}
				break;
			}
			case 11: {
				String resultadoPercurso = percursoEmLargura(raiz);
				System.out.println("Percuso em Largura: " + resultadoPercurso);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + posicao);
			}
			System.out.println(" ");
			System.out.println("Deseja continuar? (1 para continuar, 0 para encerrar)");
			continuar = ler.nextInt();

		} while (continuar == 1); 
	}

}
