package introDS_Alg;
/*
 * 实现一下单向链表
 */
public class SingleLinkedListMe {
	private int size; // the number of Nodes in single linked list
	private Node head; // the head node

	// 初始化 构造函数
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

	// 链表头插入节点
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

	// 链表尾插入节点
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

	// 在链表头删除节点
	public void deleteNodeHead() {
		if (head != null) {
			head = head.next;
			size--; // 别忘了
		}
	}

	// 在链表尾删除节点
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
				needle2.next = null; // 当最后一个引用消失时，对象就会被垃圾收集器回收
				size--;
			}
		}
	}
	
	//查找指定位置的节点，给定序号，普通人思维从1开始数到i，返回该节点
	public Node findNodebyNumber(int i) {
		if ((i>size) || (i<1)) {
			System.out.println("查找的位置超出了链表的范围");
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
	
	//查找某元素在不在链表内，普通人思维从1开始数到i，返回节点所有位置序号所组成的数组
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
			System.out.println("找不到这个元素");
			return null;
		} else {
			int[] rt = new int[j];
			for (int k = 0; k < j; k++) {
				rt[k] = me[k];
			}
			//打印数组
			System.out.print("[ ");
			for (int x:rt) {System.out.print(x + " ");}
			System.out.println("]");
			
			return rt;
		}
	}
	
	//在指定位置插入节点，普通人思维从1开始数第i位，且i>=2，插完后在第i位
	public void insertNode(int i, Object m) {
		if (i > (size+1)) {
			System.out.println("这样插入的话链表断了，连不上了");
		} else {
			Node newNode = new Node(m);
			Node frontNode = findNodebyNumber(i - 1);
			newNode.next = frontNode.next;
			frontNode.next = newNode;
			size++;
		}
	}
	
	//删除指定位置的节点，给定序号，普通人思维从1开始数第i位
	public boolean deleteNodebyNumber (int i) {
		if (i > size) {
			System.out.println("序号超出链表范围，删不了");
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
	
	//删除链表中与输入元素相同的节点，如果存在的话。只删除第一个，普通人思维从1开始数第i位
	public boolean deleteNodebyNode_1st(Object b) {
		int[] arr = findNodebyNode(b);
		if (arr == null) {
			System.out.println("不存在这样的节点元素");
			return false;
		} else {
			return deleteNodebyNumber(arr[0]);
		}
	}
	
	//删除链表中与输入元素相同的节点，如果存在的话。相同的全部删除，普通人思维从1开始数第i位
	public boolean deleteNodebyNode_all(Object b) {
		int[] arr = findNodebyNode(b);
		if (arr == null) {
			System.out.println("不存在这样节点的元素，无法全删");
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
	
	// 打印链表
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

	//判断链表是否为空
	public boolean isEmpty() {
		return size == 0;
	}
	
	//链表反转
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
	
	//找出链表中间节点
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
	
	//链表排序，以整数为例，从小到大
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

	// 测试代码
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
		
		
		//测试find功能
		//list1.printList();
		//list1.findNodebyNode(9);
		//System.out.println(list1.findNodebyNumber(12).data);

		/*//测试头删除功能
		list2.printList(); 
		list2.deleteNodeHead();
		list2.printList();
		list2.deleteNodeHead();
		list2.printList();
		list2.deleteNodeHead(); 
		list2.printList();
		*/
		/*//测试尾删除功能
		list2.printList();
		list2.deleteNodeTail();
		list2.printList();
		System.out.println("Is the list empty? " + list2.isEmpty());
		list2.deleteNodeTail();
		list2.printList();
		System.out.println("Is the list empty? " + list2.isEmpty());
		*/
		/*//测试插入功能
		list2.printList();
		list2.insertNode(10, 0);
		list2.printList();*/
		/*//测试根据序号删除功能
		list2.printList();
		list2.deleteNodebyNumber(2);
		list2.printList();
		list2.deleteNodebyNumber(2);
		list2.printList();
		list2.deleteNodebyNumber(1);
		list2.printList();*/
		
		/*//测试根据节点元素删除功能（只删第一个）
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
		
		/*//测试根据节点元素删除功能（全删）
		list1.printList();
		System.out.println(list1.size);
		list1.deleteNodebyNode_all(6);
		list1.printList();
		System.out.println(list1.size);*/
		
		/*//测试链表反转
		list2.printList();
		SingleLinkedListMe list3 = list2.reverseList();
		//System.out.println(list3.size);
		list3.printList();*/
		
		/*//测试找出链表中间节点
		list2.printList();
		if (list2.searchMiddle() != null) {
			int r = (int) list2.searchMiddle();
			System.out.println(r);
		} else {
			System.out.println("链表为空，无法找到中间值");
		}*/
		
		/*//测试排序功能，以整数为例
		//list2.printList();
		//list2.sortMySingleList();
		//list2.printList();
		list1.printList();
		list1.sortMySingleList();
		list1.printList();*/
		
	}

}
