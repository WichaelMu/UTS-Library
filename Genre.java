public class Genre {
		private String name;
		// write your solution below
		public Genre(){
			System.out.print("Enter the genre: ");
			name = In.nextLine();
		}

		public Genre Return(){
			return this;
		}

		@Override
		public String toString(){
			return this.name;
		}
}
