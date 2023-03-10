package com.skilloVillaProjLib.controller;

import com.skilloVillaProjLib.exceptionHandler.PostResponseHandler;
import com.skilloVillaProjLib.model.Users;
import com.skilloVillaProjLib.service.libraryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/home")
public class libraryBookController {

    @Autowired
    private libraryBookService libraryBookServices;

    @PostMapping("/addBooks")
    public ResponseEntity<Object> addBooks(@RequestBody Map<?, ?> mp,@RequestParam("role_Id") Integer role_Id) {
        try {
            if (role_Id == 1) {
                String author_name = (String) mp.get("authorName");
                String book_name = (String) mp.get("bookName");
                String genre = (String) mp.get("genre");
                libraryBookServices.addBooks(author_name,book_name,genre);
                boolean bool = true;
                return PostResponseHandler.generatePostResponse("Successful", HttpStatus.ACCEPTED, bool);
            }
            boolean bool = false;
            return PostResponseHandler.generatePostResponse("Not Authorized", HttpStatus.BAD_REQUEST, bool);
        }catch (Exception e) {
            boolean bool = false;
            return PostResponseHandler.generatePostResponse("Not Authorized", HttpStatus.BAD_REQUEST, bool);

        }
    }
}
