package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileDownloadController {
	
	@GetMapping("/download")
    public void downloadExcel(HttpServletResponse response) throws IOException {
		String fileName="employee.xlsx";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        ByteArrayInputStream stream = FileExporter.generteExcelFile(createDummyData());
        IOUtils.copy(stream, response.getOutputStream());
    }

	private List<Employee> createDummyData(){
    	List<Employee> employees = new ArrayList<Employee>();
    	employees.add(new Employee("Test", "Test"));    	
    	return employees;
    }
}
