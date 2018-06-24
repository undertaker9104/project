package com.authority_class.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.authority_class.model.Authority_classService;
import com.manager_account.model.Manager_accountVO;


@WebServlet("/Authority_classServlet")
public class Authority_classServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Authority_classServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

				
	}
}
