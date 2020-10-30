    static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
                SinglyLinkedListNode ne =new SinglyLinkedListNode(data);
                if(head==null){
                    head = ne;
                }
                else{
                SinglyLinkedListNode ptr=head;
                while(ptr.next!=null){
                    ptr=ptr.next;
                }
                ptr.next=ne;
                }
                return head;
    }
