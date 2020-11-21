import java.util.*;

public class Book {
	private String title;
	private Author author;
	private Genre genre;
	private LinkedList<Patron> holds = new LinkedList<Patron>();
	//private boolean IsCurrentlyBorrowed;
	// write your solution below

	public Book(String title, Author author, Genre genre){
		this.title = title;
		this.author = author;
		this.genre = genre;
	}

	public String GetName(){
		return this.title;
	}

	public String GetAuthor(){
		return this.author.toString();
	}

	public String GetGenre(){
		return this.genre.toString();
	}

	//public void SetIsCurrentlyBorrowed(boolean a){
	//	IsCurrentlyBorrowed = a;
	//}
//
	//public boolean IsAvailable(){
	//	return !IsCurrentlyBorrowed;
	//}

	public void PlaceAHold(Patron p){
		holds.add(p);
		System.out.println("Book held.\n");
	}

	public void RemoveAHold(Patron p){
		for (Iterator<Patron>i=holds.iterator();i.hasNext();)
			if (i.next().getID()==p.getID())
				i.remove();
	}

	public LinkedList<Patron> GetHolds(){
		return holds;
	}

	@Override
	public String toString(){
		return author + ", " + title;
	}
}
