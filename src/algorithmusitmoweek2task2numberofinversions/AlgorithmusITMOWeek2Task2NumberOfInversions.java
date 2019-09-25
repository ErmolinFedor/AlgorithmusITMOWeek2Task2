package algorithmusitmoweek2task2numberofinversions;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AlgorithmusITMOWeek2Task2NumberOfInversions {
    private final static String inFile="input.txt";
    private final static String outFile="output.txt";
    
    private static void write(String out){
         try(FileWriter writer = new FileWriter(outFile, false)){
            writer.write(out);
            writer.flush();
        }
        catch(IOException ex){             
            System.out.println(ex.getMessage());
        } 
    } 
    private static String read(){
        StringBuilder sb= new StringBuilder();
         try(FileReader reader = new FileReader(inFile)){
            int c;
            while((c=reader.read())!=-1){
                 sb.append((char)c);
            } 
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }  
         return sb.toString();
    }
    private static boolean checkNumber(String str){
        if(str==null || str.isEmpty()) return false;
        if(str.charAt(0)=='-'){
            if(str.length()<11) return true;
            return str.length()==11 && str.equalsIgnoreCase("-1000000000");
        }
        if(str.length()<10) return true;
        return str.length()==10 && str.equalsIgnoreCase("1000000000");
    }
    private static int[] split(String str){
        String[] tmp=str.split("\\n");
        int num= Integer.parseInt(tmp[0].trim());
        if(num>100001) return new int[0];
        String[] arr= tmp[1].trim().split(" ");
        int[] res= new int[arr.length];
        for(int i=0; i<res.length; i++){
            if(checkNumber(arr[i].trim())){
                res[i]=Integer.parseInt(arr[i]);
            }
        }
        return res;
    }
    
        private static long mergeSort(int[] a, int p, int r){
            long countInversion = 0;
            if(p < r)
            {
                int q = (p + r)/2;
                countInversion = mergeSort(a, p, q);
                countInversion += mergeSort(a, q+1, r);
                countInversion += merge(a, p, q, r);
            }
            return countInversion;
}

        private static long merge(int[] a, int p, int q, int r){
            long countingInversion = 0;
            int n1 = q-p+1;
            int n2 = r-q;
            int[] temp1 = new int[n1+1];
            int[] temp2 = new int[n2+1];
            for(int i=0; i<n1; i++) temp1[i] = a[p+i];
            for(int i=0; i<n2; i++) temp2[i] = a[q+1+i];

            temp1[n1] = Integer.MAX_VALUE;
            temp2[n2] = Integer.MAX_VALUE;
            int i = 0, j = 0;

            for(int k=p; k<=r; k++){
                if(temp1[i] <= temp2[j]){
                    a[k] = temp1[i];
                    i++;
                }
                else{
                    a[k] = temp2[j];
                    j++;
                    countingInversion=countingInversion+(n1-i); 
                }
            }
            return countingInversion;
}

    
    public static void main(String[] args) {
        int[] in= split(read());
        write(String.valueOf(mergeSort(in, 0, in.length-1)));
    }
    
}
