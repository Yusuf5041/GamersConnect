package gc_gui;

import java.util.HashMap;

public class RankSystem {

	private HashMap ranks;
        private int rankCount = 0;

	public RankSystem() {
		// TODO - implement RankSystem.RankSystem
		ranks = new HashMap();
	}
        public void setRanks(String rank) {
            ranks.put(rankCount, rank);
            rankCount++;
        }
        
	public HashMap getRanks() {
		return this.ranks;
	}

}