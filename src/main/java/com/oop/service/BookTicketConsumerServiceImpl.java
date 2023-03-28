package com.oop.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.oop.request.Passenger;
import com.oop.response.Ticket;

import reactor.core.publisher.Mono;

@Service
public class BookTicketConsumerServiceImpl implements BookTicketConsumerService{
	String uri="";
	
	WebClient webClient = WebClient.create();
	
	public Mono<Ticket> bookTicket(Passenger passenger) {
		uri = "http://localhost:8080/bookTicket";
		
	Mono<Ticket> resp = webClient.post()
				.uri("http://localhost:8080/bookTicket")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(passenger),Passenger.class)
				.retrieve()
				.bodyToMono(Ticket.class);
						
		return resp ;
	}

	public String cancelTicket(Integer ticketId) {

		 uri = "http://localhost:8080/cancelTicket/{ticketId}";
		String block = webClient.delete() 
				  .uri(uri,ticketId)
				  .retrieve()
				  .bodyToMono(String.class)
				  .block();
		  
		return block;
	}
	


	private void block() {
		// TODO Auto-generated method stub
		
	}

	public String downloadTicketById(HttpServletResponse response, Integer ticketId) throws Exception {

		return null;
	}

	@Override
	public <T> T checkTicketStatusById(Integer ticketId) {
	
		return null;
	}

}