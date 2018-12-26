package com.saurabh.auction.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Auction extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50604051610cf9380380610cf983398101604081815282516020808501518386015160008054600160a060020a0319163317815560c08701865293870180875291909601828601819052938501869052606085018390526080850192909252600160a0850181905282519295939492909161008f91839188019061011f565b5060208281015180516100a8926001850192019061011f565b50604082015160028201556060820151600382015560808201516004909101805460a0909301511515740100000000000000000000000000000000000000000260a060020a60ff0219600160a060020a03909316600160a060020a03199094169390931791909116919091179055506101ba915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061016057805160ff191683800117855561018d565b8280016001018555821561018d579182015b8281111561018d578251825591602001919060010190610172565b5061019992915061019d565b5090565b6101b791905b8082111561019957600081556001016101a3565b90565b610b30806101c96000396000f30060806040526004361061006c5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166363b92e9f811461006e5780638da5cb5b146100c7578063a7df8c57146100f8578063e967e60c14610110578063f2a4a82e1461015c575b005b34801561007a57600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261006c9436949293602493928401919081908401838280828437509497506102759650505050505050565b3480156100d357600080fd5b506100dc6105c1565b60408051600160a060020a039092168252519081900360200190f35b34801561010457600080fd5b506100dc6004356105d0565b6040805160206004803580820135601f810184900484028501840190955284845261006c9436949293602493928401919081908401838280828437509497506105f89650505050505050565b34801561016857600080fd5b506101716108d9565b6040805190810185905260608101849052600160a060020a038316608082015281151560a082015260c08082528751908201528651819060208083019160e08401918b019080838360005b838110156101d45781810151838201526020016101bc565b50505050905090810190601f1680156102015780820380516001836020036101000a031916815260200191505b5083810382528851815288516020918201918a019080838360005b8381101561023457818101518382015260200161021c565b50505050905090810190601f1680156102615780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b600080548190600160a060020a031633146102da576040805160e560020a62461bcd02815260206004820152601860248201527f4f6e6c79206f776e6572206973207065726d69747465642e0000000000000000604482015290519081900360640190fd5b60018054604080516020600284861615610100026000190190941693909304601f8101849004840282018401909252818152610371938793919290918301828280156103675780601f1061033c57610100808354040283529160200191610367565b820191906000526020600020905b81548152906001019060200180831161034a57829003601f168201915b5050505050610a42565b15156103ed576040805160e560020a62461bcd02815260206004820152602260248201527f596f7520686176656e27742073656c656374656420636f72726563742069746560448201527f6d2e000000000000000000000000000000000000000000000000000000000000606482015290519081900360840190fd5b60055460ff74010000000000000000000000000000000000000000909104161515600114610465576040805160e560020a62461bcd02815260206004820152601a60248201527f42696464696e6720697320616c726561647920636c6f7365642e000000000000604482015290519081900360640190fd5b60055460045460408051600160a060020a039093168352602083019190915280517fdaec4582d5d9595688c8c98545fdd1c696d41c6aeaeb636737e84ed2f5c00eda9281900390910190a1600091505b6008548210156105625760055460088054600160a060020a0390921691849081106104dc57fe5b600091825260209091200154600160a060020a03161461055757600880548390811061050457fe5b6000918252602080832090910154600160a060020a031680835260069091526040808320549051919350839281156108fc029290818181858888f19350505050158015610555573d6000803e3d6000fd5b505b6001909101906104b5565b60008054600454604051600160a060020a039092169281156108fc029290818181858888f1935050505015801561059d573d6000803e3d6000fd5b50506005805474ff0000000000000000000000000000000000000000191690555050565b600054600160a060020a031681565b60088054829081106105de57fe5b600091825260209091200154600160a060020a0316905081565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815261065a938593919290918301828280156103675780601f1061033c57610100808354040283529160200191610367565b15156106d6576040805160e560020a62461bcd02815260206004820152602260248201527f596f7520686176656e27742073656c656374656420636f72726563742069746560448201527f6d2e000000000000000000000000000000000000000000000000000000000000606482015290519081900360840190fd5b60055460ff7401000000000000000000000000000000000000000090910416151560011461074e576040805160e560020a62461bcd02815260206004820152601260248201527f42696464696e6720697320636c6f7365642e0000000000000000000000000000604482015290519081900360640190fd5b33600090815260076020526040902054156107b3576040805160e560020a62461bcd02815260206004820152601560248201527f596f752063616e2062696420666f72206f6e63652e0000000000000000000000604482015290519081900360640190fd5b6003543411610832576040805160e560020a62461bcd02815260206004820152603060248201527f42696464696e672070726963652073686f756c6420626520677265617465722060448201527f7468616e206974656d2070726963652e00000000000000000000000000000000606482015290519081900360840190fd5b336000818152600660209081526040808320349081905560079092528220805460019081019091556008805491820181559092527ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee3909101805473ffffffffffffffffffffffffffffffffffffffff191690921790915560045410156108d657346004556005805473ffffffffffffffffffffffffffffffffffffffff1916331790555b50565b60018054604080516020601f6002600019610100878916150201909516949094049384018190048102820181019092528281529183918301828280156109605780601f1061093557610100808354040283529160200191610960565b820191906000526020600020905b81548152906001019060200180831161094357829003601f168201915b505050505090806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109fe5780601f106109d3576101008083540402835291602001916109fe565b820191906000526020600020905b8154815290600101906020018083116109e157829003601f168201915b505050506002830154600384015460049094015492939092909150600160a060020a0381169074010000000000000000000000000000000000000000900460ff1686565b6000816040518082805190602001908083835b60208310610a745780518252601f199092019160209182019101610a55565b51815160209384036101000a6000190180199092169116179052604051919093018190038120885190955088945090928392508401908083835b60208310610acd5780518252601f199092019160209182019101610aae565b5181516020939093036101000a600019018019909116921691909117905260405192018290039091209390931496955050505050505600a165627a7a72305820e3f8f1e978e55d542950cdfb5f320bc89af21008e773695656c4c0e49fe9ee6a0029";

    public static final String FUNC_ENDBID = "endBid";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_ADDRESSINDICES = "addressIndices";

    public static final String FUNC_PLACEBID = "placeBid";

    public static final String FUNC_ITEM = "item";

    public static final Event AUCTIONENDED_EVENT = new Event("AuctionEnded", 
            Arrays.<TypeReference<?>>asList());
    ;

    protected Auction(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Auction(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> endBid(String _itemId) {
        final Function function = new Function(
                FUNC_ENDBID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_itemId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> addressIndices(BigInteger param0) {
        final Function function = new Function(FUNC_ADDRESSINDICES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> placeBid(String _itemId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_PLACEBID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_itemId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple6<String, String, BigInteger, BigInteger, String, Boolean>> item() {
        final Function function = new Function(FUNC_ITEM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple6<String, String, BigInteger, BigInteger, String, Boolean>>(
                new Callable<Tuple6<String, String, BigInteger, BigInteger, String, Boolean>>() {
                    @Override
                    public Tuple6<String, String, BigInteger, BigInteger, String, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<String, String, BigInteger, BigInteger, String, Boolean>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _itemId, String _itemName, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_itemId), 
                new org.web3j.abi.datatypes.Utf8String(_itemName), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(Auction.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Auction> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _itemId, String _itemName, BigInteger _price) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_itemId), 
                new org.web3j.abi.datatypes.Utf8String(_itemName), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)));
        return deployRemoteCall(Auction.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<AuctionEndedEventResponse> getAuctionEndedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(AUCTIONENDED_EVENT, transactionReceipt);
        ArrayList<AuctionEndedEventResponse> responses = new ArrayList<AuctionEndedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AuctionEndedEventResponse typedResponse = new AuctionEndedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.highestBid = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AuctionEndedEventResponse> auctionEndedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, AuctionEndedEventResponse>() {
            @Override
            public AuctionEndedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(AUCTIONENDED_EVENT, log);
                AuctionEndedEventResponse typedResponse = new AuctionEndedEventResponse();
                typedResponse.log = log;
                typedResponse.winner = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.highestBid = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<AuctionEndedEventResponse> auctionEndedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AUCTIONENDED_EVENT));
        return auctionEndedEventObservable(filter);
    }

    public static Auction load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Auction load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Auction(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class AuctionEndedEventResponse {
        public Log log;

        public String winner;

        public BigInteger highestBid;
    }
}
