package com.graphicfilters.design;

import java.awt.Label;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Design {

	/*
	 * public boolean isNone = false; public static boolean isBlur = false;
	 * public static boolean isEdgeDetect = false; public static boolean
	 * isSharpen = false; public static boolean isEmbossing = false;
	 */

	public int i = 0;
	Text text;
	public String path = "dot.jpg";
	static Filters filter = new Filters();
	static Converter converter = new Converter();
	public Image currentImage=null;
	public String currentDir=null;
	public void run() {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.addListener(SWT.Close,new Listener(){
			public void handleEvent(Event e){
				 int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
			     MessageBox messageBox = new MessageBox(shell, style);
			     messageBox.setText("Information");
			     messageBox.setMessage("Are you sure you want to exit?");
			     e.doit = messageBox.open() == SWT.YES;
			}
		});
		shell.setText("Filters");
		createContents(shell, display);

		shell.setMaximized(true);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

		}

		display.dispose();
	}

	void createContents(Shell shell, Display display) {
		shell.setLayout(new FillLayout());
		
		Composite originalComposite = new Composite(shell, SWT.NONE);
		Composite filterComposite = new Composite(shell, SWT.NONE);
		originalComposite.setLayout(new FillLayout());
		filterComposite.setLayout(new FillLayout());
		
	
			
		createBarMenu(shell, display);
		originalImage(originalComposite, display);
		filrterImage(filterComposite, display);

	}

	public void createBarMenu(final Shell shell, final Display display) {

		// Create the bar menu
		Menu menu = new Menu(shell, SWT.BAR);

		// Create all the items in the bar menu
		MenuItem fileItem = new MenuItem(menu, SWT.CASCADE);
		fileItem.setText("File");
		MenuItem filterItem = new MenuItem(menu, SWT.CASCADE);
		filterItem.setText("Filter");
		MenuItem helpItem = new MenuItem(menu, SWT.CASCADE);
		helpItem.setText("Help");

		final Menu fileMenu = new Menu(menu);
		fileItem.setMenu(fileMenu);

		Menu filterMenu = new Menu(menu);
		filterItem.setMenu(filterMenu);

		Menu helpMenu = new Menu(menu);
		helpItem.setMenu(helpMenu);

		MenuItem openItem = new MenuItem(fileMenu, SWT.NONE);
		openItem.setText("Open...");
		MenuItem saveItem = new MenuItem(fileMenu, SWT.NONE);
		saveItem.setText("Save");
		MenuItem saveAsItem = new MenuItem(fileMenu, SWT.NONE);
		saveItem.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(currentDir==null){			
				}else{
					ImageLoader imgLoader = new ImageLoader();
					imgLoader.data=new ImageData[] {currentImage.getImageData()};
					imgLoader.save(currentDir,SWT.IMAGE_JPEG);
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});	
		saveAsItem.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(currentDir==null){		
				}else{
					FileDialog dialog = new FileDialog(shell, SWT.SAVE);
					String tempDir = dialog.open();
					ImageLoader imgLoader = new ImageLoader();
					imgLoader.data=new ImageData[] {currentImage.getImageData()};
					imgLoader.save(tempDir+".jpeg",SWT.IMAGE_JPEG);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		
		saveAsItem.setText("Save As...");
		saveAsItem.setText("Save As...");
		final MenuItem printItem = new MenuItem(fileMenu, SWT.NONE);
		printItem.setText("Print");
		final MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
		exitItem.setText("Exit");

		MenuItem noneItem = new MenuItem(filterMenu, SWT.NONE);
		noneItem.setText("None");
		MenuItem blurItem = new MenuItem(filterMenu, SWT.NONE);
		blurItem.setText("Blur");
		final MenuItem sharpenItem = new MenuItem(filterMenu, SWT.NONE);
		sharpenItem.setText("Sharpen");
		MenuItem edgeDetectItem = new MenuItem(filterMenu, SWT.NONE);
		edgeDetectItem.setText("EdgeDetect");
		MenuItem embossingItem = new MenuItem(filterMenu, SWT.NONE);
		embossingItem.setText("Embossing");

		openItem.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.NULL);
				String path = dialog.open();
				currentDir=path;
				if (path != null) {

					File file = new File(path);
					if (file.isFile())
						displayFiles(new String[] { file.toString() });
					else
						displayFiles(file.list());

				}

				display.dispose();
				run();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		embossingItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				/*
				 * isEmbossing = true; isBlur = false; isSharpen = false;
				 * isEdgeDetect = false; isNone = false;
				 */
				i = 4;
				display.dispose();
				run();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		blurItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				/*
				 * isBlur = true; isSharpen = false; isEdgeDetect = false;
				 * isEmbossing = false; isNone = false;
				 */
				i = 1;
				display.dispose();
				run();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		edgeDetectItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				/*
				 * isEdgeDetect = true; isBlur = false; isSharpen = false;
				 * isEmbossing = false; isNone = false;
				 */

				i = 3;
				display.dispose();
				run();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		sharpenItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stu
				/*
				 * isSharpen = true; isBlur = false; isEdgeDetect = false;
				 * isEmbossing = false; isNone = false;
				 */

				i = 2;
				display.dispose();
				run();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		MenuItem about = new MenuItem(helpMenu, SWT.None);
		about.setText("About");

		shell.setMenuBar(menu);

		about.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

				MessageBox messageBox = new MessageBox(shell,
						SWT.ICON_INFORMATION | SWT.OK);
				messageBox
						.setMessage("Filters Application \n"
								+ "Here you can apply various filters to your photos \n"
								+ "created by Icho, Jorkata, Linh - ELSYS™");
				messageBox.setText("About");
				int response = messageBox.open();
				if (response != SWT.OK)
					System.exit(0);

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		exitItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

				MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
						| SWT.YES | SWT.NO);
				messageBox.setMessage("Do you really want to exit?");
				messageBox.setText("Exiting Application");
				int response = messageBox.open();
				if (response == SWT.YES)
					System.exit(0);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		noneItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				/*
				 * // TODO Auto-generated method stub isBlur = false; isSharpen
				 * = false; isEdgeDetect = false; isEmbossing = false; isNone =
				 * true;
				 */

				i = 0;
				display.dispose();
				run();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void originalImage(Composite composite, final Display display) {

		// Label label = new Label(shell, SWT.BORDER);

		Canvas canvas = new Canvas(composite, SWT.None);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				Image image = null;

				try {
					/*
					 * image = new Image(display, new FileInputStream(
					 * "C:\\Users\\admin\\Desktop\\plant4.jpg"));
					 */
					image = new Image(display, new FileInputStream(path));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.gc.drawImage(image, 10, 10);
				image.dispose();
			}
		});
	}
	public void filrterImage(Composite composite, final Display display) {

		// Label label = new Label(shell, SWT.BORDER);

		Canvas canvas = new Canvas(composite, SWT.None);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
			
				/*
				 * ImageData sourceImageData = new ImageData(
				 * "C:\\Users\\admin\\Desktop\\plant4.jpg");
				 */

				ImageData sourceImageData = new ImageData(path);

				Utility ut = new Utility();

				ImageData filterImageData = sourceImageData;

				filterImageData = ut.method(i, filterImageData);

				Image filteredImage = new Image(display, filterImageData);
				currentImage=filteredImage;
				e.gc.drawImage(filteredImage, 10, 10);

			}
		});

	}
	
	public void displayFiles(String[] files) {
		for (int i = 0; files != null && i < files.length; i++) {

			path = files[i];

			path = path.replace("\\", "\\\\");
		}
	}
	
	public static void main(String[] args) {
		 Design design = new Design();
		 design.run();
		}

}
