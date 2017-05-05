package test;

import com.app.service.AccountService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yujingyang on 2017/4/21.
 */
public class MainTest  {
    public static void main(String[] args) throws ApiException {
        System.out.println(BCrypt.hashpw("335412",BCrypt.gensalt()));
    }
}
