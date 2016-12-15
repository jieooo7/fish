package com.fish.view;

import com.fish.controller.AdController;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by thy on 16-12-9.
 */

public class AddHeader {

    private static final Logger log = LoggerFactory.getLogger(AddHeader.class);
    public void getNavigation(Integer naviType,ModelMap map){
        String fileUrl = "/";
        switch(naviType){
            case 1:
                //admin navigatioin
                fileUrl = fileUrl + "header_admin.html";
                break;
            case 2:
                //user navigation
                fileUrl = fileUrl + "head.html";
                break;
            default:
                fileUrl = fileUrl + "head.html";
                break;
        }
        try {
            InputStream input = this.getClass().getResourceAsStream(fileUrl);
            Document doc = Jsoup.parse(input,"UTF-8","");//baseurl 解决相对路径 问题
            map.addAttribute("headerContent", doc.select("head").toString());
//            map.addAttribute("sideBarContent", doc.select("#admin-offcanvas").first().toString());
            map.addAttribute("footerContent", changeAttrAddress(doc.select("footer"),"script","src"));
            map.addAttribute("headContent", changeAttrAddress(doc.select("head"),"link","href"));
        } catch (IOException e) {
            log.error("error when using jsoup");
            log.error(e.getMessage());
            map.addAttribute("headContent", "");
            map.addAttribute("headerContent", "");
            map.addAttribute("sideBarContent", "");
            map.addAttribute("footerContent", "");
        }
    }

    private String changeAttrAddress(Elements parentNode,String dealingNodeName,String attrName){
        Elements elements = parentNode.select(dealingNodeName);
        for(Element elment : elements){
            String orignalAddress = elment.attr(attrName);
            if(orignalAddress==null||orignalAddress.equals("")){
                continue;
            }
            orignalAddress = delInnerPath(orignalAddress);
            elment.attr(attrName,orignalAddress);
        }
        return parentNode.toString();
    }

    private String delInnerPath(String address){
        String keyWord = "static";
        if(address.contains(keyWord)==false){
            return address;
        }
        int position = address.indexOf(keyWord) + keyWord.length() + 1;
        return address.substring(position);//截取掉str从首字母起长度为beginIndex的字符串，将剩余字符串赋值给str；
    }

//    //当前日期
//    Date date = new Date();
//    //格式化并转换String类型
//    String path="E:/"+new SimpleDateFormat("yyyy/MM/dd").format(date);
//    //创建文件夹
//    File f = new File(path);
//    if(!f.exists())
//            f.mkdirs();
}
