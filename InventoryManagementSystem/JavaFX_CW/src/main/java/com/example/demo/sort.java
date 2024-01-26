package com.example.demo;

import java.util.List;

public class sort { // Use of an inner class
    public class InsertionSort {

        public void insertionSort(List<Dealers> list) {
            int n = list.size();
            for (int i = 1; i < n; i++) {
                Dealers key = list.get(i);
                int j = i - 1;
                while (j >= 0 && list.get(j).getLocation().compareTo(key.getLocation()) > 0) {
                    list.set(j + 1, list.get(j));
                    j = j - 1;
                }
                list.set(j + 1, key);
            }
        }
    }
    public class Bubblesort{
    public  void bubbleSort(List<Items> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getItemCode().compareTo(list.get(j + 1).getItemCode()) > 0) {
                    // Swap the items if they are out of order based on itemCode
                    Items temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}


}
