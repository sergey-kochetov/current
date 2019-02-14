package com.crud.melt.controller;

import com.crud.melt.model.Book;
import com.crud.melt.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository repository;

    @Test
    public void getAllBooks() throws Exception {
        when(repository.findAll()).thenReturn(Arrays.asList(
                new Book(1L, "name", "serg"),
                new Book(2L, "name2", "serg2"),
                new Book(3L, "name3", "serg3")
        ));
        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$"), hasSize(3))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2, 3)))
                .andExpect(jsonPath("$[*].author", containsInAnyOrder("serg", "serg2", "serg3")));
    }

    @Test
    public void getBookById() throws Exception {
        when(repository.findById(anyLong())).thenReturn(Optional.of(
                new Book(1L, "name", "serg")));

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.author", equalTo("serg")))
                .andExpect(jsonPath("$.name", equalTo("name")));
    }

    @Test
    public void getAllBooks1() {
    }

    @Test
    public void getBookById1() {
    }
}