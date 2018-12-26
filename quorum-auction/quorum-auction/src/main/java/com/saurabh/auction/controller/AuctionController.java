package com.saurabh.auction.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.tuples.generated.Tuple6;

import com.saurabh.auction.service.AuctionService;

@Controller
public class AuctionController {
	
	private static Logger log = LoggerFactory.getLogger(AuctionController.class);
	
	@Autowired
	private AuctionService auctionService;
	
	@GetMapping("/")
	public String getHome() {
		return "home";
	}
	
	@PostMapping("/placeBid")
	public String placeBid(@RequestParam("biddingPrice") String biddingPrice,
			@RequestParam("address") String address, Model theModel) {
		log.info("Inside placeBid()>>>> Bidding price>>> "+ biddingPrice);
		log.info("Inside placeBid()>>>> Node Address>>> "+ address);
		try {
			Tuple6<String, String, BigInteger, BigInteger, String, Boolean> itemTuple = auctionService.placeBid(biddingPrice, address);
			if(itemTuple.getValue6() == false) {
				theModel.addAttribute("winner", itemTuple.getValue5());
				theModel.addAttribute("highestBid", itemTuple.getValue4());
			} else {
				theModel.addAttribute("winner", "Auction is still on.");
				theModel.addAttribute("highestBid", "Auction is still on.");
			}
		} catch (InterruptedException | ExecutionException | IOException e) {
			log.info("Error occurred while placing the bid >>> "+e.getMessage());
		}
		
		return "home";
	}
	
	@GetMapping("/endBid")
	public String endBid(Model theModel) {
		log.info("Inside endBid()>>>>");
		try {
			Tuple6<String, String, BigInteger, BigInteger, String, Boolean> itemTuple = auctionService.endBid();
			theModel.addAttribute("winner", itemTuple.getValue5());
			theModel.addAttribute("highestBid", itemTuple.getValue4());
		} catch (InterruptedException | ExecutionException | IOException e) {
			log.info("Error occurred while placing the bid >>> "+e.getMessage());
		}
		return "home";
	}
}
