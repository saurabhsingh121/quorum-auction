pragma solidity ^0.4.22;

contract Auction {
    
    //Item struct with its attribute
    struct Item {
        string itemId;
        string itemName;
        uint price;
        uint highestBid;
        address winner;
        bool bidOn;
    }
    
    address public owner;
    Item public item;
    mapping(address => uint) fundsByBidder;
    mapping(address => uint) noOfBids;
    address[] public addressIndices;
    
    //event will fired when changes
    event AuctionEnded(address winner, uint highestBid);
    
    modifier onlyOnwer() {
        require(msg.sender == owner, "Only owner is permitted.");
        _;
    }
    
    constructor (string _itemId, string _itemName, uint _price ) public {
        owner = msg.sender;
        item =  Item ({
            itemId: _itemId,
            itemName: _itemName,
            price: _price,
            highestBid: 0,
            winner: 0,
            bidOn: true
        });
    }
    
    //bidders will place their bid with payable function
    function placeBid(string _itemId) public payable{
        require(compareStrings(_itemId, item.itemId), "You haven't selected correct item.");
        require(item.bidOn == true, "Bidding is closed.");
        require(noOfBids[msg.sender] == 0, "You can bid for once.");
        require(msg.value > item.price, "Bidding price should be greater than item price.");
        fundsByBidder[msg.sender] = msg.value;
        noOfBids[msg.sender]++;
        addressIndices.push(msg.sender);
        if(msg.value > item.highestBid){
            item.highestBid = msg.value;
            item.winner = msg.sender;
        }
    }
    
    function endBid(string _itemId) public onlyOnwer {
        require(compareStrings(_itemId, item.itemId), "You haven't selected correct item.");
        require(item.bidOn == true, "Bidding is already closed.");
        emit AuctionEnded(item.winner, item.highestBid);
        for(uint i = 0; i < addressIndices.length; i++){
            if(addressIndices[i] != item.winner){
                address tempAddress = addressIndices[i];
                tempAddress.transfer(fundsByBidder[tempAddress]);
            }
        }
        owner.transfer(item.highestBid);
        item.bidOn = false;
    }
    
    //fallback function to catch the ether
    function() payable public {
        
    }
    
    //function for comparing string
    function compareStrings (string a, string b) internal pure returns (bool){
       return keccak256(a) == keccak256(b);
   }
}