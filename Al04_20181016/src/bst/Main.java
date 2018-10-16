package bst;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		Integer[] list= null;
		BST bst = null;
		try {
			list=FileReading.readFileForList();
			bst=new BST(list);
			bst.inorder();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
