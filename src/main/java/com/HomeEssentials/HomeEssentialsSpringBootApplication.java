package com.HomeEssentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

// Klasa HomeEssentialsSpringBootApplication jest główną klasą aplikacji Spring Boot.
@SpringBootApplication
public class HomeEssentialsSpringBootApplication {

 // Metoda main jest punktem wejścia do aplikacji.
 public static void main(String[] args) {
  // Metoda run uruchamia aplikację Spring Boot.
  SpringApplication.run(HomeEssentialsSpringBootApplication.class, args);
  bubbleSort(new int[] {64, 34, 25, 12, 22, 11, 90});
  System.out.println("Przestań krzyczeć to tylko bubble sort");
 }





























 public static void bubbleSort(int[] arr) {
  int n = arr.length;
  System.out.println("Initial array: " + Arrays.toString(arr));
  for (int i = 0; i < n-1; i++) {
   for (int j = 0; j < n-i-1; j++) {
    if (arr[j] > arr[j+1]) {
     // swap arr[j+1] and arr[j]
     int temp = arr[j];
     arr[j] = arr[j+1];
     arr[j+1] = temp;
    }
   }
   System.out.println("Array after " + (i+1) + " pass: " + Arrays.toString(arr));
  }
 }
}


