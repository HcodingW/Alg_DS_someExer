package introDS_Alg;
/*
 * ʵ��һ�µ�������
 */
public class SingleLinkedListMe {
	private int size; // the number of Nodes in single linked list
	private Node head; // the head node

	// ��ʼ�� ���캯��
	public SingleLinkedListMe() {
		size = 0;
		head = null;
	}

	// the Node class in linked list
	private class Node {
		private Object data;
		private Node next;

		public Node(Object data) {
			this.data = data;
			this.next = null;
		}
	}

	// ����ͷ����ڵ�
	public void addNodeHead(Object a) {
		Node newHead = new Node(a);
		if (head == null) {
			head = newHead;
		} else {
			newHead.next = head;
			head = newHead;
		}
		size++;
	}

	// ����β����ڵ�
	public void addNodeTail(Object b) {
		Node newTail = new Node(b);
		if (head == null) {
			head = newTail;
			size++;
			return;
		}
		Node needle = head;
		while (needle.next != null) {
			needle = needle.next;
		}
		needle.next = newTail;
		size++;
		return;

	}

	// ������ͷɾ���ڵ�
	public void deleteNodeHead() {
		if (head != null) {
			head = head.next;
			size--; // ������
		}
	}

	// ������βɾ���ڵ�
	public void deleteNodeTail() {
		if (head != null) {
			if (size == 1) {
				System.out.println(head.data+" has been deleted.");
				head = null;
				size--;
			} else {
				Node needle = head;
				Node needle2 = null;
				while (needle.next != null) {
					needle2 = needle;
					needle = needle.next;
				}
				System.out.println(needle.data+" has been deleted.");
				needle2.next = null; // �����һ��������ʧʱ������ͻᱻ�����ռ�������
				size--;
			}
		}
	}
	
	//����ָ��λ�õĽڵ㣬������ţ���ͨ��˼ά��1��ʼ����i�����ظýڵ�
	public Node findNodebyNumber(int i) {
		if ((i>size) || (i<1)) {
			System.out.println("���ҵ�λ�ó���������ķ�Χ");
			return null;
		} else {
			Node needle = head;
			while((i-1) > 0) {
				needle = needle.next;
				i--;
			}
			return needle;
		}
	}
	
	//����ĳԪ���ڲ��������ڣ���ͨ��˼ά��1��ʼ����i�����ؽڵ�����λ���������ɵ�����
	public int[] findNodebyNode(Object b) {
		int[] me = new int[size];
		Node needle = head;
		int i = 1;
		int j =0;
		while(needle != null) {
			if (needle.data == b) {
				me[j] = i;
				j++;
			}
			needle = needle.next;
			i++;
		}
		if (j == 0) {
			System.out.println("�Ҳ������Ԫ��");
			return null;
		} else {
			int[] rt = new int[j];
			for (int k = 0; k < j; k++) {
				rt[k] = me[k];
			}
			//��ӡ����
			System.out.print("[ ");
			for (int x:rt) {System.out.print(x + " ");}
			System.out.println("]");
			
			return rt;
		}
	}
	
	//��ָ��λ�ò���ڵ㣬��ͨ��˼ά��1��ʼ����iλ����i>=2��������ڵ�iλ
	public void insertNode(int i, Object m) {
		if (i > (size+1)) {
			System.out.println("��������Ļ�������ˣ���������");
		} else {
			Node newNode = new Node(m);
			Node frontNode = findNodebyNumber(i - 1);
			newNode.next = frontNode.next;
			frontNode.next = newNode;
			size++;
		}
	}
	
	//ɾ��ָ��λ�õĽڵ㣬������ţ���ͨ��˼ά��1��ʼ����iλ
	public boolean deleteNodebyNumber (int i) {
		if (i > size) {
			System.out.println("��ų�������Χ��ɾ����");
			return false;
		} else if (i == 1) {
			head = head.next;
			size--;
			return true;
		} else {
			Node frontNode = findNodebyNumber(i - 1);
			Node node = findNodebyNumber(i);
			frontNode.next = node.next;
			size--;
			return true;
		}
	}
	
	//ɾ��������������Ԫ����ͬ�Ľڵ㣬������ڵĻ���ֻɾ����һ������ͨ��˼ά��1��ʼ����iλ
	public boolean deleteNodebyNode_1st(Object b) {
		int[] arr = findNodebyNode(b);
		if (arr == null) {
			System.out.println("�����������Ľڵ�Ԫ��");
			return false;
		} else {
			return deleteNodebyNumber(arr[0]);
		}
	}
	
	//ɾ��������������Ԫ����ͬ�Ľڵ㣬������ڵĻ�����ͬ��ȫ��ɾ������ͨ��˼ά��1��ʼ����iλ
	public boolean deleteNodebyNode_all(Object b) {
		int[] arr = findNodebyNode(b);
		if (arr == null) {
			System.out.println("�����������ڵ��Ԫ�أ��޷�ȫɾ");
			return false;
		} else {
			int p = 0;
			for (int i:arr) {
				int j = i - p;
				deleteNodebyNumber(j);
				p++;
			}
			return true;
		}
	}
	
	// ��ӡ����
	public void printList() {
		if (size == 0) {
			System.out.println("empty");
		} else if (size == 1) {
			System.out.println("[ "+head.data+" ]");
		} else {
			System.out.print("[ ");
			Node needle = head;
			while (needle != null) {
				System.out.print(needle.data);
				needle = needle.next;
				if (needle != null)
					System.out.print(" --> ");
			}
			System.out.println(" ]");
		}
	}

