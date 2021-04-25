package com;
import model.Advice;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("unused")
@Path("/Item")

public class AdviceService {
	
	Advice itemObj = new Advice();
	
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readItems()
	{
		
		return itemObj.readItems();
		
	}
	
	
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertItem(
			@FormParam("AdvicerName") String AdviserName,
			@FormParam("ResearchGroupId") String ResearchGroupId,
			@FormParam("ResearchType") String ResearchType,
			@FormParam("AdvicingType") String AdvisingType,
			@FormParam("AdvicerCurrentLevel") String AdviserCurrentLevel) 
{
	String output = itemObj.insertAdvice(AdviserName,ResearchGroupId,ResearchType,AdvisingType,AdviserCurrentLevel);
	return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)

public String updateItem(String itemData)
{
	//Convert the input string to a JSON object
	JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	
	//Read the values from the JSON object
	
	String Id = itemObject.get("Id").getAsString();
	String AdvicerName = itemObject.get("AdvicerName").getAsString();
	String ResearchGroupId = itemObject.get("ResearchGroupId").getAsString();
	String ResearchType = itemObject.get("ResearchType").getAsString();
	String AdvicingType = itemObject.get("AdvicingType").getAsString();
	String AdvicerCurrentLevel = itemObject.get("AdvicerCurrentLevel").getAsString();
	String output = itemObj.UpdateAdvice(Id,AdvicerName,ResearchGroupId,ResearchType,AdvicingType,AdvicerCurrentLevel);
	return output;

}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)

public String deleteAdvice(String itemData)
{
	//convert the input string to an XML document
	Document doc = Jsoup.parse(itemData,"",Parser.xmlParser());
	//Read the value from the element <Id>
	String Id = doc.select("Id").text();
	String output = itemObj.deleteAdvice(Id);
	return output;

}

	

}
