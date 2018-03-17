package Maths_Learning_Game.tiles;

import Maths_Learning_Game.gfx.Asset;

public class Stone extends Tile {

	public Stone(int id) {
		super(Asset.stone, id);
	}

	public boolean isSolid() {
		return true;
		
	}
}
