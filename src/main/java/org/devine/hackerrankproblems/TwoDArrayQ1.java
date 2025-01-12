package org.devine.hackerrankproblems;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int hourglassSum(List<List<Integer>> arr) {
        List<List<Integer>> hourglasses = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            hourglasses.add(new ArrayList<>());
            for(int j = 0; j < 4; j++){
                hourglasses.get(i).add(0);
            }
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                findMaxHourglassSum(hourglasses, arr, i, j);
            }
        }

        int maxSum = -63;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                maxSum = Math.max(maxSum, hourglasses.get(i).get(j));
            }
        }

        return maxSum;

    }

    public static void findMaxHourglassSum(
            List<List<Integer>> hourglasses,
            List<List<Integer>> arr,
            int xIdx, int yIdx){
        int hourGlassSum = 0;
        int[][] dir = { {0,0},{0,1},{0,2},
                {1,1},
                {2,0},{2,1},{2,2}};
        for(int i = 0; i < 7; i++){
            int currX = xIdx + dir[i][0];
            int currY = yIdx + dir[i][1];
            if(currX >= 0 &&  currY <= 6){
                hourGlassSum += arr.get(currX).get(currY);
            }
        }
        hourglasses.get(xIdx).set(yIdx, hourGlassSum);
    }
}

public class TwoDArrayQ1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.hourglassSum(arr);
        System.out.println(result);

        bufferedReader.close();

    }
}

