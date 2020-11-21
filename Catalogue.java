import java.util.*;

public class Catalogue {
		private Library library;
		private LinkedList<Book> booksOnShelf=new LinkedList<Book>();
		// write your solution below

		public Catalogue(Library library){
			this.library=library;
		}

		public void use(){
			//booksOnShelf=library.GetBooks();
			System.out.println("Welcome to the Catalogue! Please make a selection from the menu:");
			System.out.println(menu());
			char c;
			System.out.print("Enter a choice: ");
			while ((c=In.nextChar())!='R'){
				switch (c){
					case '1':	//	Display all books.					
					System.out.println("\nThe Library has the following books:");
					for (Book b:library.GetBooks())
						System.out.println(b);
					System.out.println();
					break;
					case '2':	//	Display all available books.
					AvailableBooks();
					break;
					case '3':	//	Display all genres.
					Genres();
					break;
					case '4':	//	Display all books in a genre.
					DisplayBooksInAGenre();
					break;
					case '5':	//	Display all authors.
					Authors();
					break;
					case '6':	//	Display all books by an author.
					DisplayBooksByAnAuthor();
					break;
					case '7':	//	Borrow a book.
					BorrowABook();
					break;
					case '8':	//	Return a book.
					ReturnABook();
					break;
					case '9':	//	Place a hold.
					Hold();
					break;
					default:
					fam();
					break;
				}
				use();
			}
			library.use();
		}

		public String menu(){
			return "1. Display all books.\n2. Display all available books.\n3. Display all genres.\n4. Display books in a genre.\n5. Display all authors.\n6. Display all books by an author.\n7. Borrow a book.\n8. Return a book.\n9. Place a hold.\nR. Return to previous menu.";
		}

		private void BorrowABook(){
			int ID = AskForPatronID();
			Patron p=library.LookForPatronWithID(ID);
			p.BorrowABook(booksOnShelf);
		}

		private void ReturnABook(){
			int ID = AskForPatronID();
			Patron p=library.LookForPatronWithID(ID);
			p.ReturnABook();
		}

		private int AskForPatronID(){
			System.out.print("\nEnter a valid patron ID: ");
			return In.nextInt();
		}

		private void Genres(){
			System.out.println("\nThe Library has books in the following genres:");
			LinkedList<String>m=new LinkedList<String>();
			for (Book b:library.GetBooks())
				m.add(b.GetGenre());
			Set<String>u=new LinkedHashSet<String>(m);
			for (String w:u){
				System.out.print(ConvertToLowerCase(w).toLowerCase()+"\n");
			}
			System.out.println();
		}

		private String ConvertToLowerCase(String s){
			String c="QWERTYUIOPASDFGHJKLZXCVBNM";
			String l="qwertyuiopasdfghjklzxcvbnm";
			String ans="";
			for (int i=0;i<s.length();i++)
				if (s.charAt(i)==c.charAt(i))
					ans+=l.charAt(i);
				else
					ans+=s.charAt(i);
			return ans;
		}

		private void DisplayBooksInAGenre(){
			System.out.print("\nEnter a genre: ");
			String s=In.nextLine();
			System.out.println("The library has the following books in that genre:");
			for (Book b:library.GetBooks())
				if (b.GetGenre().toLowerCase().equals(s))
					System.out.println(b);
			System.out.println();
		}

		private void Authors(){
			System.out.println("\nThe following authors have books in this Library:");
			LinkedList<String>m=new LinkedList<String>();
			for (Book b:library.GetBooks())
				m.add(b.GetAuthor());
			Set<String>u=new LinkedHashSet<String>(m);
			for (String w:u)
				System.out.println(w);
			System.out.println();;
		}

		private void DisplayBooksByAnAuthor(){
			System.out.print("\nEnter the name of an author: ");
			String s=In.nextLine();
			System.out.println("The library has the following books by that author:");
			for (Book b:library.GetBooks())
				if (b.GetAuthor().equals(s))
					System.out.println(b);
			System.out.println();
		}

		private void AvailableBooks(){
			System.out.println("\nThe following books are on the shelf:");
			for (Book b:booksOnShelf)
				System.out.println(b);
			System.out.println();
		}

		private void fam(){
			System.out.println("Please enter a number between 1 and 9 or press R to return to the previous menu.");
			use();
		}

		private void Hold(){
			//System.out.println("The following books are on the shelf:");
			System.out.print("\nEnter a patron ID: ");
			int ID=In.nextInt();
			System.out.print("Enter book title: ");
			String s=In.nextLine();
			Patron p=library.LookForPatronWithID(ID);
			Book b=library.LookForBookWithTitle(s);
			if (p!=null&&b!=null)
				//b.SetIsCurrentlyBorrowed(true);
				b.PlaceAHold(p);
		}

		private Book FindBookOnShelf(Book b){
			for (Book bo:booksOnShelf)
				if (bo.GetName().equals(b.GetName()))
					return bo;
			return null;
		}

		public void RemoveABookFromShelf(Book b){
			for (Iterator<Book>i=booksOnShelf.iterator();i.hasNext();)
				if (i.next().GetName().equals(b.GetName()))
					i.remove();
		}

		public void AddABookToShelf(Book b){
			booksOnShelf.add(b);
		}
}
