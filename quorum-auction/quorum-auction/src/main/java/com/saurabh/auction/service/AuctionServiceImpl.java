package com.saurabh.auction.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.quorum.tx.ClientTransactionManager;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import com.saurabh.auction.contract.Auction;
import com.saurabh.auction.util.GeneralConstant;


@Service
public class AuctionServiceImpl implements AuctionService {
	private static final Logger log = LoggerFactory.getLogger(AuctionServiceImpl.class);
	
	
	Quorum quorumForA = Quorum.build(new HttpService("http://localhost:22000"));
	Quorum quorumForB = Quorum.build(new HttpService("http://localhost:22001"));
	Quorum quorumForC = Quorum.build(new HttpService("http://localhost:22002"));
	
	
	private ClientTransactionManager nodeATxManager = new ClientTransactionManager(quorumForA, GeneralConstant.NODE_A_ADDRESS,
				null, Arrays.asList(GeneralConstant.NODE_B_PUBKEY, GeneralConstant.NODE_B_PUBKEY));
	private ClientTransactionManager nodeBTxManager = new ClientTransactionManager(quorumForB, GeneralConstant.NODE_B_ADDRESS,
			null, Arrays.asList(GeneralConstant.NODE_A_PUBKEY));
	ClientTransactionManager nodeCTxManager = new ClientTransactionManager(quorumForB, GeneralConstant.NODE_B_ADDRESS,
			null, Arrays.asList(GeneralConstant.NODE_A_PUBKEY));
	
		private TransactionReceipt receipt = null;
		private Auction auction = null;
		private Tuple6<String, String, BigInteger, BigInteger, String, Boolean> itemTuple = null;

		@Override
		public Tuple6<String, String, BigInteger, BigInteger, String, Boolean> placeBid(String biddingPrice, String address) throws InterruptedException, ExecutionException, IOException {
			if(address.equalsIgnoreCase(GeneralConstant.NODE_B_ADDRESS)) {
				log.info("Inside placeBid()===>Connected to Quorum Node B client version: "
						+ quorumForB.web3ClientVersion().send().getWeb3ClientVersion());
				auction = Auction.load(GeneralConstant.CONTRACT_ADDRESS, quorumForB, nodeBTxManager,
						BigInteger.valueOf(10000), BigInteger.valueOf(4500000));
				BigDecimal bd = Convert.toWei(biddingPrice, Unit.ETHER);
				receipt = auction.placeBid("ITEM01", bd.toBigInteger()).sendAsync().get();
				log.info("Inside placeBid()===>Tx Detail====>> "+receipt);
				
				itemTuple = auction.item().sendAsync().get();
				
			} else if(address.equalsIgnoreCase(GeneralConstant.NODE_C_ADDRESS)) {
				log.info("Inside placeBid()===>Connected to Quorum Node C client version: "
						+ quorumForC.web3ClientVersion().send().getWeb3ClientVersion());
				auction = Auction.load(GeneralConstant.CONTRACT_ADDRESS, quorumForC, nodeCTxManager,
						BigInteger.valueOf(10000), BigInteger.valueOf(4500000));
				BigDecimal bd = Convert.toWei(biddingPrice, Unit.ETHER);
				receipt = auction.placeBid("ITEM01", bd.toBigInteger()).sendAsync().get();
				log.info("Inside placeBid()===>Tx Detail====>> "+receipt);
				
				itemTuple = auction.item().sendAsync().get();
				
			}
			
			return itemTuple;
		}

		@Override
		public Tuple6<String, String, BigInteger, BigInteger, String, Boolean> endBid() throws InterruptedException, ExecutionException, IOException {
			log.info("Inside endBid()===>Connected to Quorum Node A client version: "
					+ quorumForA.web3ClientVersion().send().getWeb3ClientVersion());
			auction = Auction.load(GeneralConstant.CONTRACT_ADDRESS, quorumForA, nodeATxManager,
					BigInteger.valueOf(10000), BigInteger.valueOf(4500000));
			receipt = auction.endBid("ITEM01").sendAsync().get();
			log.info("Inside endBid()===>Tx Detail====>> "+receipt);
			
			itemTuple = auction.item().sendAsync().get();
			
			return itemTuple;
		}
	

}
