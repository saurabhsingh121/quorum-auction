package com.saurabh.auction.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.tuples.generated.Tuple6;

public interface AuctionService {

	Tuple6<String, String, BigInteger, BigInteger, String, Boolean> placeBid(String biddingPrice, String address) throws InterruptedException, ExecutionException, IOException;

	Tuple6<String, String, BigInteger, BigInteger, String, Boolean> endBid() throws InterruptedException, ExecutionException, IOException;

}
