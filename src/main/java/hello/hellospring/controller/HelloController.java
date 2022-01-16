package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) { // "hello"라는 html을 반환
        model.addAttribute("data", "hello!!"); // html에서 ${data)의 값을 hello!!로 치환
        return "hello";  // template 아래에 해당하는 html 파일 (파일명이 " hello.html ") 을 타임리프가 찾음
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("param1") String nameVariable, @RequestParam("param2") String email,Model model) {
        model.addAttribute("modelName", nameVariable);
        model.addAttribute("modelEmail", email);
        model.addAttribute("attr1", "Attribute Value 1");
        return "hello-templates";
    }
    // http://localhost:8080/hello-mvc?param1=abc&param2=abc@gmail.com


    @GetMapping("hello-string")
    @ResponseBody // @responsebody를 붙이면 html이 아닌 데이터를 그대로 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // hello 객체를 반환
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    } // {"name":"spring!!"}  JSON 타입으로 반환됨

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
