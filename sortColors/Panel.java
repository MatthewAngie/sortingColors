package sortColors;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import sortColors.Panel;

public class Panel extends JPanel implements MouseInputListener, KeyListener, Runnable {

	public int panx;
	public int pany;
	public Divider div1;
	public Divider div2;
	public Divider div3;
	public int col1temp = 0;
	public int col2temp = 0;
	public int col3temp = 0;
	public int col4temp = 0;
	public Doodad[] mergearray;
	public int mergelength;
	public Doodad[] tempMergArray;
	col1 col1Man = new col1();
	col2 col2Man = new col2();
	col3 col3Man = new col3();
	col4 col4Man = new col4();
	public Boolean ran = false;

	/**
	 * Construct a panel with specified width, height, and background color
	 * 
	 * @param width
	 * @param height
	 * @param bgColor
	 */
	public Panel(int width, int height, Color bgColor) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);

		Thread th = new Thread(this, "Panel");
		th.start();

		// Creates dividers between columns
		div1 = new Divider(200, 0);
		div2 = new Divider(400, 0);
		div3 = new Divider(600, 0);

		// sets panx and pany to 0,0 for the start of the first column
		panx = this.getX();
		pany = this.getY();
		createDoodads();

	}

	/*
	 * draw components, draws all the col of doodads then draws dividers
	 */
	protected void paintComponent(Graphics graphicHelper) {
		super.paintComponent(graphicHelper);
		Graphics2D g = (Graphics2D) graphicHelper;

		drawDoodads(g);
		drawSorted(col3Man.Col3, g);// draws the sorted merge column, only merge due to arrayCopy not allowing swaps
									// in sort method
		div1.draw(g);
		div2.draw(g);
		div3.draw(g);

		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.PLAIN, 29));
		String heap = "1 = All Sort";
		g.drawString(heap, 10, 650);


		repaint();

		if (ran == true)// calls checkThreads after sorts have been called
		{
			checkThreads();
		}
	}

	/*
	 * method checks if all theads are done running, once all threads are done
	 * prints out times stalled and amount of time threads have been stalled for
	 * each sort
	 */
	public void checkThreads() {

		if (col1Man.getThreadGroup() == null && col2Man.getThreadGroup() == null && col3Man.getThreadGroup() == null
				&& col4Man.getThreadGroup() == null) {
			System.out.println("Heap Sort was stalled " + col1Man.stallCount + " times for a total of "
					+ col1Man.stallTime + " Nano-Seconds");
			System.out.println("Quick Sort was stalled " + col2Man.stallCount + " times for a total of "
					+ col2Man.stallTime + " Nano-Seconds");
			System.out.println("Merge Sort was stalled " + col3Man.stallCount + " times for a total of "
					+ col3Man.stallTime + " Nano-Seconds");
			System.out.println("Bubble Sort was stalled " + col4Man.stallCount + " times for a total of "
					+ col4Man.stallTime + " Nano-Seconds");

			ran = false;

		}
	}

	public void createDoodads() {
		/*
		 * Makes doodads, outer most loop(i) is which main Column they are being placed
		 * at. middle loop(j) is which row of the main Column they are being placed at
		 * inner loop(k) is which sub Column position of the current row(j) they are
		 * being placed at.
		 * 
		 */
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 30; j++) {
				for (int k = 0; k < 10; k++) {
					Color color = new Color(0, (float) Math.random(), (float) Math.random());

					/*
					 * if Statements check which main Column to place and which array to place in.
					 */
					if (i == 0) {
						Doodad myMan = new Doodad(panx, pany, color);
						col1Man.Col1[col1temp] = myMan;
						col1temp++;
					} else if (i == 1) {
						Doodad myMan = new Doodad(panx, pany, color);
						col2Man.Col2[col2temp] = myMan;
						col2temp++;
					} else if (i == 2) {
						Doodad myMan = new Doodad(panx, pany, color);
						col3Man.Col3[col3temp] = myMan;
						col3temp++;
					} else if (i == 3) {
						Doodad myMan = new Doodad(panx, pany, color);
						col4Man.Col4[col4temp] = myMan;
						col4temp++;
					}
					//System.out.println("column " + i + ", line " + j + ", sub column " + k + " - x cord is " + panx + ", y cord is " + pany);
					panx += 20;

				}
				/*
				 * once row is done being made, Shifts down a row and resets x cord depending on
				 * the col
				 */
				pany += 20;
				if (i == 0) {
					panx = 0;
				} else if (i == 1) {
					panx = 200;
				} else if (i == 2) {
					panx = 400;
				} else if (i == 3) {
					panx = 600;
				}

			}
			/*
			 * if statements to make sure cords are set before shifting main column over 
			 */
			if (i == 0) {
				panx = 200;
				pany = 0;
			} else if (i == 1) {
				panx = 400;
				pany = 0;
			} else if (i == 2) {
				panx = 600;
				pany = 0;
			}
		}
		
		//reset all variables incase this method is called again 
		col1temp = 0;
		col2temp = 0;
		col3temp = 0;
		col4temp = 0;
		panx = 0;
		pany = 0;

	}

	/*
	 * Draws all of the doodad arrays from thier classes
	 */
	public void drawDoodads(Graphics2D g) {
		for (int i = 0; i < col1Man.Col1.length; i++) {
			if (col1Man.Col1[i] != null) {
				col1Man.Col1[i].draw(g);
			}
		}
		for (int i = 0; i < col2Man.Col2.length; i++) {
			if (col2Man.Col2[i] != null) {
				col2Man.Col2[i].draw(g);
			}
		}
		for (int i = 0; i < col3Man.Col3.length; i++) {
			if (col3Man.Col3[i] != null) {
				col3Man.Col3[i].draw(g);
			}
		}
		for (int i = 0; i < col4Man.Col4.length; i++) {
			if (col4Man.Col4[i] != null) {
				col4Man.Col4[i].draw(g);
			}
		}
	}

	/*
	 * Method to redraw sorted Column. Current implementation is only for
	 * Col3(mergeSort)  due to arrayCopy in merge sort
	 */
	public void drawSorted(Doodad Col[], Graphics2D g) {
		col3temp = 0;
		panx = 400;
		pany = 0;

		for (int j = 0; j < 30; j++) {
			for (int k = 0; k < 10; k++) {
				Col[col3temp].x = panx;
				Col[col3temp].y = pany;
				Col[col3temp].draw(g);
				col3temp++;
				panx += 20;
			}

			pany += 20;
			panx = 400;
		}
	}

	/* Print array */
	static void printArray(Doodad array[]) {
		int n = array.length;
		for (int i = 0; i < n; ++i)
			System.out.print(array[i].numColor + " ");
		System.out.println();
	}

	public void stall(long nanos) {
		long start = System.nanoTime();
		while (System.nanoTime() - start < nanos) { // do nothing
		}
		return;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("Click");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyChar() == '1' || k.getKeyChar() == ' ') { // key press starts all the thread for 

			col1Man.start();
			col2Man.start();
			col3Man.start();
			col4Man.start();
			ran = true;

		}
		repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void run() {

	}

}