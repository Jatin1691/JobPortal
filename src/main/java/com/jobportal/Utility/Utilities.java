package com.jobportal.Utility;

import com.jobportal.Exception.JobPortalException;
import com.jobportal.Model.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;


@Component
public class Utilities {

    private static MongoOperations mongoOperations;

    @Autowired
    public void setMongoOperations(MongoOperations mongoOperations) {
        Utilities.mongoOperations = mongoOperations;
    }

    public static Long getNextseq(String key) throws JobPortalException {
        Query query=new Query(Criteria.where("_id").is(key));
        Update update=new Update();
        update.inc("seq", 1);
        FindAndModifyOptions options=new FindAndModifyOptions();
        options.returnNew(true);
        Sequence sequence=mongoOperations.findAndModify(query,update,options, Sequence.class);
        if(sequence==null){
            throw new JobPortalException("unable to get the sequence id for key: "+key);
        }
        return sequence.getSeq();
    }

    public static String generateOtp(){
        StringBuilder otp=new StringBuilder();
        SecureRandom random = new SecureRandom();
        for(int i=0; i<6; i++){
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }


}
