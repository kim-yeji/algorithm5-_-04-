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

	// Ʈ���� ����ִ��� �˻��ϴ� �Լ�
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

	// ����
	public void insert(int x) {
		// ������ ��� ����
		TreeNode node = new TreeNode(x);

		// Ʈ���� ������ ������ ��带 ��Ʈ�� ����
		if (root == null) {
			root = node;
			return;
		}
		// current�� ���� ���, parent�� current�� ���� ���
		TreeNode current = root;
		TreeNode parent = null;

		while (true) {
			parent = current;
			// ������ ���� ���� ��庸�� ������ �������� Ž��
			if (x < current.data) {
				current = current.left;
				// ���� �ڽ� ��尡 ������ ���ڸ��� ����
				if (current == null) {
					parent.left = node;
					return;
				}
			}
			// ������ ���� ���� ��庸�� ũ�� ���������� Ž��
			else {
				current = current.right;
				// ������ �ڽ� ��尡 ������ ���ڸ��� ����
				if (current == null) {
					parent.right = node;
					return;
				}
			}
		}
	}

	// median����
	public void median_insert(int[] arr, int start, int end) {
		if ((end - start + 1) < 1)
			return;
		int median = (end + start) / 2;
		insert(arr[median]);
		median_insert(arr, start, median - 1);
		median_insert(arr, median + 1, end);
	}

	// �ݺ�Ž��
	public int iterativeSearch(int key) {
		current = root;
		while (current != null) {
			// ��ġ�ϴ� ��带 ã������ ����
			if (current.data == key) {
				return current.data;
			}
			// ã������ ���� �� ���(���� ���)���� ������ �������� Ž��
			else if (current.data > key) {
				current = current.left;
			}
			// ã������ ���� �� ���(���� ���)���� ũ�� ���������� Ž��
			else {
				current = current.right;
			}
		}
		return 0;
	}

	// ��ȯŽ��
	public int recursiveSearch(int key) {
		current = root;
		while (current != null) {
			// ��ġ�ϴ� ��带 ã������ ����
			if (current.data == key) {
				return current.data;
			}
			// ã������ ���� �� ���(���� ���)���� ������ �������� Ž��
			else if (current.data > key) {
				root = current.left;
				return recursiveSearch(key);
			}
			// ã������ ���� �� ���(���� ���)���� ũ�� ���������� Ž��
			else {
				root = current.right;
				return recursiveSearch(key);
			}
		}
		return 0;
	}

	// ����
	public boolean delete(String choice, int x) {
		// ������ġ�κ����� ��Ʈ ���
		TreeNode parent = root;
		// ������ġ
		TreeNode current = root;
		// ���� �ڽİ� ���� ��ġ�ϴ��� Ȯ���ϴ� flag
		boolean isLeftChild = false;
		// while���� ���Ǻ��� ���� ��ü ��忡�� x���� ã��������
		// Ž���ϴ°��ε� Ʈ�� ��ü���� x���� ������ while�� ���� �Ǿ�����.
		while (current.data != x) {
			parent = current;
			if (current.data > x) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
			// current�� null�̸� Ʈ�� ��ü����x�� ���� ���°���.
			if (current == null) {
				System.out.println("Ʈ���� �������� �ʽ��ϴ�.");
				return false;
			}
		}
		/// ������� while�� ������ x�� ��ġ�� ã������.

		// 1. �ڽĳ�尡 ���� ���(�ܸ����)
		if ((current.left == null) && (current.right == null)) {
			// x�� ���� ���� ������ ��尡 ��Ʈ�̰� �ڽ��̾��ٸ� ��Ʈ ����
			if (current == root) {
				root = null;
			}
			// �ܸ������� ������ ����
			if (isLeftChild) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		// 2. �ϳ��� �ڽ��� ���� ���
		// �����ڽ��� �����°��
		else if (current.right == null) {
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		}
		// ������ �ڽ��� ������ ���
		else if (current.left == null) {
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		}
		// 3. �ΰ��� �ڽ��� ���� ���(successor & predecessor)
		else if (current.left != null && current.right != null) {
			if ("successor".equals(choice)) {
				// ������ ����Ʈ���� �ּڰ��� ã��
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
				// ���� ����Ʈ���� �ִ��� ã��
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

	// �����ڸ� ���ϴ� �Լ� //������ ����Ʈ���� �ּҰ�(������ ����Ʈ���� ���ʰ�)
	public TreeNode getSuccessor(TreeNode deleteNode) {
		TreeNode successor = null;
		TreeNode successorParent = null;
		TreeNode current = deleteNode.right;

		while (current != null) {
			successorParent = successor;
			successor = current;
			// ���� ����Ʈ���� Ÿ�� ������
			current = current.left;
		}
		if (successor != deleteNode.right) {
			successorParent.left = successor.right;
			successor.right = deleteNode.right;
		}
		return successor;
	}

	// �����ڸ� ���ϴ� �Լ� (���� ����Ʈ���� �ִ�)
	public TreeNode getPredecessor(TreeNode deleteNode) {
		TreeNode predecessor = null;
		TreeNode predecessorParent = null;
		TreeNode current = deleteNode.left;

		while (current != null) {
			predecessorParent = predecessor;
			predecessor = current;
			// ������ ����Ʈ���� Ÿ�� ������
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

	// median insert �� �� �����ϱ� ���� ����Ʈ
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
		return right; // ���� ������ �Ǻ� ��ȯ
	}

}
