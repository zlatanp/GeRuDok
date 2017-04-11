package gerudok.statepattern;

import java.awt.Point;
import java.awt.event.MouseEvent;

import gerudok.comands.AddCommand;
import gerudok.model.FrowneyElement;
import gerudok.view.SlotView;

public class FrowneyState extends State {

	public FrowneyState(SlotView view) {
		super(view);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Dodavanje fraunija :(
		Point point = e.getPoint();
		slot.getCommandManager()
				.addCommand(new AddCommand(slot, new FrowneyElement(slot, point)));
	}
}
