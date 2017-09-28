package cs6301.g44;


import java.util.Arrays;

/**
 * @author Akshay Rawat, Amrut Suresh , Gokul Surendra, Vinayaka Raju Gopal
 */
public class MergeSortVersions {
    static final int THRESHOLD = 10;

    static void mergeSortV1(int[] ele){
        mergeSortUtilV1(ele, 0, ele.length-1);
    }
    static void mergeSortUtilV1(int[] ele, int low, int high){
        if (low < high){
           int mid = (low + high) / 2;
           mergeSortUtilV1(ele, low, mid);
           mergeSortUtilV1(ele, mid+1, high);
           mergeV1(ele, low, mid, high);
        }
    }
    static void mergeV1(int[] ele, int low, int mid, int high){
        int[] left = new int[mid-low+2];
        int[] right = new int[high-mid+1];
        int h=0, p=0;
        for (int i=low; i<=mid; i++)
            left[h++] = ele[i];
        for (int j=mid+1; j<=high; j++)
            right[p++] = ele[j];
        left[mid-low+1] = Integer.MAX_VALUE;
        right[high-mid] = Integer.MAX_VALUE;
        int l =0, r=0;
        for (int k=low; k<=high; k++) {
            if (left[l] < right[r])
                ele[k] = left[l++];
            else
                ele[k] = right[r++];
        }
    }

    static void insertionSort(int[] ele, int low, int high){
        int temp, j;
        for (int i = low+1; i<=high; i++){
            temp = ele[i];
            j = i-1;
            while (j>= low && temp < ele[j]) {
                ele[j + 1] = ele[j];
                j = j-1;
            }
            ele[j+1] = temp;
        }
    }

    static void mergeSortV2(int[] ele){
        int[] temp = new int[ele.length+1];
        mergeSortUtilV2(ele ,temp ,0, ele.length-1);
    }

    static void mergeSortUtilV2(int[] ele, int[] temp, int low, int high){
        if (high-low <= THRESHOLD) {
            insertionSort(ele, low, high);
        }
        else{
            int mid = (low + high) / 2;
            mergeSortUtilV2(ele, temp, low, mid);
            mergeSortUtilV2(ele, temp, mid+1, high);
            mergeV2(ele, temp,low, mid, high);
        }
    }
    static void mergeV2(int[] ele, int[] temp, int low, int mid, int high){

        for (int i=low; i<=high; i++)
            temp[i] = ele[i];
        temp[high+1] = Integer.MAX_VALUE;
        int left = low, right = mid+1;

        for (int k=low; k<=high; k++){
            if ((left <=mid && temp[left] <= temp[right])|| right > high)
                ele[k] = temp[left++];
            else
                ele[k] = temp[right++];
        }
    }

    static void mergeSortV3(int[] ele){
        int[] temp = Arrays.copyOf(ele, ele.length);
        mergeSortUtilV3(ele,temp ,0, ele.length-1);
    }

    static void mergeSortUtilV3(int[] ele,int[] temp, int low, int high){
        if (high-low <= THRESHOLD) {
            insertionSort(ele, low, high);
        }
        else{
            int mid = (low + high)/2;
            mergeSortUtilV3(temp, ele, low, mid);
            mergeSortUtilV3(temp, ele, mid+1, high);
            mergeV3(temp, ele, low, mid, high);
        }
    }

    static void mergeV3(int[] ele,int[] temp, int low, int mid, int high){
        int left = low, right = mid+1;
        int cur = low;
        while (left <= mid && right <=high){
            if (ele[left] <= ele[right])
                temp[cur++] = ele[left++];
            else
                temp[cur++] = ele[right++];
        }
        while (left<=mid)
            temp[cur++] = ele[left++];
        while (right<=high)
            temp[cur++] = ele[right++];
    }
}
