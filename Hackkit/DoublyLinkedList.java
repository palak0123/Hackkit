
import java.util.Scanner;

//Node class
class Node {
    
    private int data;
    private Node next, prev;
    
    public Node(){
        data = 0;
        next = null;
        prev = null;
    }
    
    public Node(int d, Node n, Node p){
        data = d;
        next = n;
        prev = p;
    }
    
    public Node getNext(){
        return next;
    }
    
    public Node getPrev(){
        return prev;
    }
    
    public void setNext(Node n){
        next = n;
    }
    
    public void setPrev(Node p){
        prev = p;
    }
    
    public void setData(int d){
        data = d;
    }
    
    public int getData(){
        return data;
    }
}

//DoublyLinked List class

class linkedList {

protected Node start; // start means the node existing before adding a new one(it may                         be null or present also)
protected Node end;
public int size;

public linkedList(){
start = null;
end = null;
size = 0;
}

public boolean isEmpty(){
if (size == 0){
return(true);
}
else{
return(false);
}
}

public int getSize(){
return size;
}

public void insertAtFirst(int val){

Node n = new Node(val, null, null);
if (start == null){
start = n;
end = start;
}
else{
start.setPrev(n);  // this line means "start" the existing node's previous is joined with the address of new node n.
n.setNext(start);  // this line means we attached the new node with existing but existing node is not attached to new node; so we made the the node "Next" place to be the address of existing node present in start..as start was pointing existing node in starting.
start = n; // now make the satrt to point new node by keeping new node value to start
}
    size++;
}

public void insertAtLast(int val){
Node n = new Node(val, null, null);
if (start == null){
start = n;
end = start;
}
else {
n.setPrev(end);
end.setNext(n);
end = n;
}
size++;
}

public void insertAtPos(int val, int pos){

    Node nptr = new Node(val, null, null);
    if (pos == 1)
    {
        insertAtFirst(val);
        return;
    }
    Node ptr = start;
    for (int i = 2; i <= size; i++)
    {
        if (i == pos)
        {
            Node tmp = ptr.getNext();
            ptr.setNext(nptr);
            nptr.setPrev(ptr);
            nptr.setNext(tmp);
            tmp.setPrev(nptr);
        }
        ptr = ptr.getNext();
    }
    size++ ;
}

public void deleteAtPos(int pos){

if (pos == 1){
    if (size == 1){
       start = null;
       end = null;
       size = 0;
       return;
      }
start = start.getNext();  // here start is at 2 position..so just remove refference of                       2 node from 1 node and 1 gets deleted in from of garbage value
start.setPrev(null);
size--;
return;
}

if (pos == size)
{
end = end.getPrev();
end.setNext(null);
size--;
}

Node ptr = start.getNext();
for (int i = 2; i < size; i++)     // removed = from the actual code if found bug,change
{
if (i == pos)
{
Node p = ptr.getPrev();
Node n = ptr.getNext();

p.setNext(n);
n.setPrev(p);
size--;
return;
}
ptr = ptr.getNext();
}
}

public void display()
{
System.out.println("\nDoubly Linked List = ");
if (size == 0)
{
    System.out.print("empty\n");
    return;
}
if (start.getNext() == null)
{
    System.out.println(start.getData() );
    return;
}

Node ptr = start ;
System.out.println(start.getData()+ " <-> ");
ptr = ptr.getNext();
while(ptr.getNext() != null)
{
   System.out.print(ptr.getData()+ " <-> ");
   ptr = ptr.getNext();
}
    System.out.println(ptr.getData()+"\n");
}

}



public class DoublyLinkedList {
    
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
               /* Creating object of linkedList */
               linkedList list = new linkedList();
               System.out.println("Doubly Linked List Test\n");
              boolean flag = true;
               
        while(flag){
                   System.out.println("\nDoubly Linked List Operations\n");
                   System.out.println("1. insert at begining");
                   System.out.println("2. insert at end");
                   System.out.println("3. insert at position");
                   System.out.println("4. delete at position");
                   System.out.println("5. check empty");
                   System.out.println("6. get size");
                   System.out.println("7. View List Elements");
                   System.out.println("8. Exit");
        
                   System.out.println("Enter your choice:");
                   int choice = scan.nextInt();
                   switch (choice)
                   {
                   case 1 :
                       System.out.println("Enter integer element to insert");
                       list.insertAtFirst( scan.nextInt() );
                       break;
                   case 2 :
                       System.out.println("Enter integer element to insert");
                       list.insertAtLast( scan.nextInt() );
                       break;
                   case 3 :
                       System.out.println("Enter integer element to insert");
                       int num = scan.nextInt() ;
                       System.out.println("Enter position");
                       int pos = scan.nextInt() ;
                       if (pos < 1 || pos > list.getSize() )
                           System.out.println("Invalid position\n");
                       else
                           list.insertAtPos(num, pos);
                       break;
                   case 4 :
                       System.out.println("Enter position");
                       int p = scan.nextInt() ;
                       if (p < 1 || p > list.getSize() )
                           System.out.println("Invalid position\n");
                       else
                           list.deleteAtPos(p);
                       break;
                   case 5 :
                       System.out.println("Empty status = "+ list.isEmpty());
                       break;
                   case 6 :
                       System.out.println("Size = "+ list.getSize() +" \n");
                       break;
                   case 7:
                       System.out.println("List elements are");
                       list.display();
                       break;
                   case 8:
                       flag = false;
                       System.out.println("Exiting");
                       break;
                   default :
                       System.out.println("Wrong Entry \n ");
                           
                }

            }
       }
   }
