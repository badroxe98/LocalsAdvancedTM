package org.sid.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.sid.dao.FormationRepository;
import org.sid.dao.LocalRepository;
import org.sid.entities.Client;
import org.sid.entities.Formation;
import org.sid.entities.Local;
import org.sid.mailSender.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class LocalController {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private FormationRepository formationRepository;
	
	private String picture1;
	private String picture2;
	private String picture3;
	private String picture4;
	private String picture5;
	private String picture6;
	@Autowired
	private LocalRepository localRepository;
	@Value("${dir.Localimages}")
	private String localImageDir;
	

	
	@RequestMapping(value="/AddLocal", method = RequestMethod.GET)
	public String formulaireLocal(Model model,HttpServletRequest request) {
		HttpSession session=request.getSession(true);
		if(session.getAttribute("user")==null) {
			return "login";
		}
		model.addAttribute("local",new Local());
		return "Local-Listing";		
	}
	
	@RequestMapping(value="/saveLocal", method =RequestMethod.POST)
	public String saveLocal(Model model,@Valid Local local,
			@RequestParam("category") String category,
			@RequestParam("ville") String city,
			@RequestParam("disponibiliteFrom") java.sql.Date dateFrom,
			@RequestParam(name="photo1") MultipartFile file1,
			@RequestParam(name="photo2") MultipartFile file2,
			@RequestParam(name="photo3") MultipartFile file3,
			@RequestParam(name="photo4") MultipartFile file4,
			@RequestParam(name="photo5") MultipartFile file5,
			@RequestParam(name="photo6") MultipartFile file6, 
			HttpServletRequest request) throws IllegalStateException, IOException {
		
		HttpSession session=request.getSession(true);
		local.setDisponibiliteFrom(dateFrom);
		local.setCategory(category);
		local.setVille(city);
		local.setOwner((Client) session.getAttribute("user"));
		Client client= (Client) session.getAttribute("user");
		
		String message="<div class='container'><div style='text-align:center;'><h1 style='color:blue;'>Training Management</h1></div>"+
	            "<div style='color: black;box-shadow:0 0 10px rgba(0, 0, 0, 0.5);border-radius:5px;'><h1>Hi "+client.getNom()+" "+client.getPrenom()+"</h1>"+
        		"<p>" + 
        		"	    Thank you for adding a new local. We will make sure your local article appears to the maximum of participants."+
        		"</p>"+
        		"<p>See you soon.</p></div></div>";;
		
		
		local.setPicture1(file1.getOriginalFilename());
		local.setPicture2(file2.getOriginalFilename());
	    local.setPicture3(file3.getOriginalFilename());
	    local.setPicture4(file4.getOriginalFilename());
	    local.setPicture5(file5.getOriginalFilename());
		local.setPicture6(file6.getOriginalFilename());
	
		localRepository.save(local);
	
		local.setPicture1(file1.getOriginalFilename());
		file1.transferTo(new File(localImageDir+local.getId()+"_1"));

		
		local.setPicture2(file2.getOriginalFilename());
		file2.transferTo(new File(localImageDir+local.getId()+"_2"));
		
	
		local.setPicture3(file3.getOriginalFilename());
		file3.transferTo(new File(localImageDir+local.getId()+"_3"));
		

		local.setPicture4(file4.getOriginalFilename());
		file4.transferTo(new File(localImageDir+local.getId()+"_4"));
		

		local.setPicture5(file5.getOriginalFilename());
		file5.transferTo(new File(localImageDir+local.getId()+"_5"));

		local.setPicture6(file6.getOriginalFilename());
		file6.transferTo(new File(localImageDir+local.getId()+"_6"));
		
		
		try {
			notificationService.sendNotification(client,message);
		} catch (Exception e) {
			
		}
	
		return "redirect:EditAds";	
	}
	
	@RequestMapping(value="/editlocal", method =RequestMethod.GET)
	public String editlocal(Model model, Long id) throws IllegalStateException, IOException {
		Local local =localRepository.getOne(id);
		model.addAttribute("local",local );
		picture1=local.getPicture1();
		picture2=local.getPicture2();
		
		picture3=local.getPicture3();
		picture4=local.getPicture4();
		picture5=local.getPicture5();
		picture6=local.getPicture6();
		return "Update-Local";
	}
	
	@RequestMapping(value="/updateLocal", method =RequestMethod.POST)
	public String updateLocal(Model model, Local local,
			@RequestParam("category") String category,
			@RequestParam("ville") String ville,
			@RequestParam("disponibiliteFrom") java.sql.Date dateFrom,
			@RequestParam(name="photo1") MultipartFile file1,
			@RequestParam(name="photo2") MultipartFile file2,
			@RequestParam(name="photo3") MultipartFile file3,
			@RequestParam(name="photo4") MultipartFile file4,
			@RequestParam(name="photo5") MultipartFile file5,
			@RequestParam(name="photo6") MultipartFile file6, 
			HttpServletRequest request) throws IllegalStateException, IOException {
		
			local.setPicture1(file1.getOriginalFilename());
			local.setPicture2(file2.getOriginalFilename());
		    local.setPicture3(file3.getOriginalFilename());
		    local.setPicture4(file4.getOriginalFilename());
		    local.setPicture5(file5.getOriginalFilename());
			local.setPicture6(file6.getOriginalFilename());
		
		
			if((file1.isEmpty())) {
			
				local.setPicture1(picture1);
			
			}
			else {
				local.setPicture1(file1.getOriginalFilename());
				File f=new File(localImageDir+local.getId()+"_1");
				if(f.exists()) {
						byte[] bytes=file1.getBytes();
						Path path=Paths.get(localImageDir+local.getId()+"_1");
						Files.write(path, bytes);		
				}
				else 
					{
					
					file1.transferTo(new File(localImageDir+local.getId()+"_1"));
					}
				
			}
		
			if((file2.isEmpty())) {
				
				local.setPicture2(picture2);
			
			}
			
			else {
				local.setPicture1(file2.getOriginalFilename());
				File f=new File(localImageDir+local.getId()+"_2");
				if(f.exists()) {
						byte[] bytes=file2.getBytes();
						Path path=Paths.get(localImageDir+local.getId()+"_2");
						Files.write(path, bytes);		
				}
				else 
					{
					
					file2.transferTo(new File(localImageDir+local.getId()+"_2"));
					}
				
			}
			
			if((file3.isEmpty())) {
				
				local.setPicture3(picture3);
			
			}
			
			else {
				local.setPicture1(file3.getOriginalFilename());
				File f=new File(localImageDir+local.getId()+"_3");
				if(f.exists()) {
						byte[] bytes=file3.getBytes();
						Path path=Paths.get(localImageDir+local.getId()+"_3");
						Files.write(path, bytes);		
				}
				else 
					{
					
					file3.transferTo(new File(localImageDir+local.getId()+"_3"));
					}
				
			}
			
			if((file4.isEmpty())) {
				
				local.setPicture4(picture4);
			
			}
			
			else {
				local.setPicture1(file4.getOriginalFilename());
				File f=new File(localImageDir+local.getId()+"_4");
				if(f.exists()) {
						byte[] bytes=file4.getBytes();
						Path path=Paths.get(localImageDir+local.getId()+"_4");
						Files.write(path, bytes);		
				}
				else 
					{
					
					file4.transferTo(new File(localImageDir+local.getId()+"_4"));
					}
				
			}
			
			if((file5.isEmpty())) {
				
				local.setPicture5(picture5);
			
			}
			
			else {
				local.setPicture1(file5.getOriginalFilename());
				File f=new File(localImageDir+local.getId()+"_5");
				if(f.exists()) {
						byte[] bytes=file5.getBytes();
						Path path=Paths.get(localImageDir+local.getId()+"_5");
						Files.write(path, bytes);		
				}
				else 
					{
					
					file5.transferTo(new File(localImageDir+local.getId()+"_5"));
					}
				
			}
			
			if((file6.isEmpty())) {
				
				local.setPicture6(picture6);
			
			}
			
			else {
				local.setPicture1(file6.getOriginalFilename());
				File f=new File(localImageDir+local.getId()+"_6");
				if(f.exists()) {
						byte[] bytes=file6.getBytes();
						Path path=Paths.get(localImageDir+local.getId()+"_6");
						Files.write(path, bytes);		
				}
				else 
					{
					
					file6.transferTo(new File(localImageDir+local.getId()+"_6"));
					}
				
			}
			
			
			
				
			
		
		HttpSession session=request.getSession(true);
		local.setDisponibiliteFrom(dateFrom);
		local.setCategory(category);
		local.setVille(ville);
		local.setOwner((Client) session.getAttribute("user"));
		
		
	
		localRepository.save(local);
	
		
		
	
		return "redirect:EditAds";	
	}
	
	
	@RequestMapping(value="getLocalPhoto",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getLocalPhoto(Long id,Long subId) throws FileNotFoundException, IOException {
		File f=new File(localImageDir+id+"_"+subId);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="ListLocals")
	public List<Local> ListeLocals(Long uId) {
	
		List<Local> local= localRepository.findByUserId(uId);
		
		return local;

	}
	
	
	@RequestMapping(value="/deletelocal", method =RequestMethod.GET)
	public String deletelocal(Long id) {
		
		localRepository.deleteById(id);
		return "redirect:EditAds";
	}
	@RequestMapping(value="/findAll", method =RequestMethod.GET)
	public Page<Local> findAll(Pageable pageable){
		return localRepository.findAll(pageable);
	}
	
	@RequestMapping(value="/findAllToAdd", method =RequestMethod.GET)
	public List<Local> findAllToAdd(){
		return localRepository.findAll();
	}
	@RequestMapping(value="/findByCity", method =RequestMethod.GET)
	public List<Local> findByCity(String ville){
		return localRepository.findByCity(ville);
	}
	@RequestMapping(value="/findById", method =RequestMethod.GET)
	public Local findById(Long id){
		return localRepository.getOne(id);
	}
	
	
	
	/*-----------------------------Acces aux locaux!-------------------------------------------*/
	
	@RequestMapping(value="getLocalPics",produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(Long id) throws FileNotFoundException, IOException {
		File f=new File(localImageDir+id);
		return IOUtils.toByteArray(new FileInputStream(f));
	}
	
	@RequestMapping(value="/RechercheLocal", method = RequestMethod.GET )
	public String RechercheLocal(Model model,HttpServletRequest request){
		HttpSession session=request.getSession(true);
		model.addAttribute("session", session.getAttribute("user"));
		List<Local> locals=localRepository.findAll(); 
		model.addAttribute("locals",locals);
		return "RechercheLocaux";
	}
	
	@RequestMapping(value="RechercherLocaux",method=RequestMethod.POST)
	public String RechercherLocaux(Model model ,HttpServletRequest request, @RequestParam(name="StartDate" )Date StartDate, @RequestParam(name="EndDate") Date EndDate,@RequestParam("City")String City,@RequestParam("TypeLocal")String TypeLocal,@RequestParam("MinNbrPlaces")int MinNbrPlaces,@RequestParam("MaxNbrPlaces")int MaxNbrPlaces,@RequestParam("MinPrice")int MinPrice,@RequestParam("MaxPrice")int MaxPrice ) {
		HttpSession session=request.getSession(true);
		model.addAttribute("session", session.getAttribute("user"));
		List<Local> locals=localRepository.rechercherLocaux(StartDate, EndDate, City, TypeLocal, MinNbrPlaces, MaxNbrPlaces, MinPrice, MaxPrice);
		model.addAttribute("locals",locals);
		return "RechercheLocaux";
	}
	
	
	
	

}
