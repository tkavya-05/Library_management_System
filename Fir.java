import java.util.*;
class Fir{
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("\nWelcome to the Library");
        // Using a loop to keep menu active until user chooses to exit
        while(true){
            boolean exity=false;
            // Creating object for the Library Class
            Library l1=new Library();
            // Adding some books initially to the Libarary
            Book bb1=new Book("AAA","BBB",111);  
            Book bb2=new Book("CCC","DDD",222);
            Book bb3=new Book("EEE","FFF",333);    
            l1.addbook(bb3); l1.addbook(bb2); l1.addbook(bb1);
            // Menu to the User
            System.out.println("\n1:Add Book \n2:Remove Book\n3:Search Book\n4:Loan Book \n5:Return Book \n6:Exit ");
            System.out.println("Choose respective numeral for Operation: ");
            int n=sc.nextInt();
            // Using switch operation based on respective numeral for a operation
            switch(n){
              case 1: 
                    //  Adding Book to Library 
                     System.out.println("Enter tittle,author,ISBN of a book:  ");
                     String t=sc.next();  String a=sc.next();  int i=sc.nextInt();
                    //  Creating book object with given book details by user
                     Book b1=new Book(t,a,i);
                    // Adding new Book to the Library by addbook function
                    l1.addbook(b1);
                     break;
              case 2: 
                    //   Removing book from library
                      l1.removebook();
                      break; 
              case 3: 
                    //   Searching a book in Library 
                      System.out.println("Enter 1,2,3 for respictive operation Search by Tittle, Author, ISBN of a book: ");
                      int ops=sc.nextInt();
                    //   Using if else condition for appropriate operation required by the User by respective numerals
                    // Opeartion done individually based on respective function...
                      if(ops==1)
                          l1.searchtittle();
                      else if(ops==2)
                        l1.searchauthor();
                      else 
                        l1.searchisbn();  
                      break;
              case 4: 
                    //  Loan of a book by function loan()
                     l1.loan();
                     break;
              case 5: 
                     // Return of a book by function returnbook()
                     l1.returnbook();
                     break;
              case 6:
                    // Updating the catalogue of Library 
                    // Removing the books from request Queue remReq 
                      l1.removebooksofrecReq();
                    //   Updating exity to true as per user request to exit
                      exity=true;    
           }
           if(exity) break;
       }
    }
}
// Creating Special Object Node(LinkedList) as its value as Book and next as Node
class Node{
    Book obj;
    Node next;
    public Node(Book obj,Node next){
        this.obj=obj;
        this.next=next;
    }
}
// Creating class Book with respective tittle,author and ISBN and updating isAvailable to true initially during object creation
class Book{
    String tittle,author;
    int ISBN;
    boolean isAvail=false;
    public Book(String tittle,String author,int ISBN){
        this.tittle=tittle;
        this.author=author;
        this.ISBN=ISBN;
        isAvail=true;
    }
}
// Creating Library class with various methods such as AddBook, removeBook etc..
class Library{
// Creating Stack of recent addition, Queue of removal requests, LinkedList of Books stream
   static Node books=new Node(new Book("Dummy","SV",4585),null);
    static Node cur=books;
    static Queue<Book> remReq=new LinkedList<>();
    static  Stack<Book> recadd=new Stack<>();
    public static void addbook(Book obj){
        //  Adding book to the linked list stream 
        cur.next=new Node(obj,null);
        cur=cur.next;
        // updating the lastest book addition to the stack recadd
        recadd.push(obj);
    }
    public static void removebook(){
         Scanner sc=new Scanner(System.in);
        
        
// Description to the user for removal by tittle or ISBN of a book
        System.out.println("Enter 1:Remove Book by Tittle or 2:Remove Book by ISBN:  ");
        int n=sc.nextInt();
        if(n==1){
            System.out.println("Enter tittle of Book: ");
            String s=sc.next();
            boolean exist=false;
            Node t=books.next;
            while(t!=null){
                Book b=t.obj;
                String temt=b.tittle;
                if(temt.equals(s)){
                    remReq.add(b); 
                    exist=true;
                    break;
                }
                t=t.next;
            }
            if(!exist)
             System.out.println("Book doesnt exist....");
            else
            System.out.println("Operation successful...");
        }
        else{
            System.out.println("Enter ISBN of book: ");
            int nis=sc.nextInt();
            boolean exist=false;
            Node t=books.next;
            while(t!=null){
                Book b=t.obj;
                if(b.ISBN==nis){
                    remReq.add(b); 
                    exist=true;
                    break;
                }
                t=t.next;
            }
            if(!exist)
             System.out.println("Book doesnt exist....");
            else
            System.out.println("Operation successful..."); 
        }
    }
    public static void searchtittle(){
        System.out.println("Enter tittle of book: ");
        Scanner sc= new Scanner(System.in);
        String s=sc.next();
        int reg=0;
        Node t=books.next;
        while(t!=null){
            Book tb=t.obj;
            String tt=tb.tittle;
            if(tt.equals(s)){
                if(tb.isAvail)
                    reg=1;
                else  reg=2;                
                break;
            }
            t=t.next;
        }
        if(reg==1) System.out.println("Book Exist");
        else if(reg==2) System.out.println("Book on Loan");
        else   System.out.println("Book Does not exist");
    }  
    public static void searchauthor(){
        System.out.println("Enter Author of Book: ");
        Scanner sc= new Scanner(System.in);
        String s=sc.next();
        int reg=0;
        Node t=books.next;
        while(t!=null){
            Book tb=t.obj;
            String tt=tb.author;
            if(tt.equals(s)){
                if(tb.isAvail)
                    reg=1;
                else  reg=2;                
                break;
            }
            t=t.next;
        }
        if(reg==1) System.out.println("Book Exist");
        else if(reg==2) System.out.println("Book on Loan");
        else   System.out.println("Book Does not exist");
    }  
    public static void searchisbn(){
        System.out.println("Enter ISBN of Book: ");
        Scanner sc= new Scanner(System.in);
        int s=sc.nextInt();
        int reg=0;
        Node t=books.next;
        while(t!=null){
            Book tb=t.obj;
            int tt=tb.ISBN;
            if(tt==s){
                if(tb.isAvail)
                    reg=1;
                else  reg=2;                
                break;
            }
            t=t.next;
        }
        if(reg==1) System.out.println("Book Exist");
        else if(reg==2) System.out.println("Book on Loan");
        else   System.out.println("Book Does not exist");
    } 
    public static void loan(){
        System.out.println("Enter ISBN of book: ");
        Scanner sc= new Scanner(System.in);
        int s=sc.nextInt();
        boolean ex=false;
        Node t=books.next;
        while(t!=null){
            Book tb=t.obj;
            int tt=tb.ISBN;
            if(tt==s){
                ex=tb.isAvail;
                if(ex){
                    tb.isAvail=false;
                    System.out.println("Operation Successful"); 
                }
                else{
                    System.out.println("Already on loan");
                }
                break;
            }
            t=t.next;
        }
        
    } 
    public static void returnbook(){
        System.out.println("Enter ISBN of book: ");
        Scanner sc= new Scanner(System.in);
        int s=sc.nextInt();
        boolean ex=false;
        Node t=books.next;
        while(t!=null){
            Book tb=t.obj;
            int tt=tb.ISBN;
            if(tt==s){
                tb.isAvail=true;
                System.out.println("Operation Successful");
                break;
            }
            t=t.next;
        }
        
    } 
    public static void removebooksofrecReq(){
        while(!remReq.isEmpty()){
            Book ob=remReq.poll();
            Node te=books;
            while(te.next!=null){
                Book b9=te.next.obj;
                if(b9==ob){
                    te.next=te.next.next;
                    break;
                }
                te=te.next;
            }
        }
        System.out.println("All Books data updated Successfully");
    }
}