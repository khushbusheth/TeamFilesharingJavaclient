package javaimagesharing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;


public class Confirmation extends JFrame implements ActionListener 
{
		
	JLabel l4,l5;	
	JButton upload,download,Logout;
	String email;
	JFileChooser openFile;
	int returnval;
	String filename,filename2;
	String line1;
	JComboBox<String> cb1;
	String[] str =new String[100];
	int i=0;
	Confirmation(String line,String s1) throws ClientProtocolException, IOException
	{      
		 try
		 {
		    email =s1;
			setVisible(true);
			setSize(1000,1000);
			setLayout(null);
			getContentPane().setBackground(Color.GRAY);
			setTitle("Upload/Download Your Images");
			HttpClient client=new DefaultHttpClient();
			//HttpPost post1=new HttpPost("http://group9sjsucom.fatcow.com/CMPE207/list.php");
			HttpPost post1=new HttpPost("http://www.khushbusheth.com/project/list.php");
			ArrayList params = new ArrayList(1);
			params.add(new BasicNameValuePair("emailid", s1));
			post1.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse statuscode= client.execute(post1);
			HttpEntity entity=statuscode.getEntity();
			if(statuscode.getStatusLine().toString().equals("HTTP/1.1 200 OK"))
			{
				System.out.println("**************************");
				InputStream i1= entity.getContent();
				BufferedReader r1=new BufferedReader(new InputStreamReader(i1));
				while((line1=r1.readLine())!=null)
				{
					if(!(line1.equals(".")||line1.equals("..")))
					{	
						//System.out.println(line1);
				       str[i]=line1;
					}
				  i++;
				  //System.out.println(line1);
				}
			}
			l4 = new JLabel(line);
			l4.setForeground(Color.pink);
			l4.setFont(new Font("Serif", Font.BOLD,24));
			l4.setFont(new Font("Serif",Font.ITALIC, 24));
			
			upload=new JButton("UPLOAD");
			download=new JButton("DOWNLOAD");
			//upload.setBackground(Color.green);
			Logout=new JButton("Logout");
			cb1=new JComboBox<String>(str);
			upload.addActionListener(this);
			download.addActionListener(this);
			Logout.addActionListener(this);
			
			l4.setBounds(250, 30, 400, 30);
			upload.setBounds(80, 70, 200, 30);
			download.setBounds(80, 120, 200, 30);
			Logout.setBounds(900,70,80,30);
			cb1.setVisible(true);
			cb1.setBounds(300,120,200,30);
			
	        						
	         add(l4);
	         add(upload);
	         add(download);
	         add(cb1);
	         add(Logout);
	         client.getConnectionManager().shutdown();
		 }
		 catch(Exception E)
		 {
			 System.out.println("Exception Caught"+E);
		 }
	 }
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==Logout)
		 {
		  this.setVisible(false);
		  Login l= new Login();
		  l.setVisible(true);
		 }
	
			 if(e.getSource() == upload)
			 {
				 try
				 {
					JFileChooser chooser = new JFileChooser();
				    FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG GIF & PNG Images", "jpg", "gif", "png");
					chooser.setFileFilter(filter);
					 int returnVal = chooser.showOpenDialog(getParent());
					 if(returnVal == JFileChooser.APPROVE_OPTION) 
					 {
						 File selectedFile = chooser.getSelectedFile();
						 System.out.println("Selected file: " + selectedFile.getAbsolutePath());
						 filename = selectedFile.getAbsolutePath() ;
					 } 
					// filename = "lab8_data.txt";
					 File file = new File(filename); 
					 ContentBody contentPart = new FileBody(file); 
					 MultipartEntityBuilder mentity = MultipartEntityBuilder.create(); 
					 mentity.addPart("userfile", contentPart); 
					 System.out.println(email);
					 String response = uploadimage("http://www.khushbusheth.com/project/upload.php?Emailid="+email, mentity);
					 System.out.println(response);
					 if(response.contains("Success")) 
					{
						 JOptionPane.showMessageDialog(this,"Image is success fully uploaded.");		   
					} 	
				 }   
				 catch(Exception E)
				 {
					 System.out.println("Exception"+E);
				 } 
			 }
			 
			 if(e.getSource()==download)
			 {
				 
			 String line2;
			 String s=cb1.getSelectedItem().toString();
				 if(s!=null)
				 {
					 try
					 {
						
						    HttpClient client=new DefaultHttpClient();
							//HttpPost post1=new HttpPost("http://group9sjsucom.fatcow.com/CMPE207/download.php");
						    HttpPost post1=new HttpPost("http://www.khushbusheth.com/project/download.php");
							ArrayList params = new ArrayList(2);
							params.add(new BasicNameValuePair("emailid", email));
						    params.add(new BasicNameValuePair("filenamed", s));	
							post1.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
							HttpResponse statuscode= client.execute(post1);
							HttpEntity entity=statuscode.getEntity();
							if(statuscode.getStatusLine().toString().equals("HTTP/1.1 200 OK"))
							{
								InputStream i1= entity.getContent();
								BufferedReader r1=new BufferedReader(new InputStreamReader(i1));
									
					             line2=r1.readLine();
				
								System.out.println(line2);
								client.getConnectionManager().shutdown();
								JFileChooser fc = new JFileChooser();
								int returnval=fc.showSaveDialog(getParent());
								 if(returnval == JFileChooser.APPROVE_OPTION) 
								 {
									 File selectedFile = fc.getSelectedFile();
									 System.out.println("Selected file: " + selectedFile.getAbsolutePath());
									 filename2 = selectedFile.getAbsolutePath() ;
								 } 
								URL url1= new URL(line2);
								
							
								InputStream is=new BufferedInputStream(url1.openStream());
							    OutputStream os=new FileOutputStream(filename2);
								
                                         String inputLine;
                                         int length;
                                         byte[]b =new byte[2048];
								        //while ((inputLine = in.readLine()) != null)
                                         while ((length = is.read(b)) != -1) 
								        {
                                        	 os.write(b, 0, length);
								        	
								        }
                                        JOptionPane.showMessageDialog(this,s+" is successfully Downloaded" + " at location"+filename2 );
								//byte[] b = new byte[2048];
								//int length;
							 
								//while ((length = is.read(b)) != -1) {
									//os.write;
								//}
							 
							is.close();
							os.close();
							//os.close();
								
					 }
							
							
					 }
					 catch(Exception E1)
					 {
						 System.out.println("Exception Caught"+E1);
					 }
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(this,"Please make a Selection!!");
				 }
				
					
			 }
	}
	private static String uploadimage(String urlString, MultipartEntityBuilder mentity) { 
	    try { 
	        URL url = new URL(urlString); 
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
	        /* first set method to post */ 
	        conn.setRequestMethod("GET"); 
	        /* Set connection parameters. 
	         * Many of these are not strictly required, but let them be... 
	         */ 
	        conn.setConnectTimeout(20000);   /* 5 seconds */ 
	        conn.setUseCaches(false);       /* Do not use caches for this one */ 
	        conn.setDoInput(true);          /* make it writable */ 
	        conn.setDoOutput(true);         /* amke it readable as well */ 
	        conn.setReadTimeout(20000);      /* Read time out is 3 sec */ 
	        /* Add content length propoerty*/ 
	        HttpEntity Hentity = mentity.build();
	        conn.addRequestProperty("Content-length", Hentity.getContentLength()+""); 
	        /* set keep alive */ 
	        conn.setRequestProperty("Connection", "Keep-Alive"); 
	        /* Set type. to make the file generic, get the mime type from mentity */ 
	        conn.addRequestProperty(Hentity.getContentType().getName(), Hentity.getContentType().getValue()); 
	        /* Get output stream and write to it the file contents */ 
	        OutputStream os = conn.getOutputStream(); 
	        Hentity.writeTo(conn.getOutputStream()); 
	 
	        /* Close connections */ 
	        os.close(); 
	        conn.connect(); 
	 
	        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
	        { 
	            return readStream(conn.getInputStream()); 
	        } 
	 
	    } 
	    catch (Exception e) 
	    { 
	        System.out.println("multipart post error " + e + "(" + urlString + ")"); 
	    } 
	    return null; 
	}
	    private static String readStream(InputStream in) 
	    { 
	        BufferedReader reader = null; 
	        StringBuilder builder = new StringBuilder(); 
	        try 
	        { 
	            reader = new BufferedReader(new InputStreamReader(in)); 
	            String line = ""; 
	            while ((line = reader.readLine()) != null) 
	            { 
	                builder.append(line); 
	            } 
	        } 
	        catch (IOException e) 
	        { 
	            e.printStackTrace(); 
	        } 
	        finally 
	        { 
	            if (reader != null) 
	            { 
	                try { 
	                    reader.close(); 
	                } 
	                catch (IOException e) { 
	                    e.printStackTrace(); 
	                } 
	            } 
	        } 
	        return builder.toString(); 
	    } 
} 	
	
