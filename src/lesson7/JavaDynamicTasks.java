package lesson7;

import kotlin.NotImplementedError;

import java.util.*;

import static java.util.Collections.binarySearch;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     *
     * complexity: O( n^2 )
     * memory: O( n^2 )
     */
    public static String longestCommonSubSequence(String first, String second) {
        if (first == null || second == null) return null;
        if (first.length() == 0 || second.length() == 0) return "";

        String[] arr1 = first.split("");
        String[] arr2 = second.split("");
        StringBuilder lcs = new StringBuilder();

        int[][] lcsMatrix = new int[arr1.length + 1][arr2.length + 1];

        for (int i = 0; i < arr1.length + 1; i++) lcsMatrix[i][0] = 0;
        for (int j = 1; j < arr2.length + 1; j++) lcsMatrix[0][j] = 0;
        for (int i = 1; i < arr1.length + 1; i++) {
            for (int j = 1; j < arr2.length + 1; j++) {
                if (arr1[i - 1].equals(arr2[j - 1])) {
                    lcsMatrix[i][j] = lcsMatrix[i - 1][j - 1] + 1;
                } else {
                    lcsMatrix[i][j] = Math.max(lcsMatrix[i - 1][j], lcsMatrix[i][j - 1]);
                }
            }
        }

        int i = first.length(), j = second.length();
        while (i > 0 && j > 0) {
            if (first.charAt(i - 1) == second.charAt(j - 1)) {
                lcs.append(first.charAt(i - 1));
                i--;
                j--;
            } else if (lcsMatrix[i - 1][j] > lcsMatrix[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     * <p>
     * complexity: O( n^2 )
     * memory: O( n )
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.isEmpty() || list.size() == 1) return list;

        var tailIndices = new int[list.size()];
        var prevIndices = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            tailIndices[i] = 1;
            prevIndices[i] = -1;
        }

        var lastIndex = 0;

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(i) > list.get(j) && tailIndices[i] < tailIndices[j] + 1) {
                    tailIndices[i] = tailIndices[j] + 1;
                    prevIndices[i] = j;
                    if (tailIndices[i] > tailIndices[lastIndex]) lastIndex = i;
                }
            }
        }


        var subSequence = new ArrayList<Integer>();

        while (lastIndex != -1) {
            subSequence.add(list.get(lastIndex));
            lastIndex = prevIndices[lastIndex];
        }
        for (int i = 0; i < subSequence.size() / 2; i++) {
            int temp = subSequence.get(i);
            subSequence.set(i, subSequence.get(subSequence.size() - i - 1));
            subSequence.set(subSequence.size() - i - 1, temp);
        }
        return subSequence;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
