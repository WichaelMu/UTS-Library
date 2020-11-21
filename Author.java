
public class Author {
		private String name;
		// write your solution below

		public Author(){
			System.out.print("Enter the author's name: ");
			name = In.nextLine();
		}

		public Author Return(){
			return this;
		}

		@Override
		public String toString(){
			return name;
		}
}
