
public class Winnings implements Comparable<Winnings> {
	
	private int winnings;
	private String name;
	
	public Winnings(String name, int winnings) {
		this.winnings = winnings;
		this.name = name;
	}
	
	public int getWinnings() {
		return this.winnings;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public int compareTo(Winnings o) {
		if (this.winnings >= o.getWinnings()) {
			return -1;
		}
		return 1;
	}

}
