/*
   THOUGHT PROCESS
   1)We need to find the maximum minimum difference between any two mountains we climb.
     This is because we want to maximize the minimum points earned.

   2)One brute force approach which comes into my mind  is to try all combinations of k
    mountains and calculate the points earned for each combination,However this approach
    would take O(n^k) time and is not feasible for large inputs.

   3)Instead,We can start with, for example 0 as the minimum points earned and try to climb K
     mountains, then will check is that possible along the given array,if yes we will try to
     maximize this by checking for 2,3,4,....till height.length-1 subsequently,wherever it's not possible
     to cover ths min distance along the given array , that set the previous one will be our ans.

   4)To optimize this search of considering the min points to be earned we will use binary search
     because you understand if for min 5 point we are not able to climb k mountains along the given
     array how its possible to do it in 5,7,8.... point so will cut down this search space doing
     r=mid-1.

   5) On the other hand if for certain consideration our condition is getting satisfied we will try to
     maximize this minimum solution by moving towards the right.To start with this first of all we have to
     sort the array and then follow the above steps.


*/

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Taking the inputs according to the given Question
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt(); // Height array.
        }
        int k = sc.nextInt(); // Number of mountains to be climbed

        int maxPoints = findMaxPoints(heights, k);
        System.out.println("Final answer="+maxPoints); // printing the answer
    }
    public static int findMaxPoints(int[] heights, int k) {
        Arrays.sort(heights);
        int l = 1, r = heights[heights.length-1]-heights[0];
      //  System.out.println(r);

        int result=0;
        // Applying binary search
        while (l <= r) {
          //  System.out.println("im in while");
            int mid = l + (r - l) / 2;
            if (canClimbKMountains(heights, k, mid)) {
                result = mid;
             //   System.out.println(result);
             //   System.out.println("im in");
                l = mid + 1;
            } else {
                r = mid-1;
            }
        }
        return result;
    }

    public static boolean canClimbKMountains(int[] heights, int k, int minDiff) {
        int count = 1, coordinate=heights[0];
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] - coordinate >= minDiff) {
                coordinate=heights[i];
                count++;
            }
            if(count>=k)return true;
        }
        return false;
    }


}