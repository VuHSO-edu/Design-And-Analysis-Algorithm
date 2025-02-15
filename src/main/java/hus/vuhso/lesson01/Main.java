package hus.vuhso.lesson01;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public class Main {

    private static final Random random = new Random();

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static long measureTime(Runnable sortingMethod) {
        long startTime = System.nanoTime();
        sortingMethod.run();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; // Convert to milliseconds
    }

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000};
        Map<String, Map<Integer, Long>> allTimes = new LinkedHashMap<>();

        for (int size : sizes) {
            int[] originalArray = random.ints(size, 0, 100_000).toArray();

            allTimes.putIfAbsent("Bubble Sort", new LinkedHashMap<>());
            allTimes.putIfAbsent("Selection Sort", new LinkedHashMap<>());
            allTimes.putIfAbsent("Insertion Sort", new LinkedHashMap<>());
            allTimes.putIfAbsent("Merge Sort", new LinkedHashMap<>());
            allTimes.putIfAbsent("Quick Sort", new LinkedHashMap<>());

            allTimes.get("Bubble Sort").put(size, measureTime(() -> bubbleSort(Arrays.copyOf(originalArray, originalArray.length))));
            allTimes.get("Selection Sort").put(size, measureTime(() -> selectionSort(Arrays.copyOf(originalArray, originalArray.length))));
            allTimes.get("Insertion Sort").put(size, measureTime(() -> insertionSort(Arrays.copyOf(originalArray, originalArray.length))));
            allTimes.get("Merge Sort").put(size, measureTime(() -> mergeSort(Arrays.copyOf(originalArray, originalArray.length), 0, originalArray.length - 1)));
            allTimes.get("Quick Sort").put(size, measureTime(() -> quickSort(Arrays.copyOf(originalArray, originalArray.length), 0, originalArray.length - 1)));
        }

        System.out.println("All times: " + allTimes);

        drawChart(allTimes);
    }

    private static void drawChart(Map<String, Map<Integer, Long>> allTimes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Map<Integer, Long>> algorithmEntry : allTimes.entrySet()) {
            String algorithm = algorithmEntry.getKey();
            Map<Integer, Long> sizeTimes = algorithmEntry.getValue();

            for (Map.Entry<Integer, Long> entry : sizeTimes.entrySet()) {
                dataset.addValue(entry.getValue(), algorithm, entry.getKey());
            }
        }

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Sorting Algorithms Time Comparison",
                "Number of Elements",
                "Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Increase line thickness and add data points
        CategoryPlot plot = lineChart.getCategoryPlot();
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        for (int i = 0; i < dataset.getRowCount(); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(5.0f)); // Increase line thickness
            renderer.setSeriesShapesVisible(i, true); // Show data points
        }

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 800));

        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setTitle("NguyenDuyVu-22000132");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }




}
