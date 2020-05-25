package org.sid.web;

import java.util.Date;
import java.util.List;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.sid.dao.CommentaireRepository;
import org.sid.entities.Client;
import org.sid.entities.Commentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.BeanUtils;
@Controller
public class CommentaireController {

	@Autowired
	private CommentaireRepository commentaireRepository;


	@RequestMapping(value ="/saveComment", method =RequestMethod.POST)
	public String saveComment (Model model,@RequestParam(name="commenting") String contenu,HttpServletRequest request ) {
		HttpSession session =request.getSession(true);
		Client client=(Client) session.getAttribute("user");
		Commentaire commentaire=new Commentaire();
		commentaire.setClient(null);

		if(client!=null) {
			commentaire.setClient(client);
		}


		commentaire.setContenu(contenu);
		commentaire.setDateEcriture(new java.sql.Date(new java.util.Date().getTime()));

		commentaireRepository.save(commentaire);
		return "redirect:TrainingManagement";

	}


	public List<Commentaire> findRecent(int n) {
		List<Commentaire> commentaires=commentaireRepository.findRecent(n);
		return commentaires;
	}






}
