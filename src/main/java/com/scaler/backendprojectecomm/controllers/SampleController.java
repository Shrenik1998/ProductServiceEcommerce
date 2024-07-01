package com.scaler.backendprojectecomm.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//this class will be hosting a set of http API's eg:localhost:8080/Say/Hello
@RestController
@RequestMapping("/Say")
public class SampleController {

    @GetMapping("/Hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name,@PathVariable("times") int times){
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<times;i++)
        {
            sb.append(name);
            sb.append(" ");
        }
        return sb.toString();
    }

    @GetMapping("/Bye")
    public String sayBye(){
        return "Bye World";
    }
}
