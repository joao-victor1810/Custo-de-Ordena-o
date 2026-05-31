public class mergeSort {
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
}
