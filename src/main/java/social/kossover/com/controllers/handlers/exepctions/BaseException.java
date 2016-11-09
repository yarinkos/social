package social.kossover.com.controllers.handlers.exepctions;

/**
 * Created by Yarin.kossover on 4/2/2016.
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BaseException(String error){
        super(error);
    }
}
