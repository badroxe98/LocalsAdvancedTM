
package org.sid;
import org.sid.dao.AdminRepository;
import org.sid.dao.ClientRepository;
import org.sid.dao.CommentaireRepository;
import org.sid.dao.FormationRepository;
import org.sid.dao.LocalRepository;
import org.sid.dao.RatingRepository;
import org.sid.entities.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FormationOpApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FormationOpApplication.class);
    }
	
	public static void main(String[] args) {
		
		
			
		ApplicationContext ctx =SpringApplication.run(FormationOpApplication.class, args);
		FormationRepository formationdao =ctx.getBean(FormationRepository.class);
		ClientRepository clientRepository=ctx.getBean(ClientRepository.class);
		LocalRepository localRepository=ctx.getBean(LocalRepository.class);
		CommentaireRepository commentaireRepository= ctx.getBean(CommentaireRepository.class);
		RatingRepository ratingRepository=ctx.getBean(RatingRepository.class);
		AdminRepository adminRepository =ctx.getBean(AdminRepository.class);
	}

}
