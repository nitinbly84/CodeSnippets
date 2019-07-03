package SimpleFamilyTree;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class FamilyTreeExplorer extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6334794351426762225L;

	FamilyTree tree;
	private static String SHOW_ANCESTOR_CMD = "showAncestor";

	public FamilyTreeExplorer() {
		super(new BorderLayout());

		// Construct the panel with the toggle buttons.
		JRadioButton showDescendant = new JRadioButton("Show descendants", true);
		final JRadioButton showAncestor = new JRadioButton("Show ancestors");
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(showDescendant);
		bGroup.add(showAncestor);
		showDescendant.addActionListener(this);
		showAncestor.addActionListener(this);
		showAncestor.setActionCommand(SHOW_ANCESTOR_CMD);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(showDescendant);
		buttonPanel.add(showAncestor);

		// Construct the tree.
		tree = new FamilyTree(Generator.getGenealogyGraph());
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setPreferredSize(new Dimension(300, 500));

		// Add everything to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * Required by the ActionListener interface. Handle events on the
	 * showDescendant and showAncestore buttons.
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == SHOW_ANCESTOR_CMD) {
			tree.showAncestor(true);
		} else {
			tree.showAncestor(false);
		}
	}

	/**
	 * Create the GUI and show it.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("FamilyTreeExplorer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		FamilyTreeExplorer newContentPane = new FamilyTreeExplorer();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
