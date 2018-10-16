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

	// Ʈ���� ����ִ��� �˻��ϴ� �Լ�
	public boolean isEmpty() {
		return root == null;
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

	// �ݺ�Ž��
	public int iterativeSearch(int key) {
		TreeNode current = root;
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
		TreeNode current = root;
		while (current != null) {
			// ��ġ�ϴ� ��带 ã������ ����
			if (current.data == key) {
				return current.data;
			}
			// ã������ ���� �� ���(���� ���)���� ������ �������� Ž��
			else if (current.data > key) {
				return recursiveSearch(current.left.data);
			}
			// ã������ ���� �� ���(���� ���)���� ũ�� ���������� Ž��
			else {
				return recursiveSearch(current.right.data);
			}
		}
		return 0;
	}

	// ����
	public boolean delete(int x) {
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

		// 3. �ΰ��� �ڽ��� ���� ���
		else if (current.left != null && current.right != null) {
			// ������ ����Ʈ���� �ּҰ��� ã��
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
