package application;

public class Grid {

	private CircleList<CircleList<Life>> grid;

	public Grid(CircleList<CircleList<Life>> grid) {
		setcList(grid);
	}

	public CircleList<CircleList<Life>> getGrid() {
		return grid;
	}

	public void setcList(CircleList<CircleList<Life>> cList) {
		this.grid = cList;
	}

	public void makeGrid() {
		// makes 2d CircleList of dead objects.
		for (int i = 0; i < 40; i++) {

			CircleList<Life> lList = new CircleList<>();
			Node<CircleList<Life>> yList = new Node<CircleList<Life>>(lList, null, null);
			grid.appendNode(yList);
			for (int j = 0; j < 90; j++) {
				Life l = new Life(i, j, false, false);
				Node<Life> n = new Node<Life>(l, null, null);
				lList.appendNode(n);
			}
			lList.circleLink();
		}
		grid.circleLink();
	}

	public void Update() {
		// loops through all life detecting surroundings and setting that life to live
		// or dead.
		int liveCount = 0;
		for (int i = 0; i < 40; i++) {

			CircleList<Life> ylist = grid.getNode(i).getValue();
			for (int j = 0; j < 90; j++) {
				
				Life l = ylist.getNode(j).getValue();
				
                //check each adjacent cell for status.
				Life abovecurrent = ylist.getNode(j).getPrevious().getValue();
				Life belowcurrent = ylist.getNode(j).getNext().getValue();

				Life leftabovecurrent = grid.getNode(i).getPrevious().getValue().getNode(j).getPrevious().getValue();
				Life leftofcurrent = grid.getNode(i).getPrevious().getValue().getNode(j).getValue();
				Life leftbelowcurrent = grid.getNode(i).getPrevious().getValue().getNode(j).getNext().getValue();

				Life rightabovecurrent = grid.getNode(i).getNext().getValue().getNode(j).getPrevious().getValue();
				Life rightofcurrent = grid.getNode(i).getNext().getValue().getNode(j).getValue();
				Life rightbelowcurrent = grid.getNode(i).getNext().getValue().getNode(j).getNext().getValue();


				if (abovecurrent.getAlive() == true) {
					liveCount++;
				}
				if (belowcurrent.getAlive() == true) {
					liveCount++;
				}

				if (leftabovecurrent.getAlive() == true) {
					liveCount++;
				}
				if (leftofcurrent.getAlive() == true) {
					liveCount++;
				}
				if (leftbelowcurrent.getAlive() == true) {
					liveCount++;
				}

				if (rightabovecurrent.getAlive() == true) {
					liveCount++;
				}
				if (rightofcurrent.getAlive() == true) {
					liveCount++;
				}
				if (rightbelowcurrent.getAlive() == true) {
					liveCount++;
				}

				//live cell with out enough neighbors dies.
				if (liveCount < 2 || liveCount > 3 && l.getAlive() == true) {		
						l.setChange(true);
						
				}
				//live cell with enough neighbors lives.
				 if (liveCount == 2 || liveCount == 3 && l.getAlive() == true) {
					l.setChange(false);
				}
				//dead cell with enough neighbors lives.
				 if (liveCount == 3 && l.getAlive() == false) {
					l.setChange(true);
				}
				//dead cell without enough neighbors stays dead.
				 if (liveCount != 3 && l.getAlive() == false){
					l.setChange(false);
				}
				liveCount = 0;								
			}
		}
		updateLife();
	}
	
	public void updateLife() {
		for (int i = 0; i < 40; i++) {

			CircleList<Life> ylist = grid.getNode(i).getValue();
			for (int j = 0; j < 90; j++) {
				Life l = ylist.getNode(j).getValue();

				if (l.getChange() == true) {
					if (l.getAlive() == true) {
						l.setAlive(false);
					} else if (l.getAlive() == false){
						l.setAlive(true);
					}
				} else if (l.getChange() == false){
					l.setAlive(l.getAlive());
				}			
			}
		}
	}
}
