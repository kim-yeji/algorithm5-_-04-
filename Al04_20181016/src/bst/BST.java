package bst;

public class BST {
	private int[] sortedArr;
	private TreeNode root;
	TreeNode current = root;

	public BST(Integer[] list) {
		this.root = null;
		sortedArr = new int[list.length];
		for (int i = 0; i < list.length; i++) {
			insert(list[i].intValue());
			sortedArr[i] = list[i].intValue();
		}
	}

	public void resetRoot() {
		this.root = null;
	}

	public void menu(String menu) {
		if ("median".equals(menu)) {
			quickSort(sortedArr, 0, sortedArr.length - 1);
			median_insert(sortedArr, 0, sortedArr.length - 1);
		}
	}

	public TreeNode getRoot() {
		return this.root;
	}

	// 트리가 비어있는지 검사하는 함수
	public boolean isEmpty() {
		return root == null;
	}

	public void printTree(TreeNode node, int depth) {
		if (node == null)
			return;
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		System.out.println(node.data);
		printTree(node.left, depth + 1);
		printTree(node.right, depth + 1);
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

	// median삽입
	public void median_insert(int[] arr, int start, int end) {
		if ((end - start + 1) < 1)
			return;
		int median = (end + start) / 2;
		insert(arr[median]);
		median_insert(arr, start, median - 1);
		median_insert(arr, median + 1, end);
	}

	// 반복탐색
	public int iterativeSearch(int key) {
		current = root;
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
		current = root;
		while (current != null) {
			// 일치하는 노드를 찾았으면 리턴
			if (current.data == key) {
				return current.data;
			}
			// 찾으려는 값이 비교 노드(현재 노드)보다 작으면 왼쪽으로 탐색
			else if (current.data > key) {
				root = current.left;
				return recursiveSearch(key);
			}
			// 찾으려는 값이 비교 노드(현재 노드)보다 크면 오른쪽으로 탐색
			else {
				root = current.right;
				return recursiveSearch(key);
			}
		}
		return 0;
	}

	// 삭제
	public boolean delete(String choice, int x) {
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
		// 3. 두개의 자식을 갖는 경우(successor & predecessor)
		else if (current.left != null && current.right != null) {
			if ("successor".equals(choice)) {
				// 오른쪽 서브트리의 최솟값을 찾음
				TreeNode successor = getSuccessor(current);
				if (current == root) {
					root = successor;
				} else if (isLeftChild) {
					parent.left = successor;
				} else {
					parent.right = successor;
				}

				successor.left = current.left;

			} else if ("predecessor".equals(choice)) {
				// 왼쪽 서브트리의 최댓값을 찾음
				TreeNode predecessor = getPredecessor(current);
				if (current == root) {
					root = predecessor;
				} else if (isLeftChild) {
					parent.left = predecessor;
				} else {
					parent.right = predecessor;
				}

				predecessor.right = current.right;
			}
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

	// 선임자를 구하는 함수 (왼쪽 서브트리의 최댓값)
	public TreeNode getPredecessor(TreeNode deleteNode) {
		TreeNode predecessor = null;
		TreeNode predecessorParent = null;
		TreeNode current = deleteNode.left;

		while (current != null) {
			predecessorParent = predecessor;
			predecessor = current;
			// 오른쪽 서브트리를 타고 내려감
			current = current.right;
		}
		if (predecessor != deleteNode.left) {
			predecessorParent.right = predecessor.left;
			predecessor.left = deleteNode.left;
		}
		return predecessor;
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

	// median insert 할 때 정렬하기 위한 퀵소트
	private void swap(int arr[], int left, int right) {
		int tmp = arr[left];
		arr[left] = arr[right];
		arr[right] = tmp;
	}

	public void quickSort(int data[], int left, int right) {
		if (left < right) {
			int pivot = partition(data, left, right);
			quickSort(data, left, pivot - 1);
			quickSort(data, pivot + 1, right);
		}
	}

	int partition(int data[], int left, int right) {
		int pivot = right;

		while (left < right) {
			while (data[left] <= data[pivot] && left < right)
				left++;
			while (data[right] >= data[pivot] && left > right)
				right--;
			if (left < right)
				swap(data, left, right);
		}

		swap(data, right, pivot);
		return right; // 최종 오른쪽 피봇 반환
	}

}
