package dataStructures.list;
class Node{
	int data;
	Node next;
	Node(int data,Node next)
	{
		this.data=data;
		this.next=next;
	}
}
class Linked_list	{
	Node head;
	Linked_list()
	{
		this.head=null;
	}
	
	 void add(int data )
	 {
		while(head!=null&&head.next!=null)
		{
			head=head.next;
		}
		if(head==null)
			head=new Node(data,null);
		else
		head.next=new Node(data,null);
	 }
	 void printList()	{
		Node temp=this.head;
		while(temp!=null)	{
			System.out.print(temp.data+" ->");
			temp=temp.next;
		}
	 }
	Node revLL()
	{
	Node node=head,prevNode=null,nextNode=null;
		while(node!=null)
		{
			nextNode=nextNode.next;
			node.next=prevNode;
			prevNode=node;
			node=nextNode;
		
		}
	return prevNode;
	}

}
public class ReverseOfLL {


	public static void main(String[] args) {
		Linked_list list=new Linked_list();
		list.add(5);
		list.add(5);
		list.add(5);
		list.add(5);
		list.add(5);
		list.revLL();
		list.printList();
		
}

}
