package com.example.demo;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by song on 2018/3/9.
 */
@Controller
public class MyController {

    @Autowired
    TransportClient transportClient;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/get/nba/news")
    public ModelAndView get(@RequestParam("query") String query,
                            @RequestParam(name = "pageNow",required = false, defaultValue = "1") String pageNow,
                            Map<String,Object> model){
        if(query.isEmpty()){
            return new ModelAndView("index");
        }
        List<ShowPage> showPageList=new ArrayList<>();

        HighlightBuilder hiBuilder=new HighlightBuilder();
        hiBuilder.preTags("<font color='red'>");
        hiBuilder.postTags("</font>");
        hiBuilder.field("headline");
        hiBuilder.field("content");

        SearchResponse searchResponse = transportClient.prepareSearch("nbanews")
                .setTypes("news")
                .setSearchType(SearchType.DEFAULT)
                .setQuery(QueryBuilders.multiMatchQuery(query, "headline", "content"))
                .highlighter(hiBuilder)
                .setFrom((Integer.valueOf(pageNow)-1)*10)
                .setSize(10)
                .setExplain(true)
                .execute()
                .actionGet();
        SearchHits searchHits = searchResponse.getHits();
//        System.out.println("-----------------搜索关键字["+query+"]---------------------");
//        System.out.println("共匹配到:"+searchHits.getTotalHits()+"条记录!");
        long resultNumber=searchHits.getTotalHits();
        Page page=new Page(resultNumber,Integer.valueOf(pageNow));
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit searchHit : hits) {

            Map<String, Object> map = searchHit.getSourceAsMap();
            String time=map.get("time").toString();
            String url=map.get("url").toString();


            Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
            HighlightField mheadline = highlightFields.get("headline");
            String sheadline="";
            if(mheadline!=null){
                Text[] texts = mheadline.getFragments();
                for (Text text:texts){
                    sheadline+=text;
                }
            }

            String scontent="";
            HighlightField mcontent = highlightFields.get("content");
            if(mcontent!=null){
                Text[] texts = mcontent.getFragments();
                for (Text text:texts){
                    scontent+=text;
                }
            }

            if(sheadline.equals("")){
                 sheadline = map.get("headline").toString();
            }

            if(scontent.equals("")){
                 scontent = map.get("content").toString().substring(0,50);//取前100个字符
            }

            showPageList.add(new ShowPage(sheadline,scontent,time,url));
        }
        model.put("showPageList",showPageList);
        model.put("query",query);
        model.put("page",page);

        return new ModelAndView("show",model);

    }
}
