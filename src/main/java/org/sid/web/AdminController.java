package org.sid.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.sid.dao.AdminRepository;
import org.sid.dao.ClientRepository;
import org.sid.dao.FormationRepository;
import org.sid.dao.LocalRepository;
import org.sid.entities.Admin;
import org.sid.entities.Client;
import org.sid.entities.Commentaire;
import org.sid.entities.Formation;
import org.sid.entities.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private FormationRepository formationRepository;
	@Autowired
	private LocalRepository localRepository;

	@Value("${dir.userimages}")
	private String imageDir;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String admin(Model model,HttpServletRequest request) {

		HttpSession session=request.getSession(true);

		long countUsers=adminRepository.countUsers();

		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("2020", 100);
		surveyMap.put("2021", 350);
		surveyMap.put("2022", 400);
		surveyMap.put("2023", 550);
		surveyMap.put("2024", 600);

		model.addAttribute("surveyMap", surveyMap);


		model.addAttribute("onestar", (adminRepository.countByStarRatings(1)/adminRepository.countUsersFromRating())*100);
		model.addAttribute("twostars", (adminRepository.countByStarRatings(2)/adminRepository.countUsersFromRating())*100);
		model.addAttribute("treestars", (adminRepository.countByStarRatings(3)/adminRepository.countUsersFromRating())*100);
		model.addAttribute("fourstars", (adminRepository.countByStarRatings(4)/adminRepository.countUsersFromRating())*100);
		model.addAttribute("fivestars", (adminRepository.countByStarRatings(5)/adminRepository.countUsersFromRating())*100);

		model.addAttribute("countusers", countUsers);

		List<Client> customers=clientRepository.findByType("Customer");
		model.addAttribute("customers", customers);

		List<Client> trainers=clientRepository.findByType("Trainer");
		model.addAttribute("trainers", trainers);

		List<Client> providers=clientRepository.findByType("Local Provider");
		model.addAttribute("providers", providers);
		
		List<Formation> formations=formationRepository.findByTodaysDate();
		model.addAttribute("trainings", formations);
		
		List<Local> locals=localRepository.findByTodaysDate();
		model.addAttribute("locals", locals);
		
		model.addAttribute("session", session.getAttribute("user"));

		return "admin";		
	}

	@RequestMapping(value="/Adminlogin", method = RequestMethod.POST)
	public String login(Model model,HttpServletRequest request,@RequestParam("email") String email, @RequestParam("password")  String password) {

		HttpSession session=request.getSession(true);
		String error=null;
		Admin Admin = adminRepository.findByEmail(email);

		if(Admin==null) {
			error="Invalid Information ! Check your email.";
			model.addAttribute("error",error);
			return "admin";
		}
		else {
			if(Admin.getPassword().equals(password)) {
				session=request.getSession(true);
				session.setAttribute("user", Admin);
			}
			else{
				error="Invalid Information ! Check your password.";
				model.addAttribute("error",error);
				return "admin";
			}
		}


		model.addAttribute("error",error);
		model.addAttribute("session", session.getAttribute("user"));

		return "redirect:/";		
	}


	@RequestMapping(value="getPicture",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPicture(Long id) throws FileNotFoundException, IOException {
		File f=new File(imageDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(Model model,HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}

}
