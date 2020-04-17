package controller;



import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Employee;

public class ActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String row;
	

	public ActionController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		System.out.println("ACTION = "+action);

		HBaseUtils hbaseUtils = new HBaseUtils();

		if("retrieve".equals(action)){
			ArrayList<Employee> listEmployee = hbaseUtils.getEmployee();
			request.setAttribute("dataList", listEmployee);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}else if("insert".equals(action)){
			// TODO
			String name = request.getParameter("name");
			String city = request.getParameter("city");
			String designation = request.getParameter("designation");
			int salary = Integer.parseInt(request.getParameter("salary"));			
			boolean result = hbaseUtils.insertData(name, city, designation, salary);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}			
		}else if("delete".equals(action)){
			String row = request.getParameter("row");
			System.out.println("ROW DELETED = "+row);
			
			boolean result = hbaseUtils.delete(row);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}
		}else if("to_input".equals(action)) {
			RequestDispatcher rd = request.getRequestDispatcher("/Input.jsp");
			rd.forward(request, response);
		}else if("update".equals(action)) {
			String row = request.getParameter("row");
			String name = request.getParameter("name");
			String city = request.getParameter("city");
			String designation = request.getParameter("designation");
			int salary = Integer.parseInt(request.getParameter("salary"));
			Employee emp = new Employee(name, city, designation, salary);
			emp.setvRow(row);
			request.setAttribute("dataList", emp);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
		}else if("edit".equals(action)) {
			String row = request.getParameter("row");
			String name = request.getParameter("name");
			String city = request.getParameter("city");
			String designation = request.getParameter("designation");
			int salary = Integer.parseInt(request.getParameter("salary"));			
			boolean result = hbaseUtils.update(row, name, city, designation, salary);
			if(result) {
				RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);
			}			
		}
	}

}