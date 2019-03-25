package pro.kretov.repository.search.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    private RedisDAO redisDAO;
//
//    @Autowired
//    public TestController(RedisDAO redisDAO) {
//        this.redisDAO = redisDAO;
//    }
//
//    @GetMapping(value = "/back/redis")
//    private Word redis() {
//        Word word = new Word();
//        String uuid = UUID.randomUUID().toString();
//        word.setId(uuid);
//        word.setSequence("ola!)");
//        redisDAO.save(word);
//
//        Word wordFromRedis = redisDAO.findById(uuid.toString()).get();
//        return wordFromRedis;
//    }
//
//    @GetMapping(value = "back/get")
//    private List<Word> get() {
//        List<Word> list = new ArrayList<>();
//        redisDAO.findAll().forEach(list::add);
//        return list;
//    }
}
