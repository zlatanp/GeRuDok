package gerudok.actions;

import gerudok.GerudokFileFilter;
import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Document;
import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.model.Workspace;
import gerudok.view.DocumentView;
import gerudok.view.PageView;
import gerudok.view.ProjectView;
import gerudok.view.SlotView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

public class OpenProjectAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new GerudokFileFilter());
		jfc.setDialogTitle("Open Gerudok project");
		File projectFile = null;
		Project project = null;
		int choosen = jfc.showOpenDialog(MainFrameGerudok.getInstance());
		if (choosen == JFileChooser.APPROVE_OPTION) {
			projectFile = jfc.getSelectedFile();
			ObjectInputStream is;
			try {

				is = new ObjectInputStream(new FileInputStream(projectFile));
				project = (Project) is.readObject();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			if (project != null) {
				JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
				JDesktopPane desktopPane = MainFrameGerudok.getInstance().getDesktopPane();

				TreePath path = tree.getSelectionPath();
				tree.expandPath(path);

				// Dodavanje otvorenog ubacenog projekta u stablo
				((Workspace) tree.getModel().getRoot()).addProject(project);
				SwingUtilities.updateComponentTreeUI(tree);

				// Prikaz kompletnog projekta u delu prikaza

				ProjectView projectView = project.getProjectView();
				desktopPane.add(projectView);
				projectView.setVisible(true);

				// Dodavanje svih prikaza dokumenata u prikaz projekta
				for (Document doc : project.getDocuments()) {
					DocumentView docView = doc.getDocumentView();
					projectView.addDocumentView(docView);
					// Dodavanje svih prikaza stranica u prikaz svakog dokumenta
					for (Page page : doc.getPages()) {
						PageView pageView = page.getPageView();
						docView.addPageView(pageView);
						// Dodavanje svih prikaza slotova u prikaz svake
						// stranice
						for (Slot slot : page.getSlots()) {
							SlotView slotView = slot.getSlotView();
							pageView.addSlotView(slotView);
						}
					}
				}
				
				project.setProjectFile(projectFile);

			}
		}
	}
}
