import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.FileDialog;
import java.io.IOException;
import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class Uploader_GUI {
	private static Text ipNumber;
	private static Text portNumber;
	static String IP = "";
	static String Port = "";
	static String path = "";
	static String name = "";
	private static Text fileName;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("File Uploader");
		
		Label lblPort = new Label(shell, SWT.NONE);
		lblPort.setText("Please Input the Port number");
		lblPort.setBounds(10, 77, 172, 14);
		
		portNumber = new Text(shell, SWT.BORDER);
		portNumber.setBounds(191, 77, 140, 19);
		
		Label lblOutputPath = new Label(shell, SWT.NONE);
		lblOutputPath.setText("Please Select File Path");
		lblOutputPath.setBounds(10, 149, 172, 14);
		
		Label lblPathDisplay = new Label(shell, SWT.BORDER);
		lblPathDisplay.setBounds(10, 176, 400, 14);
		lblPathDisplay.setText(path);
		
		Button btnSelectPath = new Button(shell, SWT.NONE);
		btnSelectPath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSelectPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Shell shell = new Shell(display);
				shell.setSize(450, 300);
				shell.setText("Path Selection");
				DirectoryDialog dialog = new DirectoryDialog(shell);
				dialog.setFilterPath("");
				dialog.open();
				path = dialog.getFilterPath();
				lblPathDisplay.setText(path);
				while(!shell.isDisposed()) {
					if (!display.readAndDispatch())
						display.sleep();
				}
				shell.open();
			}
		});
		btnSelectPath.setBounds(188, 143, 112, 27);
		btnSelectPath.setText("Select Path");
		
		Label lblFileName = new Label(shell, SWT.NONE);
		lblFileName.setText("Select Input File Name with extension");
		lblFileName.setBounds(10, 110, 172, 14);
		
		fileName = new Text(shell, SWT.BORDER);
		fileName.setBounds(191, 111, 140, 19);
		
		Button btnDownload = new Button(shell, SWT.NONE);
		btnDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Port = portNumber.getText();
				name = fileName.getText();
				path += "/";
				path += name;
				lblPathDisplay.setText(path);
				System.out.println("Port: " + Port);
				System.out.println("File Path: " + path);
				Uploader ul = new Uploader();
				try {
					ul.upload(path, Port);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDownload.setBounds(22, 230, 147, 38);
		btnDownload.setText("Upload");
				
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
