package bst;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReading {
	
	public static Integer[] readFileForList() throws IOException {
		List list = new ArrayList();
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/[Al]03/Data1.txt"));
		
		int i = 0;
        while(true) {
            String line = br.readLine();
            if (line==null) {
            	break;
            } else {
                list.add(i, Integer.parseInt(line));
            }
            i++;
        }
        Integer[] arr = ( Integer[]) list.toArray(new Integer[list.size()]);
        
        br.close();
        
        return arr;
    }
}