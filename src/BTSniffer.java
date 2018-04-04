import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.HttpClientUtil;
import utils.HttpsUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;
import static com.sun.jmx.snmp.ThreadContext.contains;
import static com.sun.org.apache.xml.internal.resolver.Catalog.DOCTYPE;
import static com.sun.xml.internal.ws.client.ContentNegotiation.none;
import static java.awt.SystemColor.text;
import static java.awt.SystemColor.window;
import static javafx.scene.input.KeyCode.J;
import static javafx.scene.input.KeyCode.M;
import static utils.HttpClientUtil.GetUrlHeaderFields;
import static utils.HttpsUtil.post;

/**
 * Created by xuyaoxin on 2016/11/24.
 */
public class BTSniffer {

    public static List<String> nextUrls = new ArrayList<String>();
    public static List<String> BtUrls = new ArrayList<String>();

    public static void main(String[] org0){
        /*//String webUrl = "http://avlang8.com/thread-htm-fid-5.html";
        String webUrl = "http://avlang8.com/read-htm-tid-839535.html";
        try {
            recursion(webUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("首页搜索完毕");
        int i = 0;
        while(nextUrls!=null&&nextUrls.size()>0){
            try {
                for (String url:nextUrls) {
                    recursion(url);
                    nextUrls.remove(webUrl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(i++==2){
                return ;
            }
        }*/
        try {
            Document doc = Jsoup.connect("https://auth.alipay.com/login/index.htm?goto=https%3A%2F%2Fenterpriseportal.alipay.com%2Findex.htm").header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
            Elements ua  = doc.select("input[name=ua]");
            Elements support  = doc.select("input[name=support]");
            Elements needTransfer  = doc.select("input[name=needTransfer]");
            Elements CtrlVersion  = doc.select("input[name=CtrlVersion]");
            Elements loginScene  = doc.select("input[name=loginScene]");
            Elements redirectType  = doc.select("input[name=redirectType]");
            Elements personalLoginError  = doc.select("input[name=personalLoginError]");
            Elements Goto = doc.select("input[name=goto]");
            Elements errorVM  = doc.select("input[name=errorVM]");
            Elements sso_hid  = doc.select("input[name=sso_hid]");
            Elements site  = doc.select("input[name=site]");
            Elements errorGoto  = doc.select("input[name=errorGoto]");
            Elements rds_form_token  = doc.select("input[name=rds_form_token]");
            Elements json_tk  = doc.select("input[name=json_tk]");
            Elements method  = doc.select("input[name=method]");
            Elements logonId  = doc.select("input[name=logonId]");
            Elements superSwitch  = doc.select("input[name=superSwitch]");
            Elements noActiveX  = doc.select("input[name=noActiveX]");
            Elements passwordSecurityId  = doc.select("input[name=passwordSecurityId]");
            Elements qrCodeSecurityId  = doc.select("input[name=qrCodeSecurityId]");
            Elements password_input  = doc.select("input[name=password_input]");
            Elements password_rsainput  = doc.select("input[name=password_rsainput]");

            Elements J_aliedit_using  = doc.select("input[name=J_aliedit_using]");
            Elements password  = doc.select("input[name=password]");
            Elements J_aliedit_key_hidn  = doc.select("input[name=J_aliedit_key_hidn]");
            Elements J_aliedit_uid_hidn  = doc.select("input[name=J_aliedit_uid_hidn]");
            Elements alieditUid  = doc.select("input[name=alieditUid]");
            Elements REMOTE_PCID_NAME  = doc.select("input[name=REMOTE_PCID_NAME]");
            Elements _seaside_gogo_pcid  = doc.select("input[name=_seaside_gogo_pcid]");
            Elements _seaside_gogo_  = doc.select("input[name=_seaside_gogo_]");
            Elements _seaside_gogo_p  = doc.select("input[name=_seaside_gogo_p]");
            Elements J_aliedit_prod_type  = doc.select("input[name=J_aliedit_prod_type]");
            Elements security_activeX_enabled  = doc.select("input[name=security_activeX_enabled]");
            Elements J_aliedit_net_info  = doc.select("input[name=J_aliedit_net_info]");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void recursion(String webUrl) throws IOException {
        Document doc = Jsoup.connect(webUrl).header("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2").get();
        URL realUrl = new URL(webUrl);
        List<String> list = findUrl(doc,realUrl.getHost());
        for (String url:list) {
            System.out.print(url);
            Map<String, List<String>> headerFields = GetUrlHeaderFields(url,"");
            if(headerFields!=null&&headerFields.get("Content-Type")!=null&&"application/octet-stream".equals(headerFields.get("Content-Type").get(0))){
                System.out.println("处理完毕 BtUrls.add");
                BtUrls.add(url);
            }else{
                System.out.println("处理完毕 nextUrls.add");
                nextUrls.add(url);
            }
        }
    }

    public static List<String> findUrl(Document doc,String host){
        List<String> list = new ArrayList<String>();
        Elements links = doc.select("a[href]");
        for(Element link:links){
            String url = link.attr("abs:href");
            if(!"".equals(url)&&url.contains(host)&&!url.equals(link.baseUri())){
                list.add(url);
            }
        }
        return list;
    }
}
