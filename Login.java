package javaimagesharing;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
//import org.apache.commons.httpclient.methods.PostMethod;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.*;
import java.sql.*;
import java.util.ArrayList;

public class Login extends JFrame implements ActionListener 
{ 
	@SuppressWarnings("deprecation")
	JLabel l1, l2, l3;
	JTextField tf1;
	JButton btn1, btn2,button1,button2;
	JPasswordField p1;
	JFrame j1;
	String s1,s3;

	Login()
	{
		setVisible(true);
		setSize(700, 700);
		setLayout(null);
		getContentPane().setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("WELCOME !!! TO GROUP 9 Image Uploading");

		l1 = new JLabel("Please login ");
		l1.setForeground(Color.pink);
		l1.setFont(new Font("Serif", Font.BOLD,24));
		l1.setFont(new Font("Serif",Font.ITALIC, 24));
		l2 = new JLabel("Userid");
		l2.setFont(new Font("Calibri",Font.BOLD,14));

		l3 = new JLabel("Password");
		l2.setForeground(Color.GREEN);
		l3.setFont(new Font("Calibri",Font.BOLD,14));
		l3.setForeground(Color.GREEN);

		tf1 = new JTextField();
		p1 = new JPasswordField();
		btn1 = new JButton("Login");
		btn2 = new JButton("New User");
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		l1.setBounds(80, 30, 400, 30);
		l2.setBounds(80, 70, 200, 30);
		l3.setBounds(80, 150, 200, 30);
		tf1.setBounds(200, 70, 200, 30);
		p1.setBounds(200, 150, 200, 30);
		btn1.setBounds(100, 250, 100, 30);
		btn2.setBounds(230, 250, 100, 30);
		add(l1);
		add(tf1);
		add(l2);
		add(l3);
		add(p1);
		add(btn1);
		add(btn2);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == btn1)
		{
			try
			{
				 s1= tf1.getText();
				 s3=p1.getText();


				 String line="";
				 HttpClient client=new DefaultHttpClient();
				 HttpPost post1=new HttpPost("http://khushbusheth.com/project/test.php");
				 //HttpPost post1=new HttpPost("http://localhost/207/test.php");
				 ArrayList params = new ArrayList(2);
				 params.add(new BasicNameValuePair("userid", s1));
				 params.add(new BasicNameValuePair("password", s3));
				 post1.setEntity(new UrlEncodedFormEntity(params, "UTF-8")); 
				 HttpResponse statuscode= client.execute(post1);
				 System.out.println(statuscode);
				 HttpEntity entity=statuscode.getEntity();
				 if(statuscode.getStatusLine().toString().equals("HTTP/1.1 200 OK"))
				 {
					System.out.println("**************************");

					InputStream i1= entity.getContent();
					BufferedReader r1=new BufferedReader(new InputStreamReader(i1));
					while((line=r1.readLine())!=null)
					{
						if(line.contains("ERROR"))
						{
							//returnval=1;
							JOptionPane.showMessageDialog(this,line,"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
						}
						 /*else
						{
							JOptionPane.showMessageDialog(this,line);
						} */
						if(line.contains("WELCOME")) 
						{
						       setVisible(false);       
								Confirmation c= new Confirmation(line,s1);		   
					    } 				               
					 }
				 }
			}	
			catch(Exception E)
			{
			}
		}
		if(e.getSource()==btn2)
		{
			this.setVisible(false);
			new Register();
		}
	}
	public static void main(String args[])
	{
		new Login();
	}
}
