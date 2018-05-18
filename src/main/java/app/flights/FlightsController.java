package app.flights;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.GenericResult;

@RestController
public class FlightsController {
	   
		private List<Flight> flights;
		private int maxFlight = 1;
	    public FlightsController() {
			super();
			flights = new ArrayList<Flight>();
			
			Flight flight1 = new Flight(""+maxFlight++, "910", "EWR", "HKG", "01/01/2017 20.40", "CX");
			flights.add(flight1);
			
			Flight flight2 = new Flight(""+maxFlight++, "870", "SFO", "PVG", "11/01/2017 22.10", "CX");
			flights.add(flight2);
			
			Flight flight3 = new Flight(""+maxFlight++, "489", "LAX", "PEK", "01/01/2017 12.25", "CX");
			flights.add(flight3);
		}		

		@RequestMapping(value="/flights/intl/flights", method=RequestMethod.GET, produces="application/json") 
	    public @ResponseBody List<Flight> getFlights() {
			
			
	    		return flights;
	    	
	    }
		
		@RequestMapping(value="/flights/intl/flight/{id}.xml", method=RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE) 
	    public @ResponseBody Flight getFlightsXML(@PathVariable("id") String id) {
			
			
			Flight flight = getFlight(id);
			if(flight == null){
				flight = new Flight();
			}
			return flight;
	    	
	    }
		
		@RequestMapping(value="/flights/intl/flights/{id}", method=RequestMethod.GET, produces="application/json") 
	    public @ResponseBody Flight returnFlight(@PathVariable("id") String id) {
			
			Flight flight = getFlight(id);
			if(flight == null){
				flight = new Flight();
			}
	    	return flight;
	    	
	    }

	@RequestMapping(value="/flights/intl/flight", method=RequestMethod.POST, produces="application/json", consumes="application/json") 
    public @ResponseBody Flight postFlight(@RequestBody Flight newFlight) {
        
		Flight flight = new Flight(""+maxFlight++, newFlight.getNumber(), newFlight.getFrom(), newFlight.getTo(), newFlight.getDepartureDateAndTime(), newFlight.getAirline());
		flights.add(flight);
		
    	return flight;
    }
	
	
	@RequestMapping(value="/flights/intl/flight/{id}", method=RequestMethod.DELETE, produces="application/json") 
    public @ResponseBody GenericResult deleteFlight(@PathVariable("id") String id) {
        
		Flight foundItem = getFlight(id);
    	if(foundItem != null){
    		flights.remove(foundItem);
    		return new GenericResult("Flight "+id+" successfully deleted");
    	}
    	
    	return new GenericResult("Flight "+id+" NOT FOUND");
    }

	
	@RequestMapping(value="/flights/intl/flight/{id}", method=RequestMethod.PUT, consumes="application/json") 
    public @ResponseBody GenericResult updateFlight(@RequestBody Flight newFlight, @PathVariable("id") String id) {
        
		Flight foundFlight = getFlight(id);
    	if(foundFlight != null){
    		foundFlight.setAirline(newFlight.getAirline());
    		foundFlight.setDepartureDateAndTime(newFlight.getDepartureDateAndTime());
    		foundFlight.setFrom(newFlight.getFrom());
    		foundFlight.setNumber(newFlight.getNumber());
    		foundFlight.setTo(newFlight.getTo());

    		

    		return new GenericResult("Flight "+id+" successfully updated");
    	}
    	
    	return new GenericResult("Flight "+id+" NOT FOUND");
    }
		
	//******************************************* Private Methods *******************************************
	
	private Flight getFlight(String id){
		Flight ret = null;
		for (Flight flight: flights){
			if (flight.getId().equals(id)){
				ret = flight;
				break;
			}
		}
		return ret;
	}
	
}