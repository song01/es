package com.example.demo;

//exclude
//@RestController
//public class FirstController {
//
//    @Autowired
//    TransportClient transportClient;
//
////    @GetMapping("/")
////    public String index(){
////        return "index";
////    }
//
//    @GetMapping("/get/book/novel")
//    public ResponseEntity get(@RequestParam("id") String id){
//        if(id.isEmpty()){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        GetResponse result = transportClient.prepareGet("book", "novel", id).get();
//
//        if(!result.isExists()){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//
//        return new  ResponseEntity(result.getSource(), HttpStatus.OK);
//
//    }
//
//    @PostMapping("/add/book/novel")
//    public ResponseEntity add(@RequestParam("author")String author,
//                              @RequestParam("title")String title,
//                              @RequestParam("word_count") int wordCount,
//                              @RequestParam("publish_date")
//                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate){
//        try {
//            XContentBuilder builder = XContentFactory.jsonBuilder()
//                    .startObject()
//                    .field("author", author)
//                    .field("title", title)
//                    .field("word_count", wordCount)
//                    .field("publish_date", publishDate.getTime())
//                    .endObject();
//
//            IndexResponse response = transportClient.prepareIndex("book", "novel")
//                    .setSource(builder)
//                    .get();
//
//            return new ResponseEntity(response.getId(),HttpStatus.OK);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//    }
//
//    @DeleteMapping("/delete/book/novel")
//    public ResponseEntity delete(@RequestParam("id")String id){
//        DeleteResponse deleteResponse = transportClient.prepareDelete("book", "novel", id)
//                .get();
//        return new ResponseEntity(deleteResponse,HttpStatus.OK);
//    }
//
//    @PutMapping("/update/book/novel")
//    public ResponseEntity update(@RequestParam("id")String id,
//                                 @RequestParam("title")String title){
//        UpdateRequest updateRequest = new UpdateRequest("book", "novel", id);
//        try {
//            XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
//                   .startObject()
//                   .field("title", title)
//                   .endObject();
//            updateRequest.doc(xContentBuilder);
//            try {
//                UpdateResponse updateResponse = transportClient.update(updateRequest).get();
//                return new ResponseEntity(updateResponse,HttpStatus.OK);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
