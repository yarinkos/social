package social.kossover.com.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
 
    @Override
    protected String getDatabaseName() {
        return "test1";
    }
 
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }
 
    @Override
    protected String getMappingBasePackage() {
        return "social.kossover.com.repository";
    }
}