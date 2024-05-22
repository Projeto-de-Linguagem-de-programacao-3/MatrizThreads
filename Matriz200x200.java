import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Matriz200x200 {
    private static final int TAM = 20;  // tamanho 200
    private static final int MAX_MOVS =3; // 150 iteracoes para cada pos
    private static final int MAX_VALOR = 100; // nteros de 0 a 100
    private static final double PERMANECE = 0.60; 
    private static final double MOVIMENTA = 0.10;

    private int[][] matriz;
    private Random random;
    private int cont = 0;

    public static void main(String[] args) {

        Matriz200x200 matriz = new Matriz200x200();
        try {
            matriz.salvaMatrizTXT("matriz_inicial.txt");
            matriz.movimentos();
            matriz.salvaMatrizTXT("matriz_final.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Matriz200x200() {
        matriz = new int[TAM][TAM];
        preencheMatriz();
    }

    private void preencheMatriz() {

        if (cont == 0) {
            long startTime = System.currentTimeMillis();

            Runnable threadTeste = () -> {
                random = new Random();
                for (int i = 0; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        matriz[i][j] = random.nextInt(MAX_VALOR + 1);
                    }
                }
            };

            Thread thread = new Thread(threadTeste);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Tempo para 1 thread: " + (endTime - startTime) + " ms");

            cont++;
        }

        if (cont == 1) {
            long startTime = System.currentTimeMillis();

            Runnable threadTeste = () -> {
                random = new Random();
                for (int i = 0; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        matriz[i][j] = random.nextInt(MAX_VALOR + 1);
                    }
                }
                
            };

            Runnable threadTeste2 = () -> {
                random = new Random();
                for (int i = 100; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        matriz[i][j] = random.nextInt(MAX_VALOR + 1);
                    }
                }
                
            };

            Thread thread = new Thread(threadTeste);
            Thread thread2 = new Thread(threadTeste2);
            thread.start();
            thread2.start();
            try {
                thread.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Tempo para 2 threads: " + (endTime - startTime) + " ms");

            cont++;
        }

        if (cont == 2) {
            long startTime = System.currentTimeMillis();

            Runnable threadTeste = () -> {
                random = new Random();
                for (int i = 0; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        matriz[i][j] = random.nextInt(MAX_VALOR + 1);
                    }
                }
                
            };

            Runnable threadTeste2 = () -> {
                random = new Random();
                for (int i = 50; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        matriz[i][j] = random.nextInt(MAX_VALOR + 1);
                    }
                }
                
            };

            Runnable threadTeste3 = () -> {
                random = new Random();
                for (int i = 150; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        matriz[i][j] = random.nextInt(MAX_VALOR + 1);
                    }
                }
                
            };

            Runnable threadTeste4 = () -> {
                random = new Random();
                for (int i = 200; i < TAM; i++) {
                    for (int j = 0; j < TAM; j++) {
                        matriz[i][j] = random.nextInt(MAX_VALOR + 1);
                    }
                }
                
            };

            Thread thread = new Thread(threadTeste);
            Thread thread2 = new Thread(threadTeste2);
            Thread thread3 = new Thread(threadTeste3);
            Thread thread4 = new Thread(threadTeste4);
            thread.start();
            thread2.start();
            thread3.start();
            thread4.start();
            try {
                thread.join();
                thread2.join();
                thread3.join();
                thread4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Tempo para 4 threads: " + (endTime - startTime) + " ms");

            cont++;
        }

    }

    private void movimentos() {
        for (int iteracao = 0; iteracao < MAX_MOVS; iteracao++) {
            int[][] auxMatriz = new int[TAM][TAM];

            for (int i = 0; i < TAM; i++) {
                for (int j = 0; j < TAM; j++) {
                    double prob = random.nextDouble();
                    int auxValor = matriz[i][j];

                    if (prob < PERMANECE) {
                        auxMatriz[i][j] += auxValor;
                    } else if (prob < PERMANECE + MOVIMENTA) {
                        if (i > 0)
                            auxMatriz[i - 1][j] += auxValor;
                    } else if (prob < PERMANECE + 2 * MOVIMENTA) {
                        if (i < TAM - 1)
                            auxMatriz[i + 1][j] += auxValor;
                    } else if (prob < PERMANECE + 3 * MOVIMENTA) {
                        if (j > 0)
                            auxMatriz[i][j - 1] += auxValor;
                    } else {
                        if (j < TAM - 1)
                            auxMatriz[i][j + 1] += auxValor;
                    }
                }
            }
            matriz = auxMatriz;
        }
    }

    private void salvaMatrizTXT(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < TAM; i++) {
                for (int j = 0; j < TAM; j++) {
                    writer.write(matriz[i][j] + " ");
                }
                writer.newLine();
            }
        }
    }

}