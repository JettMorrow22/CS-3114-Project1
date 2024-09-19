/**
 * DoubleLL class
 *
 * @author Jett Morrow & Adam Schantz
 * @version jettmorrow & adams03
 */
public class DoubleLL {

    private DLLNode head;
    private DLLNode tail;
    private int size;

    /**
     * constructor for DoubleLL
     */
    public DoubleLL() {
        head = null;
        tail = null;
        size = 0;
    }


    /**
     * adding a record to the end of a list
     * 
     * @param n
     *            vertex to be added
     * @return true for record being added
     */
    public boolean addToEnd(int n) {
        DLLNode temp = new DLLNode(n, null, null);

        if (head == null) {
            head = temp;
            tail = temp;
        }
        else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        size++;
        return true;
    }


    /**
     * DoubleLL method to determine if list contains param int
     * 
     * @param n
     *            int to see if list contains
     * @return true if it DoubleLL contains n
     */
    public boolean contains(int n) {
        DLLNode temp = head;
        while (temp != null) {
            if (temp.data == n) {
                return true;
            }
            temp = temp.next;
        }

        return false;
    }


    /**
     * this method removes DLLNode from DoubleLL based of key in record in
     * DLLNode
     * 
     * @param index
     *            the index in the graph to be removed
     * @return true if removed, false if not found and not removed
     */
    public boolean remove(int index) {
        // should we remove a DLLNode based off the records key??
        // we do not care about the node correct?
        DLLNode temp = head;
        while (temp != null && temp.getData() != index) {
            temp = temp.next;
        }

        // not found nothing to remove
        if (temp == null) {
            return false;
        }
        else {

            if (temp == head && temp == tail) {
                head = null;
                tail = null;
            }
            else if (temp == head) {
                head = head.next;
                head.setPrev(null);

            }
            else if (temp == tail) {
                tail = tail.prev;
                tail.setNext(null);
            }
            else {
                temp.prev.setNext(temp.next);
                temp.next.setPrev(temp.prev);
            }
        }

        size--;
        return true;
    }


    /**
     * basic getter for Head
     * 
     * @return head
     */
    public DLLNode getHead() {
        return head;
    }


    /**
     * basic getter for tail
     * 
     * @return tail
     */
    public DLLNode getTail() {
        return tail;
    }


    /**
     * basis getter for Size
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * DLLNode class
     *
     * @author Jett Morrow & Adam Schantz
     * @version jettmorrow & adams03
     */
    public class DLLNode {

        private int data;
        private DLLNode prev;
        private DLLNode next;

        /**
         * basic constructor for DLLNode
         * 
         * @param d
         *            data
         * @param p
         *            prev
         * @param n
         *            next
         */
        public DLLNode(int d, DLLNode p, DLLNode n) {
            data = d;
            prev = p;
            next = n;
        }


        /**
         * getter for data field
         *
         * @return the index of the graph
         */
        public int getData() {
            return data;
        }


        /**
         * Gets the previous node in the doubly linked list.
         *
         * @return the previous node
         */
        public DLLNode getPrev() {
            return prev;
        }


        /**
         * Gets the next node in the doubly linked list.
         *
         * @return the next node
         */
        public DLLNode getNext() {
            return next;
        }


        /**
         * Setter for data field
         *
         * @param d
         *            the int for data
         */
        public void setData(int d) {
            data = d;
        }


        /**
         * Sets the previous node in the doubly linked list.
         *
         * @param prev
         *            the previous node to be set
         */
        public void setPrev(DLLNode prev) {
            this.prev = prev;
        }


        /**
         * Sets the next node in the doubly linked list.
         * <p>
         * This method is a basic setter for the next node reference.
         * </p>
         * 
         * @param next
         *            the next node to be set
         */
        public void setNext(DLLNode next) {
            this.next = next;
        }
    }
}
