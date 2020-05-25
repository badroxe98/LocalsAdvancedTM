package org.sid.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.sid.dao.FormationRepository;
import org.sid.dao.RatingRepository;
import org.sid.entities.Client;
import org.sid.entities.Formation;
import org.sid.entities.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RatingController {
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private FormationRepository formationRepository;

	@RequestMapping(value="sendRating")
	public String sendRating(Model model,Rating rating,@RequestParam("trainingRate") Long training, @RequestParam(name="rating",defaultValue = "0") int countStar, HttpServletRequest request) {
		HttpSession session=request.getSession(true);

		if(session.getAttribute("user")==null) { return "redirect:/login";}
		else {
			Client client=(Client) session.getAttribute("user");
			Formation formation=formationRepository.getOne(training);
			rating.setClient(client);
			rating.setRatingFormation(formation);
			rating.setCountStars(countStar);
			Rating r =ratingRepository.findRatingByUserAndTraining(training,client.getId());
			if(r!=null) {
				rating.setId(r.getId());
				ratingRepository.save(rating);
			}
			ratingRepository.save(rating);
			return "redirect:viewArticle?id="+training;
		}



	}
}
