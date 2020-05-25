package org.sid.mailSender;

import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.sid.entities.Client;
import org.sid.entities.Formation;
import org.sid.entities.Local;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class NotificationService {



	private JavaMailSender javaMailSender;

	@Autowired
	public NotificationService(JavaMailSender javaMailSender)  {
		this.javaMailSender=javaMailSender;
	}
	public void sendNotification(Client client, String msg) throws MailException{


		final String username = "ghikkprojet@gmail.com";
		final String password = "ghikkghikk";
		final String host= "smtp.gmail.com";
		final int port=587;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ghikkprojet@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(client.getEmail()));
			message.setSubject("Welcome to Training Management");
			message.setContent(msg,"text/html");

			Transport transport = session.getTransport("smtp");
			transport.connect (host, port,username,password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();  


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}


	public void sendNotificationIfArticleRemoved(List<String> emails,Formation formation) throws MailException{


		final String username = "ghikkprojet@gmail.com";
		final String password = "ghikkghikk";
		final String host= "smtp.gmail.com";
		final int port=587;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			for(int i=0;i<emails.size();i++) {
				String msg="<div class='container'><div style='text-align:center;'><h1 style='color:blue;'>Training Management</h1></div>"+
						"<div style='color: black;box-shadow:0 0 10px rgba(0, 0, 0, 0.5);border-radius:5px;'><h1>Hi dear customer</h1>"+
						"<p>" + 
						"The training entitled <strong>"+formation.getTitle()+"</strong> has been canceled by its owner <strong>"+formation.getUser().getNom()+" "+formation.getUser().getPrenom()+".</strong>"+
						"</p>"+
						"<p>Thank you and see you soon.</p></div></div>";
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("ghikkprojet@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(emails.get(i)));
				message.setSubject("Welcome to Training Management");
				message.setContent(msg,"text/html");

				Transport transport = session.getTransport("smtp");
				transport.connect (host, port,username,password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close(); 
			}



		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public void sendNotificationIfArticleRemoved(List<String> emails,Local local) throws MailException{


		final String username = "ghikkprojet@gmail.com";
		final String password = "ghikkghikk";
		final String host= "smtp.gmail.com";
		final int port=587;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			for(int i=0;i<emails.size();i++) {
				String msg="<div class='container'><div style='text-align:center;'><h1 style='color:blue;'>Training Management</h1></div>"+
						"<div style='color: black;box-shadow:0 0 10px rgba(0, 0, 0, 0.5);border-radius:5px;'><h1>Hi dear trainer</h1>"+
						"<p>" + 
						"The local entitled <strong>"+local.getIntitulee()+"</strong> has been canceled by its owner <strong>"+local.getOwner().getNom()+" "+local.getOwner().getPrenom()+". </strong>So please search another local for your training."+
						"</p>"+
						"<p>Thank you and see you soon.</p></div></div>";
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("ghikkprojet@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(emails.get(i)));
				message.setSubject("Welcome to Training Management");
				message.setContent(msg,"text/html");

				Transport transport = session.getTransport("smtp");
				transport.connect (host, port,username,password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close(); 
			}



		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
	public void sendNotificationIfArticleUpdated(List<String> emails, Local local) throws MailException{
		final String username = "ghikkprojet@gmail.com";
		final String password = "ghikkghikk";
		final String host= "smtp.gmail.com";
		final int port=587;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		String msg="<div class='container'><div style='text-align:center;'><h1 style='color:blue;'>Training Management</h1></div>"+
				"<div style='color: black;box-shadow:0 0 10px rgba(0, 0, 0, 0.5);border-radius:5px;'><h1>Hi dear trainer</h1>"+
				"<p>" + 
				"The local entitled <strong>"+local.getIntitulee()+"</strong> has been updated by its owner <strong>"+local.getOwner().getNom()+" "+local.getOwner().getPrenom()+". </strong>"+
				"</p>"+
				"<table>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td><strong>Name: </strong></td>"+"<td>"+local.getIntitulee()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Surface: </strong></td>"+"<td>"+local.getSuperficie()+" m²</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Price Per Hour: </strong></td>"+"<td>"+local.getPrixParHeure()+" $</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Type: </strong></td>"+"<td>"+local.getCategory()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Address: </strong></td>"+"<td>"+local.getAdresse()+", "+local.getVille()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Description: </strong></td>"+"<td>"+local.getDescription()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Other Properties: </strong></td>"+"<td>"+"Chairs: "+local.getChairs()+", Electrical Outlets: "+local.getPrises()+", Microphones: "+local.getMicro()+", Projectors: "+local.getProjecteur()+'.'+"</td>"
				+ "</tr>"
				+ "</tbody>"+

            		"</table>"+
            		"<p>Thank you and see you soon.</p></div></div>";

		try {
			for(int i=0;i<emails.size();i++) {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("ghikkprojet@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(emails.get(i)));
				message.setSubject("Welcome to Training Management");
				message.setContent(msg,"text/html");

				Transport transport = session.getTransport("smtp");
				transport.connect (host, port,username,password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close(); 
			}



		} catch (MessagingException e) {
			throw new RuntimeException(e);


		}

	}
	public void sendNotificationIfArticleUpdated(List<String> emails, Formation formation) throws MailException{
		final String username = "ghikkprojet@gmail.com";
		final String password = "ghikkghikk";
		final String host= "smtp.gmail.com";
		final int port=587;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		String msg="<div class='container'><div style='text-align:center;'><h1 style='color:blue;'>Training Management</h1></div>"+
				"<div style='color: black;box-shadow:0 0 10px rgba(0, 0, 0, 0.5);border-radius:5px;'><h1>Hi dear customer</h1>"+
				"<p>" + 
				"The training entitled <strong>"+formation.getTitle()+"</strong> has been updated by its owner <strong>"+formation.getUser().getNom()+" "+formation.getUser().getPrenom()+". </strong>"+
				"</p>"+
				"<table>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td><strong>Title:</strong> </td>"+"<td>"+formation.getTitle()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Location: </strong></td>"+"<td>"+formation.getLocal().getAdresse()+", "+formation.getLocal().getVille()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Price: </strong></td>"+"<td>"+formation.getPrix()+" $</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Category: </strong></td>"+"<td>"+formation.getArticleCat()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Requirements: </strong></td>"+"<td>"+formation.getRequirements()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Difficuty: </strong></td>"+"<td>"+formation.getDifficulty()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Description: </strong></td>"+"<td>"+formation.getDescription()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>From: </strong></td>"+"<td>"+formation.getFirstDay()+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>To: </strong></td>"+"<td>"+formation.getLastDay()+"</td>"
				+ "</tr>"
				+ "</tbody>"+

            		"</table>"+
            		"<p>Thank you and see you soon.</p></div></div>";

		try {
			for(int i=0;i<emails.size();i++) {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("ghikkprojet@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(emails.get(i)));
				message.setSubject("Welcome to Training Management");
				message.setContent(msg,"text/html");

				Transport transport = session.getTransport("smtp");
				transport.connect (host, port,username,password);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close(); 
			}



		} catch (MessagingException e) {
			throw new RuntimeException(e);


		}

	}
	public void ContactTrainer(String email, String msg) throws MailException{


		final String username = "ghikkprojet@gmail.com";
		final String password = "ghikkghikk";
		final String host= "smtp.gmail.com";
		final int port=587;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ghikkprojet@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject("Welcome to Training Management");
			message.setContent(msg,"text/html");

			Transport transport = session.getTransport("smtp");
			transport.connect (host, port,username,password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();  


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}