	//�ж������Ƿ�Ϊ��
	public boolean isEmpty() {
		return size == 0;
	}
	
	//����ת
	public SingleLinkedListMe reverseList() {
		SingleLinkedListMe lst2 = new SingleLinkedListMe();
		if (this.head == null) {
			return lst2;
		} else {
			Node needle = this.head;
			while(needle != null) {
				lst2.addNodeHead(needle.data);
				needle = needle.next;
			}
			return lst2;
		}
	}
	
	//�ҳ������м�ڵ�
	public Object searchMiddle() {
		if (head == null) {
			return null;
		} else if (head.next == null) {
			return head.data;
		} else {
			Node needleF = head;
			Node needleS = head;
			while (needleF.next != null) {
				needleF = needleF.next;
				if (needleF.next != null) {
					needleF = needleF.next;
					needleS = needleS.next;
				} else {
					return needleS.data;
				}
			}
			return needleS.data;
		}
	}
	
	//��������������Ϊ������С����
	public void sortMySingleList() {
		if (head == null) return;
		if (head.next == null) return;
		Node needle1 = head;
		while(needle1.next != null) {
			Node needle2 = needle1.next;
			while(needle2 != null) {
				if ((int)needle1.data > (int)needle2.data) {
					Object tmp = null;
					tmp = needle1.data;
					needle1.data = needle2.data;
					needle2.data = tmp;
				}
				needle2 = needle2.next;
			}
			needle1 = needle1.next;
		}
		
	}

	// ���Դ���
	public static void main(String[] args) {
		
		SingleLinkedListMe list1 = new SingleLinkedListMe();
		list1.addNodeTail(3); //pi = 3.141592653589793238462
		list1.addNodeTail(1); 
		list1.addNodeTail(4);
		list1.addNodeTail(1); 
		list1.addNodeTail(5); 
		list1.addNodeTail(9);
		list1.addNodeTail(2); 
		list1.addNodeTail(6); 
		list1.addNodeTail(5);
		list1.addNodeTail(3); 
		list1.addNodeTail(5); 
		list1.addNodeTail(8);
		list1.addNodeTail(9);
		list1.addNodeTail(7);
		list1.addNodeTail(9);
		list1.addNodeTail(3);
		list1.addNodeTail(2);
		list1.addNodeTail(3);
		list1.addNodeTail(8);
		list1.addNodeTail(4);
		list1.addNodeTail(6);
		list1.addNodeTail(2);
		
		//SingleLinkedListMe list2 = new SingleLinkedListMe();
		/*list2.addNodeHead(9);
		list2.addNodeHead(8);
		list2.addNodeHead(7); 
		list2.addNodeHead(6); 
		list2.addNodeHead(5);
		list2.addNodeHead(4);
		list2.addNodeHead(3);
		list2.addNodeHead(2); 
		list2.addNodeHead(1);*/
		
		/*list2.addNodeHead(1); list2.addNodeHead(2); list2.addNodeHead(3); list2.addNodeHead(4);
		list2.addNodeHead(5); list2.addNodeHead(6); list2.addNodeHead(7); list2.addNodeHead(8);
		list2.addNodeHead(9); list2.addNodeHead(10);*/
		
		
		//����find����
		//list1.printList();
		//list1.findNodebyNode(9);
		//System.out.println(list1.findNodebyNumber(12).data);

		/*//����ͷɾ������
		list2.printList(); 
		list2.deleteNodeHead();
		list2.printList();
		list2.deleteNodeHead();
		list2.printList();
		list2.deleteNodeHead(); 
		list2.printList();
		*/
		/*//����βɾ������
		list2.printList();
		list2.deleteNodeTail();
		list2.printList();
		System.out.println("Is the list empty? " + list2.isEmpty());
		list2.deleteNodeTail();
		list2.printList();
		System.out.println("Is the list empty? " + list2.isEmpty());
		*/
		/*//���Բ��빦��
		list2.printList();
		list2.insertNode(10, 0);
		list2.printList();*/
		/*//���Ը������ɾ������
		list2.printList();
		list2.deleteNodebyNumber(2);
		list2.printList();
		list2.deleteNodebyNumber(2);
		list2.printList();
		list2.deleteNodebyNumber(1);
		list2.printList();*/
		
		/*//���Ը��ݽڵ�Ԫ��ɾ�����ܣ�ֻɾ��һ����
		list1.printList();
		list1.deleteNodebyNode_1st(9);
		list1.printList();
		
		list2.printList();
		list2.deleteNodebyNode_1st(1);
		list2.printList();
		list2.deleteNodebyNode_1st(2);
		list2.printList();
		list2.deleteNodebyNode_1st(3);
		list2.printList();*/
		
		/*//���Ը��ݽڵ�Ԫ��ɾ�����ܣ�ȫɾ��
		list1.printList();
		System.out.println(list1.size);
		list1.deleteNodebyNode_all(6);
		list1.printList();
		System.out.println(list1.size);*/
		
		/*//��������ת
		list2.printList();
		SingleLinkedListMe list3 = list2.reverseList();
		//System.out.println(list3.size);
		list3.printList();*/
		
		/*//�����ҳ������м�ڵ�
		list2.printList();
		if (list2.searchMiddle() != null) {
			int r = (int) list2.searchMiddle();
			System.out.println(r);
		} else {
			System.out.println("����Ϊ�գ��޷��ҵ��м�ֵ");
		}*/
		
		/*//���������ܣ�������Ϊ��
		//list2.printList();
		//list2.sortMySingleList();
		//list2.printList();
		list1.printList();
		list1.sortMySingleList();
		list1.printList();*/
		
	}

}
