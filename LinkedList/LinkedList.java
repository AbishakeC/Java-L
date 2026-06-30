class Node{
    int data;
    Node next;

    public Node( int data) {
        this.data = data;
        this.next = null;
    }

}

public class LinkedList {
    
    // head Node
    Node head;
    
    // Display data
    public void display(){
        Node temp = head;

        while(temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
    
    // insert beginning
    public void InsetBeginning(int data){
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

     
    // insert End 
    public void InsertEnd(int data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Insert Middle
    public void InsertMiddle(int pos, int data){
        Node newNode = new Node(data);

        if(pos == 0){
            InsetBeginning(data);
            return;
        }

        Node temp = head;

        for( int i=0; i<pos-1 && temp != null;i++){
             temp= temp.next;
        }

        if(temp == null){
            System.out.println("invalid position");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    //Delete Node 
    public void DeleteNode(int pos){
        if(head ==  null){
          System.out.println("empty list.....");
          return;
        }

        if(pos == 0){
            head =  head.next;
            return;
        }

        Node temp = head;

        for(int i=0; i<pos -1 && temp != null; i++){
            temp = temp.next;
        }

        if(temp == null || temp.next == null){
            System.out.println(" invalid position");
            return;
        }
        temp.next = temp.next.next;
    }

    public void DeleteByValue(int Value){
        if (head == null){
            System.out.println("Empty List...");
            return;
        }

        if (head.data == Value){
            head = head.next;
            return;
        }

        Node temp =  head;

        while(temp.next != null && temp.next.data != Value){
           temp =  temp.next;
        }

        if(temp.next == null){
            System.out.println("Value not found ....");
            return;
        }
        temp.next = temp.next.next;

    }
     

    public static void main(String[] args) {
        LinkedList NewLL = new LinkedList();

        NewLL.InsetBeginning(5);
        NewLL.InsetBeginning(3);
        NewLL.InsetBeginning(4);

        NewLL.display();
        System.err.println();
        NewLL.InsertMiddle(2, 10);
        NewLL.InsertMiddle(0, 1);
        NewLL.InsertMiddle(10, 1);
        NewLL.display();

        System.out.println();

        NewLL.DeleteNode(7);
        NewLL.DeleteByValue(5);
        NewLL.DeleteNode(1);
        NewLL.display();

      
    }

}
