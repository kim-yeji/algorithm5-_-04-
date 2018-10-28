package bst;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		TreeNode node=null;
		Integer[] list= null;
		BST bst = null;
		try {
			list=FileReading.readFileForList();
			System.out.println("============== insert ===============");
			bst=new BST(list);
			bst.printTree(bst.getRoot(), 0);
			bst.inorder();
			System.out.println();
			
			System.out.println("============== median insert ===============");
			bst.deleteFile();
			bst.resetRoot();
			bst.menu("median");
			bst.printTree(bst.getRoot(), 0);
			bst.inorder();
			System.out.println();
			
			
			System.out.println("============== �ڽĳ�� �� �� �ִ� 38 search ===============");
			bst=new BST(list);
			long start = System.nanoTime();
			System.out.println("iterativeSearch:"+bst.iterativeSearch(38));
			long end = System.nanoTime();
			System.out.println("�ɸ��ð�:" + (end-start));
			System.out.println();
			
			start = System.nanoTime();
			System.out.println("recursiveSearch:"+bst.recursiveSearch(38));
			end = System.nanoTime();
			System.out.println("�ɸ��ð�:" + (end-start));
			System.out.println();
			
			System.out.println("============== �ڽĳ�� �� �� �ִ� 34 delete ===============");
			bst=new BST(list);
			System.out.println("[successor]");
			System.out.println(bst.delete("successor",34));
			bst.printTree(bst.getRoot(), 0);
			System.out.println();
			bst=new BST(list);
			
			System.out.println("[predecessor]");
			System.out.println(bst.delete("predecessor",34));
			bst.printTree(bst.getRoot(), 0);
			System.out.println();
			
			System.out.println("============== �ڽĳ�� �� �� �ִ� 13 delete ===============");
			bst=new BST(list);
			System.out.println(bst.delete("successor",13));
			bst.printTree(bst.getRoot(), 0);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
