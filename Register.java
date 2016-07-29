package javaimagesharing;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Register extends JFrame implements ActionListener {
	@SuppressWarnings("deprecation")
	JButton button1,button2;
	JPasswordField p1;
	JFrame j1;
	JLabel f1,l1,g1,pass1,pass2,e1;
	JTextField tex1,tex2,tex3;
	JPasswordField sec1,sec2;
	ButtonGroup group = new ButtonGroup();
	JRadioButton rd1,rd2;
	JLabel regi  = new JLabel("Registration For New User ");
	
Register()
{
	//System.out.println("HIIIII");
		j1 =new JFrame();
		j1.setVisible(true);
		j1.setSize(700, 700);
		j1.setLayout(null);
		j1.setDefaultCloseOperation(j1.EXIT_ON_CLOSE);
		j1.getContentPane().setBackground(Color.GRAY);
		j1.setTitle("NEW USER REGISTRATION");
		regi.setForeground(Color.pink);
		regi.setFont(new Font("Serif", Font.BOLD,24));
		f1 = new JLabel("First Name");
		l1 = new JLabel("Last Name");
		g1 = new JLabel("Gender");
		pass1 = new JLabel("Create Password:");
		pass2 = new JLabel("Confirm Password:");
		e1 = new JLabel("Email id"); 
		tex1 = new JTextField();
		tex2 = new JTextField();
		tex3 = new JTextField();
		
		f1.setForeground(Color.green);
		l1.setForeground(Color.green);
		g1.setForeground(Color.green);
		pass1.setForeground(Color.green);
		pass2.setForeground(Color.green);
		e1.setForeground(Color.green);

		sec1 = new JPasswordField();
		sec2 = new JPasswordField();
	    rd1=new JRadioButton("Male",true);
	    rd2=new JRadioButton("female",false);
		group.add(rd1);
		group.add(rd2);
		rd1.setBackground(Color.GRAY);
		rd2.setBackground(Color.GRAY);
		
		button1=new JButton("Register");
		button2=new JButton("Back");
		button1.addActionListener(this);
		button2.addActionListener(this);
		
		regi.setBounds(80, 10,400, 50);
		f1.setBounds(80, 60, 400, 30);
		l1.setBounds(80, 100, 200, 30);
		g1.setBounds(80, 170, 200, 30);
		e1.setBounds(80,230, 200, 30);
		pass1.setBounds(80, 280, 200, 30);
		pass2.setBounds(80, 330, 200, 30);
		
		tex1.setBounds(300, 60, 200, 30);
		tex2.setBounds(300, 100, 200, 30);
		rd1.setBounds(300,130,100,100);
		rd2.setBounds(400,130,100,100);
		tex3.setBounds(300,230, 200, 30);
		sec1.setBounds(300,280, 200, 30);
		sec2.setBounds(300,330, 200, 30);
		button1.setBounds(150, 400, 100, 30);
		button2.setBounds(270, 400, 100, 30);
		j1.add(regi);
		j1.add(f1);
		j1.add(tex1);
		j1.add(l1);
		j1.add(tex2);
		j1.add(g1);;
		j1.add(rd1);
		j1.add(rd2);
		j1.add(e1);
		j1.add(tex3);
		j1.add(pass1);
		j1.add(sec1);
		j1.add(pass2);
		j1.add(sec2);
		j1.add(button1);
		j1.add(button2);		
		
}

public void actionPerformed(ActionEvent e) 	
{	
	if(e.getSource()==button2)
	{
		j1.setVisible(false);
		new Login();
	}
	if (e.getSource() == button1)
	{
		
		try
		{
			String fname,lname,email,pass1,pass2,gen ;
			fname = tex1.getText();
			lname = tex2.getText();
			email = tex3.getText();
			pass1 = sec1.getText();
			pass2 = sec2.getText();
			if(rd1.isSelected())
		    {
		        gen = rd1.getText() ;
		    }
		    else if(rd2.isSelected())
		    {
		        gen = rd2.getText();
		    }
		    else
		    {
		    	gen = null;
		    }
			//System.out.println(gen);
			if(fname.equals(""))
			{
				//JOptionPane.showMessageDialog(this,"Please enter your First Name");
				JOptionPane.showMessageDialog(this,"Please enter your First Name","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else if(lname.equals(""))
			{
				//JOptionPane.showMessageDialog(this,"Please enter your Last Name");
				JOptionPane.showMessageDialog(this,"Please enter your Last Name","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else if(email.equals(""))
			{
				//JOptionPane.showMessageDialog(this,"Please enter your Email");
				JOptionPane.showMessageDialog(this,"Please enter your Email","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else if(pass1.equals(""))
			{
				//JOptionPane.showMessageDialog(this,"Please enter your Password");
				JOptionPane.showMessageDialog(this,"Please Enter your password","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else if(pass1.length()<8)
			{
				JOptionPane.showMessageDialog(this,"Password should be minimum of 8 characters","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
			
				
			}
			
			else  if(pass2.equals(""))
			{
				//JOptionPane.showMessageDialog(this,"Please confirm your Password");
				JOptionPane.showMessageDialog(this,"Please confirm your Password","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else if (!pass1.equals(pass2))
			{
				
				JOptionPane.showMessageDialog(this,"Passwords do not Match","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
				String line="";
				HttpClient client=new DefaultHttpClient();

				
				ArrayList params = new ArrayList(5);
				
				params.add(new BasicNameValuePair("fname", fname));
				params.add(new BasicNameValuePair("lname", lname));
				params.add(new BasicNameValuePair("email", email));
				params.add(new BasicNameValuePair("gender",gen));
				params.add(new BasicNameValuePair("pass", pass1));
				HttpPost post1=new HttpPost("http://khushbusheth.com/project/register.php");
				
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
							JOptionPane.showMessageDialog(this,line,"ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
						}
						if(line.contains("success"))
					
						{
							//System.out.println(line);
						       j1.setVisible(false);       	
							 Confirmation c= new Confirmation( "Welcome!!!"+fname , email);		   
					    } 	
						/*else  
						{
							JOptionPane.showMessageDialog(this,line);
						}*/
					}
				}
		}
		}
		catch(Exception E)
		{
		}
	}
}
}