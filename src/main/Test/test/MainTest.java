package test;

import com.app.service.AccountService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yujingyang on 2017/4/21.
 */
public class MainTest  {
    public static void main(String[] args) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23741172", "45c868d1fef6bc53ca7c5da9e93a747d");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend( "" );
        req.setSmsType( "normal" );
        req.setSmsFreeSignName( "高翎实业" );
        req.setSmsParamString( "{code:'1336'}" );
        req.setRecNum( "17621181235" );
        req.setSmsTemplateCode( "SMS_62700067" );
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
}
