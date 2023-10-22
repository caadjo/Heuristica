/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.ifnmg.heuristicacavalo;

/**
 *
 * @author Caio Veloso  &lt;caio.veloso at ifnmg.edu.br&gt;
 */

public class HeuristicaCavalo {
    private static final int TAMANHO_TABULEIRO = 8; // Tamanho do tabuleiro
    private static int[][] tabuleiro;
    private static int numeroMovimentos = 1;

    private static final int[] MOVIMENTO_HORIZONTAL = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] MOVIMENTO_VERTICAL = {-1, -2, -2, -1, 1, 2, 2, 1};

    public static void main(String[] args) {
        for (int linhaInicial = 0; linhaInicial < TAMANHO_TABULEIRO; linhaInicial++) {
            for (int colunaInicial = 0; colunaInicial < TAMANHO_TABULEIRO; colunaInicial++) {
                tabuleiro = new int[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
                numeroMovimentos = 1;
                tabuleiro[linhaInicial][colunaInicial] = numeroMovimentos;

                if (resolverPasseioCavalo(linhaInicial, colunaInicial)) {
                    imprimirTabuleiro();
                } else {
                    System.out.println("Nenhuma solução encontrada a partir da posição inicial (" + linhaInicial + ", " + colunaInicial + ").");
                }
            }
        }
    }

    private static boolean resolverPasseioCavalo(int linha, int coluna) {
        if (numeroMovimentos == TAMANHO_TABULEIRO * TAMANHO_TABULEIRO) {
            return true; // Todos os quadrados foram visitados
        }

        int[] acessibilidades = calcularAcessibilidades(linha, coluna);

        for (int i = 0; i < 8; i++) {
            int proximaLinha = linha + MOVIMENTO_VERTICAL[i];
            int proximaColuna = coluna + MOVIMENTO_HORIZONTAL[i];

            if (movimentoValido(proximaLinha, proximaColuna) && tabuleiro[proximaLinha][proximaColuna] == 0) {
                int proximaAcessibilidade = acessibilidades[proximaLinha * TAMANHO_TABULEIRO + proximaColuna];
                numeroMovimentos++;

                if (resolverPasseioCavalo(proximaLinha, proximaColuna)) {
                    return true;
                }

                numeroMovimentos--;
                tabuleiro[proximaLinha][proximaColuna] = 0;
            }
        }

        return false;
    }

    private static int[] calcularAcessibilidades(int linha, int coluna) {
        int[] acessibilidades = new int[TAMANHO_TABULEIRO * TAMANHO_TABULEIRO];

        for (int l = 0; l < TAMANHO_TABULEIRO; l++) {
            for (int c = 0; c < TAMANHO_TABULEIRO; c++) {
                int count = 0;
                for (int i = 0; i < 8; i++) {
                    int nextRow = l + MOVIMENTO_VERTICAL[i];
                    int nextCol = c + MOVIMENTO_HORIZONTAL[i];
                    if (movimentoValido(nextRow, nextCol)) {
                        count++;
                    }
                }
                acessibilidades[l * TAMANHO_TABULEIRO + c] = count;
            }
        }

        return acessibilidades;
    }

    private static boolean movimentoValido(int linha, int coluna) {
        return (linha >= 0 && linha < TAMANHO_TABULEIRO && coluna >= 0 && coluna < TAMANHO_TABULEIRO);
    }

    private static void imprimirTabuleiro() {
        for (int[] linha : tabuleiro) {
            for (int celula : linha) {
                System.out.printf("%2d ", celula);
            }
            System.out.println();
        }
        System.out.println();
    }
}


