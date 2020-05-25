package org.sid.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.sid.dao.ClientRepository;
import org.sid.entities.Client;
import org.sid.entities.Commentaire;
import org.sid.entities.Formation;
import org.sid.mailSender.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ClientController {

	@Autowired
	private NotificationService notificationService;



	@Autowired
	private ClientRepository clientRepository;
	@Value("${dir.userimages}")
	private String imageDir;

	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String logout(Model model,HttpSession session) {
		session.invalidate();

		return "redirect:/TrainingManagement";
	}


	@RequestMapping(value="/about",method=RequestMethod.GET)
	public String about(Model model) {

		return "about-us";
	}

	@RequestMapping(value="/contact-us",method=RequestMethod.GET)
	public String contact(Model model,HttpSession session) {

		return "contact-us";
	}

	@RequestMapping(value="/Register",method=RequestMethod.GET)
	public String formProduit(Model model) {
		model.addAttribute("client",new Client());
		return "register";
	}
	@RequestMapping(value="/Registration",method=RequestMethod.POST)
	public String save(Model model ,Client client,@RequestParam(name="photo") MultipartFile picture,HttpSession session,HttpServletRequest request) throws IllegalStateException, IOException {
		client.setPicture(picture.getOriginalFilename());
		String message="<div class='container'><div style='text-align:center;'><h1 style='color:blue;'>Training Management</h1></div>"+
				"<div style='color: black;box-shadow:0 0 10px rgba(0, 0, 0, 0.5);border-radius:5px;'><h1>Welcome "+client.getNom()+" "+client.getPrenom()+"</h1>"+
				"<p>" + 
				"	    Thank you for joining <strong>Training Management</strong> community. "+
				"We are glad to have you. Thanks to our services, you can find the best places and oppotunities to learn " + 
				"everyday in local communities near you."+
				"</p>"+
				"</div></div>";
		try {
			notificationService.sendNotification(client,message);
		} catch (Exception e) {

		}


		clientRepository.save(client);
		if(!(picture.isEmpty())) {
			client.setPicture(picture.getOriginalFilename());
			picture.transferTo(new File(imageDir+client.getId()));
		}
		session=request.getSession(true);

		session.setAttribute("user", client);



		return "redirect:login";
	}
	@RequestMapping(value="getUserPhoto",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getUserPhoto(Long id) throws FileNotFoundException, IOException {
		File f=new File(imageDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model) {
		String error=null;
		model.addAttribute("error",error);
		model.addAttribute("client",new Client());
		return "login";
	}

	@RequestMapping(value="/Authentification",method=RequestMethod.POST)
	public String authentification(Model model  ,@RequestParam("email") String email, @RequestParam("password")  String password,HttpSession session,HttpServletRequest request) {

		Client client = clientRepository.findByEmail(email);
		String error=null;
		if(client==null) {
			return "register";
		}
		if(client.getPassword().equals(password)) {
			session=request.getSession(true);
			session.setAttribute("user", client);
		}
		else {


			error="Invalid Information !";
			model.addAttribute("error",error);
			return "login";

		}
		model.addAttribute("error",error);
		return "redirect:TrainingManagement";
	}
	@RequestMapping(value="/editTrainerProfil",method=RequestMethod.GET)
	public String editTrainerProfil(Model model,HttpServletRequest request,Long id) {
		HttpSession session=request.getSession(true);
		Client client=(Client) session.getAttribute("user");

		Client trainer=clientRepository.getOne(id);
		model.addAttribute("trainer", trainer);
		model.addAttribute("session", session.getAttribute("user"));



		return "trainer-profile";
	}




	@RequestMapping(value="updatePersonnaalInfo")
	public String updatePersonnaalInfo(@RequestParam("firstName") String prenom,@RequestParam("lastName") String nom,
			@RequestParam("job") String job,@RequestParam("photo") MultipartFile file,
			@RequestParam("adresse") String adresse,@RequestParam("new-email") String email
			,@RequestParam("new-password") String password,HttpServletRequest request) throws IOException {
		HttpSession session=request.getSession(true);
		Client client=(Client) session.getAttribute("user");
		Client cli=clientRepository.getOne(client.getId());
		cli.setNom(prenom);
		cli.setPrenom(nom);
		cli.setAddress(adresse);
		cli.setJob(job);
		client.setNom(prenom);
		client.setPrenom(nom);
		client.setAddress(adresse);
		client.setJob(job);

		if(email.isEmpty()) {
			cli.setEmail(client.getEmail());
		}
		else {
			client.setEmail(email);
			cli.setEmail(email);
		}

		if(password.isEmpty()) {
			cli.setPassword(client.getPassword());
		}
		else {
			cli.setPassword(password);
			client.setPassword(password);
		}

		if(!(file.isEmpty())) {
			cli.setPicture(file.getOriginalFilename());
			File f=new File(imageDir+cli.getId());
			if(f.exists()) {
				byte[] bytes=file.getBytes();
				Path path=Paths.get(imageDir+cli.getId());
				Files.write(path, bytes);		
			}
			else 
			{

				file.transferTo(new File(imageDir+cli.getId()));
			}

		}

		clientRepository.save(cli);

		return "redirect:editUserProfile";

	}


	@RequestMapping(value="/ContactTrainer",method=RequestMethod.POST)
	public String ContactTrainer(Model model,HttpServletRequest request,@RequestParam(name="articleid") String articleid,@RequestParam(name="name",defaultValue = "Unkown") String name,@RequestParam(name="emailTrainer") String emailTrainer,@RequestParam(name="email",defaultValue = "Unkown") String email,@RequestParam("message") String message) {
		String msg="<div class='container'><div style='text-align:center;'><h1 style='color:blue;'>Training Management</h1></div>"+
				"<div style='color: black;box-shadow:0 0 10px rgba(0, 0, 0, 0.5);border-radius:5px;'><h1>Hi dear trainer</h1>"+
				"<p>" + 
				"You can find below the message sent by one of our Customers:"+
				"</p>"+
				"<table>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td><strong>Name:</strong> </td>"+"<td>"+name+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Email: </strong></td>"+email+"</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td><strong>Message: </strong></td>"+"<td>"+message+"</td>"
				+ "</tr>"

	            		+ "</tbody>"+

	            		"</table>"+
	            		"<p>Thank you and see you soon.</p></div></div>";


		try {
			notificationService.ContactTrainer(emailTrainer,msg);
		} catch (Exception e) {

		}

		return "redirect:/viewArticle?id="+articleid;
	}



}

