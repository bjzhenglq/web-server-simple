package ex02.pyrmont;

import java.io.IOException;

/**
 * @author mazhiqiang
 * @date 14-3-11.
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
