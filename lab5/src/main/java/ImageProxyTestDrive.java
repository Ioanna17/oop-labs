import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageProxyTestDrive {
	ImageComponent imageComponent;
	JFrame frame = new JFrame("Album Cover Viewer");
	JMenuBar menuBar = new JMenuBar();
	List<ImageProxy> mediaList = new ArrayList<>();
	MediaIterator mediaIterator;

	public static void main(String[] args) throws Exception {
		ImageProxyTestDrive testDrive = new ImageProxyTestDrive();
	}

	public ImageProxyTestDrive() throws Exception {
		mediaList.add(new ImageProxy(new URL( "https://upload.wikimedia.org/wikipedia/vi/b/b7/NirvanaNevermindalbumcover.jpg"), "Nevermind"));
		mediaList.add(new ImageProxy(new URL( "https://upload.wikimedia.org/wikipedia/en/5/56/Black-Sabbath-Never-Say-Die.jpg"), "Never Say Die!"));
		mediaList.add(new ImageProxy(new URL( "https://upload.wikimedia.org/wikipedia/en/thumb/3/36/Never_gone.jpg/220px-Never_gone.jpg"), "Never Gone"));
		mediaList.add(new ImageProxy(new URL( "https://upload.wikimedia.org/wikipedia/en/0/0b/Brandy_never_say_never.jpg"), "Never Say Never"));
		mediaList.add(new ImageProxy(new URL( "https://upload.wikimedia.org/wikipedia/en/2/2f/Iloveyou_the_neighbourhood.jpeg"), "i love you."));
		mediaList.add(new ImageProxy(new URL("https://upload.wikimedia.org/wikipedia/en/a/a5/Cover_art_for_Indigo_by_RM.jpg"), "indigo"));
		mediaList.add(new ImageProxy(new URL( "https://upload.wikimedia.org/wikipedia/en/1/15/Msi_if_cover.jpg"), "if"));

		mediaIterator = new MediaIterator(mediaList);

		frame.setJMenuBar(menuBar);


		JTextField filterField = new JTextField(20);
		JButton filterButton = new JButton("Filter");
		filterButton.addActionListener(event -> {
			mediaIterator.resetIterator();
			mediaIterator.setFilterKeyword(filterField.getText());
			showNextImage();
		});

		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(event->{
			showNextImage();
		});

		JButton resetButton =  new JButton("Reset");
		resetButton.addActionListener(event-> {
			mediaIterator.resetIterator();
			filterField.setText("");
			showNextImage();
		});

		JPanel filterPanel = new JPanel();
		filterPanel.add(filterField);
		filterPanel.add(filterButton);
		filterPanel.add(nextButton);
		filterPanel.add(resetButton);
		frame.getContentPane().add(filterPanel, BorderLayout.NORTH);

		Icon icon = mediaIterator.next();
		imageComponent = new ImageComponent(icon);
		frame.getContentPane().add(imageComponent, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

	void showNextImage() {
		if (mediaIterator.hasNext()) {
			imageComponent.setIcon(mediaIterator.next());
			frame.repaint();
		} else {
			JOptionPane.showMessageDialog(frame, "No more images match the filter.");
		}
	}
}
