import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Altere o nome do arquivo conforme o cenário que deseja testar
        String nomeArquivo = "dados_medio_1M.csv";

        try {

            // Carrega os dados do arquivo para um arrayc
            int[] dados = carregarDados(nomeArquivo);

            // Cria cópias do array original
            // Isso evita alterar o vetor original e permite testes justos
            int[] arrayParaSimples = Arrays.copyOf(dados, dados.length);
            int[] arrayParaEficiente = Arrays.copyOf(dados, dados.length);

            // --- TESTE ALGORITMO SIMPLES ---
            System.out.println("--- Executando Algoritmo Simples ---");
            exibirPrimeiros15(arrayParaSimples);  // Mostra os primeiros números antes da ordenação
            long inicioSimples = System.currentTimeMillis();  // Início da medição do tempo
            insertionSort(arrayParaSimples); // Chamada do algoritmo Insertion Sort


            long fimSimples = System.currentTimeMillis(); // Final da medição do tempo
            exibirPrimeiros15(arrayParaSimples);// Mostra os primeiros números após ordenação
            System.out.println("Tempo decorrido: " + (fimSimples - inicioSimples) + " ms\n"); // Exibe o tempo gasto

            // TESTE DO ALGORITMO EFICIENTE

            System.out.println("--- Executando Algoritmo Eficiente ---");
            exibirPrimeiros15(arrayParaEficiente);
            long inicioEficiente = System.currentTimeMillis();
            mergeSort(arrayParaEficiente, 0, arrayParaEficiente.length - 1);


            long fimEficiente = System.currentTimeMillis();
            exibirPrimeiros15(arrayParaEficiente);
            System.out.println("Tempo decorrido: " + (fimEficiente - inicioEficiente) + " ms");

        } catch (FileNotFoundException e) {System.out.println("Erro: Arquivo não encontrado. " + "Verifique o nome e a localização.");
        }
    }


    // INSERTION SORT

    public static void insertionSort(int[] array) {


        for (int i = 1; i < array.length; i++) {
            int chave = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > chave) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = chave;
        }
    }


    // MERGE SORT

    public static void mergeSort(int[] array, int esquerda, int direita) {

        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;

            mergeSort(array, esquerda, meio);
            mergeSort(array, meio + 1, direita);
            merge(array, esquerda, meio, direita);
        }
    }

    // METODO PARA JUNTAR AS PARTES

    public static void merge(int[] array,
                             int esquerda,
                             int meio,
                             int direita) {


        int tamanhoEsquerda = meio - esquerda + 1;
        int tamanhoDireita = direita - meio;
        int[] esquerdaArray = new int[tamanhoEsquerda];
        int[] direitaArray = new int[tamanhoDireita];


        for (int i = 0; i < tamanhoEsquerda; i++) {
            esquerdaArray[i] = array[esquerda + i];
        }

        for (int j = 0; j < tamanhoDireita; j++) {
            direitaArray[j] = array[meio + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = esquerda;

        while (i < tamanhoEsquerda && j < tamanhoDireita) {

            if (esquerdaArray[i] <= direitaArray[j]) {

                array[k] = esquerdaArray[i];
                i++;

            } else {
                array[k] = direitaArray[j];
                j++;
            }
            k++;
        }

        while (i < tamanhoEsquerda) {
            array[k] = esquerdaArray[i];
            i++;
            k++;
        }

        while (j < tamanhoDireita) {
            array[k] = direitaArray[j];
            j++;
            k++;
        }
    }

    // Método para ler o CSV e retornar o array
    private static int[] carregarDados(String nomeArquivo) throws FileNotFoundException {
        File file = new File(nomeArquivo);
        Scanner scanner = new Scanner(file);

        // Pula o cabeçalho (assumindo que o arquivo tem uma linha de título)
        if (scanner.hasNextLine()) scanner.nextLine();


        // Não sabemos o tamanho exato de antemão de forma dinâmica facilmente,
        // mas sabemos que são 1.000 ou 1.000.000, podemos instanciar fixo ou contar linhas:
        // Aqui assumimos que sabemos o tamanho para simplificar para o aluno:
        int tamanho = nomeArquivo.contains("1M") ? 1000000 : 1000;
        int[] array = new int[tamanho];

        int i = 0;


        while (scanner.hasNextLine() && i < tamanho) { // Lê linha por linha do arquivo
            array[i] = Integer.parseInt(scanner.nextLine().trim()); // Converte texto para inteiro
            i++;
        }

        scanner.close();
        return array;
    }

    // Método para exibir os 15 primeiros elementos
    private static void exibirPrimeiros15(int[] array) {

        System.out.print("Dados: ");
        int limite = Math.min(array.length, 15);
        for (int i = 0; i < limite; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("...");
    }
}