import java.util.Iterator;
import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class Library {
	private Catalogue catalogue = new Catalogue(this);
    //private List<Patron> patrons;
    private LinkedList<Patron> patrons = new LinkedList<Patron>();
    private LinkedList<Book> books = new LinkedList<Book>();
    // write your solution below

    public static void main(String[] args){
        Library l=new Library();
        l.use();
    }

    public void use(){
        //catalogue = new Catalogue();
        System.out.println("Welcome to the Library! Please make a selection from the menu:");
        System.out.println(menu());
        char c;
        System.out.print("Enter a choice: ");
        while ((c=In.nextChar())!='X'){
            switch (c){
                case '1':
                catalogue.use();
                break;
                case '2':
                ViewPatrons();
                break;
                case '3':
                Favourite();
                break;
                case '4':
                admin();
                break;
                default:
                fam();
                break;
            }
            System.out.print("Enter a choice: ");
        }
        System.exit(0); //  Why is this needed?
    }

    public String menu(){
        return "1. Explore the catalogue.\n2. View your patron record.\n3. Show you favourite books.\n4. Enter Admin Mode.\nX. Exit the system.";
    }

    private int AskForPatronID(){
        System.out.print("\nEnter a patron ID: ");
        return In.nextInt();
    }

    private void ViewPatrons(){
        //boolean f=false;
        int id=AskForPatronID();
        Patron p=LookForPatronWithID(id);
        if (p!=null){
            String n=p.GetName();
            //f=!f;
            System.out.println(p);
            System.out.println("Books currently borrowed by "+n+":");
            if (p.GetCurrentlyBorrowed() != null)
                for (Book b:p.GetCurrentlyBorrowed())
                    System.out.println(b);
            System.out.println(n+"'s"+" borrowing history:");
            if (p.GetBorrowingHistory()!=null)
                for (Book b:p.GetBorrowingHistory())
                    System.out.println(b);
            System.out.println();
        } else 
            System.out.println("That patron does not exist.\n");
        use();
    }

    private void admin(){
        System.out.println("Welcome to the administration menu:");
        System.out.println(AdminMenu());
        char c;
        System.out.print("Enter a choice: ");
        while ((c=In.nextChar())!='R'){
            switch (c){
                case '1':   //  Add a patron.
                AdminOptionOne();
                break;
                case '2':   //  Remove a patron.
                AdminOptionTwo();
                break;
                case '3':   //  Add a book to the catalogue.
                AdminOptionThree();
                break;
                case '4':   //  Remove a book frmo the catalogue.
                AdminOptionFour();
                break;
                default:
                maf();
                break;
            }
            System.out.print("Enter a choice: ");
        }
        use();
    }
    
    private String AdminMenu(){
        return "1. Add a patron.\n2. Remove a patron.\n3. Add a book to the catalogue.\n4. Remove a book from the catalogue.\nR. Return to the previous menu.";
    }

    private void AdminOptionOne(){
        System.out.println("\nAdding a new patron.");
        System.out.print("Enter a new ID: ");
        int ID=In.nextInt();
        System.out.print("Enter the patron's name: ");
        String s=In.nextLine();
        System.out.println("Patron added.\n");
        patrons.add(new Patron(ID, s, catalogue));
        admin();
    }

    private void AdminOptionTwo(){
        System.out.println("\nRemoving a patron.");
        System.out.print("Enter a patron ID: ");
        RemovePatron(LookForPatronWithID(In.nextInt()));
    }

    private void AdminOptionThree(){
        String t="";
        Author a=null;
        Genre g=null;
        System.out.println("\nAdding a new book.");
        for (int i=0;i<3;i++){
            switch(i){
                case 0:
                    System.out.print("Enter the title of the book: ");
                    t = In.nextLine();
                    break;
                case 1:
                    a = new Author();
                    break;
                case 2:
                    g = new Genre();
                    break;
            }
        }
        Book b = new Book(t,a,g);
        books.add(b);
        catalogue.AddABookToShelf(b);
        System.out.println("Added "+t+" to catalogue.\n");
        admin();
    }

    private void AdminOptionFour(){
        System.out.println("\nRemoving a book.");
        System.out.print("Enter the title of the book: ");
        String t=In.nextLine();
        System.out.print("Enter the author's name: ");
        String a=In.nextLine();
        Book b=LookForBookWithTitle(t);
        if (b!=null)
            for (Book q:books)
                if (q==b){
                    books.remove(b);
                    System.out.println(a + ", "+t+" removed from catalogue.\n");
                    admin();
                    return;
                }
        System.out.println("No such book found.\n");
        admin();
    }

    public Patron LookForPatronWithID(int i){
        for (Patron p:patrons){
            if (i==p.getID())
                return p;
        }
        return null;
    }

    private void RemovePatron(Patron p){
        for (Iterator<Patron> i=patrons.iterator();i.hasNext();)
            if (i.next().GetName().equals(p.GetName())){
                i.remove();
                System.out.println("Patron removed.\n");
                admin();
                return;
            }
        System.out.println("That patron does not exist.\n");
        admin();
    }

    public Book LookForBookWithTitle(String n){
        for (Book b:books)
            if (b.GetName().equals(n))
                return b;
        return null;
    }

    public LinkedList<Book> GetBooks(){
        return books;
    }

    public LinkedList<Patron> GetPatrons(){
        return patrons;
    }

    private void fam(){
        System.out.println("Please enter a number between 1 and 4, or press X to exit.");
        use();
    }

    private void maf(){
        System.out.println("Please enter a number between 1 and 4 or press R to return to the previous menu.");
        admin();
    }

    private void Favourite(){
        int ID=AskForPatronID();
        Patron p=LookForPatronWithID(ID);
        System.out.println(p.GetName()+"'s favourite books are:");
        LinkedList<Book>m=new LinkedList<Book>();
        for (Book b:p.GetBorrowingHistory())
                m.add(b);
        for (int i=0;i<3;i++){
            int f=1;
            HashMap<Book,Integer>c=new HashMap<Book,Integer>();
            for (Book b:m)
                if (c.containsKey(b))
                    c.put(b, c.get(b)+1);
                else
                    c.put(b,1);
            Set<Entry<Book,Integer>>q=c.entrySet();
            Book k=null;
            for (Entry<Book,Integer>e:q)
                if (e.getValue()>f){
                    f=e.getValue();
                    k=e.getKey();
                }
            System.out.print((f>1)?k+"\n":"");
            for (Iterator<Book>w=m.iterator();w.hasNext();)
                if (w.next()==k)
                    w.remove();
        }
        for (int i=0;i<2;i++)
            try { System.out.println(m.get(i)); }
            catch (Exception e) { break; }
        System.out.println();
        use();
    }
}
