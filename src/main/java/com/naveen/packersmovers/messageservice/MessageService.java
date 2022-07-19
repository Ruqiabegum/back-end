package com.naveen.packersmovers.messageservice;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.naveen.packersmovers.model.Customer;


import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
    public static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId";
    public static final String AWS_SECRET_KEY = "aws.secretKey";

    static {
        System.setProperty(AWS_ACCESS_KEY_ID, "AKIAZ662ZFDLW6G76ETM");
        System.setProperty(AWS_SECRET_KEY, "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");

    }
    
    
    public String sendMessage(Customer user){
        MessageService myClient =new MessageService();
        String message = "Name: " + user.getfullName() + " " + "\n" + 
        				 "Email:" + user.getEmail() + " " + "\n" +
        				 "Cell Number:" + user.getPhone() + " " + "\n" +
        				 "From:" + user.getfromAddress() + " " + "\n" +
        				 "To:" + user.gettoAddress() + " " + "\n" +
        				 "Est. Date" + user.getestDate() + " " + "\n" +
        				 "Move Type:" + user.getmoveSize() + " " + "\n" +
        				 "http://localhost:3000/customer/+91-"+user.getPhone()+"/driver-details";
        
        
        myClient.sendCustomerDetails(message);
        return null;
		
    }
    
    public void sendCustomerDetails(String message) {
        AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.AP_SOUTH_1).build();
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("sms-topic").withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));

        PublishResult result = snsClient.publish(new PublishRequest()
        						.withTopicArn("arn:aws:sns:ap-south-1:684969240791:Java-Fullstack")
                				.withMessage(message)
                				.withMessageAttributes(smsAttributes));
        System.out.println("Message sent successfully--" + result.getMessageId());
    }
    
}
