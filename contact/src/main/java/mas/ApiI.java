package mas;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;

import java.util.List;

public interface ApiI {

    /**
     Create table in DynemoDB
     @param  dynamoDB  the DynamoDB
     */
    void  createTable(DynamoDB dynamoDB);

    /**
     Query Base on email id
     @param email emailid of required item
     @param  dynamoDB  the DynamoDB
     @return item base on query parameter
     */
    Item queryEmail(String email, DynamoDB dynamoDB);

    /**
     Query Base on name
     @param name the name of required item
     @param  dynamoDB  the DynamoDB
     @return List base on query parameter
     */
     List<String> queryName(String name, DynamoDB dynamoDB);

    /**
     Edit item base on parameter
     @param email the emailid
     @param  dynamoDB  the DynamoDB
     @param name the name
     @param contact the contact number
     @param address the address
     */
     void editItem(String email,DynamoDB dynamoDB,String name,String contact,String address);

    /**
     add item base on parameter
     @param email the emailid
     @param  dynamoDB  the DynamoDB
     @param name the name
     @param contact the contact number
     @param address the address
     */
     void create(String email,DynamoDB dynamoDB,String name,String contact,String address);

    /**
     delete item base on email
     @param email the emailid
     @param  dynamoDB  the DynamoDB
     */
     void del(String email,DynamoDB dynamoDB);

}
