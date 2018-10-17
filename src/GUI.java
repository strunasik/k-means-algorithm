import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GUI {
	private KMeans kmeans;
	private JFrame frame;
	private JPanel panel;
	private Timer t;
	
	public GUI(){
		frame = new JFrame("k-centroid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
		
		panel = new JPanel(){
			public void paint(Graphics g) {
				super.paint(g);
				t.start();
				g.drawLine(20, 10, 20, 820);      
				g.drawLine(10, 810, 820, 810);	  				
				if(kmeans!=null){
					for(int i = 0; i < kmeans.punkty.length; i++){
						if(kmeans.punkty[i].getClasster() != -1)
							g.setColor(kmeans.centroidy[kmeans.punkty[i].getClasster()].color);
						g.fillOval(25 + (int)(8*kmeans.punkty[i].x), 5 + (int)(8*kmeans.punkty[i].y), 5, 5);
					}
					for(int i = 0; i < kmeans.centroidy.length; i++){
						g.setColor(Color.BLACK);
						g.fillOval(22 + (int)(8*kmeans.centroidy[i].x), 2 + (int)(8*kmeans.centroidy[i].y), 25, 25);
						g.setColor(kmeans.centroidy[i].color);
						g.fillOval(25 + (int)(8*kmeans.centroidy[i].x), 5 + (int)(8*kmeans.centroidy[i].y), 20, 20);
					}
					if(kmeans.calculate()) t.stop();
				}
				

					
			}	
		};
		panel.setPreferredSize(new Dimension(830,830));
		
		t = new Timer(300, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.repaint();
			}
		});
		
		frame.add(panel, BorderLayout.CENTER);
		frame.add(createSettingsPanel(), BorderLayout.SOUTH);
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	public JPanel createSettingsPanel(){
		Font font = new Font("TimesRoman", Font.PLAIN, 18);
		
		JPanel settingsPanel = new JPanel(new FlowLayout());
		JLabel punktLabel = new JLabel("Ilosc punktow");
		punktLabel.setFont(font);
		TextField punktField = new TextField();
		punktField.setFont(font);
		punktField.setPreferredSize(new Dimension(80, 30));
		
		JLabel centroidLabel = new JLabel("Ilosc centroidow");
		centroidLabel.setFont(font);
		TextField centroidField = new TextField();
		centroidField.setFont(font);
		centroidField.setPreferredSize(new Dimension(30, 30));
		
		JButton startButton = new JButton("Start");
		startButton.setFont(font);
		
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int n1 = 0, n2 = 0;
				String temp = punktField.getText();
				if(temp.equals(""))
					JOptionPane.showMessageDialog(frame, "Bledna ilosc punktow");
				else if (Integer.parseInt(temp) < 1)
					JOptionPane.showMessageDialog(frame, "Bledna ilosc punktow");
				else 
					n1 = Integer.parseInt(temp);
				temp = centroidField.getText();
				if(temp.equals(""))
					JOptionPane.showMessageDialog(frame, "Bledna ilosc centroidow");
				else if (Integer.parseInt(temp) < 1)
					JOptionPane.showMessageDialog(frame, "Bledna ilosc centroidow");
				else 
					n2 = Integer.parseInt(temp);
				kmeans = new KMeans(n1, n2);
				kmeans.calculate();
				panel.repaint();
			}
			
		});
		
		settingsPanel.add(punktLabel);
		settingsPanel.add(punktField);
		settingsPanel.add(centroidLabel);
		settingsPanel.add(centroidField);
		settingsPanel.add(startButton);
		
		return settingsPanel;
	}

}
