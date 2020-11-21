import java.util.*;

public class Patron {
		private int ID;
		private String name;
		private Catalogue c;
		private LinkedList<Book> currentlyBorrowed = new LinkedList<Book>();
		private LinkedList<Book> borrowingHistory = new LinkedList<Book>();
		// write your solution below

		public Patron(int ID, String name, Catalogue c){
			this.ID = ID;
			this.name = name;
			this.c = c;
		}

		public int getID(){
			return this.ID;
		}

		public String GetName(){
			return this.name;
		}

		public LinkedList<Book> GetCurrentlyBorrowed(){
			return this.currentlyBorrowed;
		}

		public LinkedList<Book> GetBorrowingHistory(){
			return this.borrowingHistory;
		}

		public void BorrowABook(LinkedList<Book> bks){
			System.out.print("Enter the title of the book you wish to borrow: ");
			String n=In.nextLine();
			for (Book b:bks){
				//if (b.GetName().equals(n)&&b.IsAvailable()){
				if (b.GetName().equals(n)){
					if (!(b.GetHolds().isEmpty())){
						LinkedList<Patron>p=new LinkedList<Patron>();
						p.addAll(b.GetHolds());
						Patron pn = p.get(0);
						if (pn.getID()==ID){
							BorrowThatBook(b);
							b.RemoveAHold(this);
						}
						else
							System.out.println("That book is not available or doesn't exist.\n");
					} else
						BorrowThatBook(b);
					return;
				}
			}
			System.out.println("That book is not available or doesn't exist.\n");
		}

		private void BorrowThatBook(Book b){
			AddACurrentlyBorrowedBook(b);
			System.out.println("Book loaned.\n");
			//b.SetIsCurrentlyBorrowed(true);
			c.RemoveABookFromShelf(b);
		}

		private void AddACurrentlyBorrowedBook(Book b){
			currentlyBorrowed.add(b);
			borrowingHistory.add(b);
		}

		public void ReturnABook(){
			System.out.println(this.name+" has the following books:\nBooks currently borrowed by "+this.name+":");
			for (Book b:currentlyBorrowed)
				System.out.println(b);
			System.out.print("Enter the title of the book you wish to return: ");
			String s=In.nextLine();
			for (Book b:currentlyBorrowed)
				if (b.GetName().equals(s)&&b!=null)
					c.AddABookToShelf(b);
					//b.SetIsCurrentlyBorrowed(false);
			ReturnABook(s);
			System.out.println();
		}

		private void ReturnABook(String name){
			for (Iterator<Book> i = currentlyBorrowed.iterator();i.hasNext();)
				if (i.next().GetName().equals(name)){
					i.remove();
					System.out.println(name + " has been returned.");
					break;
				}
		}

		@Override
		public String toString(){
			return ID + " " + name;
		}
}
