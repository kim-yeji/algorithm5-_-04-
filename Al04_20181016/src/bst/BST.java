package bst;

public class BST {
//	private int[] paramArr;
	private int[] sortedArr;
	private TreeNode root;

	public BST(Integer[] list) {
		this.root = null;
//		paramArr = new int[list.length];
		for (int i = 0; i < list.length; i++) {
			insert(list[i].intValue());
		}
	}

	// 트리가 비어있는지 검사하는 함수
	public boolean isEmpty() {
		return root == null;
	}

	// 삽입
	public void insert(int x) {

		// 삽입할 노드 생성
		TreeNode node = new TreeNode(x);

		// 트리가 없으면 삽입할 노드를 루트로 만듬
		if (root == null) {
			root = node;
			return;
		}
		// current는 비교할 노드, parent는 current의 상위 노드
		TreeNode current = root;
		TreeNode parent = null;

		while (true) {
			parent = current;
			// 삽입할 값이 비교할 노드보다 작으면 왼쪽으로 탐색
			if (x < current.data) {
				current = current.left;
				// 왼쪽 자식 노드가 없으면 그자리에 삽입
				if (current == null) {
					parent.left = node;
					return;
				}
			}
			// 삽입할 값이 비교할 노드보다 크면 오른쪽으로 탐색
			else {
				current = current.right;
				// 오른쪽 자식 노드가 없으면 그자리에 삽입
				if (current == null) {
					parent.right = node;
					return;
				}
			}
		}
	}

	// 반복탐색
	public int iterativeSearch(int key) {
		TreeNode current = root;
		while (current != null) {
			// 일치하는 노드를 찾았으면 리턴
			if (current.data == key) {
				return current.data;
			}
			// 찾으려는 값이 비교 노드(현재 노드)보다 작으면 왼쪽으로 탐색
			else if (current.data > key) {
				current = current.left;
			}
			// 찾으려는 값이 비교 노드(현재 노드)보다 크면 오른쪽으로 탐색
			else {
				current = current.right;
			}
		}
		return 0;
	}

	// 순환탐색
	public int recursiveSearch(int key) {
		TreeNode current = root;
		while (current != null) {
			// 일치하는 노드를 찾았으면 리턴
			if (current.data == key) {
				return current.data;
			}
			// 찾으려는 값이 비교 노드(현재 노드)보다 작으면 왼쪽으로 탐색
			else if (current.data > key) {
				return recursiveSearch(current.left.data);
			}
			// 찾으려는 값이 비교 노드(현재 노드)보다 크면 오른쪽으로 탐색
			else {
				return recursiveSearch(current.right.data);
			}
		}
		return 0;
	}

	// 삭제
	public boolean delete(int x) {
		// 현재위치로부터의 루트 노드
		TreeNode parent = root;
		// 현재위치
		TreeNode current = root;
		// 왼쪽 자식과 값이 일치하는지 확인하는 flag
		boolean isLeftChild = false;
		// while문의 조건부의 뜻은 전체 노드에서 x값을 찾을때까지
		// 탐색하는것인데 트리 전체에서 x값이 없더라도 while은 돌게 되어있음.
		while (current.data != x) {
			parent = current;
			if (current.data > x) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			// current가 null이면 트리 전체에서x의 값이 없는것임.
			if (current == null) {
				System.out.println("트리가 존재하지 않습니다.");
				return false;
			}
		}
		/// 여기까지 while이 돌고나면 x의 위치를 찾은것임.

		// 1. 자식노드가 없는 경우(단말노드)
		if ((current.left == null) && (current.right == null)) {
			// x와 같은 값을 가지는 노드가 루트이고 자식이없다면 루트 삭제
			if (current == root) {
				root = null;
			}
			// 단말노드와의 연결을 끊음
			if (isLeftChild) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		// 2. 하나의 자식을 갖는 경우
		// 왼쪽자식을 가지는경우
		else if (current.right == null) {
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		}
		// 오른쪽 자식을 가지는 경우
		else if (current.left == null) {
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		}

		// 3. 두개의 자식을 갖는 경우
		else if (current.left != null && current.right != null) {
			// 오른쪽 서브트리의 최소값을 찾음
			TreeNode successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}

			successor.left = current.left;
		}
		return true;
	}

	// 후임자를 구하는 함수 //오른쪽 서브트리의 최소값(오른쪽 서브트리의 왼쪽값)
	public TreeNode getSuccessor(TreeNode deleteNode) {
		TreeNode successor = null;
		TreeNode successorParent = null;
		TreeNode current = deleteNode.right;

		while (current != null) {
			successorParent = successor;
			successor = current;
			// 왼쪽 서브트리를 타고 내려감
			current = current.left;
		}
		if (successor != deleteNode.right) {
			successorParent.left = successor.right;
			successor.right = deleteNode.right;
		}
		return successor;
	}

	
	public void inorder() {
		inorder(root);
	}

	private void inorder(TreeNode r) {
		if (r != null) {
			inorder(r.getLeft());
			System.out.print(r.getData() + " ");
			inorder(r.getRight());
		}
	}
	
	
}
