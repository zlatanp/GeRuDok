package gerudok.model;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.TreeNode;

import gerudok.comands.CommandManager;
import gerudok.view.SlotView;

public class Slot extends Observable implements TreeNode, Serializable, Observer {
	private static final long serialVersionUID = 3756092790397970680L;

	Page parent = null;
	String name = null;
	transient SlotView slotView = null;
	CommandManager commandManager;

	public Slot(Page parent) {
		super();
		this.commandManager = new CommandManager();
		this.parent = parent;
		addObserver(parent);
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}
	
	public void slotChanged() {
		setChanged();
		notifyObservers();
	}

	public SlotView getSlotView() {
		return slotView;
	}

	public void setSlotView(SlotView slotView) {
		this.slotView = slotView;
	}

	public void setName(String name) {
		this.name = name;
		if (slotView != null)
			slotView.setName(name);
		// dogodila se modifikacija projekta
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return name;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return this.parent;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();
	}
}